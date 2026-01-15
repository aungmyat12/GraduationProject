package jointest;

import dao.OrderInputDisplayDBAccess;
import model.Customer;

public class JoinTest_OrderControl312 {

	public static void main(String[] args) {
		try {
			int custId = 1;
			OrderInputDisplayDBAccess oidDao = new OrderInputDisplayDBAccess();
			Customer customer = oidDao.searchCustomerById(custId);
			System.out.println(customer.getCustName());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
