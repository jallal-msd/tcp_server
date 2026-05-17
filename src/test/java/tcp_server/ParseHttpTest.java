package tcp_server;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import headers.Header;

class ParseHttpTest {

  @Test
  public void testParse() throws IOException {
    String httpHeader = "POST /coffe HTTP/1.1\r\nHost: localhost:8000\r\nUser-Agent: curl/4.1.16\r\nAccept: */*\r\nContent-Type: application/json\r\nContent-Length: 123\r\n\r\n";
    InputStream reader = new ByteArrayInputStream(httpHeader.getBytes(StandardCharsets.UTF_8));
    Request request = ParseHttp.requestFromReader(reader);

    assertEquals("POST", request.getRequestLine().getHttpMethod());
    assertEquals("/coffe", request.getRequestLine().getTarget());
    for (HashMap<String, Header> header : request.getListHeadersMap()) {
      assertEquals("Host", header.get("Host").getName());
      assertEquals("localhost:8000", header.get("Host").getValue());
      assertEquals("User-Agent", header.get("User-Agent").getName());
      assertEquals("curl/4.1.16", header.get("User-Agent").getValue());
      assertEquals("Accept", header.get("Accept").getName());
      assertEquals("*/*", header.get("Accept").getValue());
      assertEquals("Content-Type", header.get("Content-Type").getName());
      assertEquals("application/json", header.get("Content-Type").getValue());
      assertEquals("Content-Length", header.get("Content-Length").getName());
      assertEquals("123", header.get("Content-Length").getValue());
    }
    // assertEquals("1.1", request.getRequestLine().getHttpVersion());
  }
}
