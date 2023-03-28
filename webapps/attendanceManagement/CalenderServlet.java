import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.sql.*;

public class CalenderServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int user_id = Integer.parseInt(request.getParameter("user_id"));

        String url = "jdbc:mysql://localhost/attendanceManagement";
        String user = "root";
        String DBpassword = "Linus@4869";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sql = "SELECT * FROM user WHERE user_id = ?";
        try (Connection connection = DriverManager.getConnection(url, user, DBpassword);
        PreparedStatement statement = connection.prepareStatement(sql);) {
            
            statement.setInt(1, user_id);
            ResultSet results = statement.executeQuery();

            while (results.next()) {

                request.setAttribute("user_id", user_id);

                // ログインしたユーザーのwork_recordを抽出する。
                String sql_work_record = "SELECT work_record_id, date, start_time, end_time, remarks FROM work_record WHERE user_id = ?";

                try (Connection connection2 = DriverManager.getConnection(url, user, DBpassword);
                    PreparedStatement statement2 = connection.prepareStatement(sql_work_record);) {
                    
                    statement2.setInt(1, user_id);
                    ResultSet results2 = statement2.executeQuery();
                    ArrayList<HashMap<String, String>> rows = new ArrayList<HashMap<String, String>>();

                    while (results2.next()) {
                        HashMap<String, String> columns = new HashMap<String, String>();
                        
                        String work_record_id = results2.getString("work_record_id");
                        columns.put("work_record_id", work_record_id);
                        String date = results2.getString("date");
                        columns.put("date", date);
                        String start_time = results2.getString("start_time");
                        columns.put("start_time", start_time);
                        String end_time = results2.getString("end_time");
                        columns.put("end_time", end_time);
                        String remarks = results2.getString("remarks");
                        columns.put("remarks", remarks);

                        rows.add(columns);
                    }

                    request.setAttribute("rows", rows);
                } catch (Exception e) {
                    request.setAttribute("message", "Exception:" + e.getMessage());
                }


                String name = results.getString("name");
                request.setAttribute("name", name);

                String department = results.getString("department");
                request.setAttribute("department", department);

                String mail = results.getString("mail");
                request.setAttribute("mail", mail);

                String password = results.getString("password");
                request.setAttribute("password", password);

            }

        } catch (Exception e) {
            request.setAttribute("message", "Exception:" + e.getMessage());
        }

        String view = "/WEB-INF/views/calender.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
}