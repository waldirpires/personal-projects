import java.text.SimpleDateFormat

long time = System.currentTimeMillis()

log("=========================================================================================")
log("Starting ORNG Metadata Processor for ADI/CMS XML . . .")
log("=========================================================================================")
SystemUtil.showSystemInfo()

Map params = SystemUtil.getCommandArgs(this.args)

def xmlSource = params.get("-src")
def xmlDest = params.get("-dst")
//def xmlSource = "C:\\opt\\workspaceOrange\\orng-extract-grv\\test.xml"
//def xmlSource = "C:\\opt\\workspaceOrange\\orng-extract-grv\\test-cms.xml"
//def xmlDest = "C:\\opt\\workspaceOrange\\orng-extract-grv\\test2.xml"

if (xmlSource)
{

	def xml = new XmlParser().parse(xmlSource)

	if (xml.EditorialInformation)
	{
		log "CMS XML detected"
		
		xml.EditorialInformation.@opusCode="@DS-TOKEN@_" + xml.EditorialInformation.@opusCode[0].toString()
		
		log xml.EditorialInformation.@opusCode
		
		// title
		updateNodeValueWithToken(xml.EditorialInformation."CMS_EI:ContentDescription"."CMS_EI:Title"[0])
		//xml.EditorialInformation."CMS_EI:ContentDescription"."CMS_EI:Title"[0].value[0] = "@DS-TOKEN@_" + xml.EditorialInformation."CMS_EI:ContentDescription"."CMS_EI:Title"[0].value[0]
		
		log xml.EditorialInformation."CMS_EI:ContentDescription"."CMS_EI:Title"[0].value[0]
		
		// VOD title
		xml.EditorialInformation[0]."CMS_EI:ContentDescription"."CMS_EI:VOTitle"[0].value[0] = "@DS-TOKEN@_" + xml.EditorialInformation[0]."CMS_EI:ContentDescription"."CMS_EI:VOTitle"[0].value[0]
		// Short Summary
		xml.EditorialInformation[0]."CMS_EI:ContentDescription"."CMS_EI:Summary"[0]."CMS_EI:ShortSummary"[0].value[0] = xml.EditorialInformation[0]."CMS_EI:ContentDescription"."CMS_EI:Summary"[0]."CMS_EI:ShortSummary"[0].value[0]
		// long summary
		xml.EditorialInformation[0]."CMS_EI:ContentDescription"."CMS_EI:Summary"[0]."CMS_EI:LongSummary"[0].value[0] = "@DS-TOKEN@_"+ xml.EditorialInformation[0]."CMS_EI:ContentDescription"."CMS_EI:Summary"[0]."CMS_EI:LongSummary"[0].value[0]
		
		xml.ExploitationInformation.Instructions."CMS_VOD:ExploitationAttributes".'CMS_VOD:DateCatalogEnd'[0].value[0] = "@LICENSING-WINDOW-END@Z"
		
		log xml.ExploitationInformation.Instructions."CMS_VOD:ExploitationAttributes".'CMS_VOD:DateCatalogEnd'[0].value[0]
		
		xml.ExploitationInformation.Instructions."CMS_VOD:ExclusiveMetadata"."CMS_VOD:Description"."CMS_VOD:Title"[0].value[0] = "@DS-TOKEN@_" + 
			xml.ExploitationInformation.Instructions."CMS_VOD:ExclusiveMetadata"."CMS_VOD:Description"."CMS_VOD:Title"[0].value[0] 
		xml.ExploitationInformation.Instructions."CMS_VOD:ExclusiveMetadata"."CMS_VOD:Description"."CMS_VOD:VOTitle"[0].value[0] = "@DS-TOKEN@_" + 
			xml.ExploitationInformation.Instructions."CMS_VOD:ExclusiveMetadata"."CMS_VOD:Description"."CMS_VOD:VOTitle"[0].value[0] 
		 
		
	} else if (xml.Metadata)
	{
		log "ADI XML detected"

		xml.Metadata.AMS.@Provider="@PROVIDER-NAME@"
		xml.Metadata.AMS.@Product="@PRODUCT-TYPE@"
		xml.Metadata.AMS.@Asset_Name="@DS-TOKEN@_" + xml.Metadata.AMS.@Asset_Name.toString()
		xml.Metadata.AMS.@Description="@DS-TOKEN@_@PRODUCT-TYPE@ " + xml.Metadata.AMS.@Description
		xml.Metadata.AMS.@Provider_ID="@PROVIDER-ID@"
		xml.Metadata.AMS.@Asset_ID="@DS-TOKEN@_" + xml.Metadata.AMS.@Asset_ID

		def productCode = xml.Metadata.App_Data.findAll{ it.@Name == 'Orange_ProductCode' }

		productCode.each { g ->
			println g.@Value
			g.@Value = "@DS-TOKEN@_" + g.@Value
			println g.@Value
		}

		def allowedDevice = new Node(xml.Metadata[0], "App_Data", [App:'MOD', Name:'Orange_AllowedDevice', Value:'STB,,,'])

		def allowedDevices = xml.Metadata.App_Data.findAll{ it.@Name == 'Orange_AllowedDevice' }

		assert allowedDevices.size() == 1

		addTokenToAmsValues(xml.Asset.Metadata.AMS, 'title')

		addTokenToTag(xml.Asset.Metadata.App_Data, 'Title')

		addTokenToTag(xml.Asset.Metadata.App_Data, 'Title_Brief')

		addTokenToTag(xml.Asset.Metadata.App_Data, 'Summary_Long')

		addTokenToTag(xml.Asset.Metadata.App_Data, 'Summary_Medium')

		addTokenToTag(xml.Asset.Metadata.App_Data, 'Summary_Short')

		replaceValue(xml.Asset.Metadata.App_Data, "Licensing_Window_End", "@LICENSING-WINDOW-END@Z")

		addTokenToAmsValues(xml.Asset.Asset.Metadata.AMS, 'movie')

		def movieFile = xml.Asset.Asset[0].Content.@Value[0].toString()

		xml.Asset.Asset[0].Content.@Value="@DS-TOKEN@_movie" + movieFile.substring(movieFile.lastIndexOf("."))

		addTokenToAmsValues(xml.Asset.Asset.Metadata.AMS, 'poster')

		xml.Asset.Asset[1].Content.@Value="@DS-TOKEN@_boxcover.jpg"

		xml.Asset.Asset[2].Content.@Value="@DS-TOKEN@_poster.jpg"
	}

	new XmlNodePrinter(new PrintWriter(new FileWriter(xmlDest))).print(xml)

	//	new XmlNodePrinter(new PrintWriter(writer)).print(xml)
}

def updateNodeValueWithToken(path)
{
	log path
	String value = path.value[0]
	path.value[0] = "@DS-TOKEN@_" + value
	log path.toString() + ": " + path.value[0].toString() 
}

def addTokenToAmsValues(path, assetClass)
{
	def assetMetadata = path.findAll{ it.@Asset_Class == assetClass }

	assetMetadata.each { g ->
		g.@Provider="@PROVIDER-NAME@"
		g.@Product="@PRODUCT-TYPE@"
		g.@Asset_Name="@DS-TOKEN@_" + g.@Asset_Name.toString()
		g.@Description="@DS-TOKEN@_@PRODUCT-TYPE@ " + g.@Description
		g.@Provider_ID="@PROVIDER-ID@"
		g.@Asset_ID="@DS-TOKEN@_" + g.@Asset_ID
	}

}

def replaceValue(path, name, value)
{
	def tag = path.findAll{ it.@Name == name }
	tag.each { i ->
		println i.@Value
		i.@Value = value
		println i.@Value
	}
}

def addTokenToTag(path, name)
{
	def tag = path.findAll{ it.@Name == name }
	tag.each { i ->
		println i.@Value
		i.@Value = "@DS-TOKEN@_" + i.@Value
		println i.@Value
	}
}

def log(msg)
{
	SystemUtil.log(msg)
}
