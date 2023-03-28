// package webapps.todo;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.sql.*;

public class EditServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        if (request.getAttribute("message") == null) {
            request.setAttribute("message", "todoを管理しましょう");
        }

        int postId = Integer.parseInt(request.getParameter("id"));

        String url = "jdbc:mysql://localhost/todo";
        String user = "root";
        String password = "Linus@4869";

        // jdbcがきちんと動いているか。
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {

            e.printStackTrace();
        }

        String sql = "SELECT * FROM posts WHERE id = ?";

        // sqlに接続できるか、sqlを実行できるか、値が帰ってくるか。
        try (Connection connection = DriverManager.getConnection(url, user, password); PreparedStatement statement = connection.prepareStatement(sql);) {
            
            statement.setInt(1, postId);
            ResultSet results = statement.executeQuery();

            while (results.next()) {


                String id = results.getString("id");
                request.setAttribute("id", id);
                String title = results.getString("title");
                request.setAttribute("title", title);
                String content = results.getString("content").replace("\n", "<br>");
                request.setAttribute("content", content);
            }

        } catch (Exception e) {
            request.setAttribute("message", "Exception:" + e.getMessage());
        }

        // webページを表示するファイルを指定。
        String view = "/WEB-INF/views/edit.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
}