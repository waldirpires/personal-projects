import zlib
import base64
import sys

print sys.argv[1]
input = sys.argv[1]
decoded = base64.b64decode(input)
output = zlib.decompress(decoded)

print output
