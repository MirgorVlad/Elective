package com.elective.db.dao.mysql;

import com.elective.db.dao.ConnectionFactory;
import com.elective.db.dao.DAOFactory;
import com.elective.db.dao.DBException;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.User;
import org.junit.jupiter.api.Test;

import javax.naming.Context;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {

    @Test
    void insertUserTest() throws DBException, SQLException, IOException, ClassNotFoundException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        String email = "test1@mail.com";
        User expected = new User();
        expected.setEmail(email);
        expected.setRole(UserDAO.STUDENT_ROLE);
        expected.setFirstName("Test");
        expected.setLastName("First");
        expected.setPassword("password");

        userDAO.insert(expected);

        User actual = userDAO.findByEmail(email);
        assertEquals(expected, actual);
    }

}
