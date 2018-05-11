#!/usr/bin/perl -sw
$version = "1.17";

# Created by Steve Voisey (srv) 21 Jan 2011.
# email: [steve.voisey@ericsson.com]

# ./subToken.pl -check=yes -file=configFiles.list
# ./subToken.pl -update=no -file=configFiles.list -token=ukdev2.token.list
# ./subToken.pl -file=file.list -token=adi.token.list

# cat ../fullFileList.output | grep -v "#" | grep -v '^$'

use File::Copy;
use File::Basename;
use File::Find;
use Time::Local;
use Cwd;
  
($tmpsecond,$tmpminute,$tmphour,$tmpmday,$tmpmon,$tmpyear,$tmp,$tmp,$tmp) = localtime(time());

my $YEAR   = 1900 + $tmpyear;
my $MONTH  = sprintf("%02d" , $tmpmon + 1);
my $DAY    = sprintf("%02d" , $tmpmday);
my $HOUR   = sprintf("%02d" , $tmphour);
my $MINUTE = sprintf("%02d" , $tmpminute);
my $SECOND = sprintf("%02d" , $tmpsecond);

my $SEPERATOR = ",";
my @ERRORS    = ();
my $DIVIDE    = "=" x 80 . "\n";

my @GlobalFileList = ();
my @fileList = ();
my @originalFileList = ();
my $currentDirectory = cwd();
 
#print "Current Directory: [$currentDirectory]\n"; exit;

$GlobalIgnoreFileType = "ORIGINAL.*\$|\.tar|\.tgz|\.zip|\.lic|\.java|\.rpmsave|\.rpmorig|\.jpg|\.mpg|\.mpeg|\.ts|\.bmp|\.gif|\.jar";
#$GlobalIgnoreFileType = "ORIGINAL-TOKEN\$";

if (defined($help))    { help(); exit;}
if (defined($history)) { history(); exit;}



my @defaultConfigDir = ("/opt/tandbergtv/cms/workflow/conf/", 
                        "/opt/tandbergtv/cms/conf/",
                        "/opt/tandbergtv/cms/report/local",
                        "/opt/tandbergtv/cms/report/jreport",
                        "/opt/tandbergtv/watchpoint/tomcat/webapps/filemanager/WEB-INF/classes/com/tandbergtv/workflow/webservice/filesubsystem",
                        "/opt/tandbergtv/cms/workflow/plugins/subsystems/ADI 1.1 (ISA)/resources/adaptor");

# Filter out unwanted matches.
#my $tokenRegExp = "\@.*\@";
#my $tokenRegExp = "\@[A-Za-z0-9_\-][A-Za-z0-9_\-]*\@";
my $tokenRegExp = "\@[-A-Za-z0-9]+?\@";
                  

my $configFileIn;
my $configFileOut;
my $line;
my $match = 0;
my @tmp = ();
my %trackTokenHash;
my %trackFileHash;


$divide = "=" x 80 . "\n";
# Keep track of files processed, used to generate a counter token, default start at zero.
$fileCounter = 0;
# But let user over ride.
if (defined($seed)) { $fileCounter = ($seed -1); }

# Just display the original , saved, tokens.
unless (defined($original)) { $original = "no"; }

unless (defined($restore)) { $restore = "no"; }

unless (defined($makedir)) { $makedir = "yes"; }

# Just display but not actually change any files, or go for it?
unless (defined($update)) { $update = "yes"; }
# Create a copy of the original before updating?
unless (defined($backup)) { $backup = "yes"; }

unless (defined($check))  { $check = "no"; }

unless (defined($doc))    { $doc = "no"; }

unless (defined($regexp)) { $regexp = "no"; }

if ($doc   =~ /^y/i) { $check  = "yes"; }
if ($check =~ /^y/i) { $update = "no"; }

# unless (defined($dir))    { $dir = $defaultConfigDirectory; }

