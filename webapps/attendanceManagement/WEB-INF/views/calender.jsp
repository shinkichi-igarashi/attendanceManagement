<%@include file="./template/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%><%@ page import="java.util.*" %>
<main>
    <div style="height: 10vh;"></div>

    <div class="d-flex align-items-center justify-content-center p-1">
        <div class="border col-7 p-3">
            <br>
            <h5>勤怠管理ページ</h2>
            <% String message = (String) request.getAttribute("message");%>
            <p><%= message %></p>
                <br>
                <div class="row">
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col" class="text-center">日付</th>
                                <th scope="col" class="text-center">出勤時間</th>
                                <th scope="col" class="text-center">退勤時間</th>
                                <th scope="col" class="text-center">備考</th>
                            </tr>
                        </thead>
                        <%
                        ArrayList<HashMap<String, String>> rows = (ArrayList<HashMap<String, String>>)request.getAttribute("rows");
                        %>
                    <tbody>
                        <%
                            for (HashMap<String, String> columns : rows) {
                        %>
                            <tr>
                                <th scope="row"><a href='edit?work_record_id=<%= columns.get("work_record_id") %>'><%= columns.get("date") %></a></th>
                                <td><%= columns.get("start_time") %></td>
                                <td><%= columns.get("end_time") %></td>
                                <td><%= columns.get("remarks") %></td>
                            </tr>
                        <% } %>
                    </tbody>
                    </table>
                </div>
                <br>
        </div>


    </div>
    <div style="height: 10vh;"></div>
</main>
<%@include file="./template/footer.jsp"%>