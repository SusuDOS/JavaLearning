package com.itheima.web.request;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 请求转发:访问req5->req5设置一个属性再转发到req6->req6再处理，取出数据...
 */
@WebServlet("/req5")
public class RequestDemo5 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("demo5...");
        System.out.println(request);
        // 存储数据
        // 难道添加上面数据直接拿着request直接setAttribute就行？
        request.setAttribute("msg", "hello");

        // 请求转发
        request.getRequestDispatcher("/req6").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
}