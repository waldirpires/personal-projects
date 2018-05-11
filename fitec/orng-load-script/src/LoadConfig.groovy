
class LoadConfig {

	def windowTimes
	def windowRates
	def limit
	def limitTime
    def numPackages
	def windowSize
	def selectionDistribution
	
	
	def getTimeFormatted()
	{
		return windowTimes.split(",")
	}
	
	def getRateFormatted()
	{
		return windowRates.split(",")

	}	
}
