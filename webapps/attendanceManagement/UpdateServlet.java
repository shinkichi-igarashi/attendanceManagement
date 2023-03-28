import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.sql.*;

public class UpdateServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int work_record_id = Integer.parseInt(request.getParameter("work_record_id"));
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        String date = request.getParameter("date");
        String start_time = request.getParameter("start_time");
        String end_time = request.getParameter("end_time");
        String remarks = request.getParameter("remarks");

        String url = "jdbc:mysql://localhost/attendanceManagement";
        String user = "root";
        String DBpassword = "Linus@4869";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sql = "UPDATE work_record SET date = ?, start_time = ?, end_time = ?, remarks = ? WHERE work_record_id = ?";
        try (Connection connection = DriverManager.getConnection(url, user, DBpassword); PreparedStatement statement = connection.prepareStatement(sql);) {
            
            statement.setString(1, date);
            statement.setString(2, start_time);
            statement.setString(3, end_time);
            statement.setString(4, remarks);
            statement.setInt(5, work_record_id);
            int number = statement.executeUpdate();
            request.setAttribute("message", date + "の更新・登録が出来ました。");

        } catch (Exception e) {
            request.setAttribute("message", "Exception:" + e.getMessage());
        }

        String forward = "/calender?id=" + user_id;
        RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
        dispatcher.forward(request, response);
    }
}