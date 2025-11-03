package vote;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class VoteInitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 项目启动时，初始化票数到ServletContext
        ServletContext context = sce.getServletContext();
        context.setAttribute("zhangsan", 0); // 张三初始票数
        context.setAttribute("lisi", 0);     // 李四初始票数
        context.setAttribute("wanger", 0);   // 王二初始票数
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {}
}