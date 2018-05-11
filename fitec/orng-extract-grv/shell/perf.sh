#!/bin/bash

echo $1

now="$(date +'%Y-%m-%d')"
dir="/var/log/load/$1"
mkdir "$dir"

#filename="$dir/watch_perf_$now.txt"

counter=0
#echo "" > $filename

 date >> "$dir/info.log"
 echo "LSCPU:" >> "$dir/info.log"
 lscpu >> "$dir/info.log"
 echo "cpuinfo:" >> "$dir/info.log"
 cat /proc/cpuinfo >> "$dir/info.log"


while [ true ]
do
        let counter=$(($counter + 1))
        date >> "$dir/sar.log"
        hostname >> "$dir/sar.log"
        echo "SAR:" >> "$dir/sar.log"
        sar 1 10 >> "$dir/sar.log"
	echo "---------------[$counter]" >> "$dir/sar.log"

        date >> "$dir/vmstat.log"
        hostname >> "$dir/vmstat.log"   
        echo "VMSTAT:" >> "$dir/vmstat.log"
        vmstat >> "$dir/vmstat.log"
        echo "---------------[$counter]" >> "$dir/vmstat.log"

        date >> "$dir/net.log"
        hostname >> "$dir/net.log"
        echo "NETWORK:" >> "$dir/net.log"
        sar -n DEV 1 10 >> "$dir/net.log"
        echo "---------------[$counter]" >> "$dir/net.log"

        date >> "$dir/top.log"
        hostname >> "$dir/top.log"
        echo "TOP:" >> "$dir/top.log"
        top -b -n1 -c >> "$dir/top.log"
        echo "---------------[$counter]" >> "$dir/top.log"
        ps -eo pcpu,pid,user,args | sort -k 1 -r | head -10 >> "$dir/top.log"
        echo "---------------[$counter]" >> "$dir/top.log"

        echo "jmx:" >> "$dir/sim-jmx.log"
        date >> "$dir/sim-jmx.log"
        curl --user tomcat:s3cret "http://10.104.65.159:8081/manager/jmxproxy/" >> "$dir/sim-jmx.log"
        echo "---------------[$counter]" >> "$dir/sim-jmx.log"
		echo "jmx:" >> "$dir/sim2-jmx.log"
        date >> "$dir/sim2-jmx.log"
        curl --user tomcat:s3cret "http://10.104.65.159:8082/manager/jmxproxy/" >> "$dir/sim2-jmx.log"
        echo "---------------[$counter]" >> "$dir/sim2-jmx.log"

        date >> "$dir/mpstat.log"
        hostname >> "$dir/mpstat.log"
        echo "msstat:" >> "$dir/mpstat.log"
        mpstat -P ALL >> "$dir/mpstat.log"
	echo "MPSTAT usage: " >> "$dir/mpstat.log"
        mpstat | awk '$12 ~ /[0-9.]+/ { print 100 - $12"%" }' >> "$dir/mpstat.log"	
        echo "---------------[$counter]" >> "$dir/mpstat.log"

        date >> "$dir/iostat.log"
        hostname >> "$dir/iostat.log"
        echo "iostat:" >> "$dir/iostat.log"
        iostat -xtcdnNm 5 3 >> "$dir/iostat.log"

        echo "---------------[$counter]" >> "$dir/iostat.log"
        date >> "$dir/meminfo.log"
        hostname >> "$dir/meminfo.log"
        echo "meminfo:" >> "$dir/meminfo.log"
        cat /proc/meminfo >> "$dir/meminfo.log"
        echo " " >> "$dir/meminfo.log"
        free >> "$dir/meminfo.log"
        echo "---------------[$counter]" >> "$dir/meminfo.log"

        date  >> "$dir/cms-queue.log"
        hostname >> "$dir/cms-queue.log"
        echo "Content Management:" >> "$dir/cms-queue.log"
        tail -n 10000 /opt/tandbergtv/cms/log/jboss.log | grep "Content Management" | grep items | awk ' { print $20, $21, $22} ' | tail -n 10 >>  "$dir/cms-queue.log"
        echo "Heavy Content Management:" >>  "$dir/cms-queue.log"
        tail -n 10000 /opt/tandbergtv/cms/log/jboss.log | grep "Heavy Operations" | grep "released resource" | awk '{print $18, $19}' | tail -n 10  >> "$dir/cms-queue.log"
        echo "---------------[$counter]" >>  "$dir/cms-queue.log"

        date >> "$dir/cms-queue2.log"
        #echo "Content Management:" >> "$dir/cms-queue2.log"
        #tail -n 10000 /opt/tandbergtv/cms/log/jboss.log | grep "Content Management" | grep items >> "$dir/cms-queue2.log"
        #echo "Heavy Operations:" >> "$dir/cms-queue2.log"
        #tail -n 10000 /opt/tandbergtv/cms/log/jboss.log | grep "Heavy Operations" | grep "released resource"  >> "$dir/cms-queue2.log"
        #echo "File Subsystem:" >> "$dir/cms-queue2.log"
        #tail -n 10000 /opt/tandbergtv/cms/log/jboss.log | grep "File Subsystem" | grep "released resource"  >> "$dir/cms-queue2.log" 
        #echo "scac-adapter-motcdn:" >> "$dir/cms-queue2.log"
        #tail -n 10000 /opt/tandbergtv/cms/log/jboss.log | grep "scac-adapter-motcdn" | grep "released resource" >> "$dir/cms-queue2.log"
        #echo "scac-adapter-rightv:" >> "$dir/cms-queue2.log"
        #tail -n 10000 /opt/tandbergtv/cms/log/jboss.log | grep "scac-adapter-rightv" | grep "released resource" >> "$dir/cms-queue2.log"
        #echo "scac-adapter-viaccess:" >> "$dir/cms-queue2.log"
        #tail -n 10000 /opt/tandbergtv/cms/log/jboss.log | grep "scac-adapter-viaccess" | grep "released resource" >> "$dir/cms-queue2.log"
        echo "Items:" >> "$dir/cms-queue2.log"
        tail -n 10000 /opt/tandbergtv/cms/log/jboss.log | grep "items." | awk '{print $1, $2, $3, $4, $11, $12, $13, $14, $15, $16, $17, $18, $19, $20, $21}' >> "$dir/cms-queue2.log"
        echo "Users:" >> "$dir/cms-queue2.log" 
        tail -n 10000 /opt/tandbergtv/cms/log/jboss.log | grep "users." | awk '{print $1, $2, $3, $4, $11, $12, $13, $14, $15, $16, $17, $18, $19, $20, $21}' >> "$dir/cms-queue2.log"  
       echo "---------------[$counter]" >>  "$dir/cms-queue2.log"

        date >> "$dir/watchfolder.log"
        hostname >> "$dir/watchfolder.log"
        echo "WacthFolder:" >> "$dir/watchfolder.log"
        ls -lahs /remoteShortTermContent/inputLOVELL/sftp >> "$dir/watchfolder.log"
        echo "OUT folder:" >> "$dir/watchfolder.log"
        ls -lahs /remoteShortTermContent/dataset/out >> "$dir/watchfolder.log"
        echo "---------------[$counter]" >> "$dir/watchfolder.log" 
        date >> "$dir/df.log"
        echo "Disk info: " >> "$dir/df.log"
        df -kh >> "$dir/df.log"
        echo "ps cmmmand:" >> "$dir/ps.log"
        echo "---------------[$counter]" >> "$dir/df.log"
        date >> "$dir/ps.log"
        ps u |grep cp | wc -l >> "$dir/ps.log"
        ps u |grep adiGen_ds.pl | wc -l >> "$dir/ps.log"

        echo "---------------[$counter]" >> "$dir/ps.log"
        date >> "$dir/ps.log"
        #echo "sleeping... " $counter
        sleep 120
done

