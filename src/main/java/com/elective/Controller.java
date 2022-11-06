package com.elective;


import com.elective.command.Command;
import com.elective.command.CommandContainer;
import com.elective.command.ViewAllCoursesCommand;
import com.elective.db.dao.DBException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    static Logger log = LogManager.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String address = ReferencePages.ERROR_PAGE;
        try {
            address = getAndExecuteCommand(req, resp);
        } catch (Exception ex){
            req.setAttribute("exception", ex);
            log.log(Level.ERROR, ex.getMessage());
        }
        req.getRequestDispatcher(address).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String address = ReferencePages.ERROR_PAGE;
        try {
            address = getAndExecuteCommand(req, resp);
        } catch (Exception ex){
            req.setAttribute("exception", ex);
            log.log(Level.ERROR, ex.getMessage());
        }
        resp.sendRedirect(address);
    }

    private String getAndExecuteCommand(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        String commandName = req.getParameter("command");
        Command command = CommandContainer.getCommand(commandName);
        return command.execute(req, resp);
    }
}
