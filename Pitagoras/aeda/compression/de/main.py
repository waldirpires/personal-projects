def intToBits(num):
    return "{0:b}".format(num)
  
def delta_encode (buffer):
    delta = 0
    en = []
    for c in word:
        en.append(ord(c))
    print en
    for i in range(0, len(buffer)):
        original = en[i]
        en[i] -= delta
        delta = original
    return en
 
def delta_decode (buffer):
    delta = 0
    i = 0
    for i in range(0, len(buffer)):
        buffer[i] += delta
        delta = buffer[i]
    return buffer

def intToBin(buffer):
    length = 0
    bufferBin = []
    maxLen = -1
    for i in buffer:
        valueBin = intToBits(i)
        length += len(valueBin)
        bufferBin.append(valueBin)
    info (bufferBin, "Buffer Binary")
    return length

def info(buffer, op):
    print op
    print (str(len(word)) + ' bytes\t' + str(len(word)*8) + ' bits')
    print buffer

word = "abcdcdedcdabcd"
info(word, 'Encoding')
encoded = delta_encode(word)
info(encoded, 'Encoded')
print str(intToBin(encoded)) + ' bits'
decoded = delta_decode(encoded)
info(decoded, 'Decoded')
print str(intToBin(decoded)) + ' bits'
