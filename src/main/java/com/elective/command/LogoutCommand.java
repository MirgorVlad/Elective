package com.elective.command;

import com.elective.ReferencePages;
import com.elective.db.dao.DBException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class LogoutCommand implements Command{

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException, IllegalAccessException {
        if(req.getSession().getAttribute("user") != null){
            req.getSession().removeAttribute("user");
        }
        return ReferencePages.LOGIN_PAGE;
    }
}
