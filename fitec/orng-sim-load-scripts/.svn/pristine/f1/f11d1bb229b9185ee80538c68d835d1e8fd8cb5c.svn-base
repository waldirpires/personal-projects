use strict;
use warnings;
use POSIX;
use DateTime;
use Time::Localtime;
use constant MINUTE => 60;

require 'load_functions.pl';

my $testId = 1;

print "Test $testId \n\n";
  my $numPackages = 4;
  my $range = 10;
  my $random_number = 0;
  my @distribution = (0.2, 0.5, 0.9, 1.0);
  my @stats = (0, 0, 0, 0);

  my $size = 1000;
  my @expectedValues = (0, 0, 0, 1, 1, 2, 2, 3);
  my @random = (0, 1, 2, 3, 5, 6, 9, 10);
  
  my $i = 0;
  my $expected = 0;
  my $value = 0;
  for ($i = 0; $i < $size; $i++)
  {
    print "Test $testId \n\n";  
    #$expected = $expectedValues[$i];
	$random_number = rand();
	#getRandomNumber($range);
	#$random[$i];
	print "Random number: $random_number\n";
    $value = getSelectedPackageId($numPackages, \@distribution);
    $stats[$value] = $stats[$value] + 1;
    logit(join(", ", @stats));
    #assertValue($testId, $expected, $value);
    $testId++;
  }
  
  
print "DONE!\n"