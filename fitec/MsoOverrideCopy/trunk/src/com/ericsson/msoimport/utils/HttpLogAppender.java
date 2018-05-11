package com.ericsson.msoimport.utils;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.log4j.Layout;
import org.apache.log4j.WriterAppender;

public class HttpLogAppender extends WriterAppender {

    static ThreadLocal<OutputStream> streamPerHttpThread = new ThreadLocal<OutputStream>();

    public HttpLogAppender() {

    }

    public HttpLogAppender(Layout layout) {
        setLayout(layout);       //super-class method
        activateOptions();
    }

    public void setCurrentHttpStream(OutputStream stream) {
        streamPerHttpThread.set(stream);
    }


    public void activateOptions() {
        setWriter(createWriter(new CurrentHttpThreadOutStream()));
    }


    /**
     * An implementation of OutputStream that redirects to the
     * current http threads servlet output stream
     */
    private static class CurrentHttpThreadOutStream extends OutputStream {
        public CurrentHttpThreadOutStream() {
        }

        public void close() {
        }

        public void flush() throws IOException {
            OutputStream stream = streamPerHttpThread.get();
            if (stream != null) {
                stream.flush();
            }
        }

        public void write(final byte[] b) throws IOException {
            OutputStream stream = streamPerHttpThread.get();
            if (stream != null) {
                stream.write(b);
            }
        }

        public void write(final byte[] b, final int off, final int len)
                throws IOException {
            OutputStream stream = streamPerHttpThread.get();
            if (stream != null) {
                stream.write(b, off, len);
            }
        }

        public void write(final int b) throws IOException {
            OutputStream stream = streamPerHttpThread.get();
            if (stream != null) {
                stream.write(b);
            }
        }
    }
}