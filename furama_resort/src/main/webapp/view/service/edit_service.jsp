<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Service</title>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="view/service/service.css">
    <script>
        function toggleFields() {
            var serviceType = document.getElementById("service_type_id").value;
            var villaFields = document.getElementById("villa_fields");
            var houseFields = document.getElementById("house_fields");
            var roomFields = document.getElementById("room_fields");

            villaFields.classList.add("hidden");
            houseFields.classList.add("hidden");
            roomFields.classList.add("hidden");

            if (serviceType == "1") {
                villaFields.classList.remove("hidden");
            } else if (serviceType == "2") {
                houseFields.classList.remove("hidden");
            } else if (serviceType == "3") {
                roomFields.classList.remove("hidden");
            }
        }
    </script>
</head>
<body onload="toggleFields()">
<div class="wrapper">
    <h2><i class="bx bx-home-alt icon"></i>Edit Service</h2>

    <div class="header-container">
        <h3>Service Details</h3>
    </div>

    <c:if test="${not empty error}">
        <div style="color: red; text-align: center; margin: 10px 0;">${error}</div>
    </c:if>

    <form id="edit-form" action="${pageContext.request.contextPath}/service" method="post" class="add-form active">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="serviceId" value="${service.serviceId}">
        <div class="form-grid">
            <div class="form-item">
                <label for="service_name">Service Name:</label>
                <input type="text" id="service_name" name="service_name" value="${service.serviceName}" required>
            </div>
            <div class="form-item">
                <label for="service_area">Service Area (m²):</label>
                <input type="number" id="service_area" name="service_area" min="0" value="${service.serviceArea}">
            </div>
            <div class="form-item">
                <label for="service_cost">Service Cost:</label>
                <input type="number" id="service_cost" name="service_cost" step="0.01" min="0"
                       value="${service.serviceCost}" required>
            </div>
            <div class="form-item">
                <label for="service_capacity">Capacity:</label>
                <input type="number" id="service_capacity" name="service_capacity" min="1"
                       value="${service.serviceCapacity}">
            </div>
            <div class="form-item">
                <label for="rent_type_id">Rent Type:</label>
                <select id="rent_type_id" name="rent_type_id" required>
                    <option value="">Select Rent Type</option>
                    <c:forEach var="rentType" items="${rentTypes}">
                        <option value="${rentType.rentTypeId}" ${rentType.rentTypeId == service.rentTypeId ? 'selected' : ''}>
                                ${rentType.rentTypeName}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-item">
                <label for="service_type_id">Service Type:</label>
                <select id="service_type_id" name="service_type_id" onchange="toggleFields()" required>
                    <option value="">Select Service Type</option>
                    <c:forEach var="serviceType" items="${serviceTypes}">
                        <option value="${serviceType.serviceTypeId}" ${serviceType.serviceTypeId == service.serviceTypeId ? 'selected' : ''}>
                                ${serviceType.serviceTypeName}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div id="villa_fields" class="form-item hidden">
                <label for="standard_room_villa">Standard Room:</label>
                <input type="text" id="standard_room_villa" name="standard_room" value="${service.standardRoom}">
                <label for="description_other_convenience_villa">Other Convenience:</label>
                <input type="text" id="description_other_convenience_villa" name="description_other_convenience" value="${service.descriptionOtherConvenience}">
                <label for="pool_area">Pool Area (m²):</label>
                <input type="number" id="pool_area" name="pool_area" step="0.01" min="0" value="${service.poolArea}">
                <label for="number_of_floors_villa">Number of Floors:</label>
                <input type="number" id="number_of_floors_villa" name="number_of_floors" min="1" value="${service.numberOfFloors}">
            </div>

            <div id="house_fields" class="form-item hidden">
                <label for="standard_room_house">Standard Room:</label>
                <input type="text" id="standard_room_house" name="standard_room" value="${service.standardRoom}">
                <label for="description_other_convenience_house">Other Convenience:</label>
                <input type="text" id="description_other_convenience_house" name="description_other_convenience" value="${service.descriptionOtherConvenience}">
                <label for="number_of_floors_house">Number of Floors:</label>
                <input type="number" id="number_of_floors_house" name="number_of_floors" min="1" value="${service.numberOfFloors}">
            </div>

            <div id="room_fields" class="form-item hidden">
                <label for="free_service">Free Service:</label>
                <input type="text" id="free_service" name="free_service" value="${service.freeService}">
            </div>
        </div>

        <div style="text-align: center; margin-top: 20px;">
            <button type="submit"><i class="bx bx-save icon"></i>Save</button>
            <a href="${pageContext.request.contextPath}/customer_list" class="action-btn cancel">
                <i class="bx bx-arrow-back icon"></i>Cancel
            </a>
        </div>
    </form>
</div>
</body>
</html>
