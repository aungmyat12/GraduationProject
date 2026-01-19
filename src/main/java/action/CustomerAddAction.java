/**
 * クラス名：	CustomerModifyAction
 * 概要　　：	顧客情報登録アクション
 * 作成者名：	ウー	
 * 作成日　：	2026/01/15
 * 修正者名：
 * 修正日　：
 */
package action;
import dao.CustomerAddDBAccess;
import model.Customer;

public class CustomerAddAction {
	/*
	 * param customer 登録する顧客情報
	 * return 登録件数
	 * throws 登録情報の未発見
	 */
	public int execute(Customer customer) throws Exception {
		
		//顧客情報新規DAOの生成
		CustomerAddDBAccess caDAO = new CustomerAddDBAccess();
	
		int result = caDAO.addCustomer(customer);
		
		return result;
	}
}
