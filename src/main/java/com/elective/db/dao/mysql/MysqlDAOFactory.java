package com.elective.db.dao.mysql;

import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DAOFactory;
import com.elective.db.dao.JournalDAO;
import com.elective.db.dao.UserDAO;
/**
 * Implementation DAOFactory for MySQL
 */
public class MysqlDAOFactory extends DAOFactory {
    @Override
    public UserDAO getUserDAO() {
        return new MysqlUserDAO();
    }

    @Override
    public CourseDAO getCourseDAO() {
        return new MysqlCourseDAO();
    }

    @Override
    public JournalDAO getJournalDAO() {
        return new MysqlJournalDAO();
    }
}
