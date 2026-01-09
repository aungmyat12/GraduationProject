/**
 * クラス名：	DeliveryCompleteDBAccess
 * 概要　　：	配達完了DAO
 * 作成者名：	加藤蒼唯
 * 作成日　：	2026/01/09
 * 修正者名：
 * 修正日　：
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeliveryCompleteDBAccess extends ControlDBAccess {
	/**
	 * 指定した顧客IDの配達情報（ステータス）を更新する。
	 * 
	 * param custId  更新する顧客ID
	 * return result  更新した件数
	 * throws Exception  更新処理に失敗した場合
	 */
	public int completeDeliveryByCustId(int custId) throws Exception {
		
		//DB接続の確立
		Connection con = createConnection();

	    PreparedStatement pstmt = null;
	    int result = 0;
	   
	    try {
	    	// -------------------------
	        // SQL文の準備
	        // 未配達の注文を配達済みにするための
	        // UPDATE文を実行する。
	        // -------------------------
	    	String sql = "UPDATE ORDERDETAIL SET STATUS=0 WHERE CUSTID=? AND STATUS=1"; //※ステータス１のみ更新
	    	
	    	pstmt = con.prepareStatement(sql);
	    	pstmt.setInt(1, custId); // プレースホルダに顧客IDを設定
	    	
	    	result = pstmt.executeUpdate();
	    	
	    } catch(Exception e) {
	    	// SQL実行エラー
	    	throw new Exception("配達完了処理に失敗しました！管理者に連絡してください。", e);
	    
	    } finally {
	        // PreparedStatement のクローズ
	        if (pstmt != null) {
	            try {
	                pstmt.close();
	            } catch (SQLException e) {
    				throw new Exception("DB切断時にエラーが発生しました。", e);
	            }
	        }
	    }

	    // DB接続のクローズ
	    closeConnection(con);
		return result;
	}
}
