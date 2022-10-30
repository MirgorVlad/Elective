package com.elective.command;

import com.elective.db.dao.DAOFactory;
import com.elective.db.dao.DBException;
import com.elective.db.dao.mysql.MysqlDAOFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public interface Command {

    DAOFactory daoFactory = MysqlDAOFactory.getInstance();
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException, IllegalAccessException, UnsupportedEncodingException;
}
