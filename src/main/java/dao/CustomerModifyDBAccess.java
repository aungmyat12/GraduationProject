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
import java.sql.SQLException;

import model.Customer;

public class CustomerModifyDBAccess extends ControlDBAccess {

	public int modifyCustomer(Customer customer) throws Exception {
		// データベース接続を取得
		Connection con = createConnection();
		// SQL実行用PreparedStatement
		PreparedStatement pstmt = null;
		// 更新件数
		int count = 0;

		try {
			// 顧客情報更新用SQL
			String sql = "UPDATE CUSTOMER SET CUSTNAME=?, KANA=?, TEL=?, ADDRESS=? WHERE CUSTID=?"; 
			// PreparedStatement生成
			pstmt = con.prepareStatement(sql);
			// プレースホルダに値を設定
			pstmt.setString(1, customer.getCustName());
			pstmt.setString(2, customer.getKana());
			pstmt.setString(3, customer.getTel());
			pstmt.setString(4, customer.getAddress());
			pstmt.setInt(5, customer.getCustId());
			// SQL実行（更新）
			count = pstmt.executeUpdate();
			// 更新件数が0件の場合（対象データなし）
			if (count == 0) {
			    throw new Exception("更新対象の顧客が存在しません。");
			}
		} catch (SQLException e) {
			// SQL例外発生時は業務用例外として再スロー
			throw new Exception("顧客情報更新処理に失敗しました！管理者に連絡してください。", e);
		} finally {
			// PreparedStatementをクローズ
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		// DB接続をクローズ
		closeConnection(con);
		// 更新件数を返却
		return count;
	}
}
