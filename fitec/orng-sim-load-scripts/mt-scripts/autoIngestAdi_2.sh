#!/bin/bash

CMS_PID=`/etc/init.d/cms status | grep cms | awk '{ print $4 } ' | sed -e 's/)//g'`

now="$(date +'%Y-%m-%d')"

dir=/var/log/load/collect/cms_top/$now
mkdir $dir

counter=0
echo
echo "=============================================================================="
echo "Starting auto-ingest script at: `date` for CMS_PID: $CMS_PID"
echo "=============================================================================="
echo
echo PARAMS
echo $1 $2 $3 $4
echo
echo "[TOP CHECK]"
#top -c -b -p $CMS_PID -d 1 -n 5 > top_$CMS_PID.txt

echo
while [ $counter -lt 1 ]
do
    let counter=$(($counter + 1))
    MOD=`expr $counter % 1`
      /opt/tandbergtv/cms/scripts/tools/adiGen_ds.pl $1 $2 $3
      date >> start.txt
      #sleep 57
      echo
      echo "[TIME STAMP: `date`]"
      echo "[TOP CHECK]"
      date >> $dir/top_$CMS_PID.txt
      top -c -b -p $CMS_PID -d 1 -n 3 >> $dir/top_$CMS_PID.txt
      echo "----------" >> $dir/top_$CMS_PID.txt
      echo
done

