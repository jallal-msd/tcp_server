package headers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ParseHeader {

  public static String rn = "\r\n";

  public static HashMap<String, Header> parseHeader(String fieldLine, HashMap<String, Header> headerMap) {
    System.out.println(fieldLine);
    Header header = new Header();
    List<String> parts = Arrays.asList(fieldLine.split(":", 2));
    if (parts.size() != 2) {
      System.err.println("malformed");
      return null;
    }
    System.out.println(parts.get(0));

    String name = parts.get(0);
    if (headerMap.containsKey(name)) {
      System.out.println("yes it exists");
      Header existingHeader = headerMap.get(name);
      String existingValue = existingHeader.getValue();
      existingHeader.setName(name);
      existingHeader.setValue(String.format("%s, %s", existingValue, parts.get(1).trim()));
      header = existingHeader;

      headerMap.put(name, existingHeader);

    } else {
      header.setName(parts.get(0));
      header.setValue(parts.get(1).trim());
      System.out.println("-- " + name);
      headerMap.put(name, header);
    }

    if (Character.isWhitespace(header.getName().charAt(header.getName().length() - 1))) {
      System.err.println("white space after name , malformed");
      return null;
    }
    return headerMap;

  }

  public static List<HashMap<String, Header>> parse(byte[] buff) {
    boolean done = false;

    int idx = 0;
    HashMap<String, Header> header = new HashMap<>();
    List<HashMap<String, Header>> listHeader = new ArrayList<>();
    int indx = 0;
    String strBuff = new String(buff);
    while (!done) {
      String chuckOfStr = strBuff.substring(idx, strBuff.length());
      indx = chuckOfStr.indexOf(rn);
      System.out.println(indx);
      if (indx == -1) {
        System.err.println("register nurse not found. malformed request");
      }
      if (indx == 0) {
        System.out.println("found 0 ");
        // empty header, first index is \r\n
        done = true;
        return listHeader;

      }
      HashMap<String, Header> res = parseHeader(chuckOfStr.substring(0, indx), header);
      if (res != null) {
        listHeader.add(res);
      }

      idx += indx + rn.length();

      if (header != null) {
      }
    }
    return null;

  }
}
