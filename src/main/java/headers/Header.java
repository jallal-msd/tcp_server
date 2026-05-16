package headers;

public class Header {

  private String name;
  private String value;
  private int len;

  public Header() {
  };

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public int getLen() {
    return len;
  }

  public void setLen(int len) {
    this.len = len;
  }
}
