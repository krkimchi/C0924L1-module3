<%--
  Created by IntelliJ IDEA.
  User: Owner
  Date: 4/11/2025
  Time: 12:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="view/login/login.css">
</head>
<body>
<div class="wrapper">
    <div class="form-header">
        <div class="title-login">Login</div>
    </div>

    <% if (request.getAttribute("errorMessage") != null) { %>
    <div class="error-message">
        <p><%= request.getAttribute("errorMessage") %>
        </p>
    </div>
    <% } %>

    <form action="<%=request.getContextPath()%>/login" method="post" class="login-form" autocomplete="off">
        <div class="input-box">
            <input type="text" class="input-field" name="username" required>
            <label class="label">Username</label>
            <i class='bx bx-user icon'></i>
        </div>
        <div class="input-box">
            <input type="password" class="input-field" name="password" required>
            <label class="label">Password</label>
            <i class='bx bx-lock-alt icon'></i>
        </div>
        <div class="input-box">
            <button type="submit" class="btn-submit">Sign In</button>
        </div>
        <div class="switch-form">
            <span>Don't have an account? <a href="<%=request.getContextPath()%>/register">Register</a></span>
        </div>
    </form>
</div>
</body>
</html>

