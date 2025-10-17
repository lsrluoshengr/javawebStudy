package org.example.sign;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Collection;
import java.util.UUID;

@MultipartConfig
@WebServlet("/Register")
public class Register extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String username = req.getParameter("username");
        String password1 = req.getParameter("password1");
        String password2 = req.getParameter("password2");
        String phone = req.getParameter("phone");
        String gender = req.getParameter("gender");
        String[] hobby = req.getParameterValues("hobby");



        resp.setContentType("text/html; charset=utf-8");
        StringBuffer sb = new StringBuffer();

        if (username.isBlank())
            sb.append("必须输入用户名<br>");
        else if (password1.isBlank())
            sb.append("必须输入密码<br>");
        else if (password2.isBlank())
            sb.append("必须输入密码<br>");
        else if (!password1.equals(password2))
            sb.append("两次输入的密码不一致<br>");
        else if (phone.isBlank())
            sb.append("必须输入电话号码<br>");
        else if (gender.isBlank())
            sb.append("必须选择性别<br>");
        else if (hobby.length == 0)
            sb.append("必须选择爱好<br>");


        if (sb.length() != 0) {
            resp.getWriter().println(sb);
            return;
        }
        Collection<Part> parts = req.getParts();
        for(Part p : parts) {
            if (p.getSubmittedFileName() == null)
                continue;
//            UUID uuid = UUID.randomUUID();

            String sfn = p.getSubmittedFileName();
            String suffix = sfn.substring(sfn.lastIndexOf("."));

            long timestamp = new Date().getTime();
            p.write("D:/pictures/" + timestamp + suffix);

        }

        sb.append("用户名：").append(username).append("<br>");
        sb.append("密码：").append(password1).append("<br>");
        sb.append("电话号码：").append(phone).append("<br>");
        sb.append("性别：").append(gender).append("<br>");
        sb.append("爱好：");
        for(String h:hobby) {
            sb.append(h).append("   ");
        }
        sb.append("<br>");

        resp.getWriter().println(sb);
    }
}

