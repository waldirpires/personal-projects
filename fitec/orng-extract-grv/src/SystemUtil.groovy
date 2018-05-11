
import java.io.File;
import java.text.SimpleDateFormat

class SystemUtil {

	static final int KILOBYTES = 1024

	static final int MEGABYTES = 1024576

	static final int GIGABYTES = 1073741824

	static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss"

	static final int MILLISECONDS = 1000000

	static final int NANOSECONDS = 1000000000

	def static moveFile(File f, dest) {
		long moveTime = System.nanoTime()
		log("Moving file $f to $dest")
		def name = f.getAbsolutePath()
		def length = f.length()
		if (System.getProperty("os.name").contains("Windows"))
		{
			File dir = new File(dest);
			boolean fileMoved = f.renameTo(new File(dir, f.getName()));
			name = f.getAbsolutePath()
		} else {
			executeCommand("mv $name $dest/")
			name = dest.concat("/").concat(f.getName())
			executeCommand("chown nobody:nobody $name")
		}

		checkFile(new File(name))
		assert new File(name).exists()
		moveTime = System.nanoTime() - moveTime
		log("$length Bytes \t Move time: $moveTime ns \t " + moveTime/MILLISECONDS + " ms\t" + moveTime/NANOSECONDS + " s")
		
	}

	def static log(msg)
	{
		println getDateTimeStamp() + ": $msg"
	}

	def static getDateTimeStamp()
	{
		return getDateTimeStamp(YYYY_MM_DD_HH_MM_SS)
	}

	def static getDateTimeStamp(format)
	{
		return new SimpleDateFormat(format).format(Calendar.getInstance().getTime())
	}

	def static getDateTimeFormatted(date, format)
	{
		return new SimpleDateFormat(format).format(date)
	}

	def static executeCommand(cmd, debugLevel)
	{
		def command = cmd// Create the String
		if ("DEBUG".equals(debugLevel)){
			log("Executing command: $cmd")
		}
		def proc = command.execute()                 // Call *execute* on the string
		proc.waitFor()                               // Wait for the command to finish

		// Obtain status and output
		def result = proc.in.text
		if (result !=null && result.trim().length() > 0){
			log("$cmd\n${result}")
		}
	}

	def static executeCommand(cmd)
	{
		def command = cmd// Create the String
		def proc = command.execute()                 // Call *execute* on the string
		proc.waitFor()                               // Wait for the command to finish

		// Obtain status and output
		def result = proc.in.text
		if (result !=null && result.trim().length() > 0){
			log("$cmd\n${result}")
		}
	}

	def static copyFile(src, dst)
	{
		boolean done = false
		long copytime = System.currentTimeMillis()
		def source = new File (src)
		def destination = new File (dst)
		log("Copying $src to $dst: " + getDataSizeFormatted(source.length()))
		if (source.length() > GIGABYTES)
		{
			def th = Thread.start {
				def localCopyTime = System.currentTimeMillis()
						long fileSize = 0
						double percFile = 0
						long stepTime = 0
						long bytesCopied = 0
						double rate = 0
						sleep 100
						while (!done)
						{
							stepTime = ((System.currentTimeMillis() - localCopyTime)/1000)
							destination = new File (dst)
							fileSize = destination.length()
							bytesCopied = fileSize - bytesCopied
							rate = roundNum((bytesCopied/MEGABYTES / (double)stepTime))
							percFile = fileSize / (double)source.length() * 100
							log("Still copying $src: " +
									stepTime + " s \t" +
									getDataSizeFormatted(fileSize) + "/" + getDataSizeFormatted(source.length()) + "\t" +
									roundNum(percFile) + " % \t Copied "+getDataSizeFormatted(bytesCopied)+" \t rate: $rate MB/s")
							sleep 1000
						}
				localCopyTime = (System.currentTimeMillis() - localCopyTime)/1000
						log("$src - Copy DONE: " + (destination.length()/1024000)/localCopyTime + " MB/s")
			}
		}

		source.withInputStream { is -> destination << is }
		done = true
		copytime = (System.currentTimeMillis() - copytime)/1000
		def copyRate = source.length()/MEGABYTES /(double)copytime
		log("Copying $src: " + getDataSizeFormatted(source.length()) +
			" Bytes \t time: $copytime s \t Rate: $copyRate MB/s")

	}

