/**
 * クラス名：	ItemMenuDisplayDBAccess
 * 概要　　：	商品情報表示DAO
 * 作成者名：	ウー
 * 作成日　：	2025/12/02
 * 修正者名：	
 * 修正日　：
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Item;

public class ItemMenuDisplayDBAccess extends ControlDBAccess  {

	public ArrayList<Item> searchAllItem() throws Exception {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Item> list = new ArrayList<Item>();
		try {

			pstmt = con.prepareStatement("SELECT ITEMID, ITEMNAME, SIZE, PRICE FROM ITEM");
			rs = pstmt.executeQuery();

			while (rs.next()) {

				String itemId = rs.getString("ITEMID");
				String itemName = rs.getString("ITEMNAME");
				String size = rs.getString("SIZE");
				int price = rs.getInt("PRICE");
				Item item = new Item(itemId, itemName, size, price);
				list.add(item);
			}
		} catch (SQLException e) {
			throw new Exception("商品検索処理に失敗しました！管理者に連絡してください。");
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		closeConnection(con);
		return list;
	}
}
