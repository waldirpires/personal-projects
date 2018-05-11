package questions;

import java.util.Arrays;

public class QuestionB {

	public static void main(String[] args) {

		int tapeSize = 100;
		int [] fileSizes = {70, 10, 20};

		//int[] fileSizes = { 70, 10, 20, 50, 40, 30, 20 };

		Batch impl = new BatchImpl(tapeSize, fileSizes);

		System.out.println("Tape count: " + getMinimumTapeCount(impl));

	}

	public static int getMinimumTapeCount(final Batch batch) {

		int tapeCount = 0; // tape counter, number of tapes (or buckets) we will need to store the files
		int tapeSize = 0; // tape length counter
		int fileCount = 0; // file counter per tape

		Arrays.sort(batch.getFileSizes()); // sort tapes from smallest to
											// largest in size
		// assuming that the tape sizes will always be less than or equal to the
		// tape size
		// since rule 2 states that no file can be split across tapes

		int i = 0; // files countes
		// while there are files to distribute
		while (i < batch.getFileSizes().length) {
			// while the file count satisfies rule 1 and tape size is less than
			// or equal to tape size limit
			while (fileCount < 2 && tapeSize <= batch.getTapeSize()) {
				// increment the tape size with the current tape length
				tapeSize += batch.getFileSizes()[i]; 
				// incremente file counter per tape (bucket)
				fileCount++;
				// file index incremented
				i++;
				// if there are no more files to check for
				if (i == batch.getFileSizes().length) {
					break;
				}
			}
			// increment tape (bucket) count, since one tape is already full
			tapeCount++;
			// initialize tape size and file count for the next tape (bucket)
			tapeSize = 0;
			fileCount = 0;
		}

		// the total amount of tapes (or buckets) we will need to use to store the files
		return tapeCount;
	}

}
