<%--
  Created by IntelliJ IDEA.
  User: Owner
  Date: 4/11/2025
  Time: 4:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Furama Resort</title>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="view/home/home.css">
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
                <li><i class='bx bx-hotel'></i> <a href="#room" class="nav-link">Rooms</a></li>
                <li><i class='bx bx-gift'></i> <a href="#offers" class="nav-link">Offers</a></li>
                <li><i class='bx bx-photo-album'></i> <a href="#gallery" class="nav-link">Gallery</a></li>
                <li><i class='bx bx-spa'></i> <a href="#amenities" class="nav-link">Amenities</a></li>
                <li><i class='bx bx-money'></i> <a href="#prices" class="nav-link">Prices</a></li>
            </ul>
        </nav>
        <div class="auth-buttons">
            <button class="auth-btn">Login</button>
            <button class="auth-btn signup">Signup</button>
        </div>
    </div>

    <div class="booking-bar">
        <div class="booking-form">
            <select class="booking-input">
                <option value="">Select service type</option>
                <option value="villa">Villa</option>
                <option value="house">House</option>
                <option value="room">Room</option>
            </select>

            <select class="booking-input" id="service-name" disabled>
                <option value="">Select service name</option>
            </select>

            <select class="booking-input" id="rent-type">
                <option value="">Select rent type</option>
                <option value="year">Yearly</option>
                <option value="month">Monthly</option>
                <option value="day">Daily</option>
                <option value="hour">Hourly</option>
            </select>

            <input type="date" class="booking-input" placeholder="Check-in date">
            <input type="date" class="booking-input" placeholder="Check-out date">

        </div>
        <button class="booking-btn">Book Now</button>
    </div>

</div>

<footer class="social-section">
    <a href="#" class="social-icon"><i class='bx bxl-facebook'></i></a>
    <a href="#" class="social-icon"><i class='bx bxl-twitter'></i></a>
    <a href="#" class="social-icon"><i class='bx bxl-instagram'></i></a>
    <a href="#" class="social-icon"><i class='bx bxl-youtube'></i></a>
</footer>

</body>
</html>
