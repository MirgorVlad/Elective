package com.elective.commad;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        System.out.println("LOGIN: " + login);
        String password = req.getParameter("password");
        System.out.println("PASSWORD: " + password);

        //DB get User
        //Logic if admin -> admin.jsp if clien -> client.jsp
        //User to session

        return "";
    }
}
