package tcp_server;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

class ParseHttpTest {

  @Test
  public void testParse() throws IOException {
    String httpHeader = "POST /coffe HTTP/1.1\r\nHost: localhost:8000\r\nUser-Agent: curl/4.1.16\r\nAccept: */*\r\nContent-Type: application/json\r\nContent-Length: 123";
    String httpHeader1 = "POST /cof\r\n";
    InputStream reader = new ByteArrayInputStream(httpHeader1.getBytes(StandardCharsets.UTF_8));
    Request request = ParseHttp.requestFromReader(reader);

    assertEquals("POST", request.getRequestLine().getHttpMethod());
    assertEquals("/cof", request.getRequestLine().getTarget());
    // assertEquals("1.1", request.getRequestLine().getHttpVersion());
  }
}
