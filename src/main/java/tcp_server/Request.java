package tcp_server;

import java.util.HashMap;
import java.util.List;

import headers.*;

public class Request {

  private int index;
  private RequestLine requestLine;
  private HashMap<String, Header> mapHeaders;
  private List<HashMap<String, Header>> listHeadersMap;

  public Request() {
  }

  public int getIndex() {
    return index;
  }

  public RequestLine getRequestLine() {
    return requestLine;
  }

  public List<HashMap<String, Header>> getListHeadersMap() {
    return listHeadersMap;
  }

  public HashMap<String, Header> getMapHeaders() {
    return mapHeaders;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public void setRequestLine(RequestLine requestLine) {
    this.requestLine = requestLine;
  }

  public void setMapHeaders(HashMap<String, Header> mapHeaders) {
    this.mapHeaders = mapHeaders;
  }

  public void setListHeadersMap(List<HashMap<String, Header>> listHeadersMap) {
    this.listHeadersMap = listHeadersMap;
  }
}
