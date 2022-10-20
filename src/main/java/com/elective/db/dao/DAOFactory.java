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

    public static synchronized DAOFactory getInstance() {
        if (instance == null) {

            //Properties props = new Properties();
            try {
                //props.load(new FileInputStream(DAOFactory.class.getResource("/").getPath() + "app.properties"));
               // String daoFQN = props.getProperty("dao.fqn");
                Class<?> c = Class.forName("com.elective.db.dao.mysql.MysqlDAOFactory");
                Constructor<?> constr = c.getDeclaredConstructor();
                instance = (DAOFactory) constr.newInstance();
            } catch (ReflectiveOperationException ex) {
                throw new IllegalStateException("Cannot obtain an instance of DAO", ex);
            }
        }
        return instance;
    }

    public abstract UserDAO getUserDAO();
    public abstract CourseDAO getCourseDAO();
    public abstract JournalDAO getJournalDAO();


}
