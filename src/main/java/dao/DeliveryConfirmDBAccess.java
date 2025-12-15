/**
 * クラス名：	DeliveryConfirmDBAccess
 * 概要　　：	配達確認DAO
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
import java.util.ArrayList;

import model.Customer;
import model.Item;
import model.OrderDetail;
import model.Tax;

public class DeliveryConfirmDBAccess extends ControlDBAccess {
	/**
	 * 指定した顧客IDの注文情報（OrderDetail）をすべて取得する。
	 *
	 * param custId 検索する顧客ID
	 * return OrderDetail のリスト
	 * throws Exception 検索に失敗した場合
	 */
	public ArrayList<OrderDetail> searchDeliveryByCustId(int custId) throws Exception {

	    // DB接続の確立
	    Connection con = createConnection();

	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    // 結果を格納するリスト
	    ArrayList<OrderDetail> list = new ArrayList<OrderDetail>();

	    try {
	        // -------------------------
	        // SQL文の準備
	        // 顧客情報、商品情報、消費税率情報を JOIN して
	        // 一度に注文詳細を取得する。
	        // -------------------------
	        String sql =
	            "SELECT O.NO, O.ORDERDATE, O.QUANTITY, O.STATUS, " +
	            "C.CUSTID, C.CUSTNAME, C.KANA, C.TEL, C.ADDRESS, " +
	            "I.ITEMID, I.ITEMNAME, I.SIZE, I.PRICE, " +
	            "T.TAXID, T.RATE, T.ENDDATE " +
	            "FROM ORDERDETAIL O " +
	            "LEFT JOIN CUSTOMER C ON O.CUSTID = C.CUSTID " +
	            "LEFT JOIN ITEM I ON O.ITEMID = I.ITEMID " +
	            "LEFT JOIN TAX T ON O.TAXID = T.TAXID " +
	            "WHERE O.CUSTID = ? AND O.STATUS = 1 "; // ※ステータス 1 の注文のみ取得
//	            "ORDER BY O.ORDERDATE DESC, O.NO DESC";

	        pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, custId); // プレースホルダに顧客IDを設定

	        rs = pstmt.executeQuery();

	        // -------------------------
	        // 結果セットを 1件ずつ取り出して、
	        // Customer / Item / Tax / OrderDetail オブジェクトに変換
	        // -------------------------
	        while (rs.next() == true) {

	        	// 顧客情報
	            String custName = rs.getString("CUSTNAME");
	            String kana = rs.getString("KANA");
	            String tel = rs.getString("TEL");
	            String address = rs.getString("ADDRESS");
	            Customer customer = new Customer(custId, custName, kana, tel, address);

	            // 消費税率情報
	            int taxId = rs.getInt("TAXID");
	            double rate = rs.getDouble("RATE");
	            String endDate = rs.getString("ENDDATE");
	            Tax tax = new Tax(taxId, rate, endDate);

	            // 商品情報
	            String itemId = rs.getString("ITEMID");
	            String itemName = rs.getString("ITEMNAME");
	            String size = rs.getString("SIZE");
	            int price = rs.getInt("PRICE");
	            Item item = new Item(itemId, itemName, size, price);

	            // 注文情報
	            Long no = rs.getLong("NO");
	            String orderDate = rs.getString("ORDERDATE");
	            int quantity = rs.getInt("QUANTITY");
	            int status = rs.getInt("STATUS");

	            // OrderDetail オブジェクト生成
	            OrderDetail orderDetail =
	                    new OrderDetail(no, customer, item, orderDate, quantity, tax, status);

	            // リストに追加
	            list.add(orderDetail);
	        }

	    } catch (SQLException e) {
	        // SQL 実行エラー
	        throw new Exception("配達情報検索処理に失敗しました！管理者に連絡してください。", e);

	    } finally {
	    	// ResultSet のクローズ（null チェック付き）
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
    				throw new Exception("DB切断時にエラーが発生しました。", e);
	            }
	        }

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

	    return list;
	}
}
