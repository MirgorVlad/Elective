package com.elective;


import com.elective.commad.Command;
import com.elective.commad.CommandContainer;
import com.elective.db.dao.DBException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/controller")
public class Controller extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String address = ReferencesPages.ERROR_PAGE;
        try {
            address = getAndExecuteCommand(req, resp);
        } catch (Exception | DBException ex){
            req.setAttribute("exception", ex);
            //log
        }
        req.getRequestDispatcher(address).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String address = ReferencesPages.ERROR_PAGE;
        try {
            address = getAndExecuteCommand(req, resp);
        } catch (Exception | DBException ex){
            req.getSession().setAttribute("exception", ex);
            //log
        }
        resp.sendRedirect(address);
    }

    private String getAndExecuteCommand(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, DBException {
        String commandName = req.getParameter("command");
        Command command = CommandContainer.getCommand(commandName);
        return command.execute(req, resp);
    }
}
