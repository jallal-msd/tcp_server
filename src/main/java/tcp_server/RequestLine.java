package tcp_server;

public class RequestLine {

  private String httpMethod;
  private String target;
  private String httpVersion;
  private String restOfMessage;

  public RequestLine() {

  }

  public String getHttpMethod() {
    return httpMethod;
  }

  public String getHttpVersion() {
    return httpVersion;
  }

  public String getTarget() {
    return target;
  }

  public String getRestOfMessage() {
    return restOfMessage;
  }

  public void setHttpMethod(String httpMethod) {
    this.httpMethod = httpMethod;
  }

  public void setHttpVersion(String httpVersion) {
    this.httpVersion = httpVersion;
  }

  public void setTarget(String target) {
    this.target = target;
  }

  public void setRestOfMessage(String restOfMessage) {
    this.restOfMessage = restOfMessage;
  }
}
