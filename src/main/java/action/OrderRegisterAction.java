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
	
	public ArrayList<OrderDetail> execute(ArrayList<OrderDetail> orderDetailList) throws Exception {
		TaxSearchDBAccess dao = new TaxSearchDBAccess();
		Tax tax = dao.searchCurrentTax();
		int custId = 0;
		for (OrderDetail orderDetail: orderDetailList) {
			custId = orderDetail.getCustomer().getCustId();
			orderDetail.setTax(tax);
		}
		OrderRegisterDBAccess orDAO = new OrderRegisterDBAccess();
		orDAO.registerOrder(orderDetailList);
		OrderConfirmDBAccess ocDao = new OrderConfirmDBAccess();
		orderDetailList = ocDao.searchOrderByCustId(1);
		return orderDetailList;
	}
}
