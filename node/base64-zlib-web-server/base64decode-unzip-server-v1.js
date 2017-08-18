var http = require('http');
var zlib = require('zlib');

http.createServer(function (req, res) {

if (req.method == 'POST') {
        whole = ''
        req.on('data', (chunk) => {
            whole += chunk.toString()
        })

        req.on('end', () => {
            console.log(whole)
            
            
            
            
            
            res.writeHead(200, 'OK', {'Content-Type': 'text/html'})
            res.end('Data received.')
        })
    }

}).listen(8082);