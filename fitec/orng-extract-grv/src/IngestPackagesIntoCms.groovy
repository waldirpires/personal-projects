long time = System.currentTimeMillis()

log("=========================================================================================")
log("Starting Ingest Script for CMS 1/2 Profile . . .")
log("=========================================================================================")
SystemUtil.showSystemInfo()
def properties = SystemUtil.loadProperties("generateload.properties")

SystemUtil.listProperties(properties)
Map params = SystemUtil.getCommandArgs(this.args)

if (params.keySet().isEmpty())
{
		log("ERROR: missing parameters - -seed and/or -profile")
		System.exit(1)
}

def seed = params.get("-seed")

def profile = params.get("-profile")

log("Using seed: seed \t Profile: $profile")

def ingestDir = properties.get("load."+profile+".ingestdir")

int windowSize = Integer.parseInt properties.get("load.windowsize").trim()

def windowTimes = properties.get("load.windowtimes").split(",")

def windowRates = properties.get("load."+profile+".windowrates").split(",")

log("-----------------------------------------------------------------------------------------")
log("INGESTING into CMS!")
def cmsWf = properties.get("load.cmswf")

int hour = 0
int sleepTime = 0
File [] files = SystemUtil.listFiles(ingestDir, seed)
log("INGEST: found files: " + files.length)
int id = 0
int processedFiles = 0
int processedTitles = 0
double perc = 0
while (files.length > 0)
{
	def f = files[0]
	hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
	sleepTime = SystemUtil.getSleepTime(hour, windowTimes, windowRates)
	log("Current hour: $hour \t Sleep time: $sleepTime ms")
	
	if (f.getName().endsWith(".tar"))
	{
		log("Processing TAR file ${f.absolutePath}")
		SystemUtil.moveFile(f, cmsWf)
		processedFiles++
		processedTitles++
	} else {
		log("Processing Ingest files")
		def titleId = seed + "-" + id + "-"
		log("Ingesting title $titleId")
		File [] titleFiles = SystemUtil.listFiles(ingestDir, titleId)
		log("title: $titleId \t Found files: $titleFiles.length")
		if (titleFiles.length == 0)
		{
			id++
			files = SystemUtil.listFiles(ingestDir, seed)
			log("INGEST: found files: " + files.length)
			continue
		}
		for (f2 in titleFiles)
		{
			def newName = f2.getAbsolutePath()
			if (f2.getAbsolutePath().endsWith(".done"))
			{
				newName = newName.replace(".done", "")
				log("Reverting file $f2 back to the name $newName")
				SystemUtil.renameFile(f2, newName)
			}
			SystemUtil.moveFile(new File(newName), cmsWf)
			processedFiles++
		}
		processedTitles++
	}
	log("Sleeping for " + (sleepTime/1000) + "s . . .")
	perc = SystemUtil.roundNum(id/(double)files.length * 100)
	for (int j = 0; j < sleepTime/1000; j++){
		log("${id}/${files.length} $perc % \t Sleeping . . . " + j)
		Thread.sleep(1000)
	}
	id++
	files = SystemUtil.listFiles(ingestDir, seed)
	if (files.length > 0){
		perc = SystemUtil.roundNum(processedFiles/files.length * 100)
	} else {
		perc = 100
	}
	log("Processed files: $processedFiles/${files.length} \t $perc %")
	log("INGEST: found files: " + files.length)
}
time = (System.currentTimeMillis() - time)/1000
log("DONE! $time s")
processedTitles = processedTitles/ ((double)time * 60)
log("Processed titles: $processedTitles")
log("Titles per minute: $processedTitles / min")
log("-----------------------------------------------------------------------------------------")

def log(msg)
{
	SystemUtil.log(msg)
}
