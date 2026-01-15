package action;

import java.util.ArrayList;

import dao.CustomerControlDBAccess;
import model.Customer;
import model.OrderControlUtility;

public class CustomerControlAction {

	public String[][] execute() throws Exception {
		String[][] customerData = new String[0][0]; // お客様の配列を作る
		ArrayList<Customer> list = new ArrayList<Customer>(); // リストを作成
		CustomerControlDBAccess dao = new CustomerControlDBAccess(); // メソッドを呼び出すためのインスタンス化
		list = dao.searchCustomer();
		if (list.size() != 0 && list != null) { // リストにデータがあるかチェック
			customerData = OrderControlUtility.customerToArray(list); // リストを配列化したデータを代入
		}
		return customerData;
	}
}
