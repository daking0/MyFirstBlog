<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="container">
    <form>
      <div class="form-group">
        <label for="username">Username:</label>
        <input type="text" class="form-control" placeholder="Enter Username" id="username">
      </div>
      <div class="form-group">
        <label for="pwd">Password:</label>
        <input type="password" class="form-control" placeholder="Enter password" id="pwd">
      </div>
      <div class="form-group">
        <label for="email">Email address:</label>
        <input type="email" class="form-control" placeholder="Enter email" id="email">
      </div>


      <button id="btn-save" class="btn btn-primary">회원가입 완료</button>
    </form>
</div>

<script src="/blog/js/user.js">
<%@ include file="../layout/footer.jsp" %>


