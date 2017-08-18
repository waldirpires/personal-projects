var http = require('http');
var zlib = require('zlib');

console.log("==============================================");
console.log(new Date().toISOString());

process.argv.forEach(function (val, index, array) {
  console.log(index + ': ' + val);
});
console.log();

var port = process.argv[2];

http.createServer(function (req, res) {

        console.log("==============================================");        
        console.log(new Date().toISOString());
        console.log("Operation: " + req.method+req.url);        

if (req.method == 'POST' && req.url.indexOf('decode') != -1) {
        whole = ''
        req.on('data', (chunk) => {
            whole += chunk.toString()
        })
        req.on('end', () => {
            console.log("Received: " + whole.length + ' characters')
            
            var buf = new Buffer(whole, 'base64');

            console.log("Decoding content . . .");
            const buffer = Buffer.from(whole, 'base64');
            console.log("Decoded: " + buffer.length + ' characters')
            var output = '';
            console.log("Unzipping content . . .");
            zlib.unzip(buffer, (err, buffer) => {
              if (!err) {
                output = buffer.toString();
                console.log("Unzipped: " + output.length + ' characters')
                //res.writeHead(200, "OK", {'Content-Type': 'application/json'});
                res.writeHead(200, "OK", {'Content-Type': 'text/plain'});
                console.log(output);
                res.end(output);
              } else {
                // handle error
              }
            });
        })
    }
    else if (req.method == 'POST' && req.url.indexOf('encode') != -1) {
        whole = ''
        req.on('data', (chunk) => {
            whole += chunk.toString()
        })
        req.on('end', () => {
            console.log("Received: " + whole.length + ' characters')

            zlib.deflate(whole, function(err, buffer) {
            if (!err) {
                output = buffer.toString('base64');
                console.log("Zipped and Coded: " + output.length + ' characters')
                res.writeHead(200, "OK", {'Content-Type': 'text/plain'});
                console.log(output);
                res.end(output);
              } else {
                // handle error
              }
            });
        })
    }
    
    else {
        res.writeHead(200, "OK", {'Content-Type': 'text/plain'});
        res.end("Server running");        
    }

}).listen(port);

console.log("Server started on port " + port);