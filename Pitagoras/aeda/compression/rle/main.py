def encode(input_string):
    count = 1
    prev = ''
    lst = []
    for character in input_string:
        if character != prev:
            if prev:
                entry = (prev,count)
                lst.append(entry)
                print lst
            count = 1
            prev = character
        else:
            count += 1
    else:
        entry = (character,count)
        lst.append(entry)
        print lst
    return ''.join(chr(cnt)+ch for (ch, cnt) in lst)

def decode(encoded_text):
    chars = []
    a = iter(encoded_text)
    for (cnt, ch) in zip(a, a):
        chars.append(ch * ord(cnt))
    return ''.join(chars)

word = "aaaaahhhhhhmmmmmmmuiiiiiiiaaaaaa"
print 'RLE'
print word
ao = len(word)
print ('Original: ' + str((ao)))  # -> 32
# encode
rle_text = encode(word)
print rle_text
ac = len(rle_text)
print(ac) # -> 12
tc = ao/(ac*1.0)
print('Tc = ' + str(tc))
# decode
print(decode(rle_text))  # -> "aaaaahhhhhhmmmmmmmuiiiiiiiaaaaaa"