package jointest;

import action.CustomerModifyAction;
import model.Customer;

public class JoinTest_OrderControl203 {

	public static void main(String[] args) throws Exception {
		try {
			Customer customer = new Customer(1, "青木まゆみ", "アオキマユミ", "09012345678", "test");
			CustomerModifyAction customerModifyAction = new CustomerModifyAction();
			int count = customerModifyAction.execute(customer);
			System.out.println("更新件数: " + count);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
