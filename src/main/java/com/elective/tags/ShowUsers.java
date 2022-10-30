package com.elective.tags;

import com.elective.db.dao.UserDAO;
import com.elective.db.entity.User;
import jakarta.servlet.http.HttpSession;


import jakarta.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ShowUsers extends TagSupport {
    private List<User> userList;
    private ResourceBundle bundle;

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public int doStartTag(){
        getLocale(pageContext.getSession());
        try {
            pageContext.getOut().write(
                    getOutTable()
            );
        } catch (IOException e) {
            throw new RuntimeException("Cannot show users", e);
        }
        return SKIP_BODY;
    }

    private String getOutTable() {
        String out = "<table border=\"1\">\n" +
                "    <tr class=\"header\">\n" +
                "        <th>"+bundle.getString("profile.name")+"</th>\n" +
                "        <th>"+bundle.getString("profile.email")+"</th>\n" +
                "        <th>"+bundle.getString("profile.role")+"</th>\n" +
                "        <th>"+bundle.getString("profile.action")+"</th>\n" +
                "    </tr>";

        for(User user : userList){
            out += " <tr>\n" +
                    "      <th>"+user.getFullName()+"</th>\n" +
                    "      <th><a href=\"controller?command=viewProfile&userId="+user.getId()+"\">"+user.getEmail()+"</a></th>\n" +
                    "      <th>"+bundle.getString("role."+user.getRole())+"</th>\n" +
                    "      <th><a href=\"controller?command=viewAllUsers&userId="+user.getId()+"\">\n";
            if(!user.getRole().equals(UserDAO.MANAGER_ROLE)) {
                if (user.isBlock()) {
                    out += bundle.getString("manager.block");
                } else {
                    out += bundle.getString("manager.unblock");
                }
            }

            out+= "</th></tr>";
        }
        out += "</table>";
        return out;
    }

    private void getLocale(HttpSession session) {
        String lang = "en";
        if( session.getAttribute("lang") != null)
            lang = (String) session.getAttribute("lang");

        Locale locale = Locale.of(lang);

        bundle = ResourceBundle.getBundle("messages", locale);
    }

}
