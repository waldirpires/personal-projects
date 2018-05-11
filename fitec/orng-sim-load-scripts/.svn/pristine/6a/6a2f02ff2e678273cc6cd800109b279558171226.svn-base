#!/usr/bin/perl -sw
#
# Created by Steve Voisey (srv) 06 Jun 2011.
# email: [steve.voisey@ericsson.com]
#
# file:    adiGen.pl
# version: V1.011
#
# Author  Date      Version Description
# srv     24/jan/12 V1.10  Renamed from generateAdi.pl to adiGen.pl
#                          Enable providerId and productType to be set as input parameters.
# srv     09/feb/12 V1.11  Introduce meta/content root/default. Makes easier to customize.
# xwalpin 21/may/13 V1.12  Added ingestfiles parameter. Option for ingesting files instead of package.
#						   						 Force unpack to "yes" when ingestfiles=yes.
#													 Added replacement of @BATCH-ID@ token value by idnumber
# xwalrib  adding functionalities for load testing at metrology

use Switch;
use Cwd;
use File::stat;
use POSIX;

my $numberDigits = 4;
my $seedId  = "";
my $timeStamp = ` date '+%H%M%S'`;
chomp($timeStamp);

my $site = "telenor";
my %checkSumHash;
my %propertiesHash;
my $thisDir = cwd();

open(my $fh, '>>', '/var/log/load/ingested-titles.txt');

if (defined($help))        { help(); exit;}
unless(defined($scripts))  { $scripts = "/opt/tandbergtv/cms/scripts"; }
unless (defined($prop))    { $prop = "$scripts/conf/generateAdi.properties"; }
 
returnPropertyHash( $prop, \%propertiesHash );

if(defined($metaroot)) { $propertiesHash{metaroot} = "$metaroot"; } 
unless (defined($propertiesHash{metaroot})) { $propertiesHash{metaroot} = "$scripts/adi/meta"; } 

