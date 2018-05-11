import groovy.sql.Sql
//  groovy -cp ojdbc7-12.1.0.1.jar clearTable.groovy IAP_MASS_ENCRYPTION_UPDATE

//  groovy -cp ojdbc7-12.1.0.1.jar clearTable.groovy IAP_MASS_ENCRYPTION_UPDATE

// PATH="/usr/java/jdk1.7.0_51/bin:/usr/local/jboss/bin:/usr/local/apache-ant-1.8.4/bin:/usr/local/jboss/bin:/usr/java/jdk1.7.0_51/bin:/usr/local/jboss/bin:/usr/local/apache-ant-1.8.4/bin:/usr/lib64/qt-3.3/bin:/usr/local/jboss/bin:/usr/local/sbin:/usr/local/bin:/sbin:/bin:/usr/sbin:/usr/bin:/root/bin:/opt/groovy/groovy-2.2.1/bin"

def propsFile = "connect.properties"
def props = new java.util.Properties();

println "Loading properties file: " + propsFile

new File(propsFile).withInputStream { 
  stream -> props.load(stream) 
}

println "Properties loaded: " + props.keySet().size()
println "Connecting to Oracle DB . . ."

def sql = Sql.newInstance(props.connectionUrl, props.connectionUser,
                      props.connectionPw, props.connectionDriver)

def tableName = args[0];

println "Querying the table . . ."
sql.eachRow("select count(*) as count from " + tableName) {
    println "Count: ${it.count}"
}

println ""
					  					  
println "Clearing table data in ${tableName}. . ."
sql.execute("delete from " + tableName)

println ""

println "Querying the table . . ."
sql.eachRow("select count(*) as count from " + tableName) {
    println "Count: ${it.count}"
}

println "DONE!"



