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
    List<HashMap<String, Header>> headers = ParseHeader.parse(s.getBytes());
    for (HashMap<String, Header> header : headers) {

      assertEquals("Host", header.get("Host").getName());
      assertEquals("localhost:8000", header.get("Host").getValue());
      assertEquals("FOOF", header.get("FOOF").getName());
      assertEquals("s", header.get("FOOF").getValue());
    }

  }

  @Test
  public void testEmptyParseHeader() {
    String s = "\r\n\r\n";
    List<HashMap<String, Header>> headers = ParseHeader.parse(s.getBytes());
    assertEquals(0, headers.size());
  }

  @Test
  public void testWsParseHeader() {
    String s = "Host : localhost:8000\r\n\r\n";
    List<HashMap<String, Header>> headers = ParseHeader.parse(s.getBytes());
    assertEquals(0, headers.size());
  }
}
