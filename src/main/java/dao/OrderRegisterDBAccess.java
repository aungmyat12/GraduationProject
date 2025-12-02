/**
 * クラス名：	OrderRegisterDBAccess
 * 概要　　：	注文情報登録DAO
 * 作成者名：	ウー
 * 作成日　：	2025/12/03
 * 修正者名：
 * 修正日　：
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import model.OrderDetail;

public class OrderRegisterDBAccess extends ControlDBAccess {

	public void registerOrder(ArrayList<OrderDetail> list) throws Exception {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "INSERT INTO ORDERDETAIL (CUSTID, ITEMID, ORDERDATE, QUANTITY, TAXID, STATUS) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			for (OrderDetail order : list) {
			    pstmt.setInt(1, order.getCustomer().getCustId());
			    pstmt.setString(2, order.getItem().getItemId());
			    pstmt.setString(3, order.getOrderDate());
			    pstmt.setInt(4, order.getQuantity());
			    pstmt.setInt(5, order.getTax().getTaxId());
			    pstmt.setInt(6, order.getStatus());

			    pstmt.addBatch();
			}

			pstmt.executeBatch();
		} catch (SQLException e) {
			throw new Exception("注文登録処理に失敗しました！管理者に連絡してください。");
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		closeConnection(con);
	}
}
