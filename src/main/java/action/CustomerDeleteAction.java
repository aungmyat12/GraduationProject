/**
 * クラス名：	CustomerModifyAction
 * 概要　　：	顧客情報変更アクション
 * 作成者名：	藤代
 * 作成日　：	2026/01/09
 * 修正者名：
 * 修正日　：
 */
package action;

import dao.CustomerDeleteDBAccess;

public class CustomerDeleteAction {
	/*
	 * param customer 削除する顧客情報
	 * return 削除件数
	 * throws 削除情報の未発見
	 */
	public int execute(int custId) throws Exception {
		CustomerDeleteDBAccess dao = new CustomerDeleteDBAccess();
		return dao.deleteCustomer(custId);
	}
}
