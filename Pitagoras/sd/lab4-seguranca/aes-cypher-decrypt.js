// node aes-cypher-decrypt.js -a aes192 -s password -f test.enc -o test.out
//
const crypto = require('crypto');
const fs = require('fs');

var i = 2;
var alg, encrypted, secret;
var srcFile, dstFile;

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
  else if (process.argv[i] == '-e')
  {
    encrypted = process.argv[i+1];
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

var title = "AES-Cypher Decrypt";
console.time(title);

const decipher = crypto.createDecipher(alg, secret);

if (srcFile){
  if (!dstFile)
  {
    console.log("Error: missing destination file (option -o)");
    console.log("Exiting");
    process.exit();
  }
  const input = fs.createReadStream(srcFile);
  const output = fs.createWriteStream(dstFile);  
  input.pipe(decipher).pipe(output);

  fs.stat(dstFile, (err, stats) => {
    if (err) throw err;
    console.log(`stats: ${JSON.stringify(stats)}`);
  });  
  fs.readFile(dstFile, 'utf8', function (err,data) {
    if (err) {
      return console.log(err);
    }
    console.log("Size: " + data.length + " Bytes");
  });  
} else 
{
var decrypted = '';
decipher.on('readable', () => {
  const data = decipher.read();
  if (data)
    decrypted += data.toString('utf8');
});
decipher.on('end', () => {
  console.log('Decrypted:\n'+decrypted.length);
  console.log(decrypted);
  console.timeEnd(title);
  console.log('-----');
});

decipher.write(encrypted, 'hex');
decipher.end();
}
  console.timeEnd(title);
  console.log('-----');




// node aes-cypher-decrypt.js -a aes192 -s password -e 8d383f9bf006c72c4c7837d2e0eef1c397265ddac49468dc3ccf53c06dc5bbb1

// Decrypted:
// 24
// the book is on the table
// AES-Cypher Decrypt: 16ms
// -----