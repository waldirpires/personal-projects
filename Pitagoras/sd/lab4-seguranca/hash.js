const crypto = require('crypto');
const hash = crypto.createHash('sha256');

hash.on('readable', () => {
  const data = hash.read();
  if (data)
    console.log(data.toString('hex'));
});

var msg = 'the book is on the table.';

hash.write(msg);
hash.end();
// http://www.zdnet.com/article/google-breaks-sha-1-web-crypto-for-good-but-torvalds-plays-down-impact-on-git/