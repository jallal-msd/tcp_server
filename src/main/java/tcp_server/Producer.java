package tcp_server;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.BlockingQueue;

class Producer implements Runnable {

  private BlockingQueue<String> channel;

  public Producer(BlockingQueue<String> channel) {
    this.channel = channel;
  }

  @Override
  public void run() {
    String path = "../messages.txt";

    byte[] buff = new byte[8];

    StringBuilder str = new StringBuilder();
    try (InputStream inputStream = new FileInputStream(path)) {
      int buffReader;

      while ((buffReader = inputStream.read(buff)) != -1) {
        for (int i = 0; i < buffReader; i++) {
          char c = (char) buff[i];
          if (c == '\n') {
            channel.put(str.toString());
            str.setLength(0);
          } else {
            str.append(String.valueOf(c));
          }
        }
      }
      System.err.println("end of the line");
      channel.put("EOF");
    } catch (Exception e) {
      System.err.println(e);
    }
  }
}
