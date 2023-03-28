// package webapps.todo;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.sql.*;

public class NewServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        request.setAttribute("message", "新規作成ページです");

        // "webページを表示するファイルを指定。"
        String view = "/WEB-INF/views/new.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
}