package action;

import java.util.ArrayList;

import dao.CustomerSearchDBAccess;
import model.Customer;
import model.OrderControlUtility;

public class CustomerSearchAction {

	public String[][] execute(String[] data) throws Exception {
		String[][] customerData = new String[0][0];
		data[0] = data[0].trim();
		data[1] = data[1].trim();
		CustomerSearchDBAccess dao = new CustomerSearchDBAccess();
		if (!data[0].isEmpty() && data[1].isEmpty()) {
			ArrayList<Customer> list = dao.searchCustomerByTel(data[0]);
			customerData = OrderControlUtility.customerToArray(list);
		} else if (data[0].isEmpty() && !data[1].isEmpty()) {
			ArrayList<Customer> list = dao.searchCustomerByKana(data[1]);
			customerData = OrderControlUtility.customerToArray(list);
		} else {
			ArrayList<Customer> list = dao.searchCustomer(data[0], data[1]);
			customerData = OrderControlUtility.customerToArray(list);
		}
		return customerData;
	}
}
