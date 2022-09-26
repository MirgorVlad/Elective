package com.elective.db.dao.mysql;

import com.elective.db.dao.ConnectionFactory;
import com.elective.db.dao.DAOFactory;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.User;

import java.sql.*;

public class MysqlUserDAO implements UserDAO {

    @Override
    public void insert(User user) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try(Connection con = ConnectionFactory.getConnection()) {
            pstmt = con.prepareStatement(SQLQueris.INSERT_USER,
                    Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            pstmt.setString(k++, user.getFirstName());
            pstmt.setString(k++, user.getLastName());
            pstmt.setString(k++, user.getEmail());
            pstmt.setString(k++, user.getPassword());

            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int userId = rs.getInt(1);
                    user.setId(userId);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }
}
