package com.itheima.web.request;

// 注释上面一行无法work
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLDemo {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String username = "张三";

        // 1. URL编码
        String encode = URLEncoder.encode(username, "utf-8");
        System.out.println(encode);

        // 2. URL解码
        // String decode = URLDecoder.decode(encode, "utf-8");
        String decode = URLDecoder.decode(encode, "ISO-8859-1");

        System.out.println(decode);

        // 3. 转换为字节数据,编码
        byte[] bytes = decode.getBytes("ISO-8859-1");
        String s = new String(bytes, "utf-8");
        // String s = new String(bytes, StandardCharsets.UTF_8);

        System.out.println(s);

    }
}
