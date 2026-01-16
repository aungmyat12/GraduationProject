/**
 * クラス名：	ItemDeleteDBAccess
 * 概要　　：	商品情報削除DAO
 * 作成者名：	加藤蒼唯
 * 作成日　：	2026/01/16
 * 修正者名：	
 * 修正日　：
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemDeleteDBAccess extends ControlDBAccess {
	
	public int deleteItem(String itemId) throws Exception {
		
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		int count = 0;

		try {
			String sql = "DELETE FROM item WHERE ITEMID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, itemId);
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			// SQL例外発生時は業務用例外として再スロー
			throw new Exception("商品情報削除処理に失敗しました！管理者に連絡してください。", e);
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
