ar express = require('express');
var app = express();

app.get('/', function (req, res) {
  res.send('Hello World!');
});

app.get('http://localhost:13080/cpm/business/v1', function(req, res) {
  var bi_id = req.id;
  res.send(bi_id);
});

app.listen(13080, function () {
  console.log('Example app listening on port 13080!');
});