package com.aliyun.tauris.utils;


/**
 * Listener for events from a {@link ExTailer}.
 *
 * @version $Id: ExTailerListener.java 1304052 2012-03-22 20:55:29Z ggregory $
 * @since 2.0
 */
public interface ExTailerListener {

    /**
     * The tailer will call this method during construction,
     * giving the listener a method of stopping the tailer.
     * @param tailer the tailer.
     */
    void init(ExTailer tailer);

    /**
     * This method is called if the tailed file is not found.
     * <p>
     * <b>Note:</b> this is called from the tailer thread.
     */
    void fileNotFound();

    /**
     * Called if a file rotation is detected.
     *
     * This method is called before the file is reopened, and fileNotFound may
     * be called if the new file has not yet been created.
     * <p>
     * <b>Note:</b> this is called from the tailer thread.
     */
    void fileRotated();

    /**
     * Handles a line from a Tailer.
     * <p>
     * <b>Note:</b> this is called from the tailer thread.
     * @param line the line.
     */
    void handle(String line);

    /**
     * Handles an Exception .
     * <p>
     * <b>Note:</b> this is called from the tailer thread.
     * @param ex the exception.
     */
    void handle(Exception ex);

}
