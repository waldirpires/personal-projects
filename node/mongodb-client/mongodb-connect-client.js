// Retrieve
var MongoClient = require('mongodb').MongoClient;

const args = process.argv;
//var url = 'mongodb://192.168.99.100:32769';
var url = 'mongodb://'+args[2]+':'+args[3];
console.log("INFO: connection url -> " + url);

// Connect to the db
MongoClient.connect(url+"/exampleDb", function(err, db) {
  if(!err) {
    console.log("We are connected");
	db.close();
	console.log("We are disconnected");
  } else {
    console.log("ERROR: " + err);
  }
});