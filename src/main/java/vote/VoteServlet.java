package vote;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/VoteServlet")
public class VoteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取用户选择的候选人（表单提交的value）
        String candidate = req.getParameter("candidate");
        if (candidate == null || candidate.isEmpty()) {
            resp.sendRedirect("vote.jsp"); // 未选择则返回投票页
            return;
        }

        // 2. 从ServletContext获取当前票数（注意线程安全，用同步代码块）
        ServletContext context = getServletContext();
        synchronized (context) { // 防止并发投票导致票数错误
            int count;
            switch (candidate) {
                case "zhangsan":
                    count = (int) context.getAttribute("zhangsan");
                    context.setAttribute("zhangsan", count + 1);
                    break;
                case "lisi":
                    count = (int) context.getAttribute("lisi");
                    context.setAttribute("lisi", count + 1);
                    break;
                case "wanger":
                    count = (int) context.getAttribute("wanger");
                    context.setAttribute("wanger", count + 1);
                    break;
                default:
                    resp.sendRedirect("vote.jsp");
                    return;
            }
        }

        // 3. 投票成功后跳转到结果页
        resp.sendRedirect("ResultServlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp); // 支持GET请求（可选）
    }
}