package com.aliyun.tauris.utils;

import org.apache.commons.io.input.TailerListener;

/**
 * {@link TailerListener} Adapter.
 *
 * @version $Id: ExTailerListenerAdapter.java 1304052 2012-03-22 20:55:29Z ggregory $
 * @since 2.0
 */
public class ExTailerListenerAdapter implements ExTailerListener {

    /**
     * The tailer will call this method during construction,
     * giving the listener a method of stopping the tailer.
     * @param tailer the tailer.
     */
    public void init(ExTailer tailer) {
    }

    /**
     * This method is called if the tailed file is not found.
     */
    public void fileNotFound() {
    }

    /**
     * Called if a file rotation is detected.
     *
     * This method is called before the file is reopened, and fileNotFound may
     * be called if the new file has not yet been created.
     */
    public void fileRotated() {
    }

    /**
     * Handles a line from a Tailer.
     * @param line the line.
     */
    public void handle(String line) {
    }

    /**
     * Handles an Exception .
     * @param ex the exception.
     */
    public void handle(Exception ex) {
    }

}
