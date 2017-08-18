// node bacon-cypher.js -f 'the book is on the table' -n 10 -d
// node bacon-cypher.js -f '~ro*lyyu*s}*yx*~ro*~klvo' -n 10 -d
var i = 2;
var frase, op, saida = '', n, x = 0; 

while (i < process.argv.length){
  if (process.argv[i] == '-e' || process.argv[i] == '-d')  {
    op = process.argv[i]; i=i+1;
  } else
  if (process.argv[i] == '-f')  {
    frase = process.argv[i+1]; i=i+2;
  } else
  if (process.argv[i] == '-n')  {
    n = parseInt(process.argv[i+1]); i=i+2;
  } 
}

for (i = 0; i < frase.length; i++){
  if (op == '-e')  {
    x = frase.charCodeAt(i)+n;
  } else if (op == '-d')  {
    x = frase.charCodeAt(i)-n;
  }
  saida = saida + String.fromCharCode(x);
}

console.log(saida);
