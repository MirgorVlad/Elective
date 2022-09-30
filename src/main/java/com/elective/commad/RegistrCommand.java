package com.elective.commad;

import com.elective.db.dao.DAOFactory;
import com.elective.db.dao.DBException;
import com.elective.db.dao.UserDAO;
import com.elective.db.dao.mysql.MysqlDAOFactory;
import com.elective.db.dao.mysql.MysqlUserDAO;
import com.elective.db.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class RegistrCommand implements Command{

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException {
        UserDAO userDAO = daoFactory.getUserDAO();
        String page = null;

        String email = req.getParameter("email");
        System.out.println("EMAIL: " + email);
        String password = req.getParameter("password");
        System.out.println("PASSWORD: " + password);
        String fName = req.getParameter("fName");
        System.out.println("FIRST NAME: " + fName);
        String lName = req.getParameter("lName");
        System.out.println("LAST NAME: " + lName);
        String role = req.getParameter("userRole");
        System.out.println("ROLE: " + role);

        User user = new User();
        user.setFirstName(fName);
        user.setLastName(lName);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);

        userDAO.insert(user);

        if(user.getRole().equals("teacher"))
            page =  "teacher.jsp";
        if(user.getRole().equals("student"))
            page =  "student.jsp";

        return page;
    }
}
