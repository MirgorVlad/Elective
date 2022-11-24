package com.elective.command;

import com.elective.db.dao.DAOFactory;
import com.elective.db.dao.DBException;
import com.elective.db.dao.mysql.MysqlDAOFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

/**
 * Interface Command defines the command objects' API
 * encapsulate all the information required for command execution
 */
public interface Command {

    DAOFactory daoFactory = MysqlDAOFactory.getInstance();

    default DAOFactory getDaoFactory(){
        return daoFactory;
    }
    String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
