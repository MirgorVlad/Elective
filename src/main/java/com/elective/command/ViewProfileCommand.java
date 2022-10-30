package com.elective.command;

import com.elective.ReferencePages;
import com.elective.db.dao.DBException;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ViewProfileCommand implements Command{
    static Logger log = LogManager.getLogger(ViewProfileCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException, IllegalAccessException {
        UserDAO userDAO = daoFactory.getUserDAO();
        int userId = Integer.parseInt(req.getParameter("userId"));
        User user = userDAO.findById(userId);

        log.log(Level.INFO, "View profile: " + user.getEmail());
        log.log(Level.DEBUG, user);

        req.setAttribute("currentUser", user);
        return ReferencePages.PROFILE;
    }
}
