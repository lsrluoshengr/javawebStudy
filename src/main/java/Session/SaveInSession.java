package Session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebServlet("/SaveInSession")
public class SaveInSession extends HttpServlet {
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

        HttpSession httpSession = req.getSession(true);
        httpSession.setMaxInactiveInterval(60*60);
        httpSession.setAttribute("title", title);
        httpSession.setAttribute("desc", desc);

        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().println("<h2 style='text-align: center'>" +
                "<a href='./ViewSession'>查看今日任务</a></h2>");
    }
}
