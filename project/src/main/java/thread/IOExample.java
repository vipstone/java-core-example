package thread;


import javafx.scene.input.DataFormat;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IOExample {
    public static void main(String[] args) throws IOException {

        String filePath = "D:\\log.txt";
        String Content = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " surprise \n";

        // 添加文件
        FileWriter fileWriter = new FileWriter(filePath, true);
        fileWriter.write(Content);
        fileWriter.close();

        // 读取文件
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuffer bf = new StringBuffer();
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            bf.append(str + "\n");
        }
        bufferedReader.close();
        fileReader.close();
        System.out.println(bf.toString());

        // ====================JDK 1.7 之后 读写文件====================
        // 写入文件（追加方式：StandardOpenOption.APPEND）
        Files.write(Paths.get(filePath), Content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);

        // 读取文件
        byte[] data =  Files.readAllBytes(Paths.get(filePath));
        System.out.println(new String(data, StandardCharsets.UTF_8));

        // 创建多（单）层目录（如果不存在创建，存在不会报错）
        Files.createDirectories(Paths.get("D://a//b"));

        // JDK 1.7 之前 创建多（单）层目录（如果不存在创建，存在不会报错）
        new File("D://a//b").mkdirs();

    }
}
