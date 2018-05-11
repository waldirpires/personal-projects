import javax.management.ObjectName
import javax.management.remote.JMXConnectorFactory as JmxFactory
import javax.management.remote.JMXServiceURL as JmxUrl
import groovy.swing.SwingBuilder
import javax.swing.WindowConstants as WC

def serverUrl = 'service:jmx:rmi:///jndi/rmi://10.121.54.92:9998/jmxrmi'
def server = JmxFactory.connect(new JmxUrl(serverUrl)).MBeanServerConnection
def serverInfo = new GroovyMBean(server, 'Catalina:type=Server').serverInfo
println "Connected to: $serverInfo"

def query = new ObjectName('Catalina:*')
String[] allNames = server.queryNames(query, null)
def modules = allNames.findAll{ name ->
	name.contains('j2eeType=WebModule')
}.collect{ new GroovyMBean(server, it) }


