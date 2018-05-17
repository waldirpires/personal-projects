//Set up mongoose connection
var mongoose = require('mongoose');
var mongoDB = 'mongodb://dbuser:dbuser@ds251548.mlab.com:51548/express-library';
mongoose.connect(mongoDB);
mongoose.Promise = global.Promise;
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'MongoDB connection error:'));

module.exports = db;