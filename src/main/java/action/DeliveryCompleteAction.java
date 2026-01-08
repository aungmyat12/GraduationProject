/**
 * クラス名：	DeliveryCompleteAction
 * 概要　　：	配達完了アクション
 * 作成者名：	丸山
 * 作成日　：	20XX/06/20
 * 修正者名：
 * 修正日　：
 */
package action;

import dao.DeliveryCompleteDBAccess;

public class DeliveryCompleteAction {
	
	public int execute(int custId) throws Exception {
		DeliveryCompleteDBAccess dao = new DeliveryCompleteDBAccess();
		int result = dao.completeDeliveryByCustId(custId);
		return result;
	}
}