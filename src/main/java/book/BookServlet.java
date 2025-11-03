package book;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet("/books")
@MultipartConfig(location = "D:/java/javafile/sign/src/main/webapp/upload")
public class BookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 获取文本信息
        Book book = new Book();
        book.setName(request.getParameter("name"));
        book.setPublisher(request.getParameter("publisher"));
        book.setEditor(request.getParameter("editor"));
        book.setPrice(Double.parseDouble(request.getParameter("price")));
        book.setIntro(request.getParameter("intro"));
        book.setType(request.getParameter("type"));

        // 处理封面上传
        Part part = request.getPart("cover");
        String fileName = UUID.randomUUID().toString() +
                part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf("."));
        part.write(fileName);
        book.setCover("upload/" + fileName);

        // 保存到数据库
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement("INSERT INTO book(name,publisher,editor,price,intro,cover,type) VALUES(?,?,?,?,?,?,?)");
            ps.setString(1, book.getName());
            ps.setString(2, book.getPublisher());
            ps.setString(3, book.getEditor());
            ps.setDouble(4, book.getPrice());
            ps.setString(5, book.getIntro());
            ps.setString(6, book.getCover());
            ps.setString(7, book.getType());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, null);
        }

        // 传递书籍信息到展示页
        request.setAttribute("book", book);
        request.getRequestDispatcher("bookshow.jsp").forward(request, response);
    }
}