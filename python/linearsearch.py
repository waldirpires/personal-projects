import sys

def pesquisaLinear(v, k):
    for i in range(len(v)):
        if v[i] == k:
            return i
    return -1


print "pesquisa linear"
v = [9, 2, 3, 6, 7, 1]
print "Vetor: ", v
k = 1
print "Chave: ", k
r = pesquisaLinear(v, k)
print "Resultado: ", r

