package com.codegym.furama_resort.controller;

import com.codegym.furama_resort.model.RentType;
import com.codegym.furama_resort.model.Service;
import com.codegym.furama_resort.service.IRentTypeService;
import com.codegym.furama_resort.service.IServiceService;
import com.codegym.furama_resort.service.RentTypeService;
import com.codegym.furama_resort.service.ServiceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ServiceController", value = "/service")
public class ServiceController extends HttpServlet {
    private IServiceService serviceService;
    private IRentTypeService rentTypeService;

    @Override
    public void init() throws ServletException {
        serviceService = new ServiceService();
        rentTypeService = new RentTypeService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<RentType> rentTypes = rentTypeService.getAllRentTypes();
            req.setAttribute("rentTypes", rentTypes);
            req.getRequestDispatcher("/view/service/service.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Failed to load rent types: " + e.getMessage());
            req.getRequestDispatcher("/view/service/service.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("add".equals(action)) {
            try {
                String serviceName = req.getParameter("service_name");
                String serviceAreaStr = req.getParameter("service_area");
                String serviceCostStr = req.getParameter("service_cost");
                String serviceCapacityStr = req.getParameter("service_capacity");
                String rentTypeIdStr = req.getParameter("rent_type_id");
                String serviceTypeIdStr = req.getParameter("service_type_id");
                String standardRoom = req.getParameter("standard_room");
                String descriptionOtherConvenience = req.getParameter("description_other_convenience");
                String poolAreaStr = req.getParameter("pool_area");
                String numberOfFloorsStr = req.getParameter("number_of_floors");
                String freeService = req.getParameter("free_service");

                if (serviceName == null || serviceName.trim().isEmpty()) {
                    req.setAttribute("error", "Service name is required");
                    forwardWithRentTypes(req, resp);
                    return;
                }
                if (serviceCostStr == null || serviceCostStr.trim().isEmpty()) {
                    req.setAttribute("error", "Service cost is required");
                    forwardWithRentTypes(req, resp);
                    return;
                }
                if (rentTypeIdStr == null || rentTypeIdStr.trim().isEmpty()) {
                    req.setAttribute("error", "Rent type is required");
                    forwardWithRentTypes(req, resp);
                    return;
                }
                if (serviceTypeIdStr == null || serviceTypeIdStr.trim().isEmpty()) {
                    req.setAttribute("error", "Service type is required");
                    forwardWithRentTypes(req, resp);
                    return;
                }

                int serviceArea = serviceAreaStr.isEmpty() ? 0 : Integer.parseInt(serviceAreaStr);
                double serviceCost = Double.parseDouble(serviceCostStr);
                int serviceCapacity = serviceCapacityStr.isEmpty() ? 0 : Integer.parseInt(serviceCapacityStr);
                int rentTypeId = Integer.parseInt(rentTypeIdStr);
                int serviceTypeId = Integer.parseInt(serviceTypeIdStr);
                double poolArea = poolAreaStr.isEmpty() ? 0 : Double.parseDouble(poolAreaStr);
                int numberOfFloors = numberOfFloorsStr.isEmpty() ? 0 : Integer.parseInt(numberOfFloorsStr);

                if (serviceTypeId == 1) {
                    if (poolArea <= 0) {
                        req.setAttribute("error", "Pool area is required for Villa");
                        forwardWithRentTypes(req, resp);
                        return;
                    }
                    if (numberOfFloors <= 0) {
                        req.setAttribute("error", "Number of floors is required for Villa");
                        forwardWithRentTypes(req, resp);
                        return;
                    }
                } else if (serviceTypeId == 2) {
                    if (numberOfFloors <= 0) {
                        req.setAttribute("error", "Number of floors is required for House");
                        forwardWithRentTypes(req, resp);
                        return;
                    }
                } else if (serviceTypeId == 3) {
                    if (freeService == null || freeService.trim().isEmpty()) {
                        req.setAttribute("error", "Free service is required for Room");
                        forwardWithRentTypes(req, resp);
                        return;
                    }
                } else {
                    req.setAttribute("error", "Invalid service type");
                    forwardWithRentTypes(req, resp);
                    return;
                }

                Service service = new Service();
                service.setServiceName(serviceName);
                service.setServiceArea(serviceArea);
                service.setServiceCost(serviceCost);
                service.setServiceCapacity(serviceCapacity);
                service.setRentTypeId(rentTypeId);
                service.setServiceTypeId(serviceTypeId);
                service.setStandardRoom(standardRoom);
                service.setDescriptionOtherConvenience(descriptionOtherConvenience);
                service.setPoolArea(poolArea);
                service.setNumberOfFloors(numberOfFloors);
                service.setFreeService(freeService);

                serviceService.addService(service);
                req.getSession().setAttribute("success", "Service added successfully!");
                resp.sendRedirect(req.getContextPath() + "/service");
            } catch (NumberFormatException e) {
                req.setAttribute("error", "Invalid number format in input fields");
                forwardWithRentTypes(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
                req.setAttribute("error", "Failed to add service: " + e.getMessage());
                forwardWithRentTypes(req, resp);
            }
        }
    }

    private void forwardWithRentTypes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<RentType> rentTypes = rentTypeService.getAllRentTypes();
            req.setAttribute("rentTypes", rentTypes);
            req.getRequestDispatcher("/view/service/add_service.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Failed to load rent types: " + e.getMessage());
            req.getRequestDispatcher("/view/service/add_service.jsp").forward(req, resp);
        }
    }
}