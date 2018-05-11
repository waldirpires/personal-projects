#!/bin/bash
#
#
echo "=========================================================================="
date
echo "Copy Ingest Script - ORNG"
echo 
DEST=/remoteShortTermContent/inputLOVELL/sftp
SRC=/opt/watchFolder01

echo "Source folder: $SRC"

du -h $SRC
echo
echo "Destination folder: $DEST" 
#FILES=/opt/watchFolder01/*
FILES=$SRC/*.xml.done
#FILES=$1/*

#INGEST_DIR=$2

#SLEEP = $3
date
#echo "ORNG - Move Ingest Files Script"
#echo
#echo "Parameters: "
#echo
#echo "Package folder: $1"
#echo
#echo "Ingest folder: $2"
#echo
#echo "Sleep: $3s"

echo "Number of files to move: " 
ls -1a $SRC/*  | wc -l
sleep 3
# max number of items to process per loop
LIMIT=5
#max number of outer loop
MAX_COUNTER=4
OUTER_COUNTER=0

while [  $OUTER_COUNTER -lt $MAX_COUNTER ]; do
             echo The counter is $OUTER_COUNTER
echo "------------------------------------------------"
echo "Processing XML files . . ."
COUNTER=0
for f in $FILES
do
  date 
  echo "Processing $f file . . ."
  echo "Moving"
  mv $f $DEST
  ls -la $DEST
  rename .xml.done .xml $DEST/*
  if ["$COUNTER" -eq "$LIMIT"]; then
    break
  fi
  echo "Sleeping for 2s . . ."
  #sleep 2
  let COUNTER=COUNTER+1
done

echo "Processed $COUNTER XML files . . ."

du -h $SRC
echo
FILES=$SRC/*.jpg.done
echo "Processing images . . ."
COUNTER=0
for f in $FILES
do
  date
  echo "Processing $f file . . ."
  echo "Moving"
  mv $f $DEST
  ls -la $DEST
  rename .jpg.done .jpg $DEST/*
  if ["$COUNTER" -eq "$LIMIT]"; then
    break
  fi

  echo "Sleeping for 2s . . ."
  #sleep 2
  let COUNTER=COUNTER+1
done

echo "Processed $COUNTER JPG files . . ."

du -h $SRC
echo
FILES=$SRC/*.done
echo "Processing remaining files . . ."
COUNTER=0
for f in $FILES
do
  date
  echo "Processing $f file . . ."
  echo "Moving"
  mv $f $DEST
  ls -la $DEST
  rename .ts.done .ts $DEST/*
  rename .mov.done .mov $DEST/*
  rename .mpg.done .mpg $DEST/*
  rename .stl.done .stl $DEST/*

  if ["$COUNTER" -eq "$LIMIT"]; then
    break
  fi

  echo "Sleeping for 10s . . ."
  sleep 10
  let COUNTER=COUNTER+1
done

echo "Processed $COUNTER files . . ."

du -h $SRC
echo

  let OUTER_COUNTER=OUTER_COUNTER+1
done

for i in `seq 1 10`;
        do
               date
               echo $i
               ls -la $DEST
               sleep 5
        done

date