	def static listFiles(dir, boolean showFileList)
	{
		log("Listing files in $dir")
		File [] files = new File(dir).listFiles()
		Arrays.sort(files)
		if (showFileList){
			for (File f in files)
			{
				log(f.getAbsolutePath() + "\t" + getDataSizeFormatted(f.length()))
			}
		}
		return files
	}
		
	def static listFiles(dir)
	{
		log("Listing files in $dir")
		File [] files = new File(dir).listFiles()
		Arrays.sort(files)
		for (File f in files)
		{
			log(f.getAbsolutePath() + "\t" + getDataSizeFormatted(f.length()))
		}
		return files
	}

	def static listFiles(dir, String token)
	{
		log("Listing files in $dir")
		File [] files = new File(dir).listFiles(new FilenameFilter(){
					public boolean accept(File fileDir, String name)
					{
						return name.contains(token)
					}
				})
		Arrays.sort(files)
		for (File f in files)
		{
			log(f.getAbsolutePath() + "\t" + f.length() + " Bytes")
		}
		return files
	}

	def static showSizeDir(dir)
	{
		if (System.getProperty("os.name").contains("Windows"))
		{
		} else {
			def cmd = "du -h $dir"
			executeCommand(cmd)
		}
	}

	def static removeDir(dir)
	{
		if (System.getProperty("os.name").contains("Windows"))
		{
			assert new File(dir).deleteDir()
		} else {
			def cmd = "rm -rf $dir"
			executeCommand(cmd)
		}
	}

	def static loadProperties(fileName)
	{
		def properties = new Properties()
		new File(fileName).withInputStream { stream ->
			properties.load(stream)
		}
		return properties
	}

	def static getSelectedPackageId(double num, int numPackages, selectionDistribution)
	{
		for (int i = 0; i < numPackages; i++)
		{
			if (num <= Double.parseDouble(selectionDistribution[i].trim()))
			{
				return i
			}
		}

		return numPackages-1;
	}

	def static getFilesFromContentFolder(contentFolder)
	{
		File [] contentFiles = new File("$contentFolder").listFiles(new FilenameFilter()
			{
					public boolean accept(File dir, String name)
					{
						return name.endsWith("jpg") || name.endsWith("xml") ||
						name.endsWith("movie.ts") || name.endsWith("movie.mov") || name.endsWith("movie.mpg") ||
						name.endsWith("movie.mpeg") || name.contains("subtitle.stl")
					}
				})
		return contentFiles
	}

	def static processXmlFile(File f, String fileSeed, debugLevel, properties)
	{
		processXmlFile(f, fileSeed, properties)
		if ("DEBUG".equals(debugLevel)){
			log(f.text)
		}
	}

	def static processXmlFile(File f, String fileSeed, Properties properties)
	{
		def originalXmlFile = f.getAbsolutePath()
		log("Replacing tokens in file")

		String result = f.text.replaceAll('@DS-TOKEN@', fileSeed)

		result = result.replaceAll('@PRODUCT-TYPE@', '2424VIDEO')
		result = result.replaceAll('@PROVIDER-NAME@', 'WHATSON')
		result = result.replaceAll('@PROVIDER-ID@', 'WON')
		
		Calendar today = Calendar.getInstance()
		int days = Integer.parseInt(properties.get("load.licensingwindowdays").trim())
		result = processLicensingWindowEnd(fileSeed, result, today, days)
		
		f.write(result)
	}
	
	def static processLicensingWindowEnd(String id, String text, Calendar today, int days)
	{
		today.add(Calendar.DAY_OF_YEAR, days)
		log("$id -> Adding $days days to LICENSING WINDOW END -> " + getDateTimeFormatted(today.getTime(), YYYY_MM_DD_HH_MM_SS))
		def dateFormatted = getDateTimeFormattedForCMS(today.getTime())
		text = text.replaceAll("@LICENSING-WINDOW-END@", dateFormatted)
	}
	
	def static getDateTimeFormattedForCMS(date)
	{
		def dateFormatted = getDateTimeFormatted(date, "yyyy-MM-dd") + "T" +
			getDateTimeFormatted(date, "HH:mm:ss")
	}

	def static createTar(dir, fileId) // DEPRECATED
	{
		def tarFile
		if (System.getProperty("os.name").contains("Windows"))
		{
		  log("ERROR: unable to process TAR files in " + System.getProperty("os.name") + " OS.")
		} else {
			tarFile = "$dir/"+fileId+ ".tar"
			def cmd = "perl createTar.pl -dir=$dir -fileHash=$fileId"
			//	"tar cvfh $tarFile $dir/*"
			log(cmd)
			executeCommand(cmd)
			checkFile(new File(tarFile))
			checkTar(tarFile)
		}
		return tarFile
	}

