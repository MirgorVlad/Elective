package com.elective.command;

import com.elective.ReferencePages;
import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DBException;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class RegistrCommand implements Command{
    static Logger log = LogManager.getLogger(RegistrCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException {
        UserDAO userDAO = daoFactory.getUserDAO();
        String page = null;

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String fName = req.getParameter("fName");
        String lName = req.getParameter("lName");
        String role = req.getParameter("userRole");

        log.log(Level.DEBUG, "email: " + email + "; password: "+ password + "; fName: "+ fName + "; lName: "
        + lName + "; role: " + role);

        User user = new User();
        user.setFirstName(fName);
        user.setLastName(lName);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);

        userDAO.insert(user);

        req.getSession().setAttribute("user", user);

        log.log(Level.INFO, "Register user: " + user);

        if(user.getRole().equals(UserDAO.TEACHER_ROLE))
            page = ReferencePages.TEACHER_PAGE;
        if(user.getRole().equals(UserDAO.STUDENT_ROLE))
            page =  ReferencePages.STUDENT_PAGE;

        req.getSession().setAttribute("topicList", CourseDAO.topicList);

        return page;
    }
}