# Need a list of files to process.
if (defined($aline)) {
    print "Input is a single line:\n";
    $fileList[0] = $aline;
} else {

    # Need a list of files to process.
    unless (defined($file)) {
        print "Must specify an input list file, usage:\n";
        print " >./subToken.pl -file=configFiles.list -token=token.list\n";
        print "For help enter:\n";
        print "    >./subToken.pl -help\n";
        exit;
    }

    if ($file =~ /^all$/i) {
        foreach $dir (@defaultConfigDir) {
            find (\&search, $dir);
            # Concatenate two arrays in perl
            @fileList = (@fileList, @GlobalFileList);
            @GlobalFileList = ();
            #@fileList = (@GlobalFileList);
        }
        unless ($doc   =~ /^y/i) {
            #debug only - bit messy printing out full file list each time. parameterise in future?
            #print "List of all files found that will be searched for TOKENS.\n";
            #foreach $dir (sort(@fileList)) { print "$dir\n"; }
        }
    }

    unless ($file =~ /^all$/i) {
        # If file does NOT start with a full directory path, append the current directory.
        # Makes it easier to customize from the command line.
        unless( $file =~ /^\// ) { $file = $currentDirectory . "/" . $file; }

        unless (-f $file)       { die "invalid input parameter [file] Error accessing [$file]"; }
        unless (open (FILE, "<$file"))   { die "cannot open $file $!"; }
        @fileList   = <FILE>;
        close FILE;
    }

    @fileList = removeComments(\@fileList);
}
#foreach $thing  (@fileList) { print "$thing\n"; }

# restore any previous , saved , properties files with original tokens.
if ($restore =~ /yes/i){
    restoreTokens(\@fileList);
    exit;
}

if ($check =~ /yes/i){
    if ($original =~ /yes/i){
        foreach $item  (@fileList) {
            push(@originalFileList, "${item}.ORIGINAL-TOKEN");
        }
    
        $match = checkListForTokens(\@originalFileList,$tokenRegExp,"WARNING",$doc);
    } else {
        $match = checkListForTokens(\@fileList,$tokenRegExp,"WARNING",$doc);
    }
    if ($match > 0) {
        unless ($doc   =~ /^y/i) {
            printTokenToDefine();
        }
    } else {
        print "No unresolved tokens.\n";
    }
    exit;
}

# For anything other than check, we need a token mapping file.
unless (defined($token)) {
    print "Must specify an input token file, usage:\n";
    print "    >./subToken.pl -file=configFiles.list -token=token.list\n";
    print "For help enter:\n";
    print "    >./subToken.pl -help\n";
    exit;
}

unless( $token =~ /^\// ) { $token = $currentDirectory . "/" . $token; }
unless (-f $token)      { die "invalid input parameter [token] Error accessing [$token]"; }
unless (open (TOKEN, "<$token"))  { die "cannot open $token $!"; }
@tokenArray = <TOKEN>;
close TOKEN;

my %tokenHash  = buildConfigHash(\@tokenArray, $regexp);

# using unix system calls, could have done something like this:
#
# $status = system("grep $key $configFile");
# system("sed -i s/$key/$tokenHash{$key}/g $configFile");

processFiles(\@fileList, \%tokenHash, $update, $backup, $regexp);

if ( @ERRORS > 0 ) { 
    print "\nIMPORTANT: The following ERRORS and WARNINGS were generated! Please investigate\n\n"; 
    foreach $element (@ERRORS) {
        print " --> $element\n";
    }
    print "\n\n";
}
exit;

sub restoreTokens {
    my $fileArrayRef = $_[0];
    my $file = "";
    
    foreach $file (@$fileArrayRef) {
        if ( -f "${file}.ORIGINAL-TOKEN" ) {
            print "\nrestoring: $file\n";
            # Need to quote file name as we have some nasty windows file names with spaces
            system("mv -v \"${file}.ORIGINAL-TOKEN\" \"${file}\"");
        }
    }
}

sub processFiles {
    my $fileArrayRef = $_[0];
    my $tokenHashRef = $_[1];
    my $update       = $_[2];
    my $backup       = $_[3];
    my $regexp       = $_[4];
    
    my @fileArray    = ();
    #my $token        = "";
    #my $lineCount    = 0;
    my $line         = "";
    my $configFileIn = "";
    my $localBackup  = "";

    #print "\n\n+++++++++++ processFiles +++++++++++++\n";
    foreach $line (@$fileArrayRef) {
        $localBackup = $backup;

        chomp($line);
        #print "line: $line\n";

        @tmp = split ("$SEPERATOR", $line, 2);
        $configFileIn = $tmp[0];
        # Do we have both an input and output file? If not, update the input file, if yes, no need to backup.
        if (@tmp > 1) { $configFileOut = $tmp[1]; $localBackup = "no"; } else { $configFileOut = $tmp[0] };
        # Check it exists.
        unless ( -f $configFileIn ) { 
            print "\n$divide  WARNING: File not exist ( RPM may not currently be installed ).\n"; 
            print "  $configFileIn\n";
            next; 
        }
        # should we expect the parent directory to exists, or create it?
        $directory = dirname($configFileOut);
        if ( $makedir =~ /yes/i ){
            unless ( -d $directory) {   
                print "\n$divide INFO: Directory not exist: $directory\n";  
                print " INFO: Creating  directory: $directory\n";
                system("mkdir -p $directory");            
            }
        } else {
            # makedir not yes, so check for output directory. File might not exist yet, but the parent directory MUST!
            unless ( -d $directory) { print "\n$divide ERROR: Directory not exist: $configFileOut\n"; next; };        
        }
        #checkForTokens($configFileIn, $tokenRegExp);
        open (CONFIG, "<$configFileIn");
        @fileArray = <CONFIG>;
        close CONFIG;
        # Lets get an updated file!
        @newFile = updateFile(\@fileArray, $tokenHashRef, $configFileIn, $localBackup, $regexp);
        #print "file: $configFileOut   - return value: " . @newFile . " \n";
        if (($update =~ /yes/i ) && ( @newFile > 0 )) {
            unless (open (CONFIG, ">$configFileOut"))  { die "cannot open $configFileOut $!"; }
            print "\n  updating: --> $configFileOut\n";
            print CONFIG  @newFile;
            close CONFIG;
        }
    }
}


sub updateFile {
    my $fileArrayRef = $_[0];
    my $tokenHashRef = $_[1];
    my $fileName     = $_[2];
    my $backup       = $_[3];
    my $regexp       = $_[4];

    my $line  = "";
    my $lineCount = 0;
    my $key   = "";
    my $hashValue = "";
    my $firstMatch = 1;
    my $updated    = 0;
    my @newFile    = ();
    my @tokenKeys = sort(keys(%$tokenHashRef));
    my $hashToken = "";
    my $tmpMsg = "";
    
    # Keep track of the number of files processed.
    $fileCounter++;
    # Process each line in the file.
    foreach $line (@$fileArrayRef) {
        $lineCount++;
        # For each line, search for a key/TOKEN
        foreach $key (@tokenKeys) {
            # print "$key -> $$tokenHashRef{$key}\n";
            $hashValue = $$tokenHashRef{$key};
            # New functionality to support regular expressions.
            # Instead of a typical token, the following type of regular expression can be used:
            #     /^( start )(middle)( end )$/=fred
            # Which will generate -> start fred end
            # srv 26mar12 - Now only check for a reg expression if EXPLICITLY requested!
            if (( $regexp =~ /yes/i ) && ($key =~ /^\/\(.*\)\(.*\)\(.*\)\/$/)) {
                # We have a regular expression special case!
                $regularExpression = 1;
                # print "regexp line [$lineCount] [$key]\n";
                # Lets strip of the surrounding // makes it easier to process.
                $regexpKey = $key;
                $regexpKey =~ s/^\///;
                $regexpKey =~ s/\/$//;
            } else {
                $regularExpression = 0;
                $regexpKey = $key;
            }
            if ($line =~ /$regexpKey/) {
                $updated = 1;
                if ($firstMatch) {
                    # Only want to backup once, so if its the first match found in the file...
                    unless ($backup =~ /no/i) {
                        print "\n${divide}copying:\n from: $fileName\n to:   $fileName.ORIGINAL-TOKEN\n";
                        copy($fileName, "$fileName.ORIGINAL-TOKEN") or die "File cannot be copied.";
                    }
                    print "\nprocessing:\n  $fileName\n\n";
                    $firstMatch = 0;
                }
                # As the hash value could also have a COUNT token, easier to load into a variable.
                # A COUNT token lets us generate unique ID's, like an Asset ID in an ADI.XML file.
                if ($hashValue =~ /\@COUNT_N.*\@/) {
                    # How many "N's" in the COUNT token? Use this to pad the replacement number.
                    ($digits) = $hashValue =~ /\@COUNT_(N.*)\@/;
                    $numberDigits = length($digits);
                    $value = sprintf("%0${numberDigits}d" , $fileCounter);
                    # If we have to truncate, lose most significant digit.
                    # we want 22, 23, 24 --> 2, 3, 4 and not 2, 2, 2
                    $value = substr($value, -${numberDigits});
                    $hashValue =~ s/\@COUNT_N.*\@/$value/g;
                }
                # Support automatically adding current date time for special tokens.
                if ($hashValue =~ /\@CURRENT_YEAR\@/)   { $hashValue =~ s/\@CURRENT_YEAR\@/$YEAR/g; }
                if ($hashValue =~ /\@CURRENT_MONTH\@/)  { $hashValue =~ s/\@CURRENT_MONTH\@/$MONTH/g; }
                if ($hashValue =~ /\@CURRENT_DAY\@/)    { $hashValue =~ s/\@CURRENT_DAY\@/$DAY/g; }
                if ($hashValue =~ /\@CURRENT_HOUR\@/)   { $hashValue =~ s/\@CURRENT_HOUR\@/$HOUR/g; }
                if ($hashValue =~ /\@CURRENT_MINUTE\@/) { $hashValue =~ s/\@CURRENT_MINUTE\@/$MINUTE/g; }
                if ($hashValue =~ /\@CURRENT_SECOND\@/) { $hashValue =~ s/\@CURRENT_SECOND\@/$SECOND/g; }

                # What about if a hash value contains a standard token?
                if ($hashValue =~ /.*\@.*\@.*/) {
                    # Lets try and resolve it.
                    ($hashToken) = $hashValue =~ /.*(\@.*\@).*/;
                    if (defined($$tokenHashRef{$hashToken})){
                        $hashValue =~ s/$hashToken/$$tokenHashRef{$hashToken}/g;
                    } else {
                        $tmpMsg = "WARNING: Undefined token on RIGHT side of assignment (L=R), potential configuration error!!!";
                        print "\n* $tmpMsg\n\n";
                        push(@ERRORS, "$tmpMsg");
                    }
                }
                
                # So sorted out our hashValue, now lets update the line.
                if ($regularExpression) {
                    $line =~ s/$regexpKey/${1}${hashValue}${3}/g;
                } else {
                    $line =~ s/$regexpKey/$hashValue/g;
                }
                # print "  line[$lineCount]\t$key \t\t--> $hashValue\n";
                # printf "\# %-30.30s | %45s\n", $token, $shortLine;
                if ($regularExpression) {
                    printf "  line[$lineCount] REGEXP\t /%-44s --> (PATTERN 1)$hashValue(PATTERN 3) --> $line\n", $regexpKey . "/";
                } else {
                    printf "  line[$lineCount]       \t %-45s --> $hashValue\n", $regexpKey;
                }
            }
        }
        push(@newFile, "$line");
    }
    if ($updated) { return(@newFile); } else { return; }
}

sub buildConfigHash {
  # Convert a list of "x=y" values, into a hash.
  my $symArrayRef = $_[0];
  my $regexp      = $_[1];
  
  my %configHash;
  my $element = "";
  my $hashValue = "";
  my $key = "";
  my @tmp = "";
  my $regularExpression = 0;
  
  # Need to clean up each entry, strip out comments, spaces...
  foreach $element (@$symArrayRef) {
    # Drop the comments.
    if ($element =~ /^#/) { next; }
    # Drop any blank lines.
    if ($element =~ /^\s*$/) { next; }
    # Get rid of any spaces.
    # Hmmm do we want to strip out spaces in this case?
    # $element =~ s/ //g;
    # Split into two fields 'x=y'

    # No longer rely on it just looking like a regular expression.
    # Check that regular expressions are enabled, and that the key matches something like:
    #  /(AAA)(BBB)(CCC)/=XXX
    if (( $regexp =~ /yes/i ) && ($element =~ /^\/\(.*\)\(.*\)\(.*\)\/=.*$/)) {
        $regularExpression = 1;
        # print "regexp\n";
    } else {
        $regularExpression = 0;
    }
       
    if($regularExpression)  {
        # 26/mar/12 Steve - Well that was a realy bad idea, allowing for a regexp with an '=' in the
        # data is great, until we hit a TOKEN definition like:
        #       @CMS-NET-IMAGER-LICENSE-02@=51a7/o2Q1J41kirW9Tg2rPp9XP8=
        # So if regular expressions are enabled, forget 'split' and search for the explicit pattern.
        # Lots of escapes here, using [] instead of (), what we are actually looking for is:
        #
        #          [key]=[hashValue] --> /^[(.*)(.*)(.*)]=[.*]$/
        #
        ($key, $hashValue) = $element =~ /^(\/\(.*\)\(.*\)\(.*\)\/)=(.*)$/;
        chomp($hashValue);
        # Just in case we have come from windows, make sure we don't have any <CR> at end of the TOKEN
        $hashValue =~ s/\r//g;
        
        # print "key: [$key]\n";
            # Build a hash db with our setting=value pair.
        if (defined($configHash{$key})) {
            print "\nWARNING: Duplicate setting for [ $key ]\n";
            print "FIRST VALUE: $configHash{$key}\n";
            print "REPLACED BY: $key\n\n";
        }
        $configHash{$key} = $hashValue;
    }
    
    unless($regularExpression) {   
        # Define as a separate statement, guess should use an else, but want to keep this 
        # as a separate code block for now to restore the original functionality.
        # Split into two fields 'x=y'
        # The FIRST encountered '=' will be treated as the field divider.
        @tmp = split ('=',$element,2);
        # Any undefined values, set to "" and print a warning.
        unless (defined($tmp[1])) { $tmp[1] = ""; print "\nWARNING: original: $tmp[0] No setting defined!!!\n\n" }
        chomp($tmp[1]);
        # Just in case we have come from windows, make sure we don't have any <CR> at end of the TOKEN
        $tmp[1] =~ s/\r//g;
        # Build a hash db with our setting=value pair.
        if (defined($configHash{$tmp[0]})) {
            print "\nWARNING: Duplicate setting for [ $tmp[0] ]\n";
            print "FIRST VALUE: $configHash{$tmp[0]}\n";
            print "REPLACED BY: $tmp[1]\n\n";
        }
        $configHash{$tmp[0]} = $tmp[1];        
    } 
  }
  return %configHash;
}

sub removeComments {
    my $fileArrayRef = $_[0];
    my @newArray;
    my $line;
    # print "\n\n+++++++++++ removeComments +++++++++++++\n";
    foreach $line (@$fileArrayRef) {
        chomp($line);
        # Just in case we have come from windows, make sure we don't have any <CR> left in the line.
        $line =~ s/\r//g;
        # Drop the comments.
        if ($line =~ /^#/) { next; }
        # Drop any blank lines.
        if ($line =~ /^\s*$/) { next; }
        # print "[$line]\n";
        push(@newArray,"$line");
    }
    return @newArray;
}

sub checkListForTokens {
    my $fileArrayRef = $_[0];
    my $tokenRegExp  = $_[1];
    my $message      = $_[2];
    my $doc          = $_[3];

    my @fileArray    = ();
    my $token        = "";
    my $lineCount    = 0;
    my $line         = "";
    my $file         = "";
    my $match        = 0;

    #print "\n\n+++++++++++ checkListForTokens +++++++++++++\n";
    foreach $line (@$fileArrayRef) {
        # print "line: [$line]\n\n";
        # Process each line in the file.
        chomp($line);
        @tmp = split ("$SEPERATOR", $line, 2);
        $file = $tmp[0];
        #unless ( -f $file ) { print "\n$divide  ERROR: File not exist: $file\n"; next; }
        $match = $match + checkForTokens($file, $tokenRegExp, $message, $doc);
    }
    return $match;
}


sub checkForTokens {
    my $file         = $_[0];
    my $tokenRegExp  = $_[1];
    my $message      = $_[2];
    my $doc          = $_[3];

    my @fileArray    = ();
    my $token        = "";
    my $lineCount    = 0;
    my $match        = 0;
    my $line         = "";
    my $printLine    = "";
    my $shortLine    = "";

    unless (defined($message)) { $message = "INFO"; }
    if ($message eq "")        { $message = "INFO"; }

    $commentDivide = "\#" x 80 . "\n" ;
    unless ( -f $file ) { 
        print "\n$divide  ERROR: File not exist! $file\n"; 
        return 0; 
    }
                    
    unless(defined($trackFileHash{"$file"})) {
        $trackFileHash{"$file"} = 1;
    } else {
        print "ERROR: File has already been checked! [ $file ]\n";
        return 0;
    }
    open (FILE, "<$file");
    @fileArray = <FILE>;
    close FILE;

    foreach $line (@fileArray) {
        chomp $line;
        # Drop the comments.
        if ($line =~ /^#/) { next; }
        # Drop any blank lines.
        if ($line =~ /^\s*$/) { next; }
        $printLine = $line;
        if ( length($printLine) > 159 ){
           $printLine = substr($line, 0, 160) . ".... LINE LENGTH EXCEEDED 160 - TRUNCATED!";
        }
        $shortLine = $printLine;
        $lineCount++;
        # For each line, search for a key/TOKEN
        if ($line =~ /$tokenRegExp/) {
           ($token) = ($line =~ /($tokenRegExp)/);
           
            unless ( $doc =~ /yes/i ) {
                print "\nfile: $file\n";
                # srv - driving me nuts, but when we have a very long line can not trim properly
                # with printf, so resorted to substr.
                # rules file is ALL on a single line!
                print "$message: line [$lineCount] token [$token] $printLine\n";
                #printf "$message: line [$lineCount] token [$token] %60s\n", $line;
                unless(defined($trackTokenHash{"$file>$token"})) {
                    $trackTokenHash{"$file>$token"} = 1;
                } else {
                    $trackTokenHash{"$file>$token"}++;
                }
            }
            if ( $doc =~ /yes/i ) {
                $shortLine =~ s/^\s+/ /g;
                if ($match == 0) { print "${file}\n\#usage:\n\#\n"; }
                # This screws up output, no idea why, will check later.
                # printf "\# %-30.30s | %-45s\n", $token, $shortLine;
                printf "\# %-30.30s | %45s\n", $token, $shortLine;
            }
            $match++;
        }
    }
    if (( $doc =~ /yes/i ) && ( $match > 0 )) { print "${commentDivide}\n"; }
    return $match;
}

sub printTokenToDefine {
    my $tokenCount = 0;
    my $keyCount   = 0;
    my $fileCount  = 0;
    my $dir        = "";
    my $file       = "";
    my %fileHash;
    my @tokenKeys  = ();
    
    @tokenKeys = sort(keys(%trackTokenHash));

    print "\nSummary\n${divide}Count  | file-token\n${divide}\n";
    foreach $key (@tokenKeys) {
        ($fileToken, $dir) = fileparse($key);
        @tmp = split('>',$key, 2);
            unless(defined($fileHash{$tmp[0]})) {
                $fileHash{$tmp[0]} = 1;
        }
        print                     "       |$dir\n";
        print "$trackTokenHash{$key}      |$fileToken\n";
        $tokenCount = $tokenCount + $trackTokenHash{$key};
    }
    @fileKeys = keys(%fileHash);
    $fileCount = @fileKeys;
    
    print "\nTotal [$tokenCount|$fileCount]\n\n";
    
    print "File List\n";
    print "=========\n";
    foreach $file (sort(@fileKeys)) { print "$file\n"; }
    
    
}

sub search
{
  unless(-d) {
      unless ( /$GlobalIgnoreFileType/ ) {
          push (@GlobalFileList,$File::Find::name);
          # Add filename to list if not a directory
      }
  }
}

sub help {

$helpText1 = <<ENDHELPPAGE1;

  Substitute a list of TOKENS for values in each file listed in the input file list.

  Usage:

     > ./subToken.pl -token=token -file=file

  Where:

     token.list
     ==========
    \@CMS-APP-SERVER-IP\@=10.1.3.120
    \@CMS-FTP-HOST-IP\@=10.1.3.121
     ...
    \@EXAMPLE-ONE-DIGIT-COUNT\@=Asset-Id0000\@COUNT_N\@
    \@EXAMPLE-FOUR-DIGIT-COUNT\@=Asset-Id0000\@COUNT_NNNN\@

     file.list
     =========
     /opt/tandbergtv/cms/workflow/conf/ta-workflows/pse-cms-ta-deliverToWebCDN.properties
     /opt/tandbergtv/cms/workflow/conf/scac-adapter-xtve/AC5.xml
     /opt/tandbergtv/cms/workflow/conf/scac-adapter-xtve/scac-adapter-xtve.properties
     /opt/tandbergtv/cms/workflow/conf/oldFile.dat${SEPERATOR}/opt/tandbergtv/cms/workflow/conf/newFile.dat

    Each occurance of a token in 'token.list' found in any of the files in 'file.list' will be replaced
    with the tokens value.

    If the line in 'file.list' contains a single file path ( the first three in the example above),
    the updates will be applied to the original file.

    If two file paths are specified, separated by a "$SEPERATOR", the first is used for input and remains unchanged.
    The new, updated file is written to the second file path.

    Why use "$SEPERATOR" as a column separator? With windows I can not trust spaces, and windows gets confused with ":|".

    Note:
    'token.list' - The value will typically be plain text, but support has been
    added for a special counter token.
    The following values will be replaced with an integer with the appropriate number of leading
    zeros ( any number of "N's" can be included):

        \@COUNT_N\@          -->   1
        \@COUNT_NN\@         -->  01
        \@COUNT_NNN\@        --> 001
        ....
    The integer will be incremented for each file in 'file.list'.

    This functionality can be used to generate unique ID's across multiple files.

    Not typically required for managing configuration files, but can be used for generating ADI.XML test data,
    for example, asset ID's:

        <AMS Asset_Class="title" Asset_ID="XPTL\@TOKEN-ASSET-ID\@"

    can be generated with:

        \@TOKEN-ASSET-ID\@=TEST0123\@COUNT_NNNNN\@
    
    Additional SPECIAL tokens supported:
    
      date/time
      The current date/time values can be generated with the following tokens:
      
        \@CURRENT_YEAR\@
        \@CURRENT_MONTH\@
        \@CURRENT_DAY\@
        \@CURRENT_HOUR\@
        \@CURRENT_MINUTE\@
        \@CURRENT_SECOND\@
        
    NEW REGULAR EXPRESSION SUPPORT!
    
    If you are feeling daring, support has been added for a specific regular expression syntax instead of a standard token.
    
    To use this functionality the entry in the token file MUST follow the following syntax.

    Note: surrounding the entry with // indicates that this is a special case.
    
        /^(start)(middle)(end)\$/=fred
        
    The value contained in 'middle' will be replaced with the value following the '=' , in this case 'fred'.
    
    Currently only three patterns can be remembered, and only the middle, second pattern will be replaced.
    
    So,  left of the '=' sign - standard regular expression that remembers three patterns.
        right of the '=' sign - the string to be used to replace ONLY the second pattern, patterns 1 and 3 are preserved.
    
    This syntax can be used to turn existing ADI.XML files into test content WITHOUT having to add new TOKENS.
    
    For example:
    
      file:   <AMS Asset_Class="title" Asset_ID="OFFP0007010000000104" />
      token:  /^(<AMS Asset_Class="title" Asset_ID=")(OFFP0007010000000104)(" />)\$/=NEWI0007010000000999
    
      result: <AMS Asset_Class="title" Asset_ID="NEWI0007010000000999" />

    Or even better, this should work on any V1.1 ADI.XML ...
    
      token:  /^(<AMS Asset_Class="title" Asset_ID=")(.*)(" />)\$/=NEWI00070100000\@COUNT_NNNNN\@
    
      result: <AMS Asset_Class="title" Asset_ID="NEWI0007010000000001" />

  Function:

  Substitute a list of TOKENS for values in each file listed in the input file list.

      -help       : print this help text.

      -check      : value [yes|no]
                    Special case - ONLY other required parameter is: -file=FILE.DAT
                    Check each file listed in 'FILE.DAT'
                    for the regular expression "\@.*\@"
                    This may indicate the existence of an unresolved token.
                    default [no]

      -original   : value [yes|no]
                    Special case- ONLY valid with 'check=yes' or it will be ignored.
                    Same as '-check' except it processes the original ".TOKEN-ORIGINAL" file.
                    Shows all TOKENS that WERE used for the last install, before they were replaced 
                    with site specific values.
                    See "-backup" for more information.
                    default [no]
                    
      -doc        : value [yes|no]
                    Special case - ONLY other required parameter is: -check and -file=FILE.DAT
                    Check each file listed in 'FILE.DAT'
                    for the regular expression "\@.*\@"
                    Format the output so it can be used to generate a new FILE.DAT
                    default [no]

      -token      : Each line contains entry "\@TOKEN\@=value"
                    Lines starting with '#' are ignored.
  
      -file       : [file.name|all]
                    Each line contains a file name to process for TOKEN substitution.
                    If the line contains a single file, the original file will be replaced.
                    If the line contains two files, separated by a "$SEPERATOR", the changes will be applied
                    to the contents of the first file, but written to the second file.

                    [all] - Special case: Will search all potential config files under:

                           /opt/tandbergtv/cms/workflow/conf
                           /opt/tandbergtv/cms/conf
                           
      -aline      : [input.file.name] or [input.file.name,output.file.name]
                    Instead of providing a list of files using [file] specify a single file.
                    Useful for automated ADI generation, can invoke the script in a loop.
                    Used 'aline' because 'line' appears to be a special perl reserved name.
                           
      -update     : value [yes|no]
                    no - Just display the proposed changes but do NOT update the ORIGINAL file.
                    default [yes]

      -backup     : value [yes|no]
                    yes - Create a backup copy with the extension ".TOKEN-ORIGINAL" before updating
                    the ORIGINAL file.
                    If the line in 'file.list' specifies both an input and output file no backup will be made.
                    default [yes]

      -restore     : value [yes|no]
                    yes - Restore a previous backup copy with the extension ".TOKEN-ORIGINAL"
                    containing the unresolved TOKENS.
                    Enables a specific TOKEN mapping to be undone.
                    default [no]

      -regexp      : value [yes|no]
                    yes - Enable support for regular expressions in the token mapping file.
                    default [no]
                    
      -seed       : value [any integer]
                    Specify the start value for the \@COUNT_N\@ generator.
                    default [1]
                    
      -makedir    : value [yes|no]
                    For ADI generation.
                    When the 'file.list' specifies an output file path.
                    If the output directory does not exist, create instead of raising an error.
                    default [yes]

ENDHELPPAGE1

$continueMsg = "Enter any key to continue\n";

print $helpText1, $continueMsg;
$continue=<STDIN>; $continue = $continue;
}

sub history {
$historyText = <<ENDHISTORY;

 Created by steve.voisey\@ericsson.com 17th November 2010.

 Current Version: $version

 Substitute a list of TOKENS for values in each file listed in the input file list:

 History

 date     author   id   description
 -------- -------- ---  ----------------------------------------------------------
 17/11/10 srv      1.00 Created
 21/01/11 srv      1.01 For check, return message if no matches found.
 25/01/11 srv      1.02 Implement -doc input parameter.
 25/01/11 srv      1.03 Improve -doc to search ALL config directories and NEVER update.
 04/03/11 srv      1.04 Improve regular expression, ignore spaces and use [A-Za-z0-9_-]
 29/03/11 srv      1.05 Include a summary with token check.
 04/07/11 srv      1.07 Added -original.
 04/07/11 srv      1.08 Added -restore.
 05/07/11 srv      1.09 Improved handling of long lines for output, handle fules file.
 08/11/11 srv      1.10 New makedir input parameter. Default yes - If out dir not exist create instead of error.
 20/11/11 srv      1.11 Add new 'aline' input parameter for ADI generation and change SEPERATOR to ",".
 12/12/11 srv      1.12 Add new support for regular expression syntax as alternative to a TOKEN.
 21/01/12 srv      1.13 Add new support for current date/time special TOKENS.
 26/03/12 srv      1.14 Add input parameter regexp=yes to enable regular expression support, 
                        otherwise regular expressions are ignored. 
                        Fixed the '=' separator with multi '=' on a line with a regular expression bug.
 22/04/12 srv      1.15 Support 'file' 'token' to be defaulted to current dir if not preceeded by path '/'.
 12/05/12 srv      1.16 Support any TOKEN on the right side of the assignment (L=R).
 21/05/12 srv      1.17 Changed help from 'perl subToken.pl' to './subToken.pl' - Strange error invoking via perl???
ENDHISTORY
print $historyText;
}



