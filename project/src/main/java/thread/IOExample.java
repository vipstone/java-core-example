package thread;

import java.io.*;
import java.util.Date;

public class IOExample {
    public static void main(String[] args) throws IOException {

        String filePath = "d:\\log.txt";
        String Content = "你好，world~\n";

        // 添加文件
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            fileWriter.write(Content);
            fileWriter.close();
        }

        // 读取文件
        // try-with-resources 模式
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            StringBuffer bf = new StringBuffer();
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                bf.append(str + "\n");
            }
            System.out.println(bf.toString());
        }

    }
}
