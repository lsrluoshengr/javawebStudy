package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {



    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)  request;
           String[] urls ={"/loginer.jsp","/Login","/css/","/js/"};
           String url = req.getRequestURI().toString();
        for (String u : urls) {
            if(url.contains(u)){
                chain.doFilter(request,response);
                return;
            }
        }

            HttpSession session = req.getSession();
            Object username = session.getAttribute("username");
            if(username != null){
                chain.doFilter(request,response);
            }
            else{
                req.setAttribute("login-message","请先登录");
                req.getRequestDispatcher("loginer.jsp").forward(req,response);
            }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }
}
