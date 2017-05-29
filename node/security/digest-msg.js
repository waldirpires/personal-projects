const crypto = require('crypto');

//for (var i = 0; i< process.argv.length; i++)
//{
//  console.log(i + " - " + process.argv[i]);
//}

var alg = process.argv[2];
var secret = process.argv[3];
var msg = process.argv[4];
//const secret = 'abcdefg';

var title = "Digest " + alg;
console.time(title);
console.log("Start . . .");
//'sha256'
const hash = crypto.createHmac(alg, secret)
                   .update(msg)
                   .digest('hex');
                   
console.timeEnd(title);
console.log("---------------------");
                   
console.log("Length: " + hash.length);
console.log(hash);
// Prints:
//   c0fa1bc00531bd78ef38c628449c5102aeabd49b5dc3a2a516ea6ea959d6658e