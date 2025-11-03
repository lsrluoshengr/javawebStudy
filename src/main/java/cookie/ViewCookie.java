package cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
@WebServlet("/ViewCookie")
public class ViewCookie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Cookie[] cookies = req.getCookies();
        String title = "", desc = "";
        for(Cookie c : cookies) {
            if (c.getName().equalsIgnoreCase("title")) {
                title = c.getValue();
            }
            else if (c.getName().equalsIgnoreCase("desc")) {
                desc = c.getValue();
            }
        }

        resp.setContentType("text/html; charset=utf-8");
        String head = """
                <!DOCTYPE html>
                        <html lang="en">
                        <head>
                          <meta charset="UTF-8">
                          <title>录入今日任务</title>
                          <link href="./css/cookies.css" rel="stylesheet" type="text/css" />
                        </head>
                        <body>
                """;
        resp.getWriter().println(head);
        resp.getWriter().println("<h2 style=\"text-align: center\">今日任务</h2>");
        resp.getWriter().println("<div>");
        resp.getWriter().println("<input class='title' type='text' value='" +
                URLDecoder.decode(title, StandardCharsets.UTF_8) +
                "' contenteditable='false'>");
        resp.getWriter().println("<textarea class='title desc' contenteditable='false'>" +
                URLDecoder.decode(desc, StandardCharsets.UTF_8));
        resp.getWriter().println("</textarea></div></body></html>");
    }
}
