#!/usr/bin/perl -sw

use Time::HiRes qw(gettimeofday);
my $time = gettimeofday;
sleep 2;
print gettimeofday - $time;
print "\n";
$time = gettimeofday;
sleep 30;
print gettimeofday - $time;
print "\n";


