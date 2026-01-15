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

public class CustomerDeleteDBAccess extends ControlDBAccess {

	public int deleteCustomer(int custId) throws Exception {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		int count = 0;

		try {
			String sql = "DELETE FROM customer WHERE CUSTID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, custId);
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			// SQL例外発生時は業務用例外として再スロー
			throw new Exception("顧客情報削除処理に失敗しました！管理者に連絡してください。", e);
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
