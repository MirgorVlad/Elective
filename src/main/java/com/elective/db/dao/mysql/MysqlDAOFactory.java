package com.elective.db.dao.mysql;

import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DAOFactory;
import com.elective.db.dao.UserDAO;

public class MysqlDAOFactory extends DAOFactory {
    @Override
    public UserDAO getUserDAO() {
        return new MysqlUserDAO();
    }

    @Override
    public CourseDAO getCourseDAO() {
        return new MysqlCourseDAO();
    }
}
