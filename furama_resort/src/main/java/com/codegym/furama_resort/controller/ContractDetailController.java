package com.codegym.furama_resort.controller;

import com.codegym.furama_resort.model.ContractDetail;
import com.codegym.furama_resort.service.ContractDetailService;
import com.codegym.furama_resort.service.IContractDetailService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "ContractDetailController", value = "/contract-detail")
public class ContractDetailController extends HttpServlet {
    private IContractDetailService contractDetailService;

    @Override
    public void init() throws ServletException {
        contractDetailService = new ContractDetailService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("view/contract/contract_detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        addContractDetail(request, response);
    }

    private void addContractDetail(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int contractId = Integer.parseInt(request.getParameter("contractId"));
            int attachServiceId = Integer.parseInt(request.getParameter("attachServiceId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            ContractDetail contractDetail = new ContractDetail();
            contractDetail.setContractId(contractId);
            contractDetail.setAttachServiceId(attachServiceId);
            contractDetail.setQuantity(quantity);

            contractDetailService.addContractDetail(contractDetail);

            response.sendRedirect("contract_detail.jsp?success=true");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("contract_detail.jsp?error=true");
        }
    }
}
