
def rootDir = "C:/code/workspace-iap/server/design"

def query = "iap-config-user-test-jar"

def map = [:]

processFiles(rootDir, map)

for (key in map.keySet())
{
	if (key.indexOf(query) != -1 || map.get(key).toString().indexOf(query) != -1)
	println key + "("+map.get(key).size()+")"+": " + map.get(key)
}

def processFiles(dir, map) {
	def list = new File(dir).listFiles()

	
	for (f in list) {
		if (f.name.equals("pom.xml")){
			//println f
			processPomFile(f.absolutePath, map)
		} else if (f.isDirectory()){
	println " Processing " + dir
			processFiles(f.absolutePath, map);
		}
	}
}

def processPomFile(file, map){
	def f = new File(file)
	if (f.length() == 0){
		println "Empty file: " + file
		return
	}
	def pers= new XmlSlurper().parseText(f.text)

	def allNodes = pers.depthFirst().collect{ it }
	
	def groupId = pers.parent.groupId 
	def artifactId = pers.parent.artifactId
	
	def artifact = "${artifactId}"
	if (map.get("${artifact}") == null)
	{
		map.put("${artifact}", new HashSet())
	}
		
	pers.dependencies.children().each {
		map.get("${artifact}").add("${it.artifactId}");
		println "."
	}
	
	


}

def processPomFileOld(file){
	def f = new File(file)
	if (f.length() == 0){
		println "Empty file: " + file
		return
	}
	def pers= new XmlParser().parseText(new File(file).text)

	//println pers.children()
	def groupId = pers.parent.groupId.toString()
	groupId = groupId.substring(groupId.lastIndexOf("=[") + 2, groupId.lastIndexOf("]")-2)
	def artifactId = pers.parent.artifactId.toString()
	artifactId = artifactId.substring(artifactId.lastIndexOf("=[") + 2, artifactId.lastIndexOf("]")-2)
	println groupId + " - " + artifactId

	int numDependencies = pers.dependencyManagement.dependencies.dependency.size()

	if (numDependencies > 0){
		println "Found " + numDependencies + " dependencies"
		for (i in 0 .. numDependencies)
		{
			println pers.dependencyManagement.dependencies[i].dependency.groupId
		}
	}

	def dependencies = pers.dependencyManagement.dependencies
	println dependencies

}