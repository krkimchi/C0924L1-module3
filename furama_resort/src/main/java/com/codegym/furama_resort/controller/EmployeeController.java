package com.codegym.furama_resort.controller;

import com.codegym.furama_resort.model.Division;
import com.codegym.furama_resort.model.EducationDegree;
import com.codegym.furama_resort.model.Employee;
import com.codegym.furama_resort.model.Position;
import com.codegym.furama_resort.repository.DivisionRepository;
import com.codegym.furama_resort.repository.EducationDegreeRepository;
import com.codegym.furama_resort.repository.PositionRepository;
import com.codegym.furama_resort.service.EmployeeService;
import com.codegym.furama_resort.service.IEmployeeService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "EmployeeController", value = "/employee")
public class EmployeeController extends HttpServlet {
    private IEmployeeService employeeService;

    @Override
    public void init() {
        employeeService = new EmployeeService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "delete":
                    delete(req, resp);
                    break;
                default:
                    showList(req, resp);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "add":
                    insert(req, resp);
                    break;
                case "update":
                    update(req, resp);
                    break;
                default:
                    showList(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        List<Employee> employees = employeeService.findAll();
        List<EducationDegree> degrees = new EducationDegreeRepository().findAll();
        List<Position> positions = new PositionRepository().findAll();
        List<Division> divisions = new DivisionRepository().findAll();

        req.setAttribute("employees", employees);
        req.setAttribute("degrees", degrees);
        req.setAttribute("positions", positions);
        req.setAttribute("divisions", divisions);

        req.getRequestDispatcher("/view/user/employee/employee.jsp").forward(req, resp);
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        String name = req.getParameter("name");
        LocalDate birthday = LocalDate.parse(req.getParameter("birthday"));
        String idCard = req.getParameter("id_card");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        double salary = Double.parseDouble(req.getParameter("salary"));
        int educationId = Integer.parseInt(req.getParameter("education_id"));
        int positionId = Integer.parseInt(req.getParameter("position_id"));
        int divisionId = Integer.parseInt(req.getParameter("division_id"));

        Employee employee = new Employee(0, name, birthday, idCard, phone, email, salary, educationId, positionId, divisionId);
        employeeService.save(employee);
        resp.sendRedirect("/employee");
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        LocalDate birthday = LocalDate.parse(req.getParameter("birthday"));
        String idCard = req.getParameter("id_card");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        double salary = Double.parseDouble(req.getParameter("salary"));
        int educationId = Integer.parseInt(req.getParameter("education_id"));
        int positionId = Integer.parseInt(req.getParameter("position_id"));
        int divisionId = Integer.parseInt(req.getParameter("division_id"));

        Employee employee = new Employee(id, name, birthday, idCard, phone, email, salary, educationId, positionId, divisionId);
        employeeService.update(employee);
        resp.sendRedirect("/employee");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        employeeService.delete(id);
        resp.sendRedirect("/employee");
    }
}

