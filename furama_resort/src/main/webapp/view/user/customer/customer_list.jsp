<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer List Using Services</title>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="/view/user/customer/customer_list.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
</head>
<body>
<div class="wrapper">
    <h2><i class="bx bx-user icon"></i>Customers Using Services</h2>

    <div class="header-container">
        <form action="${pageContext.request.contextPath}/customer-list" method="post" class="search-form">
            <input type="text" name="search" placeholder="Search by customer name..." value="${searchQuery}">
            <button type="submit"><i class="bx bx-search icon"></i>Search</button>
        </form>
    </div>

    <c:if test="${not empty error}">
        <div style="color: red; text-align: center; margin: 10px 0;">${error}</div>
    </c:if>

    <table class="customer-table">
        <thead>
        <tr>
            <th>Customer Name</th>
            <th>Service Name</th>
            <th>Contract Start Date</th>
            <th>Contract End Date</th>
            <th>Attached Services</th>
            <th>Total Money</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="customer" items="${customers}">
            <tr>
                <td>${customer.customerName}</td>
                <td>${customer.contract.serviceName}</td>
                <td><input type="text" value="${customer.contract.startDate}" class="datepicker" readonly></td>
                <td><input type="text" value="${customer.contract.endDate}" class="datepicker" readonly></td>
                <td>
                    <c:choose>
                        <c:when test="${not empty customer.contract.contractDetails}">
                            <ul class="attach-services">
                                <c:forEach var="detail" items="${customer.contract.contractDetails}">
                                    <li data-qty="${detail.quantity}" data-price="10">
                                            ${detail.attachServiceName} - ${detail.quantity} x <span class="attach-price">10</span>
                                    </li>
                                </c:forEach>
                            </ul>
                        </c:when>
                        <c:otherwise>None</c:otherwise>
                    </c:choose>
                </td>
                <td class="total-cell" data-base="100"></td>
                <td>
                    <a href="${pageContext.request.contextPath}/service?action=edit&serviceId=${customer.contract.serviceId}" class="action-btn edit"><i class="bx bx-edit icon"></i>Edit Service</a>
                    <a href="${pageContext.request.contextPath}/service?action=delete&serviceId=${customer.contract.serviceId}" class="action-btn delete" onclick="return confirm('Are you sure you want to delete this service?');"><i class="bx bx-trash icon"></i>Delete Service</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="pagination">
        <c:if test="${currentPage > 1}">
            <a href="${pageContext.request.contextPath}/customer-list?page=${currentPage - 1}&search=${searchQuery}"><i class="bx bx-chevron-left icon"></i>Previous</a>
        </c:if>
        <c:forEach begin="1" end="${totalPages}" var="i">
            <a href="${pageContext.request.contextPath}/customer-list?page=${i}&search=${searchQuery}" class="${i == currentPage ? 'active' : ''}">${i}</a>
        </c:forEach>
        <c:if test="${currentPage < totalPages}">
            <a href="${pageContext.request.contextPath}/customer-list?page=${currentPage + 1}&search=${searchQuery}">Next<i class="bx bx-chevron-right icon"></i></a>
        </c:if>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<script>
    flatpickr(".datepicker", {
        dateFormat: "d/m/Y",
        allowInput: true
    });

    document.querySelectorAll(".total-cell").forEach(cell => {
        const row = cell.closest("tr");
        const base = parseFloat(cell.dataset.base || 0);
        let total = base;

        row.querySelectorAll(".attach-services li").forEach(item => {
            const qty = parseInt(item.dataset.qty || 0);
            const price = parseFloat(item.dataset.price || 0);
            total += qty * price;
        });

        cell.textContent = total.toLocaleString("en-US", {
            style: "currency",
            currency: "USD"
        });
    });
</script>
</body>
</html>
