package book;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/loginbook")

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement("SELECT * FROM user WHERE username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                // 记录当前登录信息
                String lastIp = rs.getString("last_login_ip");
                String lastTime = rs.getString("last_login_time");

                // 更新登录信息
                ps = conn.prepareStatement("UPDATE user SET last_login_ip=?, last_login_time=? WHERE username=?");
                ps.setString(1, request.getRemoteAddr());
                ps.setString(2, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                ps.setString(3, username);
                ps.executeUpdate();

                // 保存用户信息到会话
                User user = new User();
                user.setUsername(username);
                user.setLastLoginIp(lastIp);
                user.setLastLoginTime(lastTime);
                request.getSession().setAttribute("user", user);

                response.sendRedirect("main.jsp");
            } else {
                request.setAttribute("msg", "用户名或密码错误");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
    }
}