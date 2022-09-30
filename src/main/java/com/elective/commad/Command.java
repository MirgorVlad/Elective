package com.elective.commad;

import com.elective.db.dao.DAOFactory;
import com.elective.db.dao.DBException;
import com.elective.db.dao.mysql.MysqlDAOFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public interface Command {

    DAOFactory daoFactory = MysqlDAOFactory.getInstance();
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException;
}
