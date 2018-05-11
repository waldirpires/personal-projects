package com.ericsson.msoimport;

import java.io.IOException;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class LogAppender extends FileAppender {
	private final static PatternLayout layout = new PatternLayout("[%d{dd-MMM-yyyy HH:mm:ss,SSS}][%p][%t] %l %m%n");
	public static LogAppender logAppender = null;

	private LogAppender() throws IOException {
		super(layout, "/opt/tandbergtv/cms/log/msoimport.log");
	}

	public static void addAppender(final Logger logger) {
		if (logAppender == null) {
			try {
				logAppender = new LogAppender();
			} catch (IOException e) {
				logger.warn(e);
				return;
			}
		}
		Logger.getRootLogger().addAppender(logAppender);
		logger.setLevel(Level.DEBUG);
	}
}
