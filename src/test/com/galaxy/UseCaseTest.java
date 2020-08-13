package com.galaxy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by two8g on 17-4-15.
 */
public class UseCaseTest {
    private String useCaseDirPath = "./src/test/resources/usecases";

    @After
    public void tearDown() throws Exception {
        System.setIn(System.in);
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void useCaseTest() throws Exception {
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
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        GalaxyRoot.main(null);
        Assert.assertEquals("pish tegj glob glob is 42\n" +
                        "glob prok Silver is 68 Credits\n" +
                        "glob prok Gold is 57800 Credits\n" +
                        "glob prok Iron is 782 Credits\n" +
                        "I have no idea what you are talking about\n"
                , outContent.toString());
    }

    @Test
    public void useCaseBatchTest() throws Exception {
        File dir = new File(useCaseDirPath);
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                byte[] bytes = new byte[1024];
                int length = new FileInputStream(file).read(bytes);
                String s = new String(bytes, 0, length);
                String input = s.split("\n{2,}")[0];
                String output = s.split("\n{2,}")[1];
                if (input != null) {
                    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
                    System.setIn(in);
                    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
                    ByteArrayOutputStream errContent = new ByteArrayOutputStream();
                    System.setOut(new PrintStream(outContent));
                    System.setErr(new PrintStream(errContent));
                    GalaxyRoot.main(null);
                    System.out.println(file.getName());
                    Assert.assertEquals(output, outContent.toString());
                }
            }
        }
    }

}
