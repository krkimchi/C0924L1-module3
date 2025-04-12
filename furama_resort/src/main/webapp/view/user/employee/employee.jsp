<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Employee Management</title>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="view/user/employee/employee.css">
    <script>
        function toggleAddForm() {
            document.getElementById("add-form").classList.add("active");
            document.getElementById("add-btn").style.display = "none";
        }

        function toggleUpdateForm(id) {
            document.getElementById("update-form-" + id).classList.add("active");
            document.getElementById("update-btn-" + id).style.display = "none";
        }

        function confirmDelete(id) {
            if (confirm("Are you sure you want to delete this employee?")) {
                document.getElementById("delete-" + id).submit();
            }
        }
    </script>
</head>
<body>
<div class="wrapper">
    <h2><i class="bx bx-user icon"></i>Employee Management</h2>

    <div class="header-container">
        <h3>List of Employees</h3>
        <div class="search-container">
            <form id="search-form" action="${pageContext.request.contextPath}/employee" method="get">
                <input type="text" name="search" id="search" placeholder="Search by name or email..."
                       value="${searchQuery}">
                <button type="submit" id="search-btn"><i class="bx bx-search icon"></i></button>
            </form>
        </div>
    </div>

    <div class="header-container">
        <button id="add-btn" class="btn" onclick="toggleAddForm()">
            <i class="bx bx-plus icon"></i>Add New Employee
        </button>
    </div>

    <form id="add-form" class="add-form" method="post" action="${pageContext.request.contextPath}/employee">
        <input type="hidden" name="action" value="add">
        <div class="form-grid">
            <div class="form-item"><label>Full Name:</label><input type="text" name="name" required></div>
            <div class="form-item"><label>ID Card:</label><input type="text" name="id_card" required></div>
            <div class="form-item"><label>Phone:</label><input type="text" name="phone" required></div>
            <div class="form-item"><label>Email:</label><input type="email" name="email" required></div>
            <div class="form-item"><label>Birthday:</label><input type="date" name="birthday" required></div>
            <div class="form-item"><label>Salary:</label><input type="text" name="salary" required></div>
            <div class="form-item">
                <label>Education:</label>
                <select name="education_id" required>
                    <option value="">Select Education</option>
                    <c:forEach var="degree" items="${degrees}">
                        <option value="${degree.id}">${degree.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-item">
                <label>Position:</label>
                <select name="position_id" required>
                    <option value="">Select Position</option>
                    <c:forEach var="position" items="${positions}">
                        <option value="${position.id}">${position.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-item">
                <label>Division:</label>
                <select name="division_id" required>
                    <option value="">Select Division</option>
                    <c:forEach var="division" items="${divisions}">
                        <option value="${division.id}">${division.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div style="text-align: center; margin-top: 20px;">
            <button type="submit"><i class="bx bx-check icon"></i>Confirm Add</button>
        </div>
    </form>

    <table>
        <thead>
        <tr>
            <th>Full Name</th>
            <th>Birthday</th>
            <th>ID Card</th>
            <th>Phone</th>
            <th>Email</th>
            <th>Salary</th>
            <th>Education</th>
            <th>Position</th>
            <th>Division</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="e" items="${employees}">
            <tr>
                <td>${e.employeeName}</td>
                <td>${e.employeeBirthday}</td>
                <td>${e.employeeIdCard}</td>
                <td>${e.employeePhone}</td>
                <td>${e.employeeEmail}</td>
                <td>${e.employeeSalary}</td>
                <td>${e.educationName}</td>
                <td>${e.positionName}</td>
                <td>${e.divisionName}</td>
                <td class="actions">
                    <button id="update-btn-${e.employeeId}" class="btn" onclick="toggleUpdateForm(${e.employeeId})">
                        <i class="bx bx-edit-alt icon"></i>Update
                    </button>
                    <form id="update-form-${e.employeeId}" class="update-form" method="post"
                          action="${pageContext.request.contextPath}/employee">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="id" value="${e.employeeId}">

                        <div class="form-grid">
                            <div class="form-item"><label>Full Name:</label><input type="text" name="name"
                                                                                   value="${e.employeeName}" required>
                            </div>
                            <div class="form-item"><label>ID Card:</label><input type="text" name="id_card"
                                                                                 value="${e.employeeIdCard}" required>
                            </div>
                            <div class="form-item"><label>Phone:</label><input type="text" name="phone"
                                                                               value="${e.employeePhone}" required>
                            </div>
                            <div class="form-item"><label>Email:</label><input type="email" name="email"
                                                                               value="${e.employeeEmail}" required>
                            </div>
                            <div class="form-item"><label>Birthday:</label><input type="date" name="birthday"
                                                                                  value="${e.employeeBirthday}"
                                                                                  required></div>
                            <div class="form-item"><label>Salary:</label><input type="text" name="salary"
                                                                                value="${e.employeeSalary}" required>
                            </div>
                            <div class="form-item">
                                <label>Education:</label>
                                <select name="education_id" required>
                                    <c:forEach var="degree" items="${degrees}">
                                        <option value="${degree.id}" ${degree.id == e.educationDegreeId ? 'selected' : ''}>${degree.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-item">
                                <label>Position:</label>
                                <select name="position_id" required>
                                    <c:forEach var="position" items="${positions}">
                                        <option value="${position.id}" ${position.id == e.positionId ? 'selected' : ''}>${position.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-item">
                                <label>Division:</label>
                                <select name="division_id" required>
                                    <c:forEach var="division" items="${divisions}">
                                        <option value="${division.id}" ${division.id == e.divisionId ? 'selected' : ''}>${division.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div style="text-align:center; margin-top:10px;">
                            <button type="submit"><i class="bx bx-check icon"></i>Confirm Update</button>
                        </div>
                    </form>

                    <form id="delete-${e.employeeId}" action="${pageContext.request.contextPath}/employee" method="get">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="${e.employeeId}">
                        <button type="button" class="btn btn-delete" onclick="confirmDelete(${e.employeeId})">
                            <i class="bx bx-trash icon"></i>Delete
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="pagination">
        <c:if test="${currentPage > 1}">
            <button onclick="location.href='${pageContext.request.contextPath}/employee?page=${currentPage-1}&search=${searchQuery}'">
                Previous
            </button>
        </c:if>
        <c:forEach var="i" begin="1" end="${totalPages}">
            <button onclick="location.href='${pageContext.request.contextPath}/employee?page=${i}&search=${searchQuery}'">${i}</button>
        </c:forEach>
        <c:if test="${currentPage < totalPages}">
            <button onclick="location.href='${pageContext.request.contextPath}/employee?page=${currentPage+1}&search=${searchQuery}'">
                Next
            </button>
        </c:if>
    </div>
</div>
</body>
</html>
