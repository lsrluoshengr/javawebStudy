package cookie;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/SaveInCookie")
public class SaveInCookie extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String title = req.getParameter("title");
        String desc = req.getParameter("desc");

        if ((title == null) || (title.isBlank()) || (desc == null) || desc.isBlank()) {
            resp.setContentType("text/html; charset=utf-8");
            resp.getWriter().println("任务名称及任务描述都不能为空");
            return;
        }

        Cookie cookie_title = new Cookie("title",
                URLEncoder.encode(title, StandardCharsets.UTF_8));
        cookie_title.setMaxAge(24 * 60 * 60);
        Cookie cookie_desc = new Cookie("desc",
                URLEncoder.encode(desc, StandardCharsets.UTF_8));
        cookie_desc.setMaxAge(24 * 60 * 60);
        resp.addCookie(cookie_title);
        resp.addCookie(cookie_desc);

        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().println("<h2 style='text-align: center'>" +
                "<a href='./ViewCookie'>查看今日任务</a></h2>");
    }
}
