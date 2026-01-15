/**
 * クラス名：	CustomerModifyAction
 * 概要　　：	顧客情報変更アクション
 * 作成者名：	藤代
 * 作成日　：	2026/01/09
 * 修正者名：
 * 修正日　：
 */
package action;
import dao.CustomerModifyDBAccess;
import model.Customer;

public class CustomerModifyAction {
	/*
	 * param customer 更新する顧客情報
	 * return 更新件数
	 * throws 更新情報の未発見
	 */
	public int execute(Customer customer) throws Exception {
		
		//顧客情報変更DAOの生成
		CustomerModifyDBAccess cmDAO = new CustomerModifyDBAccess();
		
		//更新する顧客の顧客情報を取得
		int count = cmDAO.modifyCustomer(customer);
		
		return count;
	}
}
