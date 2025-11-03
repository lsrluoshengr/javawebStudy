import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



@WebServlet(value = "/Login",initParams = {@WebInitParam(name = "users",value = "admin,123,1",description = "用户名"),
        @WebInitParam(name = "passwords",value = "123456,ool,1",description = "密码"),
        @WebInitParam(name = "usertypes",value = "admin,user,admin",description = "用户类型")
})
public class Login extends HttpServlet {
    private List<String> users,passwords,usertypes;
    private int count;

    @Override
    public void init(ServletConfig  config) throws ServletException {
        super.init(config);
        log("初始化成功,Method -init- had been used");
        users = new ArrayList<>();
        passwords = new ArrayList<>();
        usertypes = new ArrayList<>();
        String s1 =getInitParameter("users");
        String[] ss=s1.split(",");
        Collections.addAll(users,ss);
        s1 = getInitParameter("passwords");
        ss=s1.split(",");
        Collections.addAll(passwords,ss);
        s1 = getInitParameter("usertypes");
        ss=s1.split(",");
        Collections.addAll(usertypes,ss);
        count = users.size();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
       String usertype = req.getParameter("usertype");

        for (int i = 0; i < count; i++) {
            if (username.equals(users.get( i))&&
                    password.equals(passwords.get(i)) &&
                    usertype.equals(usertypes.get(i))
                ) {
                resp.setContentType("text/html; charset=utf-8");
                String message = "登录成功"+ username+"欢迎你"+","+ password+","+usertype;

                log(message);
                req.getSession().setAttribute("username",username);
                req.getRequestDispatcher("index.jsp").forward(req,resp);
                return;
            }

        }
resp.setContentType("text/html; charset=utf-8");
resp.getWriter().println("<script>alert('登录失败：账号或密码错误!" + username + "'); window.location.href='loginer.jsp';</script>");


    }
}
