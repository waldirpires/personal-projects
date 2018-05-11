#!/usr/bin/perl -sw

use Switch;
use Cwd;
use File::stat;
use POSIX;
use Time::HiRes qw(gettimeofday);
use feature qw/switch/; 

$dir = "/var/tmp/watchFolder01";
$ingestDir = "/opt/watchFolder01";

opendir(DIR, $dir) or die $!;

while (my $file = readdir(DIR)) {

	    next unless (-f "$dir/$file");

        # Use a regular expression to find files ending in .xml
        next unless ($file =~ m/\.xml$/);
		$xmlFileName = $file;
	
}