#!/usr/bin/perl
use strict;
use warnings;
use POSIX;
#use DateTime;
#use Time::Localtime;
use constant MINUTE => 60;
#use Time::HiRes qw/ time sleep /;

sub getSleepTime{

  my $currentHour = $_[0];
  my $i = 0;
  my $sleep = 0;
  my $windowSize = $_[1];
  my @windowTime = @{$_[2]};
  my @windowRate = @{$_[3]};
  my $windowId = -1;
  
  logit("Using hour=$currentHour\n");
  my $timeFormatted = join(", ", @windowTime);
  my $rateFormatted = join(", ", @windowRate);
  logit("Using window config {size=$windowSize, time=($timeFormatted), rate=($rateFormatted)\n");
  
  for ($i = 0; $i < $windowSize; $i++)
  {
    if ($currentHour >= $windowTime[$i] && $currentHour < $windowTime[$i+1])
	{
	  $windowId = $i;
	  logit("Hour: $currentHour -> Found: window [$windowId]: $windowTime[$windowId] -> rate: $windowRate[$windowId] i/m\n");
	  $sleep = MINUTE / $windowRate[$windowId];
	  # logit("Sleep time (s): $sleepTime");
	  last;
	}
  }  
  return $sleep;
}

sub getHour
{
  my ($sec, $min, $hour, $mday, $mon, $year, $wday, $yday, $isdst) = 
                                            localtime(time);
  return $hour;
}

sub getHourMinuteSecond
{
  my ($sec, $min, $hour, $mday, $mon, $year, $wday, $yday, $isdst) = 
                                            localtime(time);
  return $hour *10000 + $min * 100 + $sec; #format hhmmss
}

sub getSecondsInDay
{
  my ($sec, $min, $hour, $mday, $mon, $year, $wday, $yday, $isdst) =
                                            localtime(time);
  return $hour *1440 + $min * 60 + $sec; #format hhmmss

}

sub getTimestamp
{
  my ($sec, $min, $hour, $mday, $mon, $year, $wday, $yday, $isdst) = 
                                            localtime(time);
  $year += 1000;
  $mon += 1;
  return "$year-$mon-$mday-$hour-$min$sec";
}


sub assertValue{
  my $id = $_[0];
  my $exp = $_[1];
  my $val = $_[2];
  
  if ($exp != $val)
  {
    logit("[$id] ERROR: Expected: $exp | Value: $val\n");
  }
}

sub getRandomNumber
{
  my $range = $_[0];
  my $random_number = floor(rand($range));
  return $random_number;
}

sub getSelectedPackageId
{
  my $numPackages = $_[0];
  #my $range = $_[1];
  my $random_number = rand();
  my @distribution = @{$_[1]};
  #my @stats = @{$_[4]};
  
  my $i = 0;
  my $perc = $random_number;
  #logit("Percentage: $perc -> $random_number / $range\n");
  my $distributionFormatted = join(", ", @distribution);
  logit("Using distribution: $distributionFormatted\n");
  #logit(join(", ", @stats));
  logit(" \n\n");
  for ($i = 0; $i<$numPackages;$i++)
  {
	if ($perc <= $distribution[$i])
	{
	  logit("Found distribution $i: $perc");
	  #$stats[$i] = $stats[$i]+ 1;
	  return $i;
	}
  }
  
  return $random_number;
}

my $logdir="/var/log/load/";
sub logit
{
    my $s = shift;
    my ($logsec,$logmin,$loghour,$logmday,$logmon,$logyear,$logwday,$logyday,$logisdst)=localtime(time);
    $logyear = $logyear+1900;
	my $logtimestamp = sprintf("%4d-%02d-%02d %02d:%02d:%02d",$logyear,$logmon+1,$logmday,$loghour,$logmin,$logsec);
    $logmon++;
    my $logfile="$logdir$logyear-$logmon-$logmday-logfile.log";
    my $fh;
    open($fh, '>>', "$logfile") or die "$logfile: $!";
    print $fh "$logtimestamp $s\n";
	print "$logtimestamp $s";
    close($fh);
}

sub getPackagePathFromPkgId()
{
	my $pkgId = $_[0];
	my $content = $_[1];
	
	
}
;
