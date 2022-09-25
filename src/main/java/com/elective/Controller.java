package com.elective;


import com.elective.commad.Command;
import com.elective.commad.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    private static final String ERROR_PAGE = "error_page.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String address = ERROR_PAGE;
        try {
            address = getAndExecuteCommand(req, resp);
        } catch (Exception ex){
            req.setAttribute("ex", ex);
            //log
        }
        req.getRequestDispatcher(address).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String address = ERROR_PAGE;
        try {
            address = getAndExecuteCommand(req, resp);
        } catch (Exception ex){
            req.getSession().setAttribute("ex", ex);
            //log
        }
        resp.sendRedirect(address);
    }

    private String getAndExecuteCommand(HttpServletRequest req, HttpServletResponse resp) {
        String commandName = req.getParameter("command");
        Command command = CommandContainer.getCommand(commandName);
        return command.execute(req, resp);
    }
}
