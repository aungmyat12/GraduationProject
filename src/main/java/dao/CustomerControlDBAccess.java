package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Customer;

public class CustomerControlDBAccess extends ControlDBAccess {
	// 顧客を検索するメソッド
	public ArrayList<Customer> searchCustomer() throws Exception {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Customer> list = new ArrayList<Customer>();
		try{
			if(con != null) {
				String sql = "SELECT CUSTID, CUSTNAME, KANA, TEL, ADDRESS FROM customer ORDER BY CUSTID DESC";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					int custId = rs.getInt("CUSTID");
					String custName = rs.getString("CUSTNAME");
					String kana = rs.getString("KANA");
					String tel = rs.getString("TEL");
					String address = rs.getString("ADDRESS");
					Customer customer = new Customer(custId, custName, kana, tel, address);
					list.add(customer);
				}
			}
		} catch(SQLException e) {
			throw new Exception("顧客検索処理に失敗しました！管理者に連絡してください。", e);
		} finally {
			try{
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException e) {
				throw new Exception("DB切断時にエラーが発生しました。", e);
			}
			try{
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				throw new Exception("DB切断時にエラーが発生しました。", e);
			}
		}
		closeConnection(con);
		return list;
	}
}
