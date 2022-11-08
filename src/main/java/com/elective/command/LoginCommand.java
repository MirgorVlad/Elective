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
import java.util.List;

public class LoginCommand implements Command{
    static Logger log = LogManager.getLogger(LoginCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException, IllegalAccessException {
        UserDAO userDAO = getDaoFactory().getUserDAO();
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        log.log(Level.DEBUG, "email: " + email);

        User user = userDAO.findByEmail(email);
        log.log(Level.INFO, "LOGIN user: " + user);

        if(user != null){
            if(user.isBlock()) {
                log.log(Level.WARN, "User"+user.getEmail()+" is blocked by manager");
                throw new IllegalAccessException("You are blocked by manager");
            }

            if(user.getPassword().equals(password)) {
                req.getSession().setAttribute("user", user);
                if(user.getRole().equals(UserDAO.STUDENT_ROLE))
                    return ReferencePages.STUDENT_PAGE;
                if(user.getRole().equals(UserDAO.TEACHER_ROLE))
                    return ReferencePages.TEACHER_PAGE;
                if(user.getRole().equals(UserDAO.MANAGER_ROLE)) {
                    return ReferencePages.MANAGER_PAGE;
                }
            } else {
                log.log(Level.WARN, "Wrong password for "+user.getEmail()+" account");
                throw new DBException("wrong password");
            }
        } else {
            log.log(Level.WARN, "Cannot find user with login "+ email);
            throw new DBException("cannot find user");
        }

        return null;
    }
}
