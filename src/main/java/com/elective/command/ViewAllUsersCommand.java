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
import java.util.ArrayList;
import java.util.List;

public class ViewAllUsersCommand implements Command{
    static Logger log = LogManager.getLogger(ViewAllUsersCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException, IllegalAccessException {
        UserDAO userDAO = getDaoFactory().getUserDAO();
        List<User> userList = new ArrayList<>();
        final int total = 10;
        int userId;

        String userIdParam = req.getParameter("userId");
        String page = req.getParameter("page");
        String login = req.getParameter("userLogin");

        if(page != null) {
            int pageid = Integer.parseInt(page);
            if (pageid != 1) {
                pageid = pageid - 1;
                pageid = pageid * total + 1;
            }
            userList= userDAO.getUsers(pageid,total);
            log.log(Level.INFO, "View all user");
        }
        if(login != null) {
            User user = userDAO.findByEmail(login);
            if(user != null)
                userList.add(user);
            log.log(Level.INFO, "Find user: " +userList);
        }
        if(userIdParam != null) {
            userId = Integer.parseInt(userIdParam);
            User user = userDAO.findById(userId);
            userDAO.changeUserState(userId, !user.isBlock());
        }

        double userCount = userDAO.countUsers();
        req.setAttribute("pageCount", Math.ceil(userCount/total));

        req.setAttribute("userList", userList);

        return ReferencePages.VIEW_ALL_USERS;
    }
}
