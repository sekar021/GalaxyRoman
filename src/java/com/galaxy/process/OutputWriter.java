package com.galaxy.process;

import com.galaxy.exception.OutputWriteException;

/**
 * Created by chandra on 17-08-2020.
 */
public interface OutputWriter {
    void writeOutput(String output) throws OutputWriteException;

    void done();
}
