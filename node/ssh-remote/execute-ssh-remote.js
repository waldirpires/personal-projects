var exec = require('ssh-exec')
 
process.stdin
  .pipe(exec('echo try typing something; cat -', 'root@vmx-cpm-296'))
  .pipe(process.stdout)