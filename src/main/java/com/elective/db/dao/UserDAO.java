package com.elective.db.dao;

import com.elective.db.entity.User;

import java.sql.SQLException;

public interface UserDAO {

    void insert(User user) throws SQLException;

}
