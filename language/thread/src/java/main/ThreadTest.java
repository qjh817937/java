/**
 * Created with IntelliJ IDEA.
 * User: jianshen
 * Date: 15-2-1
 * Time: 上午10:02
 * To change this template use File | Settings | File Templates.
 */
public class ThreadTest extends  Thread{
    private String name;

    public ThreadTest(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(name + "运行     " + i);
            try {
                sleep(100);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadTest h1=new ThreadTest("A");
        ThreadTest h2=new ThreadTest("B");

        {
            h1.run();
            h2.run();
        }

        System.out.println("------------------------");

        {
            h1.start();
            h2.start();
        }
    }
}
