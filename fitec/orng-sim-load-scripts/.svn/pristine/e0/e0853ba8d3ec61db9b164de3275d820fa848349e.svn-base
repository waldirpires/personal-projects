#!/bin/bash

CMS_PID=`/etc/init.d/cms status | grep cms | awk '{ print $4 } ' | sed -e 's/)//g'`

counter=0
echo
echo "=============================================================================="
echo "Starting auto-ingest script at: `date` for CMS_PID: $CMS_PID"
echo "=============================================================================="
echo
echo
echo "[TOP CHECK]"
#top -c -b -p $CMS_PID -d 1 -n 5 > top_$CMS_PID.txt

echo
while [ $counter -lt 1 ]
do
    let counter=$(($counter + 1))
    MOD=`expr $counter % 1`
      /opt/tandbergtv/cms/scripts/tools/adiGen.pl $1 $2 $3
      echo /opt/tandbergtv/cms/scripts/tools/adiGen.pl -seed=$1 -pkgId=$2 -content=$3
      date >> start.txt
      #sleep 57
      echo
      echo "[TIME STAMP: `date`]"
      echo "[TOP CHECK]"
      top -c -b -p $CMS_PID -d 1 -n 3 >> top_$CMS_PID.txt

      echo
done

