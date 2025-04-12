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
                <input type="text" id="search" name="search" value="${searchQuery}"
                       placeholder="Enter Customer Name or Email"/>
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
                <input type="text" id="username" name="username" required>
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
                <input type="text" id="phone" name="phone">
            </div>
            <div class="form-item">
                <label for="address">Address:</label>
                <input type="text" id="address" name="address">
            </div>

            <div class="form-item">
                <label for="id_card">ID Card:</label>
                <input type="text" id="id_card" name="id_card">
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
            <button type="submit"><i class="bx bx-check icon"></i>Confirm Add</button>
        </div>
    </form>


    <c:if test="${not empty error}">
        <div style="color: red; text-align: center; margin: 10px 0;">${error}</div>
    </c:if>

    <table>
        <thead>
        <tr>
            <th>Customer ID</th>
            <th>Customer Name</th>
            <th>Customer Email</th>
            <th>Customer Phone</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="customer" items="${customers}">
            <tr>
                <td>${customer.customerId}</td>
                <td>${customer.customerName}</td>
                <td>${customer.customerEmail}</td>
                <td>${customer.customerPhone}</td>
                <td class="actions">
                    <button id="update-btn-${customer.customerId}" class="btn"
                            onclick="showUpdateForm(${customer.customerId})">
                        <i class="bx bx-edit-alt icon"></i>Update Customer
                    </button>
                    <form id="update-form-${customer.customerId}" action="${pageContext.request.contextPath}/customer"
                          method="post" class="update-form">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="id" value="${customer.customerId}">
                        <input type="hidden" name="currentPage" value="${currentPage}">
                        <input type="hidden" name="search" value="${searchQuery}">
                        <div class="form-group">
                            <label for="full_name_${customer.customerId}">Full Name:</label>
                            <input type="text" id="full_name_${customer.customerId}" name="full_name"
                                   value="${customer.customerName}" required>
                        </div>
                        <div class="form-group">
                            <label for="email_${customer.customerId}">Email:</label>
                            <input type="email" id="email_${customer.customerId}" name="email"
                                   value="${customer.customerEmail}">
                        </div>
                        <div class="form-group">
                            <label for="phone_${customer.customerId}">Phone:</label>
                            <input type="text" id="phone_${customer.customerId}" name="phone"
                                   value="${customer.customerPhone}">
                        </div>
                        <div class="form-group">
                            <label for="address_${customer.customerId}">Address:</label>
                            <input type="text" id="address_${customer.customerId}" name="address"
                                   value="${customer.customerAddress}">
                        </div>
                        <div class="form-group">
                            <label for="id_card_${customer.customerId}">ID Card:</label>
                            <input type="text" id="id_card_${customer.customerId}" name="id_card"
                                   value="${customer.customerIdCard}">
                        </div>
                        <div class="form-group">
                            <label for="birthday_${customer.customerId}">Birthday:</label>
                            <input type="date" id="birthday_${customer.customerId}" name="birthday"
                                   value="${customer.customerBirthday}">
                        </div>
                        <div class="form-group">
                            <label for="gender_${customer.customerId}">Gender:</label>
                            <select id="gender_${customer.customerId}" name="gender">
                                <option value="">Select Gender</option>
                                <option value="Male" ${customer.customerGender ? 'selected' : ''}>Male</option>
                                <option value="Female" ${!customer.customerGender ? 'selected' : ''}>Female</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="customer_type_id_${customer.customerId}">Customer Type:</label>
                            <select id="customer_type_id_${customer.customerId}" name="customer_type_id" required>
                                <option value="">Select Customer Type</option>
                                <c:forEach var="type" items="${customerTypes}">
                                    <option value="${type.customerTypeId}" ${type.customerTypeId == customer.customerTypeId ? 'selected' : ''}>${type.customerTypeName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button type="submit"><i class="bx bx-check icon"></i>Confirm Update</button>
                    </form>
                    <form id="delete-${customer.customerId}" action="${pageContext.request.contextPath}/customer"
                          method="get">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="${customer.customerId}">
                        <input type="hidden" name="currentPage" value="${currentPage}">
                        <input type="hidden" name="search" value="${searchQuery}">
                        <button type="button" class="btn btn-delete"
                                onclick="confirmAction('delete', ${customer.customerId})">
                            <i class="bx bx-trash icon"></i>Delete Customer
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="pagination">
        <c:if test="${currentPage > 1}">
            <button onclick="location.href='${pageContext.request.contextPath}/customer?page=${currentPage-1}&search=${searchQuery}'">
                Previous
            </button>
        </c:if>

        <c:forEach var="i" begin="1" end="${totalPages}">
            <button onclick="location.href='${pageContext.request.contextPath}/customer?page=${i}&search=${searchQuery}'">${i}</button>
        </c:forEach>

        <c:if test="${currentPage < totalPages}">
            <button onclick="location.href='${pageContext.request.contextPath}/customer?page=${currentPage+1}&search=${searchQuery}'">
                Next
            </button>
        </c:if>
    </div>
</div>

<script>
    function confirmAction(action, id) {
        var message = '';
        if (action === 'update') {
            message = 'Are you sure you want to update this customer?';
        } else if (action === 'delete') {
            message = 'Are you sure you want to delete this customer?';
        }

        var confirmed = confirm(message);
        if (confirmed) {
            if (action === 'delete') {
                document.getElementById("delete-" + id).submit();
            } else if (action === 'update') {
                document.getElementById("update-form-" + id).submit();
            }
        }
        return false;
    }

    function showUpdateForm(id) {
        document.getElementById("update-form-" + id).classList.add("active");
        document.getElementById("update-btn-" + id).style.display = "none";
    }

    function showAddForm() {
        document.getElementById("add-form").classList.add("active");
        document.getElementById("add-btn").style.display = "none";
    }
</script>
</body>
</html>