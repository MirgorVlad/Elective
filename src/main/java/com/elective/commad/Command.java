package com.elective.commad;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public interface Command {
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException;
}
