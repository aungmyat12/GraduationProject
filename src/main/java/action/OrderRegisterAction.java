/**
 * クラス名：	OrderRegisterAction
 * 概要　　：	注文情報登録アクション
 * 作成者名：	ウー
 * 作成日　：	2025/12/03
 * 修正者名：
 * 修正日　：
 */
package action;
import java.util.ArrayList;

import dao.OrderConfirmDBAccess;
import dao.OrderRegisterDBAccess;
import dao.TaxSearchDBAccess;
import model.OrderDetail;
import model.Tax;
public class OrderRegisterAction {
	/**
	 * 注文情報を登録し、登録後の今回の注文を含む未配達の注文情報リストを取得して返す。
	 *
	 * param orderDetailList 登録する注文情報のリスト
	 * return 登録後の今回の注文を含む未配達の注文情報リスト
	 * throws Exception 登録または検索処理に失敗した場合
	 */
	public ArrayList<OrderDetail> execute(ArrayList<OrderDetail> orderDetailList) throws Exception {
		for (OrderDetail od : orderDetailList) {
		    if (od.getCustomer() == null ||
		        od.getCustomer().getCustId() <= 0 ||
		        od.getItem() == null ||
				od.getItem().getItemId() == null ||
				od.getOrderDate() == null ||
		        od.getQuantity() <= 0		        
		        ) {
		        throw new IllegalArgumentException("注文データが不正です");
		    }
		}
	    // 現在有効な消費税率を取得
	    TaxSearchDBAccess taxDao = new TaxSearchDBAccess();
	    Tax tax = taxDao.searchCurrentTax();

	    // 各注文に税率情報をセット
	    for (OrderDetail orderDetail : orderDetailList) {
	        orderDetail.setTax(tax);
	    }

	    // 注文情報の登録
	    OrderRegisterDBAccess oRDao = new OrderRegisterDBAccess();
	    oRDao.registerOrder(orderDetailList);

	    // 顧客IDを取得（リストの最初の注文から）
	    int custId = orderDetailList.get(0).getCustomer().getCustId();

	    // 登録後の今回の注文を含む未配達の注文情報リストを顧客ID単位で取得
	    OrderConfirmDBAccess ocDao = new OrderConfirmDBAccess();
	    orderDetailList = ocDao.searchOrderByCustId(custId);

	    // 登録後の注文情報リストを返す
	    return orderDetailList;
	}
}
