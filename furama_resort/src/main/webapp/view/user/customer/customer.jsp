<%--
  Created by IntelliJ IDEA.
  User: Owner
  Date: 4/19/2025
  Time: 3:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Management</title>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="view/user/customer/customer.css">
</head>
<body>
<div class="wrapper">
    <h2><i class="bx bx-user icon"></i>Customer Management</h2>

    <div class="header-container">
        <h3>List of Customers</h3>
        <div class="search-container">
            <button id="search-btn" onclick="document.getElementById('search-form').submit();">
                <i class="bx bx-search icon"></i>
            </button>
            <form id="search-form" action="${pageContext.request.contextPath}/customer" method="post">
                <input type="hidden" name="action" value="search">
                <input type="hidden" name="currentPage" value="${currentPage}">
                <input type="text" id="search" name="search" value="${searchQuery}" placeholder="Enter Customer Name or Email"/>
            </form>
        </div>
    </div>

    <div class="header-container">
        <button id="add-btn" class="btn" onclick="showAddForm()">
            <i class="bx bx-plus icon"></i>Add New Customer
        </button>
    </div>

    <form id="add-form" action="${pageContext.request.contextPath}/customer" method="post" class="add-form">
        <input type="hidden" name="action" value="add">
        <input type="hidden" name="currentPage" value="${currentPage}">
        <input type="hidden" name="search" value="${searchQuery}">

        <div class="form-grid">
            <div class="form-item">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required pattern="KH-\d{4}" title="Mã khách hàng phải theo định dạng KH-0001">
            </div>
            <div class="form-item">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>

            <div class="form-item">
                <label for="full_name">Full Name:</label>
                <input type="text" id="full_name" name="full_name" required>
            </div>
            <div class="form-item">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email">
            </div>

            <div class="form-item">
                <label for="phone">Phone:</label>
                <input type="text" id="phone" name="phone" pattern="(090|091|\(84\)\+90|\(84\)\+91)\d{7}" title="Số điện thoại phải đúng định dạng 090xxxxxxx, 091xxxxxxx, (84)+90xxxxxxx hoặc (84)+91xxxxxxx">
            </div>
            <div class="form-item">
                <label for="address">Address:</label>
                <input type="text" id="address" name="address">
            </div>

            <div class="form-item">
                <label for="id_card">ID Card:</label>
                <input type="text" id="id_card" name="id_card" pattern="\d{9}(\d{3})?" title="CMND phải có 9 hoặc 12 chữ số">
            </div>
            <div class="form-item">
                <label for="birthday">Birthday:</label>
                <input type="date" id="birthday" name="birthday">
            </div>

            <div class="form-item">
                <label for="gender">Gender:</label>
                <select id="gender" name="gender">
                    <option value="">Select Gender</option>
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                </select>
            </div>

            <div class="form-item">
                <label for="customer_type_id">Customer Type:</label>
                <select id="customer_type_id" name="customer_type_id" required>
                    <option value="">Select Customer Type</option>
                    <c:forEach var="type" items="${customerTypes}">
                        <option value="${type.customerTypeId}">${type.customerTypeName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div style="text-align: center; margin-top: 20px;">
            <button type="submit" id="confirm-add-btn"><i class="bx bx-check icon"></i>Confirm Add</button>
        </div>
    </form>

    <c:if test="${not empty error}">
        <div style="color: red; text-align: center; margin: 10px 0;">${error}</div>
    </c:if>

</div>
<script>
    function showAddForm() {
        document.getElementById("add-form").classList.add("active");
        document.getElementById("add-btn").style.display = "none";
    }
    document.getElementById("add-form").addEventListener("submit", function () {
        document.getElementById("confirm-add-btn").disabled = true;
    });
</script>
</body>
</html>