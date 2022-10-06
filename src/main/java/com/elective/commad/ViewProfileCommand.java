package com.elective.commad;

import com.elective.ReferencesPages;
import com.elective.db.dao.DBException;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ViewProfileCommand implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException, IllegalAccessException {
        UserDAO userDAO = daoFactory.getUserDAO();
        int userId = Integer.parseInt(req.getParameter("userId"));
        User user = userDAO.findById(userId);
        req.setAttribute("user", user);
        return ReferencesPages.PROFILE;
    }
}
