package tcp_server;

import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

import headers.Header;

class Consumer implements Runnable {

  private BlockingQueue<Request> channel;
  private RequestLine requestLine;

  public Consumer(BlockingQueue<Request> channel) {
    this.channel = channel;
  }

  @Override
  public void run() {
    while (true) {
      try {
        System.out.println("I am here");
        Request newLine = channel.take();
        System.out.printf("Method, %s\n", newLine.getRequestLine().getHttpMethod());
        System.out.printf("target, %s\n", newLine.getRequestLine().getTarget());
        System.out.printf("version, %s\n", newLine.getRequestLine().getHttpVersion());
        HashMap<String, Header> header = newLine.getMapHeaders();
        // Header nextHeader = header.values().iterator().next();
        header.forEach((k, nextHeader) -> System.out.printf("header %s\n header-value: %s\n", nextHeader.getName(),
            nextHeader.getValue()));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
