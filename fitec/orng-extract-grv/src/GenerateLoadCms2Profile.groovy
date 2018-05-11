
import java.text.SimpleDateFormat

long time = System.currentTimeMillis()

log("=========================================================================================")
log("Starting Load Script for CMS 1/2 Profile . . .")
log("=========================================================================================")
SystemUtil.showSystemInfo()
def properties = SystemUtil.loadProperties("generateload.properties")

SystemUtil.listProperties(properties)

Map params = SystemUtil.getCommandArgs(this.args)

if (params.keySet().isEmpty() || params.get("-help") != null || params.get("-h"))
{
	showHelp()
	System.exit(0)
}

def seed = params.get("-seed")

def profile = params.get("-profile")

log("Using seed: $seed \t Profile: $profile")

def contentDir = properties.get("load."+profile+".contentdir")

def ingestDir = properties.get("load."+profile+".ingestdir")

int numPackages = Integer.parseInt properties.get("load."+ profile + ".numpackages").trim()

int numTitles

if (params.get("-numTitles") != null)
{
  numTitles = Integer.parseInt params.get("-numTitles").trim()
} else {
  numTitles =  Integer.parseInt properties.get("load."+ profile + ".numtitles").trim()
}

def selectionDistribution = properties.get("load."+profile+".distribution").split(",")

def baseTempDir = properties.get("load.basetempdir")

def random = new Random(System.currentTimeMillis())
def selectedPkgs = [:]
for (int i = 0; i < numPackages; i++)
{
	selectedPkgs.put(i, 0)
}
double perc = 0
new File("$baseTempDir/$seed").mkdir()
long timeIteration
if (params.get("-numTitles") !=null)
{
	numTitles = Integer.parseInt(params.get("-numTitles"))
	log("Generating $numTitles . . .")
}

log("")
log("Using parameters: ")
log("=====================================")
log(" SEED: \t$seed")
log(" PROFILE: \t$profile")
log(" CONTENT DIR: \t$contentDir")
log(" INGEST DIR: \t$ingestDir")
log(" NUMBER OF PACKAGES: \t$numPackages")
log(" NUMBER OF TITLES: \t$numTitles")
log(" BASE TEMPORARY FOLDER: \t$baseTempDir")
log("=====================================")
for (int i = 0; i < numTitles; i++)
{
	log("-----------------------------------------------------------------------------------------")
	timeiteration = System.currentTimeMillis()
	// generate random number
	//int num = Math.floor(random.nextFloat() * numPackages)
	float rnd = random.nextFloat()
	int num
	if (params.get("-id") !=null)
	{
		log("Selected ID: " + params.get("-id"))
		num = Integer.parseInt params.get("-id").trim()
	} else {
		num = SystemUtil.getSelectedPackageId(rnd, numPackages, selectionDistribution)
	}
	perc = SystemUtil.roundNum(i/(double)numTitles * 100)
	log("Selected package = $num - $i/$numTitles - $perc % \t rnd=$rnd")
	def fileSeed = profile + "-" + SystemUtil.getDateTimeStamp("yyMMdd-HHmmss") + "-$seed-$i-$num"
	log("File SEED: " + fileSeed)
	selectedPkgs.put(num, selectedPkgs.get(num) + 1)
	def contentFolder = contentDir + "/$num"
	log("Selected content: $contentFolder")
	SystemUtil.listFiles(contentFolder, true)
	SystemUtil.getFilesFromContentFolder(contentFolder)
	def tmpFolderName = "$baseTempDir/$seed/$num-" + Math.abs(random.nextInt())
	if ("true".equals(properties.get("load.generatepackages")))
	{
		assert new File(tmpFolderName).mkdir()
		log("Content folder: $tmpFolderName")
		File [] contentFiles = SystemUtil.getFilesFromContentFolder(contentFolder)
		log("Copying files to TMP directory")
		for (File f in contentFiles)
		{
			def dest = tmpFolderName.concat("/").concat(f.getName()).concat(".done")
			SystemUtil.copyFile(f.getAbsolutePath(), dest)
		}
		File [] files = new File(tmpFolderName).listFiles()
		for (File f in files)
		{
			if (f.getName().endsWith(".xml.done"))
			{
				SystemUtil.processXmlFile(f, fileSeed, "INFO", properties)
			}
			// Adding fileseed to name
			def name = fileSeed + "_" + f.getName()
			log("Renaming file $f to $name")
			assert f.renameTo(tmpFolderName.concat("/").concat(name))
		}
		SystemUtil.listFiles(tmpFolderName)

		if ("true".equals(properties.get("load.packageastar")))
		{
			log("Packaging title as a TAR . . .")
			
			// renaming files - removing done extension
			File [] tarFiles = SystemUtil.listFiles(tmpFolderName, false)
			for (ft in tarFiles)
			{
				SystemUtil.renameFile(ft, ft.getAbsolutePath().replaceAll(".done", ""))
			}
			
			def tarFile = SystemUtil.createTar2(tmpFolderName, fileSeed)
		}
		
		log("Moving files to ingest dir: $ingestDir")
		files = new File(tmpFolderName).listFiles()
		for (File f in files)
		{
			if ("true".equals(properties.get("load.packageastar")))
			{
				if (f.getName().endsWith(".tar"))
				{
					SystemUtil.moveFile(f, ingestDir)
				}
			} else {
			
				if ("false".equals(properties.get("load.packageastar")) ||
					f.getAbsolutePath().contains("subtitle.stl")){
					//def newDir = ingestDir.concat("/").concat(f.getName())
					log("Moving file $f to $ingestDir")
					//assert f.renameTo(new File(newDir))
					def name = f.getAbsolutePath()
					SystemUtil.executeCommand("mv $name $ingestDir/")
					SystemUtil.executeCommand("chown nobody:nobody $ingestDir/*.*")
				}
					
			}
		}
		SystemUtil.listFiles(ingestDir, fileSeed)
		SystemUtil.showSizeDir(ingestDir)
	}
	
	timeIteration = (System.currentTimeMillis() - timeIteration)/1000
	log("Iteration $i/$numTitles: $timeIteration s")
	sleep 100
	SystemUtil.removeDir(tmpFolderName)
}

SystemUtil.removeDir("$baseTempDir/$seed")
log("Selected packages["+selectedPkgs.keySet().size()+"] = $selectedPkgs")

SystemUtil.listFiles(ingestDir)
SystemUtil.showSizeDir(ingestDir)

time = (System.currentTimeMillis() - time)/1000
log("DONE: - $time s")
double rate = numTitles / (double)time
log("Rate: $rate titles/s")
log("-----------------------------------------------------------------------------------------")

def log(msg)
{
	SystemUtil.log(msg)
}

def showHelp()
{
	log("groovy GenerateLoadCms2Profile.groovy -seed=<seed> -profile=<profile> -numTitles=<numTitles> -id=<id>")
	log("")
	log("Where: ")
	log(" -seed\tdefines the seed to be used to generate the packages")
	log(" -profile\ttest profile (cms1, cms2, etc)")
	log("")
	log("Optional:")
	log(" -numTitles\tthe number of titles to generate if not specified in the properties file")
	log(" -id\tthe package ID to generate, in case if it is desired to generate a number of titles from a specific package")
}
