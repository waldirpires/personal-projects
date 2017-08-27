import time

def calc_square(numbers):
    print("Calculate the square of numbers: " + str(numbers))
    for n in numbers:
        time.sleep(0.2)
        print("Square " + str(n*n))

def calc_cube(numbers):
    print("Calculate the cube of numbers: " + str(numbers))
    for n in numbers:
        time.sleep(0.2)
        print("Cube " + str(n*n*n))

arr = [2, 3, 8, 9]

t = time.time()
calc_square(arr)
calc_cube(arr)
print("Done: ", time.time() - t)