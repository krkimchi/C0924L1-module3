<%--
  Created by IntelliJ IDEA.
  User: Owner
  Date: 3/10/2025
  Time: 9:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
  <title>Chỉnh sửa sản phẩm</title>
</head>
<body>
<h1>Chỉnh sửa sản phẩm</h1>
<form method="post" action="/products?action=edit">
  <input type="hidden" name="id" value="${product.id}">
  Tên sản phẩm: <input type="text" name="name" value="${product.name}" required><br>
  Giá: <input type="text" name="price" value="${product.price}" required><br>
  Mô tả: <input type="text" name="description" value="${product.description}"><br>
  Nhà sản xuất: <input type="text" name="manufacturer" value="${product.manufacturer}"><br>
  <input type="submit" value="Cập nhật">
</form>
</body>
</html>