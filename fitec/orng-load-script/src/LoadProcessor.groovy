import org.apache.log4j.Logger;


class LoadProcessor {

	private static final Logger log = Logger.getLogger(LoadProcessor.class)
	def random = new Random()
	
	def choosePackageRandomly()
	{
		float value = random.nextFloat()
		log.info "Selected: " + value + " - " + mapDistribution + " - Total: " + sumMapDistribution
		def selectedPackageId = getSelectedPackage(value)
		assert selectedPackageId >= 0 && selectedPackageId < mapDistribution.keySet().size()
		return selectedPackageId
	}

	
}
