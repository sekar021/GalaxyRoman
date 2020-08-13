package com.galaxy.process;

import com.galaxy.exception.OutputWriteException;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by chandra on 17-08-2020.
 */
public class FileOutputWriter implements OutputWriter {
    private String filePath;
    private FileOutputStream fileOutputStream;

    public FileOutputWriter(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public synchronized void writeOutput(String output) throws OutputWriteException {
        byte[] bytes = (output + "\n").getBytes();
        if (fileOutputStream == null) {
            try {
                fileOutputStream = new FileOutputStream(filePath);
                fileOutputStream.write(bytes);
                fileOutputStream = new FileOutputStream(filePath, true);
            } catch (IOException e) {
                throw new OutputWriteException(e);
            }
        } else {
            try {
                fileOutputStream.write(bytes);
            } catch (IOException e) {
                throw new OutputWriteException(e);
            }
        }
    }

    @Override
    public void done() {
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
