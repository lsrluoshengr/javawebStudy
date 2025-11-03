package Session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@WebServlet("/ViewSession")
public class ViewSession extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession httpSession = req.getSession();

        resp.setContentType("text/html; charset=utf-8");
        String head = """
                <!DOCTYPE html>
                        <html lang="en">
                        <head>
                          <meta charset="UTF-8">
                          <title>显示今日任务</title>
                          <link href="./css/style.css" rel="stylesheet" type="text/css" />
                        </head>
                        <body>
                """;
        resp.getWriter().println(head);
        resp.getWriter().println("<h2 style=\"text-align: center\">今日任务</h2>");
        resp.getWriter().println("<div>");

        // 修复字符串拼接和引号问题
        String titleValue = httpSession.getAttribute("title") != null ?
                httpSession.getAttribute("title").toString() : "";
        resp.getWriter().println("<input class='title' type='text' value='" +
                titleValue + "' contenteditable='false'>");

        String descValue = httpSession.getAttribute("desc") != null ?
                httpSession.getAttribute("desc").toString() : "";
        resp.getWriter().println("<textarea class='title desc' contenteditable='false'>" +
                descValue);
        resp.getWriter().println("</textarea></div></body></html>");
    }
}
