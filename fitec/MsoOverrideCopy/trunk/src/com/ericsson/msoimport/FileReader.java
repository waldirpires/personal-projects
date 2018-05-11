package com.ericsson.msoimport;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class FileReader {

	private final String fileName;

	public FileReader(String fileName) {
		this.fileName = fileName;
	}

	public interface LineProcessor {
		// return false to stop reader, true for keep going on
		public boolean OnLineRead(int lineNumber, String lineContents) throws IOException;
	}

	public void readLine(LineProcessor processor) throws IOException {
		FileInputStream fstream = new FileInputStream(fileName);
		DataInputStream in = null;
		try {
			in = new DataInputStream(fstream);
			Charset charSet = Charset.forName("ISO-8859-1");
			BufferedReader br = new BufferedReader(new InputStreamReader(in, charSet));
			String strLine;
			// Read File Line By Line
			int lineNumber = 1;
			while ((strLine = br.readLine()) != null) {
				if (!processor.OnLineRead(lineNumber++, strLine))
					break;
			}
		} finally {
			// Close the input stream
			if (in != null)
				in.close();
		}
	}
}
