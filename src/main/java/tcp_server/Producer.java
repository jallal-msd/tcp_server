package tcp_server;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

class Producer implements Runnable {

  private BlockingQueue<String> channel;

  public Producer(BlockingQueue<String> channel) {
    this.channel = channel;
  }

  @Override
  public void run() {
    readFromSocket();
  }

  public void readFromSocket() {
    try (ServerSocket serverSocket = new ServerSocket(8000)) {
      while (true) {
        Socket socket = serverSocket.accept();
        readFromFile(socket);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public void readFromFile(Socket socket) {
    // String path = "../messages.txt";

    byte[] buff = new byte[8];
    StringBuilder str = new StringBuilder();
    try (InputStream inputStream = socket.getInputStream()) {
      int buffReader;
      while ((buffReader = inputStream.read(buff)) != -1) {
        for (int i = 0; i < buffReader; i++) {
          char c = (char) buff[i];
          if (c == '\n') {
            str.append('\n');
            channel.put(str.toString());
            str.setLength(0);
          } else {
            str.append(c);
          }
        }
      }
      if (str.length() > 0) {
        channel.put(str.toString());
      }
      // channel.put("EOF");
    } catch (Exception e) {
      System.err.println(e);
    }
  }
}
