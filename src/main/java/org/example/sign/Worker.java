package org.example.sign;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/Worker")
public class Worker extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
        String reason = (String)req.getAttribute("reason");
        String info = "";
        if (reason == null) {
            info = "客户端直接请求我服务";
        }
        else {
            info = "我接替Lazy工作，因为：" + reason;
        }
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().println(info);
    }
}
