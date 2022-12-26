<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="container">
    <form>
      <input type="hidden" id="id" value="${principal.user.id}" />
      <div class="form-group">
        <label for="username">Username:</label>
        <input type="text" value="${principal.user.username}" class="form-control" placeholder="Enter Username" id="username" readonly>
      </div>
      <div class="form-group">
        <label for="pwd">Password:</label>
        <input type="password" class="form-control" placeholder="Enter password" id="pwd">
      </div>
      <div class="form-group">
        <label for="email">Email address:</label>
        <input type="email" value="${principal.user.email}" class="form-control" placeholder="Enter email" id="email">
      </div>
    </form>
    <button id="btn-update" class="btn btn-primary">회원수정 완료</button>
</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>


