import time
import threading

class CountdownThread(threading.Thread):
    def __init__(self,count):
        threading.Thread.__init__(self)
        self.count = count

    def run(self):
        while self.count > 0:
            print "Counting down", self.count
            self.count -= 1
            time.sleep(5)
        return

t1 = CountdownThread(10) # Create the thread object
t1.start() # Launch the thread
t2 = CountdownThread(20) # Create another thread
t2.start()		
print("Done!")