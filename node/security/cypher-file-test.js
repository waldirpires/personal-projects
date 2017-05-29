const crypto = require('crypto');
const fs = require('fs');
const cipher = crypto.createCipher('aes192', 'a password');

const input = fs.createReadStream('test.txt');
const output = fs.createWriteStream('test.enc');

input.pipe(cipher).pipe(output);