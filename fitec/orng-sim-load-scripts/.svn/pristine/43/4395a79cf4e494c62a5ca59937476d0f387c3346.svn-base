#!/usr/bin/perl
use strict;
use warnings;
use POSIX;
use DateTime;
use Time::Localtime;
use constant MINUTE => 60;

require 'load_functions.pl';

my $windowSize = 4;
my @windowTime = (0, 6, 12, 20, 24); # window times
my @windowRate = (2, 6, 4, 1); # per minute
my $testId = 1;

print "Test $testId \n\n";
my $expected = MINUTE / $windowRate[2]; # 13:00
my $value = getSleepTime(13, $windowSize, \@windowTime, \@windowRate);
assertValue($testId, $expected, $value);
$testId++;

print "Test $testId \n\n";
$expected = MINUTE / $windowRate[0]; # 0:00
$value = getSleepTime(0, $windowSize, \@windowTime, \@windowRate);
assertValue($testId, $expected, $value);
$testId++;

print "Test $testId \n\n";
$expected = MINUTE / $windowRate[0]; # 5:00
$value = getSleepTime(5, $windowSize, \@windowTime, \@windowRate);
assertValue($testId, $expected, $value);
$testId++;

print "Test $testId \n\n";
$expected = MINUTE / $windowRate[1]; # 6:00
$value = getSleepTime(6, $windowSize, \@windowTime, \@windowRate);
assertValue($testId, $expected, $value);
$testId++;

print "Test $testId \n\n";
$expected = MINUTE / $windowRate[2]; # 12:00
$value = getSleepTime(12, $windowSize, \@windowTime, \@windowRate);
assertValue($testId, $expected, $value);
$testId++;

print "Test $testId \n\n";
$expected = MINUTE / $windowRate[2]; # 13:00
$value = getSleepTime(13, $windowSize, \@windowTime, \@windowRate);
assertValue($testId, $expected, $value);
$testId++;

print "Test $testId \n\n";
$expected = MINUTE / $windowRate[2]; # 19:00
$value = getSleepTime(19, $windowSize, \@windowTime, \@windowRate);
assertValue($testId, $expected, $value);
$testId++;

print "Test $testId \n\n";
$expected = MINUTE / $windowRate[3]; # 20:00
$value = getSleepTime(20, $windowSize, \@windowTime, \@windowRate);
assertValue($testId, $expected, $value);
$testId++;

print "Test $testId \n\n";
$expected = MINUTE / $windowRate[3]; # 23:00
$value = getSleepTime(20, $windowSize, \@windowTime, \@windowRate);
assertValue($testId, $expected, $value);
$testId++;

print "DONE!\n"