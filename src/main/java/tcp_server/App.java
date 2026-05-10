package tcp_server;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {
    BlockingQueue<String> channel = new LinkedBlockingQueue<>();

    Producer producer = new Producer(channel);
    Consumer consumer = new Consumer(channel);

    Thread producerThread = new Thread(producer);
    Thread consumerThread = new Thread(consumer);

    producerThread.start();
    consumerThread.start();
  }
}
