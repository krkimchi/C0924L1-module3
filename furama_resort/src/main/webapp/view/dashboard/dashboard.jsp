<%--
  Created by IntelliJ IDEA.
  User: Owner
  Date: 4/17/2025
  Time: 10:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Furama Resort - Dashboard</title>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="view/dashboard/dashboard.css">
</head>
<body>

<div class="main-content">
    <div class="navbar">
        <div class="logo-container">
            <img src="resources/images/logo.png" alt="Furama Resort" class="logo">
            <span class="hotel-name">Furama Resort</span>
        </div>
        <nav>
            <ul>
                <li><i class='bx bx-home'></i> <a href="/home" class="nav-link">Home</a></li>
                <li><i class='bx bx-user'></i> <a href="/employee" class="nav-link">Employee</a></li>
                <li><i class='bx bx-group'></i> <a href="/customer" class="nav-link">Customer</a></li>
                <li><i class='bx bx-briefcase'></i> <a href="/service" class="nav-link">Service</a></li>
                <li><i class='bx bx-file'></i> <a href="#contract" class="nav-link">Contract</a></li>
            </ul>
        </nav>
        <div class="auth-buttons">
            <input type="text" class="booking-input" placeholder="Search...">
            <span class="username">${sessionScope.user.username}</span>
            <button class="auth-btn">Logout</button>
        </div>
    </div>
</div>

<div class="content-wrapper">
    <aside class="sidebar">
        <ul>
            <li>Item One</li>
            <li>Item Two</li>
            <li>Item Three</li>
        </ul>
    </aside>

    <main class="main-section">
    </main>
</div>

</body>
</html>