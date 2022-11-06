package com.elective.db.dao.mysql;

import com.elective.Generator;
import com.elective.db.dao.DBException;
import com.elective.db.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.sql.*;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserDAOTest {

    private static Random rand;
    private static Generator generator;

    @BeforeAll
    static void setup() {
       rand = new Random();
       generator = new Generator();
    }

    @Test
    void findUserByEmailTest() throws DBException, SQLException {
        User expectedUser = generator.createRandomUser(null);

        ResultSet rs = mock(ResultSet.class);
        when(rs.next())
                .thenReturn(true)
                .thenReturn(false);
        when(rs.getString("first_name")).thenReturn(expectedUser.getFirstName());
        when(rs.getString("last_name")).thenReturn(expectedUser.getLastName());
        when(rs.getString("email")).thenReturn(expectedUser.getEmail());
        when(rs.getString("password")).thenReturn(expectedUser.getPassword());
        when(rs.getInt("id")).thenReturn(expectedUser.getId());
        when(rs.getBoolean("blocked")).thenReturn(expectedUser.isBlock());

        PreparedStatement pstmt = mock(PreparedStatement.class);
        when(pstmt.executeQuery()).thenReturn(rs);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(SQLQueris.FIND_USER_BY_EMAIL)).thenReturn(pstmt);

        MysqlUserDAO userDAO = mock(MysqlUserDAO.class);
        when(userDAO.getConnection()).thenReturn(con);
        when(userDAO.findByEmail(expectedUser.getEmail())).thenCallRealMethod();
        when(userDAO.createUser(rs)).thenCallRealMethod();
        when(userDAO.getRole(expectedUser.getId())).thenReturn(expectedUser.getRole());

        User actualUser = userDAO.findByEmail(expectedUser.getEmail());

        assertEquals(expectedUser, actualUser);
    }

    @Test
    void findUserByIdTest() throws DBException, SQLException {
        User expectedUser = generator.createRandomUser(null);
        ResultSet rs = mock(ResultSet.class);
        when(rs.next())
                .thenReturn(true)
                .thenReturn(false);
        when(rs.getString("first_name")).thenReturn(expectedUser.getFirstName());
        when(rs.getString("last_name")).thenReturn(expectedUser.getLastName());
        when(rs.getString("email")).thenReturn(expectedUser.getEmail());
        when(rs.getString("password")).thenReturn(expectedUser.getPassword());
        when(rs.getInt("id")).thenReturn(expectedUser.getId());
        when(rs.getBoolean("blocked")).thenReturn(expectedUser.isBlock());

        PreparedStatement pstmt = mock(PreparedStatement.class);
        when(pstmt.executeQuery()).thenReturn(rs);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(SQLQueris.FIND_USER_BY_ID)).thenReturn(pstmt);

        MysqlUserDAO userDAO = mock(MysqlUserDAO.class);
        when(userDAO.getConnection()).thenReturn(con);
        when(userDAO.findById(expectedUser.getId())).thenCallRealMethod();
        when(userDAO.createUser(rs)).thenCallRealMethod();
        when(userDAO.getRole(expectedUser.getId())).thenReturn(expectedUser.getRole());

        User actualUser = userDAO.findById(expectedUser.getId());

        assertEquals(expectedUser, actualUser);
    }

    @Test
    void findAllUsersTest() throws DBException, SQLException {
        int length = 3;
        List<User> expectedUserList = generator.generateUserList(length);

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
        User expectedUser = generator.createRandomUser(null);

        PreparedStatement pstmt = mock(PreparedStatement.class);
        when(pstmt.executeUpdate()).thenReturn(0);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(SQLQueris.INSERT_USER, Statement.RETURN_GENERATED_KEYS)).thenReturn(pstmt);

        MysqlUserDAO userDAO = mock(MysqlUserDAO.class);
        Mockito.doCallRealMethod().when(userDAO).insert(expectedUser);
        when(userDAO.getConnection()).thenReturn(con);
        when(userDAO.findByEmail(expectedUser.getEmail())).thenReturn(expectedUser);

        assertThrows(DBException.class,
                () ->  userDAO.insert(expectedUser), "Cannot insert user");
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
        when(userDAO.isTeacher(1)).thenCallRealMethod();

        assertTrue(userDAO.isTeacher(1));
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
}
