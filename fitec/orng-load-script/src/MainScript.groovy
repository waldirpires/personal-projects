
import java.text.SimpleDateFormat

import org.apache.log4j.*

Logger log = Logger.getLogger(MainScript.class)

def start = System.currentTimeMillis()
def propsFile = "orng-load-test.properties"

log.info "=============================================================================="
log.info "ORNG load test script - Groovy"
log.info "=============================================================================="

log.info "Loading properties file . . ."
def properties = new Properties();
new File(propsFile).withInputStream { stream ->
	properties.load(stream)
}

log.info "Loaded properties: "
for (k in properties.keySet())
{
  def value = properties.get(k)
  log.info "$k: \t $value"	
}

def config = new LoadConfig()
config.limit = Integer.parseInt properties.get("load.test.ingest.limit")
config.limitTime = !isEmpty(properties.get("load.test.ingest.time.limit"))?Integer.parseInt(properties.get("load.test.ingest.time.limit").trim()):null
config.numPackages = Integer.parseInt properties.get("load.test.packages.size").trim()
config.windowSize = Integer.parseInt properties.get("load.test.windows.size").trim()
config.windowTimes = properties.get("load.test.windows.times")
config.windowRates = properties.get("load.test.windows.rates")
config.selectionDistribution = properties.get("load.test.selection.distribution")

def hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
log.info "Hour of day: $hour"

def rate = 0
def sleepTime = 0
def selectedPkgId = -1
def perc = 0;

def selectedPkgStats = [:]
def count = 0
def i = 0
def seed = 0
def ingestTime = 0
def totalIngestTime = 0
def avgIngestTime = 0
def rateIngestTime = 0
def loopCycleTime = 0
def totalLoopCycleTime = 0
def avgLoopCycleTime = 0
def rateLoopCycleTime = 0
def percDone = 0
def totalTime = 0
def numIngestedPackages = 0
def numNotIngestedPackages = 0

log.info "Starting in 10 seconds . . ."

sleep 10

if (config.limitTime)
{
  
} else {
  for (count = 0; count < config.limit; count++)
  {
	  executeCycle(count, config, perc, log)
  }

  log.info "Done!"
  def done = System.currentTimeMillis() - start
  log.info "Ran for " + (done/1000) + " s"
  def ingestRate = count/(done/60);
  log.info "INGESTS: Total: $count | Rate: $ingestRate i/m"

}

def executeCycle(count, config, perc, log)
{
	def seed = generateSeed()
	def ingestTime = System.currentTimeMillis()
	def loopCycleTime = System.currentTimeMillis()	
	
	if (config.limit)
	{
		perc = count/config.limit * 100		
	}
	log.info "Seed: $seed - Perc. $perc"
	def hour = getCurrentHourOfDay()
	
	def sleep = getSleepTime(hour, config, log)
	
}

def isEmpty(str)
{
	return str == null || str.trim().equals("")
}

def generateSeed()
{
	return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(Calendar.getInstance().getTime())
}

def choosePackageRandomly()
{
	float value = random.nextFloat()
	log.info "Selected: " + value + " - " + mapDistribution + " - Total: " + sumMapDistribution
	def selectedPackageId = getSelectedPackage(value)
	assert selectedPackageId >= 0 && selectedPackageId < mapDistribution.keySet().size()
	return selectedPackageId
}


def getSleepTime(hour, config, log)
{
	int sleep = 0
	def windowId = -1
	
	log.info "Using hour: $hour"
	
	for (int i = 0; i < config.windowSize; i++)
	{
		if (hour >= Integer.parseInt(config.timeFormatted[i].trim()) && hour < Integer.parseInt(config.timeFormatted[i+1].trim()))
		{
			windowId = i
			log.info "Found window ID $windowId"
			sleep = (1000*60) / Integer.parseInt(config.rateFormatted[i].trim())
			log.info "Sleeping for $sleep . . ."
			return sleep
		}
	}
}

def getCurrentHourOfDay()
{
	return Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
}