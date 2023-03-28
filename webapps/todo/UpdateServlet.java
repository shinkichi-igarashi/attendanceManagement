// package webapps.todo;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.sql.*;

public class UpdateServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        if (request.getAttribute("message") == null) {
            request.setAttribute("message", "todoを管理しましょう");
        }

        int postId = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        String url = "jdbc:mysql://localhost/todo";
        String user = "root";
        String password = "Linus@4869";

        // jdbcがきちんと動いているか。
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {

            e.printStackTrace();
        }

        String sql = "UPDATE posts SET title = ?, content = ? WHERE id = ?";

        // sqlに接続できるか、sqlを実行できるか、値が帰ってくるか。
        try (Connection connection = DriverManager.getConnection(url, user, password); PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, title);
            statement.setString(2, content);
            statement.setInt(3, postId);
            int number = statement.executeUpdate();
            request.setAttribute("message", "ID:" + postId + "の更新が出来ました。");
        } catch (Exception e) {
            request.setAttribute("message", "Exception:" + e.getMessage());
        }

        // webページを表示するファイルを指定。
        String forward = "/show?id=" + postId;
        RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
        dispatcher.forward(request, response);
    }
}