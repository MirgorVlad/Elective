package com.elective.db.dao.mysql;

import com.elective.db.dao.ConnectionFactory;
import com.elective.db.dao.DAOFactory;
import com.elective.db.dao.DBException;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserDAOTest {

    private String email = "test1@gmail.com";
    private String fname = "Test";
    private String lname = "First";
    private String password = "password";
    private String role = "student";
    private int id = 1;
    private boolean blocked = false;
    private final User expectedUser = createUser(id, fname, lname, email, password, role);
    @Test
    void findUserByEmailTest() throws DBException, SQLException {
        ResultSet rs = mock(ResultSet.class);
        when(rs.next())
                .thenReturn(true)
                .thenReturn(false);
        when(rs.getString("first_name")).thenReturn(fname);
        when(rs.getString("last_name")).thenReturn(lname);
        when(rs.getString("email")).thenReturn(email);
        when(rs.getString("password")).thenReturn(password);
        when(rs.getInt("id")).thenReturn(id);
        when(rs.getBoolean("blocked")).thenReturn(blocked);

        PreparedStatement pstmt = mock(PreparedStatement.class);
        when(pstmt.executeQuery()).thenReturn(rs);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(SQLQueris.FIND_USER_BY_EMAIL)).thenReturn(pstmt);

        MysqlUserDAO userDAO = mock(MysqlUserDAO.class);
        when(userDAO.getConnection()).thenReturn(con);
        when(userDAO.findByEmail(email)).thenCallRealMethod();
        when(userDAO.createUser(rs)).thenCallRealMethod();
        when(userDAO.getRole(id)).thenReturn(role);

        User actualUser = userDAO.findByEmail(email);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    void findUserByIdTest() throws DBException, SQLException {
        ResultSet rs = mock(ResultSet.class);
        when(rs.next())
                .thenReturn(true)
                .thenReturn(false);
        when(rs.getString("first_name")).thenReturn(fname);
        when(rs.getString("last_name")).thenReturn(lname);
        when(rs.getString("email")).thenReturn(email);
        when(rs.getString("password")).thenReturn(password);
        when(rs.getInt("id")).thenReturn(id);

        PreparedStatement pstmt = mock(PreparedStatement.class);
        when(pstmt.executeQuery()).thenReturn(rs);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(SQLQueris.FIND_USER_BY_ID)).thenReturn(pstmt);

        MysqlUserDAO userDAO = mock(MysqlUserDAO.class);
        when(userDAO.getConnection()).thenReturn(con);
        when(userDAO.findById(id)).thenCallRealMethod();
        when(userDAO.createUser(rs)).thenCallRealMethod();
        when(userDAO.getRole(id)).thenReturn(role);

        User actualUser = userDAO.findById(id);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    void findAllUsersTest() throws DBException, SQLException {
        int length = 3;
        List<User> expectedUserList = generateUserList(length);

        ResultSet rs = mock(ResultSet.class);
        when(rs.next())
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false);

            when(rs.getString("first_name"))
                    .thenReturn(expectedUserList.get(0).getFirstName())
                    .thenReturn(expectedUserList.get(1).getFirstName())
                    .thenReturn(expectedUserList.get(2).getFirstName());
            when(rs.getString("last_name"))
                    .thenReturn(expectedUserList.get(0).getLastName())
                    .thenReturn(expectedUserList.get(1).getLastName())
                    .thenReturn(expectedUserList.get(2).getLastName());
            when(rs.getString("email"))
                    .thenReturn(expectedUserList.get(0).getEmail())
                    .thenReturn(expectedUserList.get(1).getEmail())
                    .thenReturn(expectedUserList.get(2).getEmail());
            when(rs.getString("password"))
                    .thenReturn(expectedUserList.get(0).getPassword())
                    .thenReturn(expectedUserList.get(1).getPassword())
                    .thenReturn(expectedUserList.get(2).getPassword());
            when(rs.getInt("id"))
                    .thenReturn(expectedUserList.get(0).getId())
                    .thenReturn(expectedUserList.get(1).getId())
                    .thenReturn(expectedUserList.get(2).getId());


        Statement statement = mock(Statement.class);
        when(statement.executeQuery(SQLQueris.SELECT_ALL_USERS)).thenReturn(rs);

        Connection con = mock(Connection.class);
        when(con.createStatement()).thenReturn(statement);

        MysqlUserDAO userDAO = mock(MysqlUserDAO.class);
        when(userDAO.getConnection()).thenReturn(con);
        when(userDAO.getAll()).thenCallRealMethod();
        when(userDAO.createUser(rs)).thenCallRealMethod();


        when(userDAO.getRole(expectedUserList.get(0).getId())).thenReturn(expectedUserList.get(0).getRole());
        when(userDAO.getRole(expectedUserList.get(1).getId())).thenReturn(expectedUserList.get(1).getRole());
        when(userDAO.getRole(expectedUserList.get(2).getId())).thenReturn(expectedUserList.get(2).getRole());

        List<User> actualUserList = userDAO.getAll();

        assertEquals(expectedUserList, actualUserList);
    }

    @Test
    void insertUserThrowExceptionTest() throws SQLException, DBException {
        PreparedStatement pstmt = mock(PreparedStatement.class);
        when(pstmt.executeUpdate()).thenReturn(-1);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(SQLQueris.INSERT_USER, Statement.RETURN_GENERATED_KEYS)).thenReturn(pstmt);

        MysqlUserDAO userDAO = mock(MysqlUserDAO.class);
        Mockito.doCallRealMethod().when(userDAO).insert(expectedUser);
        when(userDAO.getConnection()).thenReturn(con);
        when(userDAO.findByEmail(email)).thenReturn(expectedUser);

        assertThrows(DBException.class,
                () ->  userDAO.insert(expectedUser));
    }

    @Test
    void isStudentTest() throws SQLException {
        ResultSet rs = mock(ResultSet.class);
        when(rs.next())
                .thenReturn(true)
                .thenReturn(false);

        PreparedStatement pstmt = mock(PreparedStatement.class);
        when(pstmt.executeQuery()).thenReturn(rs);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(SQLQueris.FIND_STUDENT)).thenReturn(pstmt);

        MysqlUserDAO userDAO = mock(MysqlUserDAO.class);
        when(userDAO.getConnection()).thenReturn(con);
        when(userDAO.isStudent(con, 1)).thenCallRealMethod();

        assertTrue(userDAO.isStudent(con, 1));
    }

    @Test
    void isTeacherTest() throws SQLException {
        ResultSet rs = mock(ResultSet.class);
        when(rs.next())
                .thenReturn(true)
                .thenReturn(false);

        PreparedStatement pstmt = mock(PreparedStatement.class);
        when(pstmt.executeQuery()).thenReturn(rs);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(SQLQueris.FIND_TEACHER)).thenReturn(pstmt);

        MysqlUserDAO userDAO = mock(MysqlUserDAO.class);
        when(userDAO.getConnection()).thenReturn(con);
        when(userDAO.isTeacher(con, 1)).thenCallRealMethod();

        assertTrue(userDAO.isTeacher(con, 1));
    }

    @Test
    void isManagerTest() throws SQLException {
        ResultSet rs = mock(ResultSet.class);
        when(rs.next())
                .thenReturn(true)
                .thenReturn(false);

        PreparedStatement pstmt = mock(PreparedStatement.class);
        when(pstmt.executeQuery()).thenReturn(rs);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(SQLQueris.FIND_MANAGER)).thenReturn(pstmt);

        MysqlUserDAO userDAO = mock(MysqlUserDAO.class);
        when(userDAO.getConnection()).thenReturn(con);
        when(userDAO.isManager(con, 1)).thenCallRealMethod();

        assertTrue(userDAO.isManager(con, 1));
    }

    private User createUser(int id, String fname, String lname, String email, String password, String role) {
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setFirstName(fname);
        user.setLastName(lname);
        user.setPassword(password);
        user.setRole(role);
        return user;
    }

    private User generateRandomUser() {
        Random random = new Random();
        int id = random.nextInt(100);
        String fname = generateString(10);
        String lname = generateString(10);
        String password = generateString(10);
        String email = fname + "@mail.com";
        String role = id < 50 ? "student" : "teacher";

        return createUser(id, fname, lname, email, password, role);

    }

    private String generateString(int targetStringLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private List<User> generateUserList(int length){
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            userList.add(generateRandomUser());
        }
        return userList;
    }
}
