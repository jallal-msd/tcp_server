package tcp_server;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class AppTest {

  @Test
  public void testOutput() throws Exception {

    // create file for test
    FileWriter file = new FileWriter("messages.txt");
    file.write("hello\nWorld\n");
    file.close();

    ByteArrayOutputStream output = new ByteArrayOutputStream();
    // original output
    PrintStream originalout = System.out;

    System.setOut(new PrintStream(output));

    try {
      App.readFrom();
      String expected = "read shello\n" +
          "read World\n";

      assertEquals(expected, output.toString());
    } catch (Exception e) {
    } finally {
      System.setOut(originalout);
    }

  }
}
