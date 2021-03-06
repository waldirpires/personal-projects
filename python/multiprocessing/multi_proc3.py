import time
import multiprocessing
#armazenando o resultado (global)
def calc_square(numbers, result):
    for idx, n in enumerate(numbers):
        print("Square " + str(n*n))
        result[idx] = n*n

if __name__ == "__main__":
    arr = [2,3,8,9] # vetor
    result = multiprocessing.Array('i', 4) # vetor de inteiros
    p1 = multiprocessing.Process(target=calc_square, args=(arr,result))
    # iniciar a execucao dos processos
    p1.start()
    # espera dos processos
    p1.join()
    print(result[:])
    print("done!")