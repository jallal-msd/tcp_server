package tcp_server;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import headers.*;

class Producer implements Runnable {

  private BlockingQueue<Request> channel;

  public Producer(BlockingQueue<Request> channel) {
    this.channel = channel;
  }

  @Override
  public void run() {
    readFromSocket();
  }

  public void readFromSocket() {
    try (ServerSocket serverSocket = new ServerSocket(8000)) {
      System.out.println("port 8000 is listinnig");
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
      Request request = ParseHttp.requestFromReader(inputStream);
      System.out.println("out of the parser");
      channel.put(request);

      // channel.put("EOF");
    } catch (Exception e) {
      System.err.println(e);
    }
  }
}
