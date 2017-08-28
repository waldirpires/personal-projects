from threading import Thread
from Queue import Queue
from random import random
import time
q = Queue()
def fib(n):
   print("fib " + str(n))
   if n<=2:
     return 1
   return fib(n-1)+fib(n-2)

def producer():
   while True:
     wt = random()*5
     print("Producer: producing . . . " + str(wt))
     time.sleep(wt)
     q.put((fib,10))

def consumer():
   while True:
     print("Consumer: waiting for producer . . .") 
     task,arg = q.get()
     print task(arg)
     q.task_done()

t1 = Thread(target=producer)
t2 = Thread(target=consumer)
t1.start()
t2.start()