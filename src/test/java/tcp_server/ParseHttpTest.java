package tcp_server;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ParseHttpTest {

  @Test
  public void testParse() {
    String httpHeader = "POST /coffe HTTP/1.1\r\nHost: localhost:8000\r\nUser-Agent: curl/4.1.16\r\nAccept: */*\r\nContent-Type: application/json\r\nContent-Length: 123";
    RequestLine req = ParseHttp.parse(httpHeader);

    assertEquals("POST", req.getHttpMethod());
    assertEquals("/coffe", req.getTarget());
    assertEquals("1.1", req.getHttpVersion());
  }
}
