package action;

import java.util.ArrayList;

import dao.CustomerSearchDBAccess;
import model.Customer;
import model.OrderControlUtility;

public class CustomerSearchAction {

	public String[][] execute(String[] data) throws Exception {
		String[][] customerData = new String[0][0]; // お客様の配列を作る
		data[0] = data[0].replaceAll("[\\s　]+", "").trim();
		data[1] = data[1].replaceAll("[\\s　]+", "").trim();
		ArrayList<Customer> list = new ArrayList<Customer>(); // リストを作成
		CustomerSearchDBAccess dao = new CustomerSearchDBAccess(); // メソッドを呼び出すためのインスタンス化
		if (!data[0].isEmpty() && data[1].isEmpty()) { // 電話番号のみ入力された場合の処理
			list = dao.searchCustomerByTel(data[0]);
		} else if (data[0].isEmpty() && !data[1].isEmpty()) { // 氏名カナのみ入力された場合の処理
			list = dao.searchCustomerByKana(data[1]);
		} else if (!data[0].isEmpty() && !data[1].isEmpty()) { // 両方入力された場合の処理
			list = dao.searchCustomer(data[0], data[1]);
		}
		if (list.size() != 0 && list != null) { // リストにデータがあるかチェック
			customerData = OrderControlUtility.customerToArray(list); // リストを配列化したデータを代入
		}
		return customerData;
	}
}
