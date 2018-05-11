import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

int size = 0
int done = 0
int inProgress = 0
def myClosure = {num -> 
    size++
	inProgress++
	println "size: $size \t in progress: $inProgress \t done: $done"
	executeOp(num)
	inProgress--
	done++
}
def threadPool = Executors.newFixedThreadPool(4)
 try {
  List<Future> futures = (1..100).collect{num->
	threadPool.submit({->
	myClosure num } as Callable)
  }
  // recommended to use following statement to ensure the execution of all tasks.
  futures.each{it.get()}
}finally {
  threadPool.shutdown()
  sleep 10000
  println "size: $size \t in progress: $inProgress \t done: $done"
}

def executeOp(num)
{	
	println "I Love Groovy ${num}"
	sleep(1000)	
}