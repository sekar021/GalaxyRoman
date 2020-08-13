package com.galaxy;

import com.galaxy.exception.ProcessException;
import com.galaxy.process.FileInputReader;
import com.galaxy.process.FileOutputWriter;
import com.galaxy.process.MerchantGuideProcessor;
import com.galaxy.process.MerchantGuideProcessorImpl;

/**
 * application main class
 * Created by chandra on 17-08-2020.
 */
public class GalaxyRoot {
    /**
     * @param args optional args: [inputFilePath [outFilePath]]
     */
    public static void main(String[] args) {
        MerchantGuideProcessor merchantGuideProcessor = new MerchantGuideProcessorImpl();
        if (args != null) {
            if (args.length >= 1) {
                merchantGuideProcessor.setInputReader(new FileInputReader(args[0]));
            }
            if (args.length >= 2) {
                merchantGuideProcessor.setOutputWriter(new FileOutputWriter(args[1]));
            }
        }
        try {
            merchantGuideProcessor.process();
        } catch (ProcessException e) {
            System.out.println(e.getMessage());
        }
    }
}
