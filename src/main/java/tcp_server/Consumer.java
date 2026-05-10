package tcp_server;

import java.util.concurrent.BlockingQueue;

class Consumer implements Runnable {

  private BlockingQueue<String> channel;

  public Consumer(BlockingQueue<String> channel) {
    this.channel = channel;
  }

  @Override
  public void run() {
    while (true) {
      try {
        String newLine = channel.take();
        if (newLine.equals("EOF"))
          break;
        System.out.printf("read: %s\n", newLine);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
