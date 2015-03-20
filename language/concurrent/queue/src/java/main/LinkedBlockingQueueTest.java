import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: jianshen
 * Date: 15-3-19
 * Time: 下午9:36
 * To change this template use File | Settings | File Templates.
 * http://www.cnblogs.com/jackyuj/archive/2010/11/24/1886553.html
 */
public class LinkedBlockingQueueTest {
    public class Producer extends Thread{
        private BlockingQueue<String> blockingQueue;
        private int sleepTime = 1000;

        Producer(BlockingQueue<String> blockingQueue, int sleepTime) {
            this.blockingQueue = blockingQueue;
            this.sleepTime  = sleepTime;
        }

        public void run() {
            String msg;
            int num = 100;
            for (int i = 0; i < num; i++) {
                try {
                    msg = "msg_" + i;
                    blockingQueue.put(msg);
                    System.out.println("put " + msg);
                    sleep(sleepTime);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class Consumer extends Thread{
        private BlockingQueue<String> blockingQueue;
        private int sleepTime = 1000;

        Consumer(BlockingQueue<String> blockingQueue, int sleepTime) {
            this.blockingQueue = blockingQueue;
            this.sleepTime  = sleepTime;
        }

        public void run() {
            while(true){
                try {
                    String msg = blockingQueue.take();
                    //String msg = blockingQueue.poll(100, TimeUnit.MILLISECONDS);
                    if (null == msg) {
                        System.out.println("poll timeout");
                    }
                    else {
                        System.out.println("poll " + msg);
                    }
                    sleep(sleepTime);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class TimeoutConsumer extends Thread{
        private BlockingQueue<String> blockingQueue;
        private int sleepTime = 1000;

        TimeoutConsumer(BlockingQueue<String> blockingQueue, int sleepTime) {
            this.blockingQueue = blockingQueue;
            this.sleepTime  = sleepTime;
        }

        public void run() {
            while(true){
                try {
                    String msg = blockingQueue.poll(1000, TimeUnit.MILLISECONDS);
                    if (null == msg) {
                        System.out.println("poll timeout");
                    }
                    else {
                        System.out.println("poll " + msg);
                    }
                    sleep(sleepTime);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 队列满，生产者阻塞
     * 队列满->不满，生产者向队列放数据成功
     *
     */
    public void testPoducer() {
        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<String>(10);
        Producer producer = new Producer(blockingQueue, 100);
        producer.start();

        Consumer consumer = new Consumer(blockingQueue, 5000);  // 消费者消费慢
        consumer.start();

    }

    /**
     * 队列没有数据，消费者阻塞(不设置超时时间)
     *      没有数据则 一直阻塞
     *      有数据则获取到数据
     */

    public void testConsumer() {
        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<String>(10);
        Producer producer = new Producer(blockingQueue, 5000);
        Consumer consumer = new Consumer(blockingQueue, 100);  // 消费者消费慢
        producer.start();
        consumer.start();
    }

    /**
     * 队列没有数据，消费者阻塞(设置超时时间)
     *      没有数据则 超时后返回
     *      有数据则获取到数据
     */
    public void testTimeoutConsumer() {
        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<String>(10);
        Producer producer = new Producer(blockingQueue, 5000);
        TimeoutConsumer consumer = new TimeoutConsumer(blockingQueue, 100);  // 消费者消费慢
        producer.start();
        consumer.start();
    }

    public static void main(String[] args) {
        LinkedBlockingQueueTest blockingQueueTest = new LinkedBlockingQueueTest();
        //blockingQueueTest.testPoducer();
        blockingQueueTest.testConsumer();
        //blockingQueueTest.testTimeoutConsumer();
    }
}
