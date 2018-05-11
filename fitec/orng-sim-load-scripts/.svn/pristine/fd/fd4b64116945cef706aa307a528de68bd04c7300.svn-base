#!/usr/bin/perl -sw

renameFilesInDir("/var/tmp", "0101");

sub renameFilesInDir{
  my $dir = $_[0];
  my $id = $_[1];
  print "$id - $dir\n";
  opendir(DIR, $dir) or die $!;
  while (my $file = readdir(DIR)) {
		print "Checking file $file . . .\n";
        # We only want files
        next unless (-f "$dir/$file");

        # Use a regular expression to find files ending in id
        next unless ($file =~ m/.tmp/);
		print "Found file: $file\n";
		my $newname = $file;
        $newname =~ s/.tmp/.done/;
        system("mv $file $newname");
        print "File $file renamed to $newname\n";		
    }
}
