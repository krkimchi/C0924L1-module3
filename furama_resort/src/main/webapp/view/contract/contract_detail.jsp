<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add New Contract Detail</title>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="view/contract/contract_detail.css">
</head>
<body>
<div class="wrapper">
    <h2><i class="bx bx-detail icon"></i>Add New Contract Detail</h2>

    <div class="header-container">
        <h3>Contract Detail Information</h3>
    </div>

    <c:if test="${not empty error}">
        <div style="color: red; text-align: center; margin: 10px 0;">${error}</div>
    </c:if>
    <c:if test="${not empty success}">
        <div style="color: green; text-align: center; margin: 10px 0;">${success}</div>
    </c:if>

    <form id="add-form" action="${pageContext.request.contextPath}/contract_detail" method="post"
          class="add-form active">
        <input type="hidden" name="action" value="add">
        <div class="form-grid">
            <div class="form-item">
                <label for="contract_id">Contract:</label>
                <select id="contract_id" name="contract_id" required>
                    <option value="">Select Contract</option>
                    <c:forEach var="contract" items="${contracts}">
                        <option value="${contract.contractId}">${contract.contractId}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-item">
                <label for="attach_service_id">Attach Service:</label>
                <select id="attach_service_id" name="attach_service_id" required>
                    <option value="">Select Attach Service</option>
                    <c:forEach var="attachService" items="${attachServices}">
                        <option value="${attachService.attachServiceId}">${attachService.attachServiceName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-item">
                <label for="quantity">Quantity:</label>
                <input type="number" id="quantity" name="quantity" min="1" required>
            </div>
        </div>

        <div style="text-align: center; margin-top: 20px;">
            <button type="submit"><i class="bx bx-check icon"></i>Confirm Add</button>
        </div>
    </form>
</div>
</body>
</html>
