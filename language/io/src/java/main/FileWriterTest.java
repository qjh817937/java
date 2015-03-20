
import java.io.*;

public class FileWriterTest {

    public void testWrite() throws Exception {
        /** 
         * 创建一个可以往文件中写入字符数据的字符流输出流对象 
         * 创建时必须明确文件的目的地 
         * 如果文件不存在，这回自动创建。如果文件存在，则会覆盖。 
         * 当路径错误时会抛异常 
         *  
         * 当在创建时加入true参数，回实现对文件的续写。 
         */ 
        FileWriter fw = new FileWriter("./output.txt", true);  
        /** 
         * 调用该对象的write方法，向文件写入字符。 
         *  
         * 其实写入到了临时存储缓冲区中 
         */  
        fw.write("hello world 1\n");  
        fw.write("hello world 2");  
        /** 
         * 进行刷新，将字符写到目的地中。 
         */  
        //      fw.flush();  
        /** 
         * 关闭流，关闭资源。在关闭前会调用flush方法 刷新缓冲区。关闭后在写的话，会抛IOException 
         */  
        fw.close();  
    }

    public static void main(String[] args) {
        try{
            FileWriterTest fileWriterTest = new FileWriterTest();
            fileWriterTest.testWrite();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
