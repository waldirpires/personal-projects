import groovy.sql.Sql

log("============================================================================================")
log("Run DB Groovy Script")
log("")

Map params = SystemUtil.getCommandArgs(this.args)

def start = params.get("-start")
def end = params.get("-end")

//def start = "26-DECEMBER-2013 20:32:00"
//def end = "27-DECEMBER-2013 11:20:00"

def con = "jdbc:oracle:thin:@DBSERVER:1521:ttv"
log("Connecting to Oracle DB: $con")

def sql = Sql.newInstance(con, "wfs", "Wf\$1234", "oracle.jdbc.OracleDriver")

log("Connection successful")


if (start !=null){
String query = """
SELECT n.name_, ROUND(avg(case when ((l.enter_ is not null) and (l.leave_ is not null)) then (round((extract(second from (l.leave_ - l.enter_))+ extract(minute from (l.leave_ - l.enter_))*60 + extract(hour from (l.leave_ - l.enter_))*60*60 + extract(day from (l.leave_ - l.enter_))*60*60*24),0)) else 0 end) , 3) as aver FROM wfs.jbpm_log l, wfs.jbpm_node n  WHERE l.node_ = n.id_ and l.class_ = '9' AND l.enter_ BETWEEN to_date('$start', 'DD-MON-YYYY HH24:MI:SS') AND to_date('$end', 'DD-MON-YYYY HH24:MI:SS') GROUP BY n.name_  ORDER BY aver DESC
"""

println query
sql.eachRow(query) {
	println it
}

println "==================================================================================================="
	
query = """
SELECT n.name_,  COUNT(*) as total FROM wfs.jbpm_log l, wfs.jbpm_node n  WHERE l.node_ = n.id_ and l.class_ = '8' AND l.enter_ BETWEEN to_date('$start', 'DD-MON-YYYY HH24:MI:SS') AND to_date('$end', 'DD-MON-YYYY HH24:MI:SS') GROUP BY n.name_  ORDER BY n.name_ DESC
"""
println query
sql.eachRow(query) {
	println it
}
println "==================================================================================================="
}
query = """
select count(*) from wfs.pmm_title
"""
println "# Total titles(master and sites)"
println query
sql.eachRow(query) {
	println it
}
println "==================================================================================================="
query = """
select count(*) from wfs.pmm_title where titlestatusid <> 5
"""
println "#Total active titles(master and sites)"
println query
sql.eachRow(query) {
	println it
}
println "==================================================================================================="
query = """
select count(*) from wfs.mdm_metadata
"""
println "#Total active master titles"
println query
sql.eachRow(query) {
	println it
}
println "==================================================================================================="
query = """
select count(*) from wfs.mdm_metadata_site
"""
println "#Total active site titles"
println query
sql.eachRow(query) {
	println it
}
println "==================================================================================================="
query = """
select count(*) from wfs.jbpm_processinstance
"""
println "#Total Work orders"
println query
sql.eachRow(query) {
	println it
}
println "==================================================================================================="
query = """
select count(*) from wfs.jbpm_processinstance where isactive = 1
"""
println "#Total active work orders"
println query
sql.eachRow(query) {
	println it
}
println "==================================================================================================="
//sql.eachRow("select * from FOOD where type=${foo}") {
//	println it
//}

def log(msg){
	SystemUtil.log(msg)
}
