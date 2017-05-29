var exec = require('ssh-exec')
 
// using ~/.ssh/id_rsa as the private key 
 
exec('ls -lh', 'ubuntu@my-remote.com').pipe(process.stdout)
 
// or using the more settings 
 
exec('ls -lh', {
  user: 'ubuntu',
  host: 'my-remote.com',
  key: myKeyFileOrBuffer,
  password: 'my-user-password'
}).pipe(process.stdout)
 
// or if you want to pipe some data to the remote process 
 
process.stdin
  .pipe(exec('echo try typing something; cat -', 'ubuntu@my-remote.com'))
  .pipe(process.stdout)