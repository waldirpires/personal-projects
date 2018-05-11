package com.ericsson.cms.orng.test.load

import org.apache.log4j.Logger;

import com.ericsson.cms.orng.test.util.CalendarUtil;


class PackageIngestProcessor {

	private static final Logger log = Logger.getLogger(PackageIngestProcessor.class)
	public static final String CMS_WATCHFOLDER_PATH = "cms.watchfolder.path"	
	
	def tarFileName
	def config
	
	def startIngestProcess(int index, int selectedPackage)
	{
		def copy = { File src,File dest->
			
			   def input = src.newDataInputStream()
			   def output = dest.newDataOutputStream()
			
			   output << input
			
			   input.close()
			   output.close()
		}
		// create ingest files
		File adiFile = new File("packages/"+selectedPackage+"/ADI-base.xml")
		File adiout = new File("ADI.xml")
		
		def root = new XmlParser().parseText(adiFile.getText())
		
		def indexFormatted = String.format("%03d", index)
		
		adiout.withWriter { out ->
			adiFile.eachLine {line->
				line = line.replace("###", indexFormatted)						
				out.writeLine(line)
			}
		}
		
		
		// copy package to WF
		File dest = copyPackageFiles(selectedPackage, indexFormatted, copy)

		if (executeTarCommand(indexFormatted))
		{
			// delete files
			deleteTemporaryFiles(indexFormatted)
		}
		
		log.info "package successfully created at: ${tarFileName}"
		// publish tar file in CMS WF
		def wfPath = config.properties.getProperty(CMS_WATCHFOLDER_PATH)
		log.info "publishing package in CMS WF: " + wfPath
		dest = new File(wfPath + "/${tarFileName}")
		copy(new File(tarFileName), dest)
		assert dest.exists()
		log.info "package published at: " + dest.getAbsolutePath()
		assert new File(tarFileName).delete()
		log.info "DONE!"
		return true

	}

	private File copyPackageFiles(int selectedPackage, String indexFormatted, Closure copy) {
		File src = new File("packages/"+selectedPackage+"/movie-cerlopes-###.ts")
		File dest = new File("movies-cerlopes-"+indexFormatted+".ts")
		copy(src, dest)
		assert dest.exists()

		src = new File("packages/"+selectedPackage+"/POSTER_CERLOPES_TEST_000###_COVER1.jpg")
		dest = new File("POSTER_CERLOPES_TEST_000"+indexFormatted+"_COVER1.jpg")
		copy(src, dest)
		assert dest.exists()

		src = new File("packages/"+selectedPackage+"/POSTER_CERLOPES_TEST_000###_COVER2.jpg")
		dest = new File("POSTER_CERLOPES_TEST_000"+indexFormatted+"_COVER2.jpg")
		copy(src, dest)
		assert dest.exists()

		src = new File("packages/"+selectedPackage+"/poster-cerlopes-###.jpg")
		dest = new File("poster-cerlopes-"+indexFormatted+".jpg")
		copy(src, dest)
		assert dest.exists()

		src = new File("packages/"+selectedPackage+"/preview-cerlopes-###.sent")
		dest = new File("preview-cerlopes-"+indexFormatted+".sent")
		copy(src, dest)
		assert dest.exists()

		src = new File("packages/"+selectedPackage+"/preview-cerlopes-###.ts")
		dest = new File("preview-cerlopes-"+indexFormatted+".ts")
		copy(src, dest)
		assert dest.exists()

		src = new File("packages/"+selectedPackage+"/preview-cerlopes-###.ts.sent")
		dest = new File("preview-cerlopes-"+indexFormatted+".ts.sent")
		copy(src, dest)
		assert dest.exists()
		return dest
	}

	private deleteTemporaryFiles(String indexFormatted) {
		assert new File("ADI.xml").delete()
		assert new File("movies-cerlopes-"+indexFormatted+".ts").delete()
		assert new File("POSTER_CERLOPES_TEST_000"+indexFormatted+"_COVER1.jpg").delete()
		assert new File("POSTER_CERLOPES_TEST_000"+indexFormatted+"_COVER2.jpg").delete()
		assert new File("poster-cerlopes-"+indexFormatted+".jpg").delete()
		assert new File("preview-cerlopes-"+indexFormatted+".sent").delete()
		assert new File("preview-cerlopes-"+indexFormatted+".ts").delete()
		assert new File("preview-cerlopes-"+indexFormatted+".ts.sent").delete()
	}
	
	def executeTarCommand(index)
	{
		tarFileName = "package${index}-" + CalendarUtil.format(Calendar.getInstance(), "yyyy-MM-dd-HH-mm-ss") +".tar"
		 
		//C:\Program Files (x86)\GnuWin32\bin\tar.exe
		def command = "C:/dev/GnuWin32/bin/tar cvf ${tarFileName} ADI.xml movies-cerlopes-${index}.ts POSTER_CERLOPES_TEST_000${index}_COVER1.jpg POSTER_CERLOPES_TEST_000${index}_COVER2.jpg "+
		"poster-cerlopes-${index}.jpg preview-cerlopes-${index}.sent preview-cerlopes-${index}.ts preview-cerlopes-${index}.ts.sent"
		//tar cvf archive_name.tar dirname/
		def proc = command.execute()                 // Call *execute* on the string
		proc.waitFor()                               // Wait for the command to finish
		
		// Obtain status and output
		println "return code: ${ proc.exitValue()}"
		println "stderr: ${proc.err.text}"
		println "stdout: ${proc.in.text}"

		return proc.exitValue() == 0
	}
}
