//https://jsonblob.com/fb95a26d-3ffe-11e7-ae4c-4b54ecc1d699
var exec = require('child_process').exec;
var child;

if (process.argv.length <= 2) {
    console.log("Usage: " + __filename + " -u <gerrit user> -s <gerrit server> -t <ms> -gcr gerrid IDs . . .");
    process.exit(-1);
}

var gerritUser = "";
var gerritServer = "";
var execute = false;
var debug = false;
var path = '';
var i = 0;
var time = 500;

for (i = 2; i < process.argv.length; i++)
{
    if (process.argv[i] == "-u")
        gerritUser = process.argv[i+1];
    else if (process.argv[i] == "-s")
        gerritServer = process.argv[i+1];
    else if (process.argv[i] == "-t")
        time = Number(process.argv[i+1]);
    else if (process.argv[i] == "-X"){
        execute = true;
        path = process.argv[i+1];
    }
    else if (process.argv[i] == "-d")
        debug = true;
    else if (process.argv[i] == "-gcr")
        break;
}

//xwalrib
//var gerritUser = process.argv[2];
//gerrit.ericsson.se
//var gerritServer = process.argv[3];

console.log("Connecting at: " + gerritUser+"@"+gerritServer);

for (var j = i+1; j < process.argv.length; j++)
{
  console.log(j + ' ' + process.argv[j]);
  var cmd = 'ssh -p 29418 '+gerritUser+'@'+gerritServer+' gerrit query --current-patch-set --format json ' + process.argv[j];
  if (debug) console.log(cmd);
  child = exec(cmd,
   function (error, stdout, stderr) {
      //console.log(stdout);
      stdout = stdout.split("\n")[0];
      //stdout = stdout.substring(0, stdout.indexOf("{\"type\":\"stats\""));
      if (debug) console.log(stdout);
      var result = JSON.parse(stdout);
      var cherrypickcmd = 'git fetch https://'+gerritUser+'@'+gerritServer+'/a/'+ result.project + " " +  result.currentPatchSet.ref + ' && git cherry-pick FETCH_HEAD';
      console.log();
      console.log(result.branch + " - " + result.number + ': ' + result.subject + " - " + result.status);
      console.log(cherrypickcmd);
      if (execute)
      {
        console.log('Executing command: ');
        console.log(cherrypickcmd);
        child = exec(cherrypickcmd,
            function (error, stdout, stderr) {
                console.log(stdout);
                if (error !== null) {
                    console.log('exec error: ' + error);
                }
            });
      }      
      if (error !== null) {
          console.log('exec error: ' + error);
      }
   });
   var waitTill = new Date(new Date().getTime() + time);
    while(waitTill > new Date()){}
}

