package vote;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        // 从ServletContext获取票数
        ServletContext context = getServletContext();
        int zs = (int) context.getAttribute("zhangsan");
        int ls = (int) context.getAttribute("lisi");
        int we = (int) context.getAttribute("wanger");

        // 计算总票数
        int total = zs + ls + we;

        // 展示结果
        out.println("<html><head><title>投票结果</title></head><body>");
        out.println("<h2>班长选举结果统计</h2>");
        out.println("张三：" + zs + "票（" + (total == 0 ? 0 : (zs * 100 / total)) + "%）<br>");
        out.println("李四：" + ls + "票（" + (total == 0 ? 0 : (ls * 100 / total)) + "%）<br>");
        out.println("王二：" + we + "票（" + (total == 0 ? 0 : (we * 100 / total)) + "%）<br>");
        out.println("<br>总票数：" + total + "<br>");
        out.println("<a href='vote.jsp'>返回继续投票</a>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}