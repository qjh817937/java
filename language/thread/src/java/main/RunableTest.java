/**
 * Created with IntelliJ IDEA.
 * User: jianshen
 * Date: 15-2-1
 * Time: 上午10:09
 * To change this template use File | Settings | File Templates.
 */
public class RunableTest implements Runnable {
    private String name;

    public RunableTest(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(name + "运行     " + i);
            try {
                Thread.sleep(100);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        RunableTest h1=new RunableTest("线程A");
        Thread demo= new Thread(h1);
        RunableTest h2=new RunableTest("线程B");
        Thread demo1=new Thread(h2);
        demo.start();
        demo1.start();
    }
}

