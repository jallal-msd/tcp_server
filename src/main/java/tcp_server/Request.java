package tcp_server;

public class Request {

  private int index;
  private RequestLine requestLine;

  public Request() {
  }

  public int getIndex() {
    return index;
  }

  public RequestLine getRequestLine() {
    return requestLine;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public void setRequestLine(RequestLine requestLine) {
    this.requestLine = requestLine;
  }

}
