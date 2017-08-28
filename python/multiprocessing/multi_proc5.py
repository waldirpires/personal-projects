import time
import multiprocessing
import os
#armazenando o resultado (global)
def info(title):
    print(title + ": process id ", os.getpid())
	
def calc_square(numbers, q):
    info('function calc_square')
    for idx, n in enumerate(numbers):
        print("Square " + str(n*n))
        q.put(n*n)

if __name__ == "__main__":
    info('main line')
    arr = [2,3,8,9] # vetor
    q = multiprocessing.Queue()
    p1 = multiprocessing.Process(target=calc_square, args=(arr,q))
    # iniciar a execucao dos processos
    p1.start()
    # espera dos processos
    p1.join()
    while q.empty() is False:
        print(q.get())
    print("done!")