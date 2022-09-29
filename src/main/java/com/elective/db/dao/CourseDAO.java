package com.elective.db.dao;

import com.elective.db.entity.Course;
import com.elective.db.entity.User;

import java.sql.SQLException;

public interface CourseDAO {

    void create(Course course) throws SQLException, DBException;

}
