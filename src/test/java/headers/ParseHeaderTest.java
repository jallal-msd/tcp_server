package headers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import headers.ParseHeader;
import headers.Header;

public class ParseHeaderTest {

  @Test
  public void testParseHeader() {
    String s = "Host: localhost:8000\r\n\r\n";
    Header header = ParseHeader.parse(s.getBytes());
    assertEquals("Host", header.getName());
    assertEquals("localhost:8000", header.getValue());
    assertEquals(24, header.getLen());

  }

  @Test
  public void testEmptyParseHeader() {
    String s = "\r\n\r\n";
    Header header = ParseHeader.parse(s.getBytes());
    assertNull(header);
  }

  @Test
  public void testWsParseHeader() {
    String st = "Host : localhost:8000\r\n\r\n";
    Header header1 = ParseHeader.parse(st.getBytes());
    assertNull(header1);
  }
}
