# 下载80电子书

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/*
https://down.8080txt.com/d/file/down/2022/10/01/我能看到所有BOSS掉落.txt
替换其中的中文到uri编码以便使用idm下载.
*/

public class testJava {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("80URL.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("Deal80.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            String dealLine = replaceChinese(line);
            bw.write(dealLine);
            bw.newLine();
            bw.flush();
        }
        br.close();
        bw.close();
    }

    public static String replaceChinese(String line) throws UnsupportedEncodingException {
        String[] split = line.split("/");
        String fileTxt = split[split.length - 1];
        String fileName = fileTxt.split("\\.")[0];
        String URI = URLEncoder.encode(fileName, "utf-8");
        return line.replaceAll(fileName, URI);
    }
}
```