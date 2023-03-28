<%@include file="./template/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%><%@ page import="java.util.*" %>
<main>
  <div style="height: 10vh;"></div>

  <div class="d-flex align-items-center justify-content-center p-1" style="height: 85vh;">
    <div class="border col-7 p-3">
      <br>
      <h2>登録・更新</h2>
      <% String message = (String) request.getAttribute("message");%>
      <p><%= message%></p>
      <br>
      <div class="row">
        <form action="update" method="get">
          <div class="form-group p-2">
            <label>日付</label>
            <input type="text" name="date" class="form-control" value='<%= request.getAttribute("date") %>' readonly>
          </div>
          <div class="form-group p-2">
            <label>出勤時間</label>
            <input type="text" name="start_time" class="form-control" value='<%= request.getAttribute("start_time") %>'>
          </div>
          <div class="form-group p-2">
            <label>退勤時間</label>
            <input type="text" name="end_time" class="form-control" value='<%= request.getAttribute("end_time") %>'>
          </div>
          <div class="form-group p-2">
            <label>備考</label>
            <input type="text" name="remarks" class="form-control" value='<%= request.getAttribute("remarks") %>'>
          </div>
          <div class="row center-block text-center p-3">
            <div class="col-1">
            </div>
            <div class="col-5">
              <button type="button" class="btn btn-outline-secondary btn-block">
                <!-- HTMLファイル内で”一つ前の画面に戻る遷移”を行う -->
                <a onclick="history.back()">閉じる</a>
              </button>
            </div>
            <div class="col-5">
              <button type="submit" class="btn btn-outline-primary btn-block">登録・更新</button>
            </div>
            <input type="hidden" name="work_record_id" value='<%= request.getAttribute("work_record_id") %>'>
            <input type="hidden" name="user_id" value='<%= request.getAttribute("user_id") %>'>
          </div>
        </form>
      </div>
      <br>
    </div>


  </div>

</main>
<%@include file="./template/footer.jsp"%>