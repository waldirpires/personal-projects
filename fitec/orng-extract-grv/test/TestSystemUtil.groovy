import static org.junit.Assert.*;

import org.junit.Test;


class TestSystemUtil {

	@Test	
	void testProcessTokenfile()
	{
		
		File f = new File("./test.xml")
		if (!f.exists())
		{
			assert f.createNewFile()
		}
		String tokenFileName = f.getParentFile().getAbsolutePath().concat("/").concat("metadata-token.properties")
		File tokenFile = new File(tokenFileName)
		if (!tokenFile.exists())
		{
			assert tokenFile.createNewFile()
		}
		tokenFile.write("""
		@PRODUCT-TYPE@=2424VIDEO
		@PROVIDER-NAME@=WHATSON
		@PROVIDER-ID@=WON
		""")
		f.write("""
		@PRODUCT-TYPE@
		@PROVIDER-NAME@
		@PROVIDER-ID@
		""")
		SystemUtil.processTokenFile(f)
		assert f.text.contains("2424VIDEO")
		assert f.text.contains("WHATSON")
		assert f.text.contains("WON")
		assert !f.text.contains("@")
	}
	
	@Test
	void testGetCommandArgs()
	{
		String [] args = new String[3]
		args[0] = "-seed=lt-438-MT14c"
		args[1] = "-profile=cms1"
		args[2] = "-list"
		
		Map params = SystemUtil.getCommandArgs(args)
		println params
		
	}
}
