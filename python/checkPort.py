import socket;
import sys;

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
result = sock.connect_ex((sys.argv[1], int(sys.argv[2])))
if result == 0:
   print "Port is open"
else:
   print 'port CLOSED, connect_ex returned: '+str(result)