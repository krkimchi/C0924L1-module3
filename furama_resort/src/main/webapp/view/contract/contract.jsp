<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add New Contract</title>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="view/contract/contract.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
</head>
<body>
<div class="wrapper">
    <h2><i class="bx bx-file icon"></i>Add New Contract</h2>

    <div class="header-container">
        <h3>Contract Details</h3>
    </div>

    <c:if test="${not empty error}">
        <div style="color: red; text-align: center; margin: 10px 0;">${error}</div>
    </c:if>
    <c:if test="${not empty success}">
        <div style="color: green; text-align: center; margin: 10px 0;">${success}</div>
    </c:if>

    <form id="add-form" action="${pageContext.request.contextPath}/contract" method="post" class="add-form active">
        <input type="hidden" name="action" value="add">
        <div class="form-grid">
            <div class="form-item">
                <label for="contract_start_date">Start Date:</label>
                <input type="text" id="contract_start_date" name="contract_start_date"
                       class="datepicker" required>
            </div>
            <div class="form-item">
                <label for="contract_end_date">End Date:</label>
                <input type="text" id="contract_end_date" name="contract_end_date"
                       class="datepicker" required>
            </div>
            <div class="form-item">
                <label for="contract_deposit">Deposit:</label>
                <input type="number" id="contract_deposit" name="contract_deposit"
                       step="0.01" min="0" required>
            </div>
            <div class="form-item">
                <label for="contract_total_money">Total Money:</label>
                <input type="number" id="contract_total_money" name="contract_total_money"
                       step="0.01" min="0" required>
            </div>
            <div class="form-item">
                <label for="employee_id">Employee:</label>
                <select id="employee_id" name="employee_id" required>
                    <option value="">Select Employee</option>
                    <c:forEach var="employee" items="${employees}">
                        <option value="${employee.employeeId}">${employee.employeeName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-item">
                <label for="customer_id">Customer:</label>
                <select id="customer_id" name="customer_id" required>
                    <option value="">Select Customer</option>
                    <c:forEach var="customer" items="${customers}">
                        <option value="${customer.customerId}">${customer.customerName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-item">
                <label for="service_id">Service:</label>
                <select id="service_id" name="service_id" required>
                    <option value="">Select Service</option>
                    <c:forEach var="service" items="${services}">
                        <option value="${service.serviceId}">${service.serviceName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div style="text-align: center; margin-top: 20px;">
            <button type="submit"><i class="bx bx-check icon"></i>Confirm Add</button>
        </div>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<script>
    flatpickr(".datepicker", {
        enableTime: true,
        dateFormat: "d/m/Y H:i",
        allowInput: true
    });
</script>
</body>
</html>
