package com.galaxy.process;

import com.galaxy.exception.ProcessException;

/**
 * Created by chandra on 17-08-2020.
 */
public interface MerchantGuideProcessor {
    void setInputReader(InputReader inputReader);

    void setOutputWriter(OutputWriter outputWriter);

    void process() throws ProcessException;
}
