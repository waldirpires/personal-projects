import urllib2
import re
import sys,os
video_data='%7C(.*?videoplayback.*?)%2C'
def download(url):
    headers={'User-Agent':None}
    req=urllib2.Request(url,None,headers)
 
    s=urllib2.urlopen(req).read()
    search=re.search(video_data,s)
    if search:
        flash_url=urllib2.unquote(search.group(1))
    req=urllib2.Request(flash_url,None,headers)
    file1=open('output','wb')
    req=urllib2.Request(flash_url,None,headers)
    s=urllib2.urlopen(req).read()
    file1.write(s)
    sys.stdout.flush()
    #this is to extract the audio from the video which was downloaded
    cmd='ffmpeg -i output -acodec libmp3lame -aq 4 audio.mp3'
    #need to download ffmpeg if the script doesnt work here
    os.system(cmd);
url=raw_input("Enter the youtube url\n")
download(url)