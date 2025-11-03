package org.example.sign;

import java.io.FileInputStream;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;



    @WebServlet("/FileDownloader")
    public class FileDownloader extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {
            req.setCharacterEncoding("UTF-8");

            String which = req.getParameter("which");
            if ((which == null) || (which.isBlank())) {
                resp.setContentType("text/html; charset=utf-8");
                resp.sendError(500, "缺少必要的参数数据");
                return;
            }

//这里是Java 17才有的功能，需要在pom.xml中将11改成17
            String name = switch (which) {
                case "PDF" -> "示例Word文档.pdf";
                case "WORD" -> "示例Word文档.docx";
                case "IMAGE1" -> "风景01.jpg";
                case "IMAGE2" -> "风景02.jpg";
                case "IMAGE3" -> "风景03.jpg";
                default -> "wheat.jpg";
            };
            String fn = "D:/Temp/" + name;
            try(FileInputStream fis = new FileInputStream(fn)) {
                String mime = getServletContext().getMimeType(fn);
                resp.setContentType(mime);
                resp.setHeader("content-disposition","attachment;filename="
                        + URLEncoder.encode(name, StandardCharsets.UTF_8));
                byte[] b = new byte[1024];
                while(fis.read(b)>0) {
                    resp.getOutputStream().write(b);
                }
            }
            catch (FileNotFoundException e) {
                resp.setContentType("text/html; charset=utf-8");
                resp.sendError(500, e.toString());
            }
        }

        }
