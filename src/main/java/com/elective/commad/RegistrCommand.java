package com.elective.commad;

import com.elective.ReferencesPages;
import com.elective.db.dao.CourseDAO;
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

        if(user.getRole().equals(UserDAO.TEACHER_ROLE))
            page = ReferencesPages.TEACHER_PAGE;
        if(user.getRole().equals(UserDAO.STUDENT_ROLE))
            page =  ReferencesPages.STUDENT_PAGE;

        req.getSession().setAttribute("topicList", CourseDAO.topicList);

        return page;
    }
}
