from multiprocessing import Pool
import time

def fa(n):
    sum = 0
    for x in range(1000):
        sum+=x*x
    return sum

if __name__ == "__main__":
    t = time.time()
    p = Pool(processes=8)
    r = 100000
    print("Range: " + str(r))
    result = p.map(fa,range(r)) # divisao das tarefas
    p.close()
    p.join()
    print("Parallel: ", time.time() - t)
    t2 = time.time()
    result = []
    for x in range(r):
        result.append(fa(x))
    print("Serial: ", time.time() - t2)