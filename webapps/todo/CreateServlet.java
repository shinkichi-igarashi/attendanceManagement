// package webapps.todo;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.sql.*;

public class CreateServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        if (request.getAttribute("message") == null) {
            request.setAttribute("message", "todoを管理しましょう");
        }
        
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

        String sql = "INSERT INTO posts (title, content) VALUES (?, ?)";

        // sqlに接続できるか、sqlを実行できるか、値が帰ってくるか。
        try (Connection connection = DriverManager.getConnection(url, user, password); PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, title);
            statement.setString(2, content);

            int number = statement.executeUpdate();
            request.setAttribute("message", "タイトル：" + title + "の新規作成が出来ました。");
        } catch (Exception e) {
            request.setAttribute("message", "Exception:" + e.getMessage());
        }

        // webページを表示するファイルを指定。
        String forward = "/list";
        RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
        dispatcher.forward(request, response);
    }
}