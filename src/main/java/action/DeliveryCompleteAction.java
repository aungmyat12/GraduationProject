/**
 * クラス名：	DeliveryCompleteAction
 * 概要　　：	配達完了アクション
 * 作成者名：	一柳遥海
 * 作成日　：	2026/01/20
 * 修正者名：
 * 修正日　：
 */
package action;

import dao.DeliveryCompleteDBAccess;

public class DeliveryCompleteAction {
	/**
	 * 指定した顧客IDの配達情報（ステータス）を更新する。
	 *
	 * param custId 更新する顧客ID
	 * return result 更新した件数
	 * throws Exception 更新処理に失敗した場合
	 */
	public int execute(int custId) throws Exception {
		// 配達完了DAOの生成
		DeliveryCompleteDBAccess dao = new DeliveryCompleteDBAccess();
		// 更新する顧客の顧客情報を取得
		int result = dao.completeDeliveryByCustId(custId);
		// 更新した件数の返却
		return result;
	}
}