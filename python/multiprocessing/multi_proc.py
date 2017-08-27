import time
import multiprocessing

def calc_square(numbers):
    for n in numbers:
        time.sleep(5)
        print("Square " + str(n*n))

def calc_cube(numbers):
    for n in numbers:
        time.sleep(5)
        print("Cube " + str(n*n*n))

if __name__ == "__main__":
    arr = [2,3,8,9] # vetor
    # criar dois processos acessando o vetor acima:
    # P1: quadrado dos numeros
    p1 = multiprocessing.Process(target=calc_square, args=(arr,))
    # P2: cubo dos numeros
    p2 = multiprocessing.Process(target=calc_cube, args=(arr,))
    # iniciar a execucao dos processos
    p1.start()
    p2.start()
    # espera dos processos
    p1.join()
    p2.join()
    print("done!")