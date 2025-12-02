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

	protected Connection createConnection() throws Exception {
		Connection con = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver"); // JDBCドライバをロード
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:65534/KIDDA_LA",
					"user1",
					"pass1"); // データベースへの接続を確立
		} catch(ClassNotFoundException e) {
			// JDBCドライバが見つからない場合のエラー
			throw new Exception("JDBCドライバが見つかりません。", e);
		} catch (SQLException e) {
			 // DB接続エラー
			throw new Exception("DB接続処理に失敗しました！管理者に連絡してください。", e);
		}
		return con;
	}

	protected void closeConnection(Connection con)  throws Exception {
		try{
			if(con != null) { // 接続がnullでない場合、閉じる
				con.close();
			}
		} catch(SQLException e) {
            throw new Exception("DB切断時にエラーが発生しました。"); // DB切断エラー
		}
	}
}