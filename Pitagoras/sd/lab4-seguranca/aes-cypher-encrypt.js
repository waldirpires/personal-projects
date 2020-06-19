// node aes-cypher-encrypt.js -a aes192 -s password -f test.txt -o test.enc
//
const crypto = require('crypto');
const fs = require('fs');

var i = 2;
var alg, msg, secret;

while (i < process.argv.length)
{
  if (process.argv[i] == '-a')
  {
    alg = process.argv[i+1];
  } 
  else if (process.argv[i] == '-s')
  {
    secret = process.argv[i+1];
  }
  else if (process.argv[i] == '-m')
  {
    msg = process.argv[i+1];
  }
  else if (process.argv[i] == '-f')
  {
    srcFile = process.argv[i+1];
  }
  else if (process.argv[i] == '-o')
  {
    dstFile = process.argv[i+1];
  }
  i=i+2;
}

// 'aes192'
//var alg = process.argv[2];
//var secret = process.argv[3];
//var msg = process.argv[4];

var title = "AES-Cypher Encrypt";
console.time(title);

const cipher = crypto.createCipher(alg, secret);

if (srcFile){
  if (!dstFile)
  {
    console.log("Error: missing destination file (option -o)");
    console.log("Exiting");
    process.exit();
  }
  console.log("Using source file: " + srcFile);
  console.log("Using destination file: " + dstFile);
  const input = fs.createReadStream(srcFile);
  const output = fs.createWriteStream(dstFile);  
  input.pipe(cipher).pipe(output);
} else {
  var encrypted = '';
  cipher.on('readable', () => {
    const data = cipher.read();
    if (data)
       encrypted += data.toString('hex');
  });
  cipher.on('end', () => {
    console.log('Encrypted:\n'+encrypted.length);
    console.log(encrypted);
    // Prints: ca981be48e90867604588e75d04feabb63cc007a8f8ad89b10616ed84d815504
    console.timeEnd(title);
    console.log('-----');
  });

// node aes-cypher-encrypt.js -a aes192 -s password -m 'the book is on the table'
// Encrypted:
// 64
// 8d383f9bf006c72c4c7837d2e0eef1c397265ddac49468dc3ccf53c06dc5bbb1
// AES-Cypher Encrypt: 16ms
// -----


//'some clear text data'
  cipher.write(msg);
  cipher.end();
}
console.timeEnd(title);
console.log('-----');



