/**
 * クラス名：	CustomerSearchDBAccess
 * 概要　　：	KIDDA_LAデータベースへのアクセス（顧客情報）を管理する。
 * 作成者名：	ウー
 * 作成日　：	2025/12/02
 * 修正者名：
 * 修正日　：
 */

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ControlDBAccess {
	/**
     * データベースへの接続を作成して返すメソッド
     * 
     * return Connection 接続オブジェクト
     * throws Exception 接続に失敗した場合の例外
     */
    protected Connection createConnection() throws Exception {
        Connection con = null;
        try {
            // JDBC ドライバをロード（MySQL 用）
            Class.forName("com.mysql.cj.jdbc.Driver");

            // データベースへ接続
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:65534/KIDDA_LA",  // 接続先URL
                    "user1",                                  // ユーザー名
                    "pass1"                                   // パスワード
            );

        } catch (ClassNotFoundException e) {
            // JDBCドライバが見つからなかった場合
            throw new Exception("JDBCドライバが見つかりません。", e);

        } catch (SQLException e) {
            // DB接続に失敗した場合
            throw new Exception("DB接続処理に失敗しました！管理者に連絡してください。", e);
        }

        return con;
    }

    /**
     * DB接続を安全に切断するメソッド
     * 
     * パラメータ con 閉じる対象の Connection オブジェクト
     * throws Exception 切断時にエラーが発生した場合
     */
    protected void closeConnection(Connection con) throws Exception {
        try {
            if (con != null) { // 接続が確立されている場合のみ閉じる
                con.close();
            }
        } catch (SQLException e) {
            // 切断時のエラー
            throw new Exception("DB切断時にエラーが発生しました。", e);
        }
    }
}