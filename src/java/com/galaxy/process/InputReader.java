package com.galaxy.process;

import com.galaxy.exception.InputReadException;

import java.io.IOException;

/**
 * Created by chandra on 17-08-2020.
 */
public interface InputReader {
    //List<String> getNotes();
    //List<String> getQueries();

    String readLine() throws InputReadException;

    void close() throws IOException;
}
