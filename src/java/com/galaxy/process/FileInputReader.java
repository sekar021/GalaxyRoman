package com.galaxy.process;

import com.galaxy.exception.InputReadException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by chandra on 17-08-2020.
 */
public class FileInputReader implements InputReader {
    private final String filePath;
    private BufferedReader bufferedReader;

    public FileInputReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public synchronized String readLine() throws InputReadException {
        if (filePath == null) {
            throw new InputReadException(new NullPointerException("filePath"));
        }
        if (bufferedReader == null) {
            FileReader fileReader = null;
            try {
                fileReader = new FileReader(filePath);
            } catch (FileNotFoundException e) {
                throw new InputReadException(e);
            }
            bufferedReader = new BufferedReader(fileReader);
        }
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new InputReadException(e);
        }
    }

    @Override
    public void close() throws IOException {
        if (bufferedReader != null) {
            bufferedReader.close();
        }
    }

}
