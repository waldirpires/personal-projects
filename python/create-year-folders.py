import os
import sys

path = sys.argv[1]
year = sys.argv[2]

print('Creating folders under ' + path + ' for year ' + year)

if os.path.exists(path):
    onetotwelve = range(1,13)
    for count in onetotwelve:
        print (year + str(count).zfill(2))
        folder = path+'/'+year + str(count).zfill(2)
        if not os.path.exists(folder):
            os.makedirs(folder)
        else:
            print('Path already exists: ' + folder)
	
#    os.makedirs(directory)
#print "%02d" % (1,)
  