import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.sql.*;

public class EditServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("message", "登録・更新ページです");

        int work_record_id = Integer.parseInt(request.getParameter("work_record_id"));

        String url = "jdbc:mysql://localhost/attendanceManagement";
        String user = "root";
        String DBpassword = "Linus@4869";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sql = "SELECT * FROM work_record WHERE work_record_id = ?";

        try (Connection connection = DriverManager.getConnection(url, user, DBpassword); PreparedStatement statement = connection.prepareStatement(sql);) {
            
            statement.setInt(1, work_record_id);
            ResultSet results = statement.executeQuery();

            while (results.next()) {

                work_record_id = results.getInt("work_record_id");
                request.setAttribute("work_record_id", work_record_id);

                int user_id = results.getInt("user_id");
                request.setAttribute("user_id", user_id);

                String date = results.getString("date");
                request.setAttribute("date", date);

                String start_time = results.getString("start_time");
                request.setAttribute("start_time", start_time);

                String end_time = results.getString("end_time");
                request.setAttribute("end_time", end_time);
                
                String remarks = results.getString("remarks");
                request.setAttribute("remarks", remarks);
            }

        } catch (Exception e) {
            request.setAttribute("message", "Exception:" + e.getMessage());
        }

        String view = "/WEB-INF/views/edit.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
}