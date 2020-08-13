package com.galaxy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import com.galaxy.GalaxyRoot;

/**
 * Created by chandra on 17-08-2020.
 */
public class GalaxyMainTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private String inputFilePath = "./src/test/resources/input";
    private String exceptedOutputFilePath = "./src/test/resources/output0";
    private String actualOutputFilePath = "./src/test/resources/output";

    @After
    public void tearDown() throws Exception {
        if (new File(actualOutputFilePath).exists()) {
            new File(actualOutputFilePath).delete();
        }
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void mainConsoleOutTest() throws Exception {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        GalaxyRoot.main(new String[]{inputFilePath});
        Assert.assertEquals("pish tegj glob glob is 42\n" +
                        "glob prok Silver is 68 Credits\n" +
                        "glob prok Gold is 57800 Credits\n" +
                        "glob prok Iron is 782 Credits\n" +
                        "I have no idea what you are talking about\n"
                , outContent.toString());
    }

    @Test
    public void mainFileOutTest() throws Exception {
        assert !new File(actualOutputFilePath).exists();
        GalaxyRoot.main(new String[]{inputFilePath, actualOutputFilePath});
        assert new File(actualOutputFilePath).exists();
        byte[] exceptedBytes = new byte[1024];
        int exceptedLength = new FileInputStream(exceptedOutputFilePath).read(exceptedBytes);
        byte[] actualBytes = new byte[1024];
        new FileInputStream(actualOutputFilePath).read(actualBytes);
        int actualLength = new FileInputStream(exceptedOutputFilePath).read(exceptedBytes);
        Assert.assertEquals(exceptedLength, actualLength);
        Assert.assertEquals(new String(exceptedBytes, 0, exceptedLength), new String(actualBytes, 0, actualLength));
    }

    @Test
    public void mainConsoleReadTest() throws Exception {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        ByteArrayInputStream in = new ByteArrayInputStream(("glob is I\n" +
                "prok is V\n" +
                "pish is X\n" +
                "tegj is L\n" +
                "glob glob Silver is 34 Credits\n" +
                "glob prok Gold is 57800 Credits\n" +
                "pish pish Iron is 3910 Credits\n" +
                "how much is pish tegj glob glob ?\n" +
                "how many Credits is glob prok Silver ?\n" +
                "how many Credits is glob prok Gold ?\n" +
                "how many Credits is glob prok Iron ?\n" +
                "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?").getBytes());
        System.setIn(in);
        GalaxyRoot.main(null);
        Assert.assertEquals("pish tegj glob glob is 42\n" +
                        "glob prok Silver is 68 Credits\n" +
                        "glob prok Gold is 57800 Credits\n" +
                        "glob prok Iron is 782 Credits\n" +
                        "I have no idea what you are talking about\n"
                , outContent.toString());
        System.setIn(System.in);
    }
}