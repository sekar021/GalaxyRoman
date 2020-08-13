package com.galaxy.process;

/**
 * Created by chandra on 17-08-2020.
 */
public class ConsoleOutputWriter implements OutputWriter {
    @Override
    public void writeOutput(String output) {
        System.out.println(output);
    }

    @Override
    public void done() {
        System.out.close();
    }
}
