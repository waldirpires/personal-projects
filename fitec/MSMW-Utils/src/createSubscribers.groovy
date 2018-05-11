import groovy.sql.Sql
// groovy -cp ojdbc7-12.1.0.1.jar createSubscribers.groovy 100
// PATH="/usr/java/jdk1.7.0_51/bin:/usr/local/jboss/bin:/usr/local/apache-ant-1.8.4/bin:/usr/local/jboss/bin:/usr/java/jdk1.7.0_51/bin:/usr/local/jboss/bin:/usr/local/apache-ant-1.8.4/bin:/usr/lib64/qt-3.3/bin:/usr/local/jboss/bin:/usr/local/sbin:/usr/local/bin:/sbin:/bin:/usr/sbin:/usr/bin:/root/bin:/opt/groovy/groovy-2.2.1/bin"

// jdbc:oracle:thin:@localhost:1521:databasename

def params = new Properties()

def args = ["-key=ST367R823H8P221L", "-numRows=1", "-pw=admin"]

for (s in args)
{
	if (s.contains("-key="))
	{
		params.put("key", s.substring(5))
	} else 
	if (s.contains("-numRows="))
	{
		params.put("numRows", s.substring(9))
	} else 
	if (s.contains("-pw="))
	{
		params.put("pw", s.substring(4))
	}
}

println params

System.exit(0)

def propsFile = "connect.properties"
def props = new java.util.Properties();

println "Loading properties file: " + propsFile

new File(propsFile).withInputStream { 
  stream -> props.load(stream) 
}

println "Properties loaded: " + props.keySet().size()
for ( e in props ) {
    println e.key + "=" + e.value
}

println "Connecting to Oracle DB . . ."

def sql = Sql.newInstance(props.connectionUrl, props.connectionUser,
                      props.connectionPw, props.connectionDriver)

int numRows = Integer.parseInt args[0];
					  					  
def date = new Date()
def sqlTimestamp = date.toTimestamp()
					  
def iapAuthentication = sql.dataSet("IAP_AUTHENTICATION")

println "Clearing table data . . ."
sql.execute("delete from IAP_AUTHENTICATION")
sql.execute("delete from IAP_MASS_ENCRYPTION_UPDATE")

println "Creating new subscribers . . ."
// DE0D97B163AC1E926557E90B80193273

// encrypt password


long time = System.currentTimeMillis()
def pw = new String('DE0D97B163AC1E926557E90B80193273'.getBytes(), "UTF-8");
for (int i in 1..numRows){
  iapAuthentication.add( OWNER_ID:i, PASSWORD:pw, UPDATE_TIME:sqlTimestamp)
  if (i % 1000 == 0)
    println i/(float)numRows
}
time = System.currentTimeMillis() - time
println "Querying the table . . ."
sql.eachRow("select * from IAP_AUTHENTICATION") {
    println "${it.owner_id} \t ${it.password} \t ${it.update_time}"
}

sql.close()
println "Time elapsed: " + time + " ms"

println "DONE!"



