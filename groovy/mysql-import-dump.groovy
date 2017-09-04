def pathToDump = args[0]
def processName = "/C/apps/internet/xampp3/mysql/bin/mysql"
def process = processName + " --host=localhost --user=fitdr --password=fitdr --port=3306 --default-character-set=utf8 -e \"source "${pathToDump}\"\" ".execute()


