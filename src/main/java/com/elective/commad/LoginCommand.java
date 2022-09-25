package com.elective.commad;

import com.elective.db.dao.DAOFactory;
import com.elective.db.dao.UserDAO;
import com.elective.db.dao.mysql.MysqlDAOFactory;
import com.elective.db.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class LoginCommand implements Command{
    private final DAOFactory daoFactory;

    public LoginCommand(){
        daoFactory = MysqlDAOFactory.getInstance();
    }
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        UserDAO userDAO = daoFactory.getUserDAO();
        String login = req.getParameter("login");
        System.out.println("LOGIN: " + login);
        String password = req.getParameter("password");
        System.out.println("PASSWORD: " + password);

        //DB get User
        //Logic if admin -> admin.jsp if clien -> client.jsp
        //User to session
        User user = new User();
        user.setEmail(login);
        user.setPassword(password);
        user.setFirstName("Vlad");
        user.setLastName("Mirhorodskyi");
        userDAO.insert(user);

        return "";
    }
}
