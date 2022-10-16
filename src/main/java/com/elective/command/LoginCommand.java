package com.elective.command;

import com.elective.ReferencesPages;
import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DBException;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class LoginCommand implements Command{
    static Logger log = LogManager.getLogger(LoginCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException, IllegalAccessException {
        UserDAO userDAO = daoFactory.getUserDAO();

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        //DB get User
        //Logic if admin -> admin.jsp if clien -> client.jsp
        //User to session
        log.log(Level.DEBUG, "email: " + email + "; password: " + password);
        User user = userDAO.findByEmail(email);
        log.log(Level.INFO, "LOGIN user: " + user);

        if(user != null){
            if(user.isBlock()) {
                log.log(Level.WARN, "User"+user.getEmail()+" is blocked by manager");
                throw new IllegalAccessException("You are blocked by manager");
            }

            req.getSession().setAttribute("topicList", CourseDAO.topicList);

            if(user.getPassword().equals(password)) {
                req.getSession().setAttribute("user", user);
                if(user.getRole().equals(UserDAO.STUDENT_ROLE))
                    return ReferencesPages.STUDENT_PAGE;
                if(user.getRole().equals(UserDAO.TEACHER_ROLE))
                    return ReferencesPages.TEACHER_PAGE;
                if(user.getRole().equals(UserDAO.MANAGER_ROLE)) {
                    return ReferencesPages.MANAGER_PAGE;
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
