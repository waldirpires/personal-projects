from threading import Thread
from Queue import Queue
from random import random
import time
q = Queue()
def fib(n):
   if n<=2:
     return 1
   return fib(n-1)+fib(n-2)

def producer():
   while True:
     wt = random()*5
     time.sleep(wt)
     q.put((fib,35))

def consumer():
   while True:
     task,arg = q.get()
     print task(arg)
     q.task_done()

t1 = Thread(target=producer)
t2 = Thread(target=consumer)
t1.start();t2.start()