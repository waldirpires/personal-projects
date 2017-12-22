#!/usr/bin/env python
import socket
import subprocess
import sys
from datetime import datetime
import threading
import multiprocessing

def checkIpPort(IP, port, q):
    try:
        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        result = sock.connect_ex((remoteServerIP, port))
        if result == 0:
            q.put(port)
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
t = datetime.now()

# Using the range function to specify ports (here it will scans all ports between 1 and 1024)

# We also put in some error handling for catching errors

ports = [20, 21, 22, 80, 443, 1494, 8080, 8443, 8444, 8081, 8686, 8888, 9090, 13080, 13180, 13443, 12742, 15000]
threads = []

q = multiprocessing.Queue()

for port in ports:  
    print("Trying port " + str(port))
    t1 = threading.Thread(target=checkIpPort,args=(remoteServerIP,port,q,))
    threads.append(t1)
    t1.start()

for t1 in threads:
    t1.join()
    
# Checking the time again
total = datetime.now() - t

# Calculates the difference of time, to see how long it took to run the script
while q.empty() is False:
    print("Port {}: 	 Open\n".format(q.get()))

# Printing the information to screen
print 'Scanning Completed in: ', total

