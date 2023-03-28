<%@include file="./template/header.jsp"%> <%@ page language="java"
contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ page
import="java.util.*" %>

<main>
  <div style="height: 10vh"></div>

  <div class="d-flex align-items-center justify-content-center p-1" style="height: 85vh">
    <div class="border col-7 p-3">
      <br />
      <h2>新規登録</h2>
      <% String message = (String) request.getAttribute("message");%>
      <p><%= message %></p>
      <br />
      <div class="row">
        <div class="col-md">
          <form action="createuser" method="get">
            <div class="form-group">
              <label>氏名：</label>
              <input type="text" name="name" class="form-control" placeholder="山田 太郎" />
            </div>
            <div class="form-group">
              <label>メール：</label>
              <input type="text" name="mail" class="form-control" placeholder="yamada@mail.com" />
            </div>
            <div class="form-group">
              <label>部署</label>
              <input type="text" name="department" class="form-control" placeholder="IT本部" />
            </div>
            <div class="form-group">
              <label>パスワード：</label>
              <input type="password" name="password" class="form-control" />
            </div>
            <div class="row center-block text-center p-3">
              <div class="col-1"></div>
              <div class="col-5">
                <button type="button" class="btn btn-outline-secondary btn-block">
                  <a href="list">閉じる</a>
                </button>
              </div>
              <div class="col-5">
                <button type="submit" class="btn btn-outline-primary btn-block">
                  新規登録
                </button>
              </div>
            </div>
          </form>
        </div>
      </div>
      <br />
    </div>
  </div>
</main>
<%@include file="./template/footer.jsp"%>