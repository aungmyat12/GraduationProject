/**
 * クラス名：	LoginDBAccess
 * 概要　　：	ログインDAO
 * 作成者名：	ウー
 * 作成日　：	2025/01/19
 * 修正者名：
 * 修正日　：
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;

public class LoginDBAccess extends ControlDBAccess {

    // 通常ログイン
    public User login(User user) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = createConnection();

            String sql =
                "SELECT USERID, NAME, EMAIL, PROVIDER " +
                "FROM user WHERE EMAIL = ? AND PASSWORD = ?";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());

            rs = pstmt.executeQuery();

            if (rs.next()) {
                User loginUser = new User();
                loginUser.setUserId(rs.getInt("USERID"));
                loginUser.setName(rs.getString("NAME"));
                loginUser.setEmail(rs.getString("EMAIL"));
                loginUser.setProvider(rs.getString("PROVIDER"));
                return loginUser;
            }
            return null;
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            closeConnection(con);
        }
    }

    // Googleログイン用：Email検索
    public User findByEmail(String email) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = createConnection();
            String sql =
                "SELECT USERID, NAME, EMAIL, PROVIDER " +
                "FROM user WHERE EMAIL = ?";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, email);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("USERID"));
                user.setName(rs.getString("NAME"));
                user.setEmail(rs.getString("EMAIL"));
                user.setProvider(rs.getString("PROVIDER"));
                return user;
            }
            return null;
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            closeConnection(con);
        }
    }

    // Googleユーザー新規登録
    public void insertGoogleUser(User user) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = createConnection();
            String sql =
                "INSERT INTO user(NAME, EMAIL, PROVIDER) VALUES (?, ?, 'GOOGLE')";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) pstmt.close();
            closeConnection(con);
        }
    }
}