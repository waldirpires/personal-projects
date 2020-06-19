import sys
import random
import time
import copy
import sort

def getTime():
  return int(round(time.time() * 1000))

  
print sys.argv  
n = int(sys.argv[1])
max = n
A = []
B = []
for i in range(n):
  A.append(random.randint(0, max))
print ("Size:" + str(len(A)))
B =  copy.copy(A)

millis = getTime()
sort.selectionSort(A)
millis = getTime() - millis
print ("SelectionSort: \t" + str(millis) + " ms")

A =  copy.copy(B)
n = len(A)
millis = getTime() 
sort.quickSort(A,0,n-1)
millis = getTime() - millis
print ("QuickSort: \t" + str(millis) + " ms")

A =  copy.copy(B)
millis = getTime() 
sort.mergeSort(A, 0, n-1)
millis = getTime() - millis
print ("MergeSort: \t" + str(millis) + " ms")

A =  copy.copy(B)
millis = getTime() 
sort.insertionSort(A)
millis = getTime() - millis
print ("InsertionSort: \t" + str(millis) + " ms")

A =  copy.copy(B)
millis = getTime() 
sort.bubbleSort(A)
millis = getTime() - millis
print ("BubbleSort: \t" + str(millis) + " ms")

A =  copy.copy(B)
millis = getTime() 
sort.heapSort(A)
millis = getTime() - millis
print ("HeapSort: \t" + str(millis) + " ms")

A =  copy.copy(B)
millis = getTime() 
sort.shellSort(A)
millis = getTime() - millis
print ("ShellSort: \t" + str(millis) + " ms")

A =  copy.copy(B)
millis = getTime() 
A.sort();
millis = getTime() - millis
print ("Python List Sort: \t" + str(millis) + " ms")