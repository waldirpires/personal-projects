import urllib

for x in range(276, 300):
    f = open('bg' + str(x) + '.jpg','wb')
    s = 'https://files.passeidireto.com/f22d447e-ef07-4823-87a9-b575a4a02fe1/bg' + str(x) + '.jpg'
    print s
    f.write(urllib.urlopen(s).read())
    f.close()