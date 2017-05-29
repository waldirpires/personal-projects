var http = require('http');
var fs = require('fs');

// Create a server
http.createServer( function (request, response) {  
   // Parse the request containing file name
   
   // Print the name of the file for which request is made.
   console.log("Request received.");

   response.writeHead(200, {'Content-Type': 'text/html'});	
   
   response.write("Hello World!");		

   response.end();
   
}).listen(8081);

// Console will print the message
console.log('Server running at http://127.0.0.1:8081/');