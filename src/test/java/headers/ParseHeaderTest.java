package headers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import headers.ParseHeader;
import headers.Header;

public class ParseHeaderTest {

  @Test
  public void testParseHeader() {
    String s = "Host: localhost:8000\r\nFOOF: s\r\n\r\n";
    HashMap<String, Header> header = ParseHeader.parse(s.getBytes());

    assertEquals("Host", header.get("Host").getName());
    assertEquals("localhost:8000", header.get("Host").getValue());
    assertEquals("FOOF", header.get("FOOF").getName());
    assertEquals("s", header.get("FOOF").getValue());

  }

  public void testEmptyParseHeader() {
    String s = "\r\n\r\n";
    HashMap<String, Header> header = ParseHeader.parse(s.getBytes());
    assertEquals("{}", header);
  }

  public void testWsParseHeader() {
    String s = "Host : localhost:8000\r\n\r\n";
    HashMap<String, Header> header = ParseHeader.parse(s.getBytes());
    assertNull(header);
  }

  @Test
  public void testSameHeaderNameParseHeader() {
    String s = "Host: localhost:8000\r\nHost: localhost:8080\r\n\r\n";
    HashMap<String, Header> header = ParseHeader.parse(s.getBytes());
    assertEquals("Host", header.get("Host").getName());
    assertEquals("localhost:8000, localhost:8080", header.get("Host").getValue());

  }
}
