#!/usr/bin/env python
import socket
import subprocess
import sys
from datetime import datetime

def getNetworkIp():
    s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    s.connect(('www.uol.com.br', 0))
    return s.getsockname()[0]

# Clear the screen
subprocess.call('clear', shell=True)

if sys.argv[1] == None:
    # Ask for input
    remoteServer    = raw_input("Enter a remote host to scan: ")
else:
    remoteServer    = sys.argv[1]
	
remoteServerIP  = socket.gethostbyname(remoteServer)
    

# Print a nice banner with information on which host we are about to scan
print "-" * 60
print "Source: ", getNetworkIp()
print "Please wait, scanning remote host", remoteServerIP
print "-" * 60

# Check what time the scan started
t1 = datetime.now()

# Using the range function to specify ports (here it will scans all ports between 1 and 1024)

# We also put in some error handling for catching errors

ports = [20, 21, 22, 80, 443, 8080, 8081, 9090, 13080, 12742, 15000]

try:
#    for port in range(1,1025):  
#        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
#        result = sock.connect_ex((remoteServerIP, port))
#        if result == 0:
#            print "Port {}: 	 Open".format(port)
#        sock.close()

    for port in ports:  
        print "Trying port {}...".format(port)
        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        result = sock.connect_ex((remoteServerIP, port))
        if result == 0:
            print "Port {}: 	 Open".format(port)
        sock.close()

except KeyboardInterrupt:
    print "You pressed Ctrl+C"
    sys.exit()

except socket.gaierror:
    print 'Hostname could not be resolved. Exiting'
    sys.exit()

except socket.error:
    print "Couldn't connect to server"
    sys.exit()

# Checking the time again
t2 = datetime.now()

# Calculates the difference of time, to see how long it took to run the script
total =  t2 - t1

# Printing the information to screen
print 'Scanning Completed in: ', total

