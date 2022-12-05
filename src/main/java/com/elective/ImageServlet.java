package com.elective;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/img/*")
public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/jpeg");
        String[] parts = req.getRequestURI().split("/");
        String path = parts[parts.length-1];
        ServletOutputStream servletOutputStream = resp.getOutputStream();

        try(FileInputStream input = new FileInputStream("E:\\EPAM\\uploads\\courses\\" + path)) {
            byte[] file = input.readAllBytes();
            servletOutputStream.write(file);
        }
    }
}
