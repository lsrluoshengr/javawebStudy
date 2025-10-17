package org.example.sign;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;
@WebServlet("/Headers")
public class Headers extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String ua = req.getHeader("user-agent");
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().println(ua+"<br><br>");

        Enumeration<String> names = req.getHeaderNames();
        StringBuilder info = new StringBuilder();
        while(names.hasMoreElements()) {
            String name = names.nextElement();
            info.append(name).append(":").append(req.getHeader(name));
            info.append("<br>");
        }
        resp.getWriter().println(info);
    }
}
