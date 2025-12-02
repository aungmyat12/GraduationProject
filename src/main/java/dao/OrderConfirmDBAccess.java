/**
 * クラス名：	OrderConfirmDBAccess
 * 概要　　：	注文情報確認DAO
 * 作成者名：	ウー
 * 作成日　：   2025/12/03
 * 修正者名：
 * 修正日　：
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Customer;
import model.Item;
import model.OrderDetail;
import model.Tax;

public class OrderConfirmDBAccess extends ControlDBAccess {

	public ArrayList<OrderDetail> searchOrderByCustId(int custId) throws Exception {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OrderDetail> list = new ArrayList<OrderDetail>();
		try {
			String sql =
				    "SELECT O.NO, O.ORDERDATE, O.QUANTITY, O.STATUS, " +
				    "C.CUSTID, C.CUSTNAME, C.KANA, C.TEL, C.ADDRESS, " +
				    "I.ITEMID, I.ITEMNAME, I.SIZE, I.PRICE, " +
				    "T.TAXID, T.RATE, T.ENDDATE " +
				    "FROM ORDERDETAIL O " +
				    "LEFT JOIN CUSTOMER C ON O.CUSTID = C.CUSTID " +
				    "LEFT JOIN ITEM I ON O.ITEMID = I.ITEMID " +
				    "LEFT JOIN TAX T ON O.TAXID = T.TAXID " +
				    "WHERE O.CUSTID = ? AND O.STATUS = 1 "
				    + "ORDER BY O.ORDERDATE DESC";  
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, custId);
			rs = pstmt.executeQuery();
			while(rs.next() == true) {
				
				String custName = rs.getString("CUSTNAME");
				String kana = rs.getString("KANA");
				String tel = rs.getString("TEL");
				String address = rs.getString("ADDRESS");
				Customer customer = new Customer(custId, custName, kana, tel, address);
				
				int taxId = rs.getInt("TAXID");
				double rate = rs.getDouble("RATE");
				String endDate = rs.getString("ENDDATE");
				Tax tax = new Tax(taxId, rate, endDate);
				
				String itemId = rs.getString("ITEMID");
				String itemName = rs.getString("ITEMNAME");
				String size = rs.getString("SIZE");
				int price = rs.getInt("PRICE");
				Item item = new Item(itemId, itemName, size, price);

				Long no = rs.getLong("NO");
				String orderDate = rs.getString("ORDERDATE");
				int quantity = rs.getInt("QUANTITY");
				int status = rs.getInt("STATUS");
				OrderDetail orderDetail = new OrderDetail(no, customer, item, orderDate, quantity, tax, status);
				list.add(orderDetail);
			}
		} catch (SQLException e) {
			throw new Exception("配達情報検索処理に失敗しました！管理者に連絡してください。");
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
