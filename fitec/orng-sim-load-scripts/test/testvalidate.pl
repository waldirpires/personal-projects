#!/usr/bin/perl -sw

sub validateIngest
{
    my $xmlfile = $_[0];
    my $id   = $_[1];
	my $dir   = $_[2];
	
    open(FILE, "$xmlfile") || die "File $xmlfile not found";
	my @lines = <FILE>;
	close(FILE);
   # check tokens
  my $n = 0;
  foreach(@lines) {
    if ($_ =~ /\@DS-TOKEN\@/) { 	$n++;	}
  }
  if ($n > 0)
  {    print "\nERROR[$id]: Token not set in file $xmlfile\n";	
  }
  
  #check folder
  my $directory = "$dir";

  opendir (DIR, $directory) or die $!;
  
  my $ok = 0;
  while (my $ingestfile = readdir(DIR)) {
    if ($ingestfile =~ /\.xml/i)
	{		$ok++;	} 
	else if ($ingestfile =~ /\movie.mov/i || $ingestfile =~ /\movie.ts/i || $ingestfile =~ /\movie.mpg/i )
	{		$ok++;	}	
	else if ($ingestfile =~ /\cover.jpg/i || $ingestfile =~ /\poster.ts/i)
	{		$ok++;	}
  }
  if ($ok < 3){ $n++;
    print "\nERROR[$id]: missing files in directory $dir\n";
	system("ls -la $dir");	
  }  
  
  if ($n > 0)
  {	exit;  }
}

