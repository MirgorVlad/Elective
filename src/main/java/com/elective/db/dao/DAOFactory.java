package com.elective.db.dao;

import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.util.Properties;

public abstract class DAOFactory {

    private static DAOFactory instance;
    private static  DataSource dataSource;

    static {
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            dataSource = (DataSource)envContext.lookup("jdbc/TestDB");
            System.out.println("ds ==> " + dataSource);
        } catch (NamingException ex) {
            throw new IllegalStateException("Cannot obtain a data source", ex);
        }
    }



    public static synchronized DAOFactory getInstance() {
        if (instance == null) {

            Properties props = new Properties();
            try {
                props.load(new FileInputStream(DAOFactory.class.getResource("/").getPath() + "app.properties"));
                String daoFQN = props.getProperty("dao.fqn");
                Class<?> c = Class.forName(daoFQN);
                Constructor<?> constr = c.getDeclaredConstructor();
                instance = (DAOFactory) constr.newInstance();
            } catch (IOException | ReflectiveOperationException ex) {
                throw new IllegalStateException("Cannot obtain an instance of DAO", ex);
            }
        }
        return instance;
    }

    public abstract UserDAO getUserDAO();

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
