package org.example.sign;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Random;

@WebServlet("/RandomImage")
public class RandomImage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Random r = new Random();
        int which = Math.abs(r.nextInt() % 5) + 1;
        String name = "风景0" + which + ".jpg";
        String fn = "D:/pictures/view/" + name;

        String mime = getServletContext().getMimeType(fn);
        resp.setContentType(mime);
        resp.setHeader("content-disposition","inline;filename="
                + URLEncoder.encode(name, StandardCharsets.UTF_8));

        try(FileInputStream fis = new FileInputStream(fn)) {
            byte[] b = new byte[1024];
            while(fis.read(b)>0) {
                resp.getOutputStream().write(b);
            }
        }
    }
}
