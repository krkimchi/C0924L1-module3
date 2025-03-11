<%--
  Created by IntelliJ IDEA.
  User: Owner
  Date: 3/10/2025
  Time: 10:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Chi tiết sản phẩm</title>
</head>
<body>
<h1>Chi tiết sản phẩm</h1>
<p>ID: ${product.id}</p>
<p>Tên sản phẩm: ${product.name}</p>
<p>Giá: ${product.price}</p>
<p>Mô tả: ${product.description}</p>
<p>Nhà sản xuất: ${product.manufacturer}</p>
<a href="/products">Quay lại danh sách sản phẩm</a>
</body>
</html>
