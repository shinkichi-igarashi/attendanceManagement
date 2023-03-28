import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.sql.*;


public class ListServlet extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getAttribute("message") == null) {
            request.setAttribute("message", "userを登録してください");
        }

        String url = "jdbc:mysql://localhost/attendanceManagement";
        String user = "root";
        String DBpassword = "Linus@4869";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sql = "SELECT * FROM user";
        try (Connection connection = DriverManager.getConnection(url, user, DBpassword);
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet results = statement.executeQuery()) {
            
            ArrayList<HashMap<String, String>> rows = new ArrayList<HashMap<String, String>>();

            while (results.next()) {
                HashMap<String, String> columns = new HashMap<String, String>();

                String user_id = results.getString("user_id");
                columns.put("user_id", user_id);
                String name = results.getString("name");
                columns.put("name", name);
                String department = results.getString("department");
                columns.put("department", department);
                String mail = results.getString("mail");
                columns.put("mail", mail);

                rows.add(columns);
             }

             request.setAttribute("rows", rows);
        } catch (Exception e) {
            request.setAttribute("message", "Exception:" + e.getMessage());
        }

        String view = "/WEB-INF/views/list.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
}