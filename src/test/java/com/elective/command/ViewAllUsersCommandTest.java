package com.elective.command;

import com.elective.Generator;
import com.elective.ReferencePages;
import com.elective.db.dao.DAOFactory;
import com.elective.db.dao.DBException;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ViewAllUsersCommandTest {
    private static Random rand;
    private static Generator generator;

    @BeforeAll
    static void setup() {
        rand = new Random();
        generator = new Generator();
    }

    @Test
    void viewAllUsersTest() throws Exception {
        List<User> userList = generator.generateUserList(5);
        User user = generator.createRandomUser(null);

        String expectedPage = ReferencePages.VIEW_ALL_USERS;

        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter("userId")).thenReturn("" + user.getId());

        HttpServletResponse resp = mock(HttpServletResponse.class);

        UserDAO userDAO = mock(UserDAO.class);
        when(userDAO.findById(user.getId())).thenReturn(user);
        when(userDAO.getAll()).thenReturn(userList);

        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getUserDAO()).thenReturn(userDAO);

        Command command = mock(ViewAllUsersCommand.class);
        when(command.execute(req, resp)).thenCallRealMethod();
        when(command.getDaoFactory()).thenReturn(daoFactory);

        String actualPage = command.execute(req, resp);

        assertEquals(expectedPage, actualPage);
    }
}
