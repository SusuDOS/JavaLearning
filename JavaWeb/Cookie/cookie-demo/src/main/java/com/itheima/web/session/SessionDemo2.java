package com.itheima.web.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//从服务器的session中获取数据.

@WebServlet("/demo2")
public class SessionDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // // 1. 获取Session对象
        HttpSession session = request.getSession();
        System.out.println(session);

        // 销毁
        // session.invalidate();

        // 2. 获取数据
        Object username = session.getAttribute("username");
        System.out.println(username);
        System.out.println("demo2....");

        /* 控制台显示:zs */

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
}