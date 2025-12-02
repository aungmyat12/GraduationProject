/**
 * クラス名：	TaxSearchDBAccess
 * 概要　　：	消費税検索DAO
 * 作成者名：	ウー
 * 作成日　：	2025/12/03
 * 修正者名：
 * 修正日　：
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Tax;

public class TaxSearchDBAccess extends ControlDBAccess {

	public Tax searchCurrentTax() throws Exception {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Tax tax = null;

		try {
			String sql = "SELECT TAXID, RATE, ENDDATE FROM TAX WHERE ENDDATE IS NULL OR ENDDATE > NOW()";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if(rs.next()) {

				int taxId = rs.getInt("TAXID");
				double rate = rs.getDouble("RATE");
				String endDate = rs.getString("ENDDATE");
				tax = new Tax(taxId, rate, endDate);
			}
		} catch (SQLException e) {
			throw new Exception("顧客情報検索処理に失敗しました！管理者に連絡してください。");
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
		return tax;
	}
}
