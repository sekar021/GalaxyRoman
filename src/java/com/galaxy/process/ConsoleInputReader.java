package com.galaxy.process;

import com.galaxy.exception.InputReadException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by chandra on 17-08-2020.
 */
public class ConsoleInputReader implements InputReader {
    private BufferedReader scanner;

    public ConsoleInputReader() {
        scanner = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String readLine() throws InputReadException {
        try {
            return scanner.readLine();
        } catch (IOException e) {
            throw new InputReadException(e);
        }
    }

    @Override
    public void close() throws IOException {
        if (scanner != null) {
            scanner.close();
        }
    }
}
