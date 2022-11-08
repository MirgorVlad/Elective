package com.elective.db.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionFactory {

    private static final DataSource dataSource;

    private ConnectionFactory(){
    }

    static {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/TestDB");
            System.out.println("ds ==> " + dataSource);
        } catch (NamingException ex) {
            throw new IllegalStateException("Cannot obtain a data source", ex);
        }
     }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
