const crypto = require('crypto');
const assert = require('assert');

var title = "Alice-Bog diffie-hellman";
console.time(title);
console.log("Start . . .");

// Generate Alice's keys...
const alice = crypto.createDiffieHellman(2048);
console.log("Generating Alice´s keys . . .");
const aliceKey = alice.generateKeys();

// Generate Bob's keys...
const bob = crypto.createDiffieHellman(alice.getPrime(), alice.getGenerator());
console.log("Generating Bob´s keys . . .");
const bobKey = bob.generateKeys();

// Exchange and generate the secret...
console.log("Computing Alice´s secret . . .");
const aliceSecret = alice.computeSecret(bobKey);
console.log("Computing Bob´s secret . . .");
const bobSecret = bob.computeSecret(aliceKey);

console.timeEnd(title);
console.log("---------------------");

// OK
assert.strictEqual(aliceSecret.toString('hex'), bobSecret.toString('hex'));
console.log("Alice´s secret: \n" + aliceSecret.toString('hex').length);
console.log("Bob´s secret: \n" + bobSecret.toString('hex').length);
console.log("---------------------\nDONE");