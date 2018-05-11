
FILE="cms-resources2.log"

tail -f /opt/tandbergtv/cms/log/jboss.log |grep users. |  awk '{print $1, $2, $3, $4, $13, $14, $15,$16,$17,$18, $19}' >> $1/$FILE &




