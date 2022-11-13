package com.elective.db.dao.mysql;

import com.elective.db.dao.ConnectionFactory;
import com.elective.db.dao.DBException;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.Course;
import com.elective.db.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlUserDAO implements UserDAO {
    static Logger log = LogManager.getLogger(MysqlUserDAO.class);

    Connection getConnection() throws SQLException {
        return ConnectionFactory.getConnection();
    }

    @Override
    public int countUsers() throws SQLException {
        int count = 0;
        try(Connection con = getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQLQueris.COUNT_USERS)) {
            if (rs.next()){
                count = rs.getInt("users");
            }
            log.log(Level.DEBUG, "Count all users");
        }
        return count;
    }

    @Override
    public void insert(User user) throws SQLException, DBException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try(Connection con = getConnection()) {
            pstmt = con.prepareStatement(SQLQueris.INSERT_USER,
                    Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            pstmt.setString(k++, user.getFirstName());
            pstmt.setString(k++, user.getLastName());
            pstmt.setString(k++, user.getEmail());
            pstmt.setString(k++, user.getPassword());

            if (pstmt.executeUpdate() > 0) {
               setUserID(rs, pstmt, user);
            } else
                throw new DBException("Cannot insert user");

            insertRole(user);

            log.log(Level.DEBUG, "Insert user " + user.getEmail());

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
    public List<User> getAll() throws SQLException, DBException {
        List<User> userList = new ArrayList<>();
        try(Connection con = getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQLQueris.SELECT_ALL_USERS)) {
            while (rs.next()){
                userList.add(createUser(rs));
            }

            log.log(Level.DEBUG, "Get all users");

        }
        return userList;
    }



    @Override
    public User findByEmail(String email) throws SQLException, DBException {
        User user = null;
        ResultSet rs;
        try(Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQLQueris.FIND_USER_BY_EMAIL)) {
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();

            log.log(Level.DEBUG, "Find by email " + email);

            if(rs.next()){
                user = createUser(rs);
            }
        }
        return user;
    }

    @Override
    public User findById(int id) throws SQLException, DBException {
        ResultSet rs = null;
        try(Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQLQueris.FIND_USER_BY_ID)) {
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            log.log(Level.DEBUG, "Find by id " + id);

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
    public String getRole(int id) throws SQLException, DBException {
        String role = null;
        try(Connection con = getConnection()) {
            if(isStudent(con, id)) role = STUDENT_ROLE;
            else if(isTeacher(id)) role = TEACHER_ROLE;
            else if(isManager(con, id)) role = MANAGER_ROLE;
        }
        if(role == null)
            throw new DBException("cannot find roll");
        return role;
    }

    @Override
    public List<User> getAllTeachers() throws SQLException, DBException {
        List<User> teachers = new ArrayList<>();
        try(Connection con = getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQLQueris.SELECT_ALL_TEACHERS)) {
            while (rs.next()){
                teachers.add(createUser(rs));
            }
            log.log(Level.DEBUG, "Get all teachers");
        }
        return teachers;
    }

    @Override
    public void changeUserState(int userId, boolean state) throws SQLException, DBException {
        try(Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQLQueris.UPDATE_USER_STATE)) {
            pstmt.setBoolean(1, state);
            pstmt.setInt(2, userId);
            int upd = pstmt.executeUpdate();
            if(upd < 0){
                log.log(Level.WARN, "Can not update state to " + state);
                throw new DBException("Can not update state");
            }
            log.log(Level.DEBUG, "Change user state to " + state);
        }
    }

    public boolean isManager(Connection con, int id) throws SQLException {
        ResultSet rs = null;
        try(PreparedStatement pstmt = con.prepareStatement(SQLQueris.FIND_MANAGER)) {
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            //log.log(Level.DEBUG, "Checking if user " + user.getEmail() + " is a manager");
            return rs.next();
        } finally {
            if(rs != null){
                rs.close();
            }
        }
    }

    @Override
    public List<User> getUsers(int start, int total) throws DBException {
        List<User> users = new ArrayList<>();
        ResultSet rs;
        try(Connection con = getConnection();
        PreparedStatement pstm = con.prepareStatement(SQLQueris.GET_USERS_PORTION)) {
            pstm.setInt(1, start-1);
            pstm.setInt(2, total);
            rs = pstm.executeQuery();
            while (rs.next()){
                users.add(createUser(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public boolean isTeacher(int id) throws SQLException {
        ResultSet rs = null;
        try(Connection con = getConnection();
                PreparedStatement pstmt = con.prepareStatement(SQLQueris.FIND_TEACHER)) {
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            //log.log(Level.INFO, "Checking if user "+user.getEmail()+" is a teacher ");
            return rs.next();
        } finally {
            if(rs != null){
                rs.close();
            }
        }
    }

    public  boolean isStudent(Connection con, int id) throws SQLException {
        ResultSet rs = null;
        try(PreparedStatement pstmt = con.prepareStatement(SQLQueris.FIND_STUDENT)) {
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            //log.log(Level.DEBUG, "Checking if user "+user.getEmail()+" is a student ");
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
        user.setBlock(rs.getBoolean("blocked"));
        user.setRole(getRole(user.getId()));
        return user;
    }

    private void insertStudent(User user) throws SQLException, DBException {
        try(Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQLQueris.INSERT_STUDENT)) {
            pstmt.setInt(1, user.getId());
            int upd = pstmt.executeUpdate();
            log.log(Level.DEBUG, "Insert user "+user.getEmail());
            if(upd < 0){
                log.log(Level.WARN, "Cannot insert user "+user.getEmail());
                throw new DBException("Can not insert student");
            }
        }
    }

    private void insertTeacher(User user) throws SQLException, DBException {
        try(Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQLQueris.INSERT_TEACHER)) {
            pstmt.setInt(1, user.getId());
            int upd = pstmt.executeUpdate();
            log.log(Level.DEBUG, "Insert teacher "+user.getEmail());
            if(upd < 0){
                log.log(Level.WARN, "Cannot insert teacher "+user.getEmail());
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
