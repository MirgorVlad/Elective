package com.elective.commad;

import com.elective.ReferencesPages;
import com.elective.db.dao.DBException;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class ViewAllUsersCommand implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException, IllegalAccessException {
        UserDAO userDAO = daoFactory.getUserDAO();
        int userId;

        if(req.getParameter("userId") != null) {
            userId = Integer.parseInt(req.getParameter("userId"));
            User user = userDAO.findById(userId);
            userDAO.changeUserState(userId, !user.isBlock());
        }

        List<User> userList = userDAO.getAll();
        req.setAttribute("userList", userList);
        return ReferencesPages.VIEW_ALL_USERS;
    }
}
