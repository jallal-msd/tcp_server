package tcp_server;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
      String line;
      while ((line = reader.readLine()) != null) {
        channel.put(line);
      }
      // channel.put("EOF");
    } catch (Exception e) {
      System.err.println(e);
    }
  }
}
