import time
import multiprocessing
#armazenando o resultado (global)
square_result=[]
def calc_square(numbers):
    global square_result
    for n in numbers:
        print("Square " + str(n*n))
        square_result.append(n*n)
    print("Process result: " + str(square_result))

if __name__ == "__main__":
    arr = [2,3,8,9] # vetor
    # criar dois processos acessando o vetor acima:
    # P1: quadrado dos numeros
    p1 = multiprocessing.Process(target=calc_square, args=(arr,))
    # iniciar a execucao dos processos
    p1.start()
    # espera dos processos
    p1.join()
    print("Result: " + str(square_result))
    print("done!")