package jointest;

import dao.CustomerModifyDBAccess;
import model.Customer;

public class JoinTest_OrderControl201 {
	public static void main(String[] args) throws Exception {
		try {
			Customer customer = new Customer(1, "青木まゆみ", "アオキマユミ", "09012345678", "test");
			CustomerModifyDBAccess cmDao = new CustomerModifyDBAccess();
			int count = cmDao.modifyCustomer(customer);
			System.out.println("更新件数: " + count);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
