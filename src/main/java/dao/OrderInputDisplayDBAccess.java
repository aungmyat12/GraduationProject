/**
 * クラス名：	OrderInputDisplayDBAccess
 * 概要　　：	注文情報入力画面表示DAO
 * 作成者名：	ウー
 * 作成日　：   2025/12/02
 * 修正者名：   
 * 修正日　：
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Customer;

public class OrderInputDisplayDBAccess extends ControlDBAccess {

	public Customer searchCustomerById(int custId) throws Exception {
		// DB接続の確立
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Customer customer = null;

		try {
			// 顧客情報を取得するSQL文の準備（CUSTID が一致する顧客を検索）
			pstmt = con.prepareStatement("SELECT CUSTNAME, KANA, TEL, ADDRESS FROM CUSTOMER WHERE CUSTID = ?");
			pstmt.setInt(1, custId); // プレースホルダーに顧客IDをセット
			rs = pstmt.executeQuery();
			// 検索結果が1件でもあれば Customer オブジェクトに詰める
			if(rs.next()) {

				String custName = rs.getString("CUSTNAME");
				String kana = rs.getString("KANA");
				String tel = rs.getString("TEL");
				String address = rs.getString("ADDRESS");
				 // Customer オブジェクトを生成
				customer = new Customer(custId, custName, kana, tel, address);
			}
		} catch (SQLException e) {
			// SQL 実行エラー
			throw new Exception("顧客情報検索処理に失敗しました！管理者に連絡してください。");
		} finally {
			// ResultSet のクローズ（null チェック付き）
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// DB切断に失敗した場合
    				throw new Exception("DB切断時にエラーが発生しました。", e);
				}
			}
            // PreparedStatement のクローズ
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
                    // DB切断に失敗した場合
    				throw new Exception("DB切断時にエラーが発生しました。", e);
				}
			}
		}

        // DB接続のクローズ
		closeConnection(con);
		// 検索した Customer オブジェクトを返す（該当なしの場合は null）
		return customer;
	}
}