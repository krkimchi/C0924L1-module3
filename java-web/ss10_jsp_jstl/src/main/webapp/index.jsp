<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách khách hàng</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
        }

        table {
            width: 80%;
            margin: auto;
            border-collapse: collapse;
            border: 2px solid #000;
        }

        th, td {
            border: 1px solid #000;
            padding: 10px;
        }

        th {
            background-color: #f4f4f4;
        }

        img {
            width: 80px;
            height: 80px;
            object-fit: cover;
        }
    </style>

</head>
<body>
<h2>Danh sách khách hàng</h2>

<c:set var="customers" value="${[
        { 'name': 'Mai Văn Hoàn', 'dob': '1983-08-20', 'address': 'Hà Nội', 'image': 'images/user1.png' },
        { 'name': 'Nguyễn Văn Nam', 'dob': '1983-08-21', 'address': 'Bắc Giang', 'image': 'images/user2.png' },
        { 'name': 'Nguyễn Thái Hòa', 'dob': '1983-08-22', 'address': 'Nam Định', 'image': 'images/user3.png' },
        { 'name': 'Trần Đăng Khoa', 'dob': '1983-08-17', 'address': 'Hà Tây', 'image': 'images/user4.png' },
        { 'name': 'Nguyễn Đình Thi', 'dob': '1983-08-19', 'address': 'Hà Nội', 'image': 'images/user5.png' }
    ]}"/>

<table>
    <tr>
        <th>Tên</th>
        <th>Ngày sinh</th>
        <th>Địa chỉ</th>
        <th>Ảnh</th>
    </tr>
    <c:forEach var="customer" items="${customers}">
        <tr>
            <td>${customer.name}</td>
            <td>${customer.dob}</td>
            <td>${customer.address}</td>
            <td><img src="${customer.image}" alt="Ảnh ${customer.name}"></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
