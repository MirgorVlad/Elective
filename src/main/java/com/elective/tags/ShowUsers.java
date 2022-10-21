package com.elective.tags;

import com.elective.db.dao.UserDAO;
import com.elective.db.entity.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class ShowUsers extends TagSupport {
    private List<User> userList;

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public int doStartTag() throws JspException {
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
        String out = "<h3>All users</h3><table border=\"1\">\n" +
                "    <tr class=\"header\">\n" +
                "        <th>Name</th>\n" +
                "        <th>Email</th>\n" +
                "        <th>Role</th>\n" +
                "        <th>Action</th>\n" +
                "    </tr>";

        for(User user : userList){
            out += " <tr>\n" +
                    "      <th>"+user.getFullName()+"</th>\n" +
                    "      <th><a href=\"controller?command=viewProfile&userId="+user.getId()+"\">"+user.getEmail()+"</a></th>\n" +
                    "      <th>"+user.getRole()+"</th>\n" +
                    "      <th><a href=\"controller?command=viewAllUsers&userId="+user.getId()+"\">\n";
            if(!user.getRole().equals(UserDAO.MANAGER_ROLE)) {
                if (user.isBlock()) {
                    out += "Unblock";
                } else {
                    out += "Block";
                }
            }

            out+= "</th></tr>";
        }
        out += "</table>";
        return out;
    }


}
