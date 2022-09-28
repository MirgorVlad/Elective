package com.elective.commad;

import com.elective.ReferencesPages;
import com.elective.db.dao.DAOFactory;
import com.elective.db.dao.DBException;
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
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException {
        UserDAO userDAO = daoFactory.getUserDAO();
        String page = "";

        String email = req.getParameter("email");
        System.out.println("LOGIN: " + email);
        String password = req.getParameter("password");
        System.out.println("PASSWORD: " + password);

        //DB get User
        //Logic if admin -> admin.jsp if clien -> client.jsp
        //User to session

        User user = userDAO.find(email);
        userDAO.getRole(user);

        if(user != null){
            if(user.getPassword().equals(password)) {
                req.getSession().setAttribute("user", user);
                if(user.getRole().equals(UserDAO.STUDENT_ROLE))
                    return ReferencesPages.STUDENT_PAGE;
                if(user.getRole().equals(UserDAO.TEACHER_ROLE))
                    return ReferencesPages.TEACHER_PAGE;
                if(user.getRole().equals(UserDAO.MANAGER_ROLE))
                    return ReferencesPages.MANAGER_PAGE;
            } else
                throw new DBException("wrong password");
        } else
            throw new DBException("cannot find user");

        return null;
    }
}
