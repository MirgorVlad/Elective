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
import java.util.List;

public class ViewAllUsersCommand implements Command{
    static Logger log = LogManager.getLogger(ViewAllUsersCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException, IllegalAccessException {
        UserDAO userDAO = getDaoFactory().getUserDAO();
        int userId;
        String userIdParam = req.getParameter("userId");
        if(userIdParam != null) {
            userId = Integer.parseInt(userIdParam);
            User user = userDAO.findById(userId);
            userDAO.changeUserState(userId, !user.isBlock());
        }

        List<User> userList = userDAO.getAll();
        req.setAttribute("userList", userList);

        log.log(Level.INFO, "View all user");
        log.log(Level.DEBUG, userList);

        return ReferencePages.VIEW_ALL_USERS;
    }
}
