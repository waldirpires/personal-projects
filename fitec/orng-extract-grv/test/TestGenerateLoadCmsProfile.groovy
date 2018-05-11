import static org.junit.Assert.*;

import org.junit.Test;


class TestGenerateLoadCmsProfile {

	@Test
	void testGenerateRandomPackages()
	{
		def profile = "cms1"
		def properties = SystemUtil.loadProperties("generateload.properties")
		def selectionDistribution = properties.get("load."+profile+".distribution").split(",")
		def random = new Random(System.currentTimeMillis())
		int numPackages = Integer.MAX_VALUE
		for (int i = 0; i < numPackages; i++)
		{
			float rnd = random.nextFloat()
			int num = SystemUtil.getSelectedPackageId(rnd, numPackages, selectionDistribution)
			println  i + "\t" + num
		}
	}
}
