import time

def f(n):
    time.sleep(0.2)
    return n*n

if __name__ == "__main__":
    t = time.time()
    array = [1,2,3,4,5]
    result = []
    for n in array:
        result.append(f(n))
    print(result)
    print("Done: ", time.time() - t)