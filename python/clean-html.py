import re
import sys

def cleanhtml(raw_html):
  cleanr = re.compile('<.*?>')
  cleantext = re.sub(cleanr, '', raw_html)
  return cleantext

with open(sys.argv[1], 'r') as my_file:
    str = my_file.read()
    print(cleanhtml(str))
    
  
  