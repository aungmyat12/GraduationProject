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

	/**
	 * 現在有効な消費税率情報を取得する。
	 * （ENDDATE が NULL または 現在日時より後の消費税率を取得）
	 *
	 * return 現在有効な消費税率情報（Tax）
	 * throws Exception 検索に失敗した場合
	 */
	public Tax searchCurrentTax() throws Exception {

	    // DB接続の確立
	    Connection con = createConnection();

	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Tax tax = null;

	    try {

	        // -------------------------
	        // SQL文の準備
	        // ENDDATE が NULL または未来日の TAX を取得
	        // -------------------------
	        String sql =
	                "SELECT TAXID, RATE, ENDDATE FROM TAX " +
	                "WHERE ENDDATE IS NULL OR ENDDATE > NOW()";

	        pstmt = con.prepareStatement(sql);
	        rs = pstmt.executeQuery();

	        // 1件だけ取得する（現在有効な税率は通常1件）
	        if (rs.next() == true) {

	            int taxId = rs.getInt("TAXID");
	            double rate = rs.getDouble("RATE");
	            String endDate = rs.getString("ENDDATE");

	            tax = new Tax(taxId, rate, endDate);
	        }

	    } catch (SQLException e) {
	        // SQL 実行エラー
	        throw new Exception("消費税検索処理に失敗しました！管理者に連絡してください。");
	    } finally {

	        // ResultSet のクローズ（null チェック付き）
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                // DB切断に失敗した場合
	                throw new Exception("DB切断時にエラーが発生しました。", e);
	            }
	        }

	        // PreparedStatement のクローズ
	        if (pstmt != null) {
	            try {
	                pstmt.close();
	            } catch (SQLException e) {
	                // DB切断に失敗した場合
	                throw new Exception("DB切断時にエラーが発生しました。", e);
	            }
	        }
	    }

	    // DB接続のクローズ
	    closeConnection(con);

	    return tax;
	}
}
