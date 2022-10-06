package com.elective.db.dao.mysql;

import com.elective.db.dao.ConnectionFactory;
import com.elective.db.dao.DBException;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.User;

import java.sql.*;

public class MysqlUserDAO implements UserDAO {

    @Override
    public void insert(User user) throws SQLException, DBException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try(Connection con = ConnectionFactory.getConnection()) {
            pstmt = con.prepareStatement(SQLQueris.INSERT_USER,
                    Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            pstmt.setString(k++, user.getFirstName());
            pstmt.setString(k++, user.getLastName());
            pstmt.setString(k++, user.getEmail());
            pstmt.setString(k++, user.getPassword());

            if (pstmt.executeUpdate() > 0) {
               setUserID(rs, pstmt, user);
            }

            insertRole(user);

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    @Override
    public User findByEmail(String email) throws SQLException, DBException {
        ResultSet rs = null;
        try(Connection con = ConnectionFactory.getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQLQueris.FIND_USER_BY_EMAIL)) {
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();

            while (rs.next()){
                return createUser(rs);
            }
        }
        return null;
    }

    @Override
    public User findById(int id) throws SQLException, DBException {
        ResultSet rs = null;
        try(Connection con = ConnectionFactory.getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQLQueris.FIND_USER_BY_ID)) {
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()){
                return createUser(rs);
            }
        }
        finally {
            if(rs != null)
                rs.close();
        }
        return null;
    }

    @Override
    public void getRole(User user) throws SQLException, DBException {
        String role = null;
        try(Connection con = ConnectionFactory.getConnection()) {
            if(isStudent(con, user)) role = STUDENT_ROLE;
            else if(isTeacher(con, user)) role = TEACHER_ROLE;
            else if(isManager(con, user)) role = MANAGER_ROLE;
        }
        if(role == null)
            throw new DBException("cannot find roll");
        user.setRole(role);
    }

    private boolean isManager(Connection con, User user) throws SQLException {
        ResultSet rs = null;
        try(PreparedStatement pstmt = con.prepareStatement(SQLQueris.FIND_MANAGER)) {
            pstmt.setInt(1, user.getId());
            rs = pstmt.executeQuery();
            return rs.next();
        } finally {
            if(rs != null){
                rs.close();
            }
        }
    }

    public boolean isTeacher(Connection con, User user) throws SQLException {
        ResultSet rs = null;
        try(PreparedStatement pstmt = con.prepareStatement(SQLQueris.FIND_TEACHER)) {
            pstmt.setInt(1, user.getId());
            rs = pstmt.executeQuery();
            return rs.next();
        } finally {
            if(rs != null){
                rs.close();
            }
        }
    }

    public boolean isStudent(Connection con, User user) throws SQLException {
        ResultSet rs = null;
        try(PreparedStatement pstmt = con.prepareStatement(SQLQueris.FIND_STUDENT)) {
            pstmt.setInt(1, user.getId());
            rs = pstmt.executeQuery();
            return rs.next();
        } finally {
            if(rs != null){
                rs.close();
            }
        }
    }

    public  User createUser(ResultSet rs) throws SQLException, DBException {
        User user = new User();
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setId(rs.getInt("id"));
        getRole(user);
        return user;
    }

    private void insertStudent(User user) throws SQLException, DBException {
        try(Connection con = ConnectionFactory.getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQLQueris.INSERT_STUDENT)) {
            pstmt.setInt(1, user.getId());
            int upd = pstmt.executeUpdate();
            if(upd < 0){
                throw new DBException("Can not insert student");
            }
        }
    }

    private void insertTeacher(User user) throws SQLException, DBException {
        try(Connection con = ConnectionFactory.getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQLQueris.INSERT_TEACHER)) {
            pstmt.setInt(1, user.getId());
            int upd = pstmt.executeUpdate();
            if(upd < 0){
                throw new DBException("Can not insert teacher");
            }
        }
    }


    private void insertRole(User user) throws SQLException, DBException {
        if(user.getRole().equals(TEACHER_ROLE)){
            insertTeacher(user);
        }
        else if(user.getRole().equals(STUDENT_ROLE)){
            insertStudent(user);
        }
        else {
            throw new DBException("Unknown role");
        }
    }
    private void setUserID(ResultSet rs, PreparedStatement pstmt, User user) throws SQLException {
        rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
            int userId = rs.getInt(1);
            user.setId(userId);
        }
    }
}
