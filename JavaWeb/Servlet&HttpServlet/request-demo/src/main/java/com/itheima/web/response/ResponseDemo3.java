package com.itheima.web.response;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 响应字符数据：设置字符数据的响应体
 */
@WebServlet("/resp3")
public class ResponseDemo3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置响应的内容需html解析而不是作为纯文本展示,设置字符集防止中文乱码.
        response.setContentType("text/html;charset=utf-8");
        // 1. 获取字符输出流
        PrintWriter writer = response.getWriter();

        // 2. 展示到网页.
        writer.write("你好");
        writer.write("<h1>aaa</h1>");
        // 细节：流不需要关闭

        // 仅仅只可以设置需要将响应的内容需要经过浏览器html解析展示,否则作为纯文本展示.
        // response.setHeader("content-type","text/html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
}