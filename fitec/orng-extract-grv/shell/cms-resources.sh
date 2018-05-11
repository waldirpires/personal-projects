 FILE="cms-resources.log"

 date >> "$1/$FILE"
 hostname >> "$1/$FILE"
 echo "CMS resources:" >> "$1/$FILE"
 
 tail -n 10000 /opt/tandbergtv/cms/log/jboss.log | grep "users" | awk ' { print $1, $2, $3, $4, $9, $10, $11, $12, $13, $15, $16, $17, $18, $19, $20, $21} '  >>  "$1/$FILE"
echo "---------------[$2]" >> "$1/$FILE"

