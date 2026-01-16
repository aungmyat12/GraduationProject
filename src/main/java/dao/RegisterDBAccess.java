/**
 * クラス名：	CustomerModifyDBAccess
 * 概要　　：	顧客情報変更DAO
 * 作成者名：	ウー
 * 作成日　：	2026/01/09
 * 修正者名：	
 * 修正日　：
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class RegisterDBAccess extends ControlDBAccess {
	// メール重複チェック
    public boolean emailExists(String email) throws Exception {
        try (Connection con = createConnection();
             PreparedStatement pstmt = con.prepareStatement("SELECT COUNT(*) FROM user WHERE email = ?")) {

            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
	// User登録するメソッド
	public int insertUser(User user) throws Exception {

		// DB接続取得
		Connection con = createConnection();
		PreparedStatement pstmt = null;

		// 実行結果（登録件数）
		int result = 0;

		try {
			// 接続確認
			if (con != null) {

				// INSERT文作成
				String sql = "INSERT INTO user (name, email, password) VALUES (?, ?, ?)";
				pstmt = con.prepareStatement(sql);

				// プレースホルダに値をセット
				pstmt.setString(1, user.getName());     // 名前
				pstmt.setString(2, user.getEmail());    // Email
				pstmt.setString(3, user.getPassword()); // パスワード

				// SQL実行（登録）
				result = pstmt.executeUpdate();
				System.out.println("result" + result);
			}

		} catch (SQLException e) {
			// 登録処理エラー
			throw new Exception("ユーザー登録処理に失敗しました！管理者に連絡してください。", e);

		} finally {
			// PreparedStatement クローズ
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				throw new Exception("DB切断時にエラーが発生しました。", e);
			}
		}

		// DB接続クローズ
		closeConnection(con);

		// 登録結果を返す（1：成功、0：失敗）
		return result;
	}

}
