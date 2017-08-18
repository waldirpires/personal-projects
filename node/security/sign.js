const crypto = require('crypto');
const sign = crypto.createSign('RSA-SHA256');

const alice = crypto.getDiffieHellman('modp14');
const bob = crypto.getDiffieHellman('modp14');

alice.generateKeys();

console.log(alice.getPrivateKey());
console.log(alice.getPublicKey());

bob.generateKeys();

sign.write('some data to sign');
sign.end();

const privateKey = alice.getPrivateKey();
console.log(sign.sign(privateKey, 'hex'));