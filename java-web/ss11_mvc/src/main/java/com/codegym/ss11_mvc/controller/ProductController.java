package com.codegym.ss11_mvc.controller;

import com.codegym.ss11_mvc.model.Product;
import com.codegym.ss11_mvc.service.ProductService;
import com.codegym.ss11_mvc.service.IProductService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductController", urlPatterns = "/products")
public class ProductController extends HttpServlet {
    private final IProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            case "view":
                viewProduct(request, response);
                break;
            case "search":
                searchProduct(request, response);
                break;
            default:
                listProducts(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "create":
                createProduct(request, response);
                break;
            case "edit":
                updateProduct(request, response);
                break;
        }
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productService.findAll();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/views/product/list.jsp").forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/product/create.jsp").forward(request, response);
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            String description = request.getParameter("description");
            String manufacturer = request.getParameter("manufacturer");

            Product newProduct = new Product(productService.generateId(), name, price, description, manufacturer);
            productService.save(newProduct);

            request.setAttribute("message", "Sản phẩm đã được thêm thành công!");
            listProducts(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Vui lòng nhập số hợp lệ cho giá sản phẩm.");
            request.getRequestDispatcher("/views/product/create.jsp").forward(request, response);
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Product product = productService.findById(id);

            if (product != null) {
                request.setAttribute("product", product);
                request.getRequestDispatcher("/views/product/edit.jsp").forward(request, response);
            } else {
                response.sendRedirect("/products");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("/products");
        }
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            String description = request.getParameter("description");
            String manufacturer = request.getParameter("manufacturer");

            Product updatedProduct = new Product(id, name, price, description, manufacturer);
            productService.update(id, updatedProduct);

            request.setAttribute("message", "Sản phẩm đã được cập nhật thành công!");
            listProducts(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Vui lòng nhập số hợp lệ cho ID hoặc giá sản phẩm.");
            request.getRequestDispatcher("/views/product/edit.jsp").forward(request, response);
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            productService.delete(id);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "ID không hợp lệ.");
        }
        response.sendRedirect("/products");
    }

    private void viewProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Product product = productService.findById(id);

            if (product != null) {
                request.setAttribute("product", product);
                request.getRequestDispatcher("/views/home.jsp").forward(request, response);
            } else {
                response.sendRedirect("/products");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("/products");
        }
    }

    private void searchProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        List<Product> searchResults = productService.findByName(name);

        request.setAttribute("products", searchResults);
        request.getRequestDispatcher("/views/product/list.jsp").forward(request, response);
    }
}
