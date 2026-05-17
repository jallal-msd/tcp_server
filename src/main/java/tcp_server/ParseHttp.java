package tcp_server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import headers.*;

class ParseHttp {

  public static String separator = "\r\n";

  enum state {
    stateInit,
    stateDone,
    stateHeaders,
    stateError
  }

  static state sta = state.stateInit;

  public static Request requestFromReader(InputStream read) throws IOException {
    Request request = new Request();
    byte[] tmp = new byte[1024];
    int ch;
    ByteArrayOutputStream buff = new ByteArrayOutputStream();
    while ((ch = read.read(tmp)) != -1) {
      buff.write(tmp, 0, ch);
      request = parseByChucks(buff.toByteArray());
    }
    return request;
  }

  public static Request parseByChucks(byte[] buff) {
    Request request = new Request();
    int read = 0;
    outer: while (sta != state.stateError) {
      switch (sta) {
        case stateInit:
          request = parse(new String(buff));
          break;
        case stateHeaders:
          request.setMapHeaders(ParseHeader.parse(buff));
          sta = state.stateDone;
        case stateDone:
          break outer;
      }
    }
    return request;
  }

  public static state done() {
    return state.stateDone;
  }

  public static Request parse(String s) {
    RequestLine requestLine = new RequestLine();
    int read = 0;
    int endOfLine = s.indexOf(separator);
    if (endOfLine == -1) {
      sta = state.stateError;
      return null;
    }
    String startOfLine = s.substring(0, endOfLine);
    // String restOfMessage = s.substring(endOfLine + 1, s.length());
    read += endOfLine + separator.length();

    List<String> parts = Arrays.asList(startOfLine.split(" "));
    if (parts.size() != 3) {
      System.err.println("malformed request");
      sta = state.stateDone;
    } else {
      requestLine.setHttpMethod(parts.get(0));
      requestLine.setTarget(parts.get(1));
      requestLine.setHttpVersion(parts.get(2).split("/")[1]);
      // requestLine.setRestOfMessage(restOfMessage);
      sta = state.stateHeaders;
    }

    Request request = new Request();
    request.setIndex(read);
    request.setRequestLine(requestLine);
    return request;
  }
}