	def static checkFile(File file)
	{
		if (System.getProperty("os.name").contains("Windows"))
		{
			def length = getDataSizeFormatted(file.length())
			log("File: $file.absolutePath \t $length")
		} else {
			def cmd = "ls -la $file"
			executeCommand(cmd)
		}
	}

	def static checkTar(file)
	{
		if (System.getProperty("os.name").contains("Windows"))
		{
			log("ERROR: unable to process TAR files in " + System.getProperty("os.name") + " OS.")
		} else {
			executeCommand("tar -tvf $file");
		}
	}

	def static getSleepTime(int hour, windowTimes, windowRates)
	{
		int windowId = -1
		int sleep = -1
		for (int i = 0; i < windowTimes.size(); i++)
		{
			if (hour >= Integer.parseInt(windowTimes[i].trim()) && hour < Integer.parseInt(windowTimes[i+1].trim())){
				windowId = i
				sleep = 1000 * 60 * 60 / Integer.parseInt(windowRates[i].trim())
				return sleep
			}
		}
	}

	def static showSystemInfo() {
		log("======================")
		log("OS information: " + System.getProperty("os.name") + "/ver. " + System.getProperty("os.version") + "/" + System.getProperty("os.arch"))
		log("")
		log("JDK information: ")
		log("JDK Home: " + System.getProperty("java.home"))
		log("JDK Version: " + System.getProperty("java.version"))
		log("JDK Classpath: " + System.getProperty("java.class.path"))
		log("JDK Vendor: " + System.getProperty("java.vendor"))

		/* Total number of processors or cores available to the JVM */
		log("Available processors (cores): " +
				Runtime.getRuntime().availableProcessors())

		/* This will return Long.MAX_VALUE if there is no preset limit */
		long maxMemory = Runtime.getRuntime().maxMemory()

		/* Total amount of free memory available to the JVM */
		log("MEMORY (Bytes): Free: " +
				Runtime.getRuntime().freeMemory() + "\t Max: " +
				(maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory) + "\t Total: " +
				Runtime.getRuntime().totalMemory())

		//		File[] roots = File.listRoots();
		//		for (File root : roots) {
		//			log("File system root: " + root.getAbsolutePath() +
		//			" | Total space (Mbytes): " + root.getTotalSpace()/1024/1024 +
		//			" | Free space (Mbytes): " + root.getFreeSpace()/1024/1024 + " | Usable space (Mbytes): " + root.getUsableSpace()/1024/1024)
		//		}
	}

	def static renameFile(File f, name)
	{
		log("Renaming file $f to $name")
		if (System.getProperty("os.name").contains("Windows"))
		{
			assert f.renameTo(new File(name))
		} else {
			def oldName = f.getAbsolutePath()
			executeCommand("mv $oldName $name")
		}
	}

	def static roundNum(Double num)
	{
		num = num * 100
		return Math.round(num)/100
	}

	def static getCommandArgs(args)
	{
		Map params = [:]
		for (int i = 0; i < args.length; i++)
		{
			def arg = args[i].split("=")
			params.put(arg[0], arg[1])
		}
		log("Params: " + params)
		return params
	}
	
	def static listProperties(properties)
	{
		log("Properties loaded: ")
		log("-----------------------------------------------------------------------------------------")
		for (s in properties.keySet()) {
			log("$s: \t " + properties.get(s))
		}
	}

	def static getDataSizeFormatted(long length)
	{
		def format = ""
		if (length > KILOBYTES && length < MEGABYTES)
		{
			format = roundNum(length/KILOBYTES) + " KB"
		} else if (length > MEGABYTES && length < GIGABYTES)
		{
			format = roundNum(length/MEGABYTES) + " MB"
		} else {
			format = roundNum(length/GIGABYTES) + " GB"
		}
		return format
	}

	def static createTar2(dir, fileId)
	{
		log("Creating TAR using ANT Builder . . .")
		def tarFile = "$dir/"+fileId+ ".tar"
		def ant = new AntBuilder()
		ant.tar(destfile: tarFile,
		basedir: "$dir",
		excludes: "**/*.stl")
		checkFile(new File(tarFile))
		checkTar(tarFile)
	}

}
