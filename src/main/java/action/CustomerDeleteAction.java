/**
 * クラス名：	CustomerDeleteAction
 * 概要　　：	顧客情報変更アクション
 * 作成者名：	ウー
 * 作成日　：	2026/01/15
 * 修正者名：
 * 修正日　：
 */
package action;

import dao.CustomerDeleteDBAccess;

public class CustomerDeleteAction {
	/*
	 * param custId 削除する顧客ID
	 * return 削除件数
	 * throws 削除情報の未発見
	 */
	public int execute(int custId) throws Exception {
		CustomerDeleteDBAccess dao = new CustomerDeleteDBAccess();
		return dao.deleteCustomer(custId);
	}
}
