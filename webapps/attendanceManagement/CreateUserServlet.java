import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.sql.*;

public class CreateUserServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // if (request.getAttribute("message") == null) {
        //     request.setAttribute("message", "ログインしてください");
        // }

        String name = request.getParameter("name");
        String department = request.getParameter("department");
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");

        String url = "jdbc:mysql://localhost/attendanceManagement";
        String user = "root";
        String DBpassword = "Linus@4869";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sql = "INSERT INTO user (name, department, mail, password) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(url, user, DBpassword);
        PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, name);
            statement.setString(2, department);
            statement.setString(3, mail);
            statement.setString(4, password);
            int number = statement.executeUpdate();

            request.setAttribute("message", "名前：" + name + "の新規作成ができました");

        } catch (Exception e) {
            request.setAttribute("message", "Exception:" + e.getMessage());
        }

        String forward = "/login";
        RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
        dispatcher.forward(request, response);

        // String view = "/WEB-INF/views/login.jsp";
        // RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        // dispatcher.forward(request, response);
        
    }
}
