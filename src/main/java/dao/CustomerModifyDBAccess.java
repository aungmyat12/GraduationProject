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
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		int count = 0;

		try {
			String sql = "UPDATE CUSTOMER SET CUSTNAME=?, KANA=?, TEL=?, ADDRESS=? WHERE CUSTID=?"; 
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, customer.getCustName());
			pstmt.setString(2, customer.getKana());
			pstmt.setString(3, customer.getTel());
			pstmt.setString(4, customer.getAddress());
			pstmt.setInt(5, customer.getCustId());
			count = pstmt.executeUpdate();
			if (count == 0) {
			    throw new Exception("更新対象の顧客が存在しません。");
			}
		} catch (SQLException e) {
			throw new Exception("顧客情報更新処理に失敗しました！管理者に連絡してください。", e);
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
		return count;
	}
}
