package tcp_server;

import java.util.concurrent.BlockingQueue;

class Consumer implements Runnable {

  private BlockingQueue<String> channel;
  private RequestLine requestLine;

  public Consumer(BlockingQueue<String> channel) {
    this.channel = channel;
  }

  @Override
  public void run() {
    while (true) {
      try {

        String newLine = channel.take();
        System.out.println(newLine);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
