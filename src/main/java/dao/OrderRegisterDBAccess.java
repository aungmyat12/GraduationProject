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
	/**
	 * 注文情報を ORDERDETAIL テーブルに登録する。
	 *
	 * param orderDetailList 登録する注文情報のリスト
	 * throws Exception 登録処理に失敗した場合
	 */
	public void registerOrder(ArrayList<OrderDetail> orderDetailList) throws Exception {
//		if (orderDetailList.size() >= 1) {
//			for (OrderDetail od : orderDetailList) {
//			    if (od.getCustomer() == null ||
//			        od.getCustomer().getCustId() <= 0 ||
//			        od.getItem() == null ||
//					od.getItem().getItemId() == null ||
//					od.getOrderDate() == null ||
//			        od.getQuantity() <= 0 ||
//			        od.getTax() ==  null ||
//			        od.getTax().getTaxId() <= 0       
//			        ) {
//			        throw new IllegalArgumentException("注文データが不正です");
//			    }
//			}
//		}
	    // DB接続の確立
	    Connection con = createConnection();

	    PreparedStatement pstmt = null;

	    try {

	        // SQL文の準備
	        String sql =
	                "INSERT INTO ORDERDETAIL (CUSTID, ITEMID, ORDERDATE, QUANTITY, TAXID, STATUS) "
	                + "VALUES (?, ?, ?, ?, ?, ?)";

	        pstmt = con.prepareStatement(sql);

	        // -------------------------
	        // リスト内の注文情報を 1件ずつパラメータにセットし、
	        // バッチとして追加していく
	        // -------------------------
	        for (OrderDetail orderDetail : orderDetailList) {

	            pstmt.setInt(1, orderDetail.getCustomer().getCustId());
	            pstmt.setString(2, orderDetail.getItem().getItemId());
	            pstmt.setString(3, orderDetail.getOrderDate());
	            pstmt.setInt(4, orderDetail.getQuantity());
	            pstmt.setInt(5, orderDetail.getTax().getTaxId());
	            pstmt.setInt(6, 1);

	            pstmt.addBatch();   // バッチ追加
	        }

	        // バッチ登録の実行
	        pstmt.executeBatch();

	    } catch (SQLException e) {
	        // SQL 実行エラー
	        throw new Exception("注文登録処理に失敗しました！管理者に連絡してください。");
	    } finally {

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
	}
}
