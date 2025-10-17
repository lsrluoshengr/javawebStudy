package org.example.sign;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/Image")
public class Image extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FileInputStream fis = new FileInputStream("D:\\pictures\\MCshot\\mcimage.png");
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);
        fis.close();
        resp.setContentType("image/png");
        ServletOutputStream op = resp.getOutputStream();
        op.write(bytes);
        op.close();
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
