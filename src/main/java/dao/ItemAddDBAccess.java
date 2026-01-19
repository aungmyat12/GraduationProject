/**
 * クラス名：	ItemAddDBAccess
 * 概要　　：	商品追加DAO
 * 作成者名：	ウー
 * 作成日　：	2026/01/16
 * 修正者名：
 * 修正日　：
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Item;

public class ItemAddDBAccess extends ControlDBAccess {
	public boolean existsItemId(String itemId) throws Exception {
	    String sql = "SELECT COUNT(*) FROM item WHERE ITEMID = ?";
	    try (Connection con = createConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, itemId);
	        ResultSet rs = ps.executeQuery();
	        rs.next();
	        return rs.getInt(1) > 0;
	    }
	}
	public int addItem(Item item) throws Exception {
		// データベース接続を取得
		Connection con = createConnection();
		// SQL実行用PreparedStatement
		PreparedStatement pstmt = null;
		// 追加件数
		int count = 0;
		try {
			// 商品情報追加用SQL（INSERT）
            String sql =
                "INSERT INTO ITEM (ITEMID, ITEMNAME, SIZE, PRICE, USERID) " +
                "VALUES (?, ?, ?, ?, ?)";

            pstmt = con.prepareStatement(sql);

            // プレースホルダに値を設定
            pstmt.setString(1, item.getItemId());
            pstmt.setString(2, item.getItemName());
            pstmt.setString(3, item.getSize());
            pstmt.setInt(4, item.getPrice());
            pstmt.setInt(5, 1);
            // SQL実行
            count = pstmt.executeUpdate();
		} catch (SQLException e) {
			// SQL例外発生時は業務用例外として再スロー
			throw new Exception("商品情報追加処理に失敗しました！管理者に連絡してください。", e);
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
