from multiprocessing import Pool
import time

def f(n):
    time.sleep(0.2)
    return n*n

def fa(n):
    sum = 0
    for x in range(100):
        sum+=x*x
    return sum

if __name__ == "__main__":
    t = time.time()
    array = [1,2,3,4,5]
    p = Pool()
    result = p.map(f,array) # divisao das tarefas, geracao do resultado    
    print(result)
    print("Done: ", time.time() - t)