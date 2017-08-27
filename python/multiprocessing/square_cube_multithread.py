import time
import threading

def println(arg):
    print(arg + "\n")
def calc_square(numbers):
    println("Calculate the square of numbers: " + str(numbers))
    for n in numbers:
        time.sleep(0.2)
        println("Square " + str(n*n))
def calc_cube(numbers):
    println("Calculate the cube of numbers: " + str(numbers))
    for n in numbers:
        time.sleep(0.2)
        println("Cube " + str(n*n*n))

arr = [2, 3, 8, 9]
t = time.time()
#criando as threads
t1 = threading.Thread(target=calc_square,args=(arr,))
t2 = threading.Thread(target=calc_cube,args=(arr,))
#iniciar as threads
t1.start()
t2.start()
#espera pelas threads
t1.join()
t2.join()
print("Done: ", time.time() - t)