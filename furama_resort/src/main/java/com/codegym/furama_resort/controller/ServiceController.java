package com.codegym.furama_resort.controller;

import com.codegym.furama_resort.service.IRentTypeService;
import com.codegym.furama_resort.service.IServiceService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet (name = "ServiceController", value = "/service")
public class ServiceController extends HttpServlet {
    private IServiceService serviceService;
    private IRentTypeService rentTypeService;
}
