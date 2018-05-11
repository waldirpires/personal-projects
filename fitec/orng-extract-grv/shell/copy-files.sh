#!/bin/bash

FILES=$1/*
FILESTAR=$1/*.tar
FILESXML=$1/*.xml.done

DEST=$2
#/remoteShortTermContent/inputLOVELL/sftp

SLEEP=$3

date
echo "ORNG - Move Ingest Files Script"
echo
echo "Parameters: "
echo
echo "Package folder: $1"
echo "Ingest folder : $2"
echo "Sleep time    : $3s"
echo
echo "Number of files to move: "
ls -1 $1 | wc -l
echo "TAR files:"
ls -l $1/*.tar |wc -l
echo "XML files:"
ls -l $1/*.xml.done |wc -l
echo "Folder size:"
du -h $1
echo
echo
COUNTER=0
for f in $FILES
do
  date
  numfiles=$(ls -1 $1 | wc -l)
  echo "Processing $f file..."
  # take action on each file. $f store current file name
  echo "Moving file $f to $DEST. . ."
  chown nobody:nobody $f
  ls -la $f
# mv $f $DEST
  sleep 1
  filename=$(basename "$f")
  ls -la $DEST/$filename
  echo "Sleeping for $3s . . ."
  du -h $1
  let COUNTER=COUNTER+1
  echo "Processed files: $COUNTER/$numfiles"
  echo
  sleep $3
done

