package org.example.sign;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/Lazy")
public class Lazy extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("reason", "Lazy我太累了，休息一会儿");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/Worker");
        //如下两行代码不会起作用，因为一旦转发了请求，在输出流中内容将被清空
        //resp.setContentType("text/html; charset=utf-8");
        //resp.getWriter().println("转发请求给Worker....");
        dispatcher.forward(req, resp);
    }
}
