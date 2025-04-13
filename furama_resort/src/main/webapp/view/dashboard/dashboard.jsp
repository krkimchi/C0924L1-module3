<%--
  Created by IntelliJ IDEA.
  User: Owner
  Date: 4/11/2025
  Time: 12:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Page</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
            border-bottom: 1px solid #000;
        }

        .logo {
            width: 50px;
            height: 50px;
            border: 1px solid #000;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .username {
            font-size: 16px;
        }

        .navbar {
            display: flex;
            justify-content: space-between;
            padding: 10px;
            border-bottom: 1px solid #000;
        }

        .nav-links {
            display: flex;
            gap: 20px;
        }

        .nav-links a {
            text-decoration: none;
            color: #000;
        }

        .search-bar input {
            padding: 5px;
            width: 200px;
            border: 1px solid #000;
            border-radius: 15px;
        }

        .main {
            display: flex;
            min-height: 400px;
        }

        .sidebar {
            width: 200px;
            border-right: 1px solid #000;
            padding: 10px;
        }

        .sidebar ul {
            list-style: none;
        }

        .sidebar ul li {
            margin: 10px 0;
        }

        .content {
            flex-grow: 1;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .footer {
            padding: 10px;
            text-align: center;
            border-top: 1px solid #000;
        }
    </style>
</head>
<body>

<div class="header">
    <div class="logo">Logo</div>
    <div class="username">${username}</div> <!-- Lấy username từ Servlet -->
</div>

<!-- Navigation Bar -->
<div class="navbar">
    <div class="nav-links">
        <a href="/home">Home</a>
        <a href="/employee">Employee</a>
        <a href="/customer">Customer</a>
        <a href="#">Service</a>
        <a href="#">Contract</a>
    </div>
    <div class="search-bar">
        <input type="text" placeholder="search">
    </div>
</div>

<!-- Main Content -->
<div class="main">
    <div class="sidebar">
        <ul>
            <li>Item One</li>
            <li>Item Two</li>
            <li>Item Three</li>
        </ul>
    </div>
    <div class="content">
        <p>Body...</p>
    </div>
</div>

<!-- Footer -->
<div class="footer">
    <p>Footer...</p>
</div>
</body>
</html>
