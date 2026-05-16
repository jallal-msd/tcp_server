package headers;

import java.util.Arrays;
import java.util.List;

public class ParseHeader {

  public static String rn = "\r\n\r\n";

  public static Header parseHeader(String fieldLine) {
    Header header = new Header();
    List<String> parts = Arrays.asList(fieldLine.split(":", 2));
    if (parts.size() != 2) {
      System.err.println("malformed");
      return null;
    }
    System.out.println(parts.get(0));
    header.setName(parts.get(0));
    header.setValue(parts.get(1).trim());
    if (Character.isWhitespace(header.getName().charAt(header.getName().length() - 1))) {
      System.err.println("white space after name , malformed");
      return null;
    }
    return header;

  }

  public static Header parse(byte[] buff) {
    boolean done = false;
    Header header = new Header();
    int indx = 0;
    String strBuff = new String(buff);
    while (!done) {
      indx = strBuff.indexOf(rn);
      if (indx == -1) {
        System.err.println("register nurse not found. malformed request");
      }
      if (indx == 0) {
        System.out.println("found 0 ");
        // empty header, first index is \r\n
        done = true;
        return null;
      }
      header = parseHeader(strBuff.substring(0, indx));
      if (header != null) {
        header.setLen(indx + rn.length());
      }
      return header;
    }
    return null;

  }
}
