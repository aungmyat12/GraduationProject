package jointest;

import dao.OrderInputDisplayDBAccess;
import model.Customer;

public class JoinTest_OrderControl311 {

	public static void main(String[] args) {
		try {
			int custId = 1;
			OrderInputDisplayDBAccess oidDao = new OrderInputDisplayDBAccess();
			Customer customer = oidDao.searchCustomerById(custId);
			System.out.println(customer.getCustId());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
