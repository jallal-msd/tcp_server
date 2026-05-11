package tcp_server;

import java.util.Arrays;
import java.util.List;

class ParseHttp {

  public static RequestLine parse(String s) {
    RequestLine requestLine = new RequestLine();
    int endOfLine = s.indexOf("\r\n");
    if (endOfLine == -1) {
      return null;
    }
    String startOfLine = s.substring(0, endOfLine);
    String restOfMessage = s.substring(endOfLine + 1, s.length());

    List<String> parts = Arrays.asList(startOfLine.split(" "));
    if (parts.size() != 3) {
      System.err.println("malformed request");
    } else {
      requestLine.setHttpMethod(parts.get(0));
      requestLine.setTarget(parts.get(1));
      requestLine.setHttpVersion(parts.get(2).split("/")[1]);
      requestLine.setRestOfMessage(restOfMessage);
    }
    return requestLine;
  }
}