if(defined($meta)) { $propertiesHash{meta} = "$meta"; } 
unless (defined($propertiesHash{meta})) { $propertiesHash{meta} = "$scripts/adi/meta/default"; } 
# If meta does NOT start with a full directory path, append the path metaroot.
# Makes it easier to customize from the command line.
unless( $propertiesHash{meta} =~ /^\// ) { $propertiesHash{meta} = $propertiesHash{metaroot} . "/" . $propertiesHash{meta}; }

if(defined($contentroot)) { $propertiesHash{contentroot} = "$contentroot"; }
unless (defined($propertiesHash{contentroot})) { $propertiesHash{contentroot} = "$scripts/adi/content"; } 

if(defined($content)) { $propertiesHash{content} = "$content"; }
unless (defined($propertiesHash{content})) { $propertiesHash{content} = "$scripts/adi/content/default"; } 
# If content does NOT start with a full directory path, append the path contentroot.
unless( $propertiesHash{content} =~ /^\// ) { $propertiesHash{content} = $propertiesHash{contentroot} . "/" . $propertiesHash{content}; }

if(defined($out)) { $propertiesHash{out} = "$out"; }
unless (defined($propertiesHash{out})) { $propertiesHash{out} = "$thisDir/aditar"; } 

if(defined($tmp)) { $propertiesHash{tmp} = "$tmp"; }
unless (defined($propertiesHash{tmp})) { $propertiesHash{tmp} = "/var/tmp/adi.${timeStamp}"; } 

if(defined($tools)) { $propertiesHash{tools} = "$tools"; }
unless (defined($propertiesHash{tools})) { $propertiesHash{tools} = "$scripts/tools"; } 

if(defined($limit)) { $propertiesHash{limit} = "$limit"; }
unless (defined($propertiesHash{limit})) { $propertiesHash{limit} = 1; } 

if(defined($type)) { $propertiesHash{type} = "$type"; }
unless (defined($propertiesHash{type})) { $propertiesHash{type} = "clear"; } 

if(defined($adi)) { $propertiesHash{adi} = "$adi"; }
unless (defined($propertiesHash{adi})) { $propertiesHash{adi} = "V3.0"; } 

my $idnumber = $seed;
print "idnumber: $idnumber\n";

print $fh "$idnumber\n";
close $fh;

if(defined($idnumber)) { $propertiesHash{idnumber} = "$idnumber"; }
unless (defined($propertiesHash{idnumber})) { $propertiesHash{idnumber} = "$idnumber"; } 

if(defined($ingest)) { $propertiesHash{ingest} = "$ingest"; }
unless (defined($propertiesHash{ingest})) { $propertiesHash{ingest} = "no"; } 

if(defined($ingestfiles)) { $propertiesHash{ingestfiles} = "$ingestfiles"; }
unless (defined($propertiesHash{ingestfiles})) { $propertiesHash{ingestfiles} = "no"; } 

if(defined($ingestdir)) { $propertiesHash{ingestdir} = "$ingestdir"; }
unless (defined($propertiesHash{ingestdir})) { $propertiesHash{ingestdir} = "/content/ingest/${site}/"; } 

if(defined($unpack)) { $propertiesHash{unpack} = "$unpack"; }
unless (defined($propertiesHash{unpack})) { $propertiesHash{unpack} = "no"; } 

if(defined($seeddir)) { $propertiesHash{seeddir} = "$seeddir"; }
unless (defined($propertiesHash{seeddir})) { $propertiesHash{seeddir} = "$propertiesHash{metaroot}/default/seed"; } 

if(defined($provider)) { $propertiesHash{providerid} = "$provider"; }
unless (defined($propertiesHash{providerid})) { $propertiesHash{providerid} = "NONE"; } 

if(defined($product)) { $propertiesHash{product} = "$product"; }
unless (defined($propertiesHash{product})) { $propertiesHash{product} = "NONE"; } 

my $id         = "CID-$propertiesHash{idnumber}";

if($propertiesHash{ingestfiles} =~ /yes/i) {#force unpack when ingesting files
$propertiesHash{unpack} = "yes";
}

if($propertiesHash{type} =~ /enc/i) { $propertiesHash{type} = "encrypt"; $id  = "EID$propertiesHash{idnumber}";}

# Just want a unique seed for clear/encrypted - Ignore the ID with name appended.
$seedId  = $id;

# Keep track of seed in a file, so not have to worry what the previous value was to create new, unique, content.

unless(defined($seed)) {
    if ( -f  "$propertiesHash{metaroot}/default/seed/adigen.${seedId}.seed" ) {
        $seed = `head $propertiesHash{seeddir}/adigen.${seedId}.seed`;
        chomp($seed);
    } else {
        $seed = 1;
    }    
}

# Add name AFTER we haveset the seed value.
if (defined($name)) {
   $id = "$id-$name";
}

# Ok, so, as it turned out didn't strictly need a case, but I like em!
#switch ($adi) {
#    case "V3.0"    { $adiFile   = "TEMPLATE.adi.${adi}.${type}.xml"; 
#                     $tokenFile = "metadata.adi.${adi}.token";}
#    case "V1.1"    { $adiFile = "TEMPLATE.adi.${adi}.${type}.xml"; 
#                     $tokenFile = "metadata.adi.${adi}.token";}
#    else          { print "invalid adi type [$adi]\n"; exit; }
#}

unless(( $propertiesHash{adi} =~ /V3.0/) ||  ( $propertiesHash{adi} =~ /V1.1/)) { die "ERROR: Invalid ADI type: [$propertiesHash{adi}]\n"; }

if(defined($template)) { $propertiesHash{template} = "$template"; }
unless (defined($propertiesHash{template})) {
    $propertiesHash{template} = "TEMPLATE.adi.$propertiesHash{adi}.$propertiesHash{type}.xml"; 
} 

if(defined($token)) { $propertiesHash{token} = "$token"; }
unless (defined($propertiesHash{token})) {
    $propertiesHash{token} = "metadata.adi.token"; 
} 



unless( -f "$propertiesHash{content}/movie.ts" )   { die "ERROR: No content movie file found: [$propertiesHash{content}/movie.ts] $!\n"; }

unless( -f "$propertiesHash{meta}/$propertiesHash{template}" )   { die "ERROR: No metadata file found: [$propertiesHash{meta}/$propertiesHash{template}] $!\n"; }
unless( -f "$propertiesHash{meta}/$propertiesHash{token}" ) { die "ERROR: No token file found: [$propertiesHash{meta}/$propertiesHash{token}] $!\n"; }

print "\n\nusing input parameters:\n\n";

# scripts not in properties hash, used to locate properties file.
print "\tadi       [$propertiesHash{adi}]\n";
print "\tscripts   [$scripts]\n";
print "\tmeta      [$propertiesHash{meta}]\n";
print "\ttemplate  [$propertiesHash{template}]\n";
print "\ttoken     [$propertiesHash{token}]\n";
print "\tcontent   [$propertiesHash{content}]\n";
print "\tout       [$propertiesHash{out}]\n";
print "\ttmp       [$propertiesHash{tmp}]\n";
print "\ttools     [$propertiesHash{tools}]\n";
print "\tlimit     [$propertiesHash{limit}]\n";
print "\tingest    [$propertiesHash{ingest}]\n";
print "\ingestfiles[$propertiesHash{ingestfiles}]\n";
print "\tingestdir [$propertiesHash{ingestdir}]\n";
print "\ttype      [$propertiesHash{type}]\n";
print "\tidnumber  [$propertiesHash{idnumber}]\n";
# id/seed not in properties hash.
print "\tid        [$id]\n";
print "\tseed      [$seed]\n";


print "\tprovider  [$propertiesHash{providerid}]\n";
print "\tproduct   [$propertiesHash{product}]\n";
print "\n\n";

unless( -d "$propertiesHash{out}" ) { system("mkdir -p $propertiesHash{out}"); }
unless( -d "$propertiesHash{tmp}" ) { system("mkdir -p $propertiesHash{tmp}"); }

if( -d "$propertiesHash{tmp}" ) { system("rm $propertiesHash{tmp}/* > /dev/null 2>&1"); }

#########################################################################
# Perform some advanced TOKEN pre-substitution BEFORE we call subToken.pl
#########################################################################

# Make a copy of our ADI TOKEN file, so we can modify checksums/filesizes to match current content if required.
system("cp -v $propertiesHash{meta}/$propertiesHash{token} $propertiesHash{tmp}/$propertiesHash{token}");
print "Token path = $propertiesHash{meta}/$propertiesHash{token}";
if ($propertiesHash{checksum} =~ /no/i){
  getCheckSum("$propertiesHash{tmp}/$propertiesHash{token}");
}

# Lets update the standard TOKEN ID to be a bit more meaningful.
print "Setting ID: $id\n";
setId("$propertiesHash{tmp}/$propertiesHash{token}",$id);

# Update provideId and provider Name, if specified as input parameter.
unless( $propertiesHash{providerid} =~ /NONE/ ) {
    setProvider("$propertiesHash{tmp}/$propertiesHash{token}",$propertiesHash{providerid});
}

# Update product, if specified as input parameter.
unless( $propertiesHash{product} =~ /NONE/ ) {
    setProduct("$propertiesHash{tmp}/$propertiesHash{token}",$propertiesHash{product});
}

#########################################################################
# Completed advanced TOKEN pre-substitution 
#########################################################################

    # Update @BATCH-ID@, if specified as an input parameter.
    unless( $propertiesHash{idnumber} =~ /NONE/ ) {
	print "sed -i \'s/^\@BATCH-ID\@=.*/\@BATCH-ID\@=$propertiesHash{idnumber}/\' $propertiesHash{tmp}/$propertiesHash{token}";
	$cmd = "sed -i \'s/^\@BATCH-ID\@=.*/\@BATCH-ID\@=$propertiesHash{idnumber}/\' $propertiesHash{tmp}/$propertiesHash{token}";
    system("$cmd");	
	}    

	my $fileId = "";
for($count = $seed; $count < $seed + $propertiesHash{limit}; $count++ ) {
  $paddedCount = sprintf("%0${numberDigits}d" , $count);
  print "Padded count: $paddedCount\n";
  #$fileId = ${id}-${paddedCount};
  $fileId = ${id};
  system("$propertiesHash{tools}/subToken.pl -aline='$propertiesHash{meta}/$propertiesHash{template},$propertiesHash{tmp}/ADI.XML' -token=$propertiesHash{tmp}/$propertiesHash{token} -seed=${count}");
 print "Template Folder: $propertiesHash{meta}/$propertiesHash{template}, destination = $propertiesHash{tmp}/ADI.XML, token=$propertiesHash{tmp}/$propertiesHash{token}\n";
  system("cp -v $propertiesHash{content}/movie.ts $propertiesHash{tmp}/${fileId}_movie.ts");
  system("cp -v $propertiesHash{content}/preview.ts $propertiesHash{tmp}/${fileId}_preview.ts");
  system("cp -v $propertiesHash{content}/boxcover.jpg $propertiesHash{tmp}/${fileId}_boxcover.jpg");
  system("cp -v $propertiesHash{content}/poster.jpg $propertiesHash{tmp}/${fileId}_poster.jpg");
  if($propertiesHash{type} =~ /encrypt/i) { system("cp -v $propertiesHash{content}/encrypted.ts $propertiesHash{tmp}/${fileId}_encrypted.ts"); }
  
  if(-f "$propertiesHash{content}/ADI.DTD") { system("cp -v $propertiesHash{content}/ADI.DTD $propertiesHash{tmp}/ADI.DTD"); }

  system("chown nobody:nobody $propertiesHash{tmp}/*");
  
  # No need for the distinct token file, don't want it in our ADI package.
  system(" rm -v $propertiesHash{tmp}/$propertiesHash{token}");
  
  unless($propertiesHash{ingestfiles} =~ /yes/i)
  {
      print "cd $propertiesHash{tmp}; tar -cvf ${fileId}.tar *; mv *.tar $propertiesHash{out}/\n";
      system("cd $propertiesHash{tmp}; tar -cvf ${fileId}.tar *; mv *.tar $propertiesHash{out}/");	  
	  print "Displaying TAR file contents: \n";
	  system("tar -tvf ${fileId}.tar");
	  print "\n";
  }
  
  $packageId = getPackageId("$propertiesHash{tmp}/ADI.XML", "${fileId}");
  if($propertiesHash{unpack} =~ /yes/i) {
      system("mkdir $propertiesHash{out}/$packageId");
      system("cp -v $propertiesHash{tmp}/* $propertiesHash{out}/$packageId");
      system("chown -R nobody:nobody $propertiesHash{out}");
  }
  
  system("rm -v $propertiesHash{tmp}/*");
  
  if($propertiesHash{ingest} =~ /yes/i) { 
	if($propertiesHash{ingestfiles} =~ /yes/i) {#ingest files
	  print "\n\nThe files [$propertiesHash{out}/$packageId/*] will now be moved into the watchfolder and ingested...\n\n";
      system("mv $propertiesHash{out}/$packageId/* $propertiesHash{ingestdir}/; chown nobody:nobody $propertiesHash{ingestdir}/*");
	  system("rm -rf $propertiesHash{out}/$packageId");
      print "\n";
	} else {#ingest package
	  system("ls -la /opt/tandbergtv/cms/scripts/tools/loadTestPkgs/");
      print "\n\nThe new package [$propertiesHash{out}/${fileId}.tar] will now be moved into the watchfolder and ingested...\n\n";
      system("mv $propertiesHash{out}/${fileId}.tar $propertiesHash{ingestdir}/; chown nobody:nobody $propertiesHash{ingestdir}/${fileId}.tar");
      print "\n";
	}
  }
}

# Store the last value of count, use it for our default seed next time.
unless( -d "$propertiesHash{meta}/seed" ) { system("mkdir $propertiesHash{meta}/seed"); } 
system("echo $count > $propertiesHash{meta}/seed/adigen.${seedId}.seed");

system("rmdir -v $propertiesHash{tmp}");

unless($propertiesHash{ingestfiles} =~ /yes/i)
{
	print "\n";
	print "created package: $packageId\n";
	print "package location:        $propertiesHash{out}/${id}.tar\n";
}

if($propertiesHash{unpack} =~ /yes/) { 
print "files location:        $propertiesHash{out}/$packageId\n"; }
print "\n";
print "example export command:\n\n";
print " > pbexport $packageId $propertiesHash{out}/$packageId\n";
print "\n";

exit;

###################################################################################
#  subroutine: setId                                               
###################################################################################

sub setId {
    my $file = $_[0];
    my $id   = $_[1];
    
    # @NAME-TOKEN@=CID01-@COUNT_NNNN@
    $cmd = "sed -i \'s/^\@NAME-TOKEN\@=.*/\@NAME-TOKEN\@=${id}\/\' $file";
    system("$cmd");

}

###################################################################################
#  subroutine: setProvider                                               
###################################################################################

sub setProvider {
    my $file     = $_[0];
    my $provider = $_[1];
    
    # @PROVIDER-ID@
    # @PROVIDER-NAME@
    #
    $cmd = "sed -i \'s/^\@PROVIDER-NAME\@=.*/\@PROVIDER-NAME\@=${provider}/\' $file";
    system("$cmd");
    $cmd = "sed -i \'s/^\@PROVIDER-ID\@=.*/\@PROVIDER-ID\@=${provider}/\' $file";
    system("$cmd");
}

###################################################################################
#  subroutine: setProduct                                               
###################################################################################

sub setProduct {
    my $file     = $_[0];
    my $product  = $_[1];
    
    # @PRODUCT-TYPE@
    $cmd = "sed -i \'s/^\@PRODUCT-TYPE\@=.*/\@PRODUCT-TYPE\@=${product}/\' $file";
    system("$cmd");
}

###################################################################################
#  subroutine: getCheckSum                                                
###################################################################################

sub getCheckSum {
    my $file = $_[0];
    
    #$hashValue = $$tokenHashRef{$key};
    my %contentHash;
    my @tmp;
    my $cmd;
    
     if ( -f "$propertiesHash{content}/movie.ts" ) { 
         $checkSum = `md5sum $propertiesHash{content}/movie.ts`;
         chomp($checkSum);
         @tmp = split(" ",$checkSum);
         $contentHash{movieClearCheckSum} = $tmp[0];
         $contentHash{movieClearSize} = stat("$propertiesHash{content}/movie.ts")->size;
         # for reference, both work!
         # $x = ( -s "$propertiesHash{content}/movie.ts" ); # print "SIZE: $x\n";
         # print "movieClear - sum: [$contentHash{movieClearCheckSum}] size: [$contentHash{movieClearSize}]\n";
     }
     if ( -f "$propertiesHash{content}/preview.ts" ) { 
         $checkSum = `md5sum $propertiesHash{content}/preview.ts`;
         chomp($checkSum);
         @tmp = split(" ",$checkSum);
         $contentHash{previewClearCheckSum} = $tmp[0];
         $contentHash{previewClearSize} = stat("$propertiesHash{content}/preview.ts")->size;
     }
     if ( -f "$propertiesHash{content}/boxcover.jpg" ) { 
         $checkSum = `md5sum $propertiesHash{content}/boxcover.jpg`;
         chomp($checkSum);
         @tmp = split(" ",$checkSum);
         $contentHash{boxcoverCheckSum} = $tmp[0];
         $contentHash{boxcoverSize} = stat("$propertiesHash{content}/boxcover.jpg")->size;
     }
     if ( -f "$propertiesHash{content}/poster.jpg" ) { 
         $checkSum = `md5sum $propertiesHash{content}/poster.jpg`;
         chomp($checkSum);
         @tmp = split(" ",$checkSum);
         $contentHash{posterCheckSum} = $tmp[0];
         $contentHash{posterSize} = stat("$propertiesHash{content}/poster.jpg")->size;
     }
     if ( -f "$propertiesHash{content}/encrypted.ts" ) { 
         $checkSum = `md5sum $propertiesHash{content}/encrypted.ts`;
         chomp($checkSum);
         @tmp = split(" ",$checkSum);
         $contentHash{movieEncryptedCheckSum} = $tmp[0];
         $contentHash{movieEncryptedSize} = stat("$propertiesHash{content}/encrypted.ts")->size;
     }
     $cmd = "sed -i \'s/^\@MOVIE-CONTENT-CHECKSUM-TOKEN\@=.*/\@MOVIE-CONTENT-CHECKSUM-TOKEN\@=$contentHash{movieClearCheckSum}/\' $file";
     system("$cmd");
     
     $cmd = "sed -i \'s/^\@MOVIE-CONTENT-FILE-SIZE-TOKEN\@=.*/\@MOVIE-CONTENT-FILE-SIZE-TOKEN\@=$contentHash{movieClearSize}/\' $file";
     system("$cmd");
     
     $cmd = "sed -i \'s/^\@PREVIEW-CONTENT-CHECKSUM-TOKEN\@=.*/\@PREVIEW-CONTENT-CHECKSUM-TOKEN\@=$contentHash{previewClearCheckSum}/\' $file";
     system("$cmd");
     
     $cmd = "sed -i \'s/^\@PREVIEW-CONTENT-FILE-SIZE-TOKEN\@=.*/\@PREVIEW-CONTENT-FILE-SIZE-TOKEN\@=$contentHash{previewClearSize}/\' $file";
     system("$cmd");
     
     $cmd = "sed -i \'s/^\@BOXCOVER-CONTENT-CHECKSUM-TOKEN\@=.*/\@BOXCOVER-CONTENT-CHECKSUM-TOKEN\@=$contentHash{boxcoverCheckSum}/\' $file";
     system("$cmd");
     
     $cmd = "sed -i \'s/^\@BOXCOVER-CONTENT-FILE-SIZE-TOKEN\@=.*/\@BOXCOVER-CONTENT-FILE-SIZE-TOKEN\@=$contentHash{boxcoverSize}/\' $file";
     system("$cmd");

     $cmd = "sed -i \'s/^\@POSTER-CONTENT-CHECKSUM-TOKEN\@=.*/\@POSTER-CONTENT-CHECKSUM-TOKEN\@=$contentHash{posterCheckSum}/\' $file";
     system("$cmd");
     
     $cmd = "sed -i \'s/^\@POSTER-CONTENT-FILE-SIZE-TOKEN\@=.*/\@POSTER-CONTENT-FILE-SIZE-TOKEN\@=$contentHash{posterSize}/\' $file";
     system("$cmd");
     
     $cmd = "sed -i \'s/^\@MOVIE-CONTENT-ENCRYPTED-CHECKSUM-TOKEN\@=.*/\@MOVIE-CONTENT-ENCRYPTED-CHECKSUM-TOKEN\@=$contentHash{movieEncryptedCheckSum}/\' $file";
     system("$cmd");
     
     $cmd = "sed -i \'s/^\@MOVIE-CONTENT-ENCRYPTED-FILE-SIZE-TOKEN\@=.*/\@MOVIE-CONTENT-ENCRYPTED-FILE-SIZE-TOKEN\@=$contentHash{movieEncryptedSize}/\' $file";
     system("$cmd");

     $cmd = "sed -i \'s/^\@MOVIE-CODING-PROFILE\@=.*/\@MOVIE-CODING-PROFILE\@=MPEG2TS_SD_ZE/\' $file";
     system("$cmd");

     $cmd = "sed -i \'s/^\@PREVIEW-CODING-PROFILE\@=.*/\@PREVIEW-CODING-PROFILE\@=MPEG2TS_SD_ZE/\' $file";
     system("$cmd");
	     
}

###################################################################################
#  subroutine: getCheckSum                                                
###################################################################################

sub getPackageId {
    my $file = $_[0];
    my $id   = $_[1];
    my $line = "";
    my $cmd  = "";

    # <Title uriId="ericsson.com/Title/OFFP0007010000000004"
    # <AMS Asset_Class="package" Asset_ID="PACC0011010000000001"
    my $adiV101Pattern = "<AMS Asset_Class=\"package\" Asset_ID=\"(.*?)\"";
    my $adiV300Pattern = "<Title uriId=.*/.*/(.*?)\"";
    
    #$cmd = "egrep '$adiV101Pattern|$adiV300Pattern' $file > /dev/null";
    $cmd = "egrep '$adiV101Pattern|$adiV300Pattern' $file";
    
    $line = `$cmd`;
    print "\n\negrep: $line\n\n";
    
    if ($line =~ /$adiV101Pattern/) {
        # print "match ADI V1.1 - $line\n";
        ($id) = $line =~ /$adiV101Pattern/;
    }
    if ($line =~ /$adiV300Pattern/ ) {
        # print "match ADI V3.0 - $line\n";
        ($id) = $line =~ /$adiV300Pattern/;
    }   
    # print "ID VALUE: $id\n";
    return $id;
}   
#            if (/Name=\"Content_FileSize\" Value=\"(.*?)\"/) {
#            ($contentFileSize) = /Name=\"Content_FileSize\" Value=\"(.*?)\"/;
#            print "contenttFileSize: $contentFileSize\n";
            
###################################################################################
#  subroutine: returnPropertyHash                                                 
###################################################################################

sub returnPropertyHash  {
    my $propertiesInputFile = $_[0];
    my $propertiesHashRef   = $_[1];
    
    
    my ($line) = "";
    my @propArray = ();
    
    
    unless (open (FILE2, "<$propertiesInputFile"))   { die "cannot open properties file $propertiesInputFile $!"; }
    @propArray   = <FILE2>;
    close FILE2;
    
    foreach $line (@propArray) {
        chomp ($line);
        if ( $line =~ /^#/ )    { next; }
        if ( $line =~ /^\s*$/ ) { next; }
        if ( $line =~ /^.*=/i ) {
             ( $propertyName, $propertyValue ) = $line =~ /^(.*)\s*=\s*(.*)$/; 
             $$propertiesHashRef{$propertyName} = $propertyValue;
        }
    }
    return;
}

###################################################################################
#  subroutine: help
###################################################################################

sub help {

$helpText1 = <<ENDHELPPAGE1;

  This wrapper script that calls the TOKEN substitution script 'subToken.pl' to generate test ADI packages.
  
  When installed as part of the module 'scac-tools' all default values should be valid, however, alternative locations of:
  
         ADI templates
         ADI tokens
         content files
         
  Can be used to override the defaults.
  
  By default, the following directory needs to be filled with appropriate content.
  The following file names MUST be used: 
  
         ls -1 /opt/tandbergtv/cms/scripts/adi/content
         boxcover.jpg
         encrypted.ts
         movie.ts
         preview.ts

  The script will automatically calculate the correct file sizes and MD5 checksums for the provided content.
  
  The same content files will then be copied and renamed for each package.
  
  An alternative content location can be specified using '-content=/nnn'
  
  Usage:
  
     > perl generateAdi.pl
     
  By default, this will create ONE unencrypted package with a base ID of '1' in the sub directory 'aditar'.
  If the subdirectory 'tar' does not exist it will be created.
  
     # tar -tvf aditar/CID01-0001.tar
     -rw-r--r-- nobody/nobody 15406 2011-11-23 11:45:46 ADI.XML
     -rw-r--r-- nobody/nobody     0 2011-11-23 11:45:46 CID01-0001_boxcover.jpg
     -rw-r--r-- nobody/nobody     0 2011-11-23 11:45:46 CID01-0001_movie.ts
     -rw-r--r-- nobody/nobody     0 2011-11-23 11:45:46 CID01-0001_preview.ts

  For more information on ADI generation see:
  
     > perl subToken.pl -help
     
  Function:

  Wrapper script that calls the TOKEN substitution script 'subToken.pl' to generate test ADI packages.

      -help       : print this help text.

      -idnumber   : value [nn]
                    The two digit number part of the ID. 
                    Typically this will always be '01' but can be selected if required.
                    Useful to identify specific sets of test data.
                    Example:
                        -idnumber=09 -type=clear  -> CID09
                    
                    default [01]
                    
      -limit      : value [integer]
                    The number of packages to generate.
                    default [1]
                    
      -type       : value [clear|encrypt]
                    Generate encrypted test content or clear, unencrypted content.
                    Note: NO ENCRYPTION is performed, will simply use whatever files are provided in 'content'
                    with the encryption section of the ADI.XML
                    default [clear]

      -out        : Directory where the output tar files will be created.
                    By default a 'tar' sundirectory is created in the current directory.
                    default [aditar]

      -adi        : value [V3.0|V1.1]
                    ADI specification to generate.
                    default [V3.0]                    

      -metaroot   : Default path where the meta data directory is located.
                    default [/opt/tandbergtv/cms/scripts/adi/meta]
                   
      -contentroot: Default path where the example test content directory is located.
                    default [/opt/tandbergtv/cms/scripts/adi/content]

      -meta       : Directory where the ADI templates and token files are located.
                    default [default]
                    
                    So the default location will be [metaroot/meta]:
                    /opt/tandbergtv/cms/scripts/adi/meta/default
                    
                    For ease of use 'meta' can contain the full path (if matches ^/) 
                    in which case 'metaroot' is ignored.
                   
      -content    : Directory where the example test content is located.
                    default [default]                    

                    So the default location will be [contentroot/content]:
                    /opt/tandbergtv/cms/scripts/adi/content/default
                    
                    For ease of use 'content' can contain the full path (if matches ^/)
                    in which case 'contentroot' is ignored.
                    
      -tools      : Directory where dependent scripts 'subToken.pl' are located.
                    default [/opt/tandbergtv/cms/scripts/tools]

      -template   : Template file used to generate 'ADI.XML'.
                    default [TEMPLATE.adi.\${adi}.\${type}.xml]

      -token      : Token file used to generate 'ADI.XML'.
                    default [metadata.adi.\${adi}.token]                    

      -name       : A meaningful name that will be added to the generated ID to identify the package.
                    For example:
                       -name=burlesque -seed=52 -type=clear  
                    generates:
                        CID01-burlesque-0052.tar
                        CID01-burlesque-0052_boxcover.jpg
                        CID01-burlesque-0052_movie.ts
                        CID01-burlesque-0052_preview.ts
                    default [NONE]   

      -ingestfiles: value   [yes|no]
                    Automatically move the files (ADI + content) into the watchfolder for instant ingestion?
                    default [no]
                    
      -ingest     : value   [yes|no]
                    Automatically move the new package/files into the watchfolder for instant ingestion?
                    default [no] 

      -ingestdir  : value   [/path]
                    Directory path of the CMS watchfolder.
                    default [/content/ingest/telenor]  
                    
      -seed       : value [integer]
                    The starting value of all package identifiers.
                    default - 
                        The script looks for the file:
                            \${meta}/adigen.ID.seed
                        If the file exists: 
                            The value is read from the file and incremented. 
                            The updated value is written back to the file.
                            This enables unique ID's to be generated per unique ID each time the script is run.
                        Else
                            default [1]
                            
      -provider   : value   [PROVIDER-ID]
                    Quick way to change 'provider Id', over writes value in the token file.
                    'provider name' will also be set to equal 'provider Id'.
                    Example values "sf-anytime.com, sony.com, ericsson.com"
                    
                    NONE - No action is taken, value from token mapping file will be used.
                    default [NONE]     

      -product    : value   [PRODUCT-TYPE]
                    Quick way to change 'product type', over writes value in the token file.
                    Example values "MOD, SVOD"
                    
                    NONE - No action is taken, value from token mapping file will be used.
                    default [NONE] 

      -unpack     : value   [yes|no]
                    Create a directory containing the unpacked files in addition to the tar file.
                    The directory name will be based on the package ID.
					Automatically set to "yes" when ingestfiles = yes.
                    default [no]     
                    
ENDHELPPAGE1

$continueMsg = "Enter any key to continue\n";

print $helpText1, $continueMsg;
$continue=<STDIN>; $continue = $continue;
}

# END
