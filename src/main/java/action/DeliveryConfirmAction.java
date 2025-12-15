/**
 * クラス名：	DeliveryConfirmAction
 * 概要　　：	配達情報確認アクション
 * 作成者名：	ウー
 * 作成日　：	2025/12/03
 * 修正者名：
 * 修正日　：
 */
package action;
import java.util.ArrayList;

import dao.DeliveryConfirmDBAccess;
import model.OrderDetail;
public class DeliveryConfirmAction {
	/**
	 * 指定した顧客IDの注文情報（OrderDetail）をすべて取得する。
	 *
	 * param custId 検索する顧客ID
	 * return OrderDetail のリスト
	 * throws Exception 検索に失敗した場合
	 */
	public ArrayList<OrderDetail> execute(int custId) throws Exception {

	    // 配達情報DAOの生成
	    DeliveryConfirmDBAccess dcDAO = new DeliveryConfirmDBAccess();

	    // 指定顧客IDの配達対象注文情報を取得
	    ArrayList<OrderDetail> orderDetailList = dcDAO.searchDeliveryByCustId(custId);

	    return orderDetailList;
	}
}
