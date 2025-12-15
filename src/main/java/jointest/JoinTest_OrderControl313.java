package jointest;

import action.OrderInputDisplayAction;
import model.Customer;

public class JoinTest_OrderControl313 {

	public static void main(String[] args) {
		try {
			String custId = "1";
			OrderInputDisplayAction orderInputDisplayAction = new OrderInputDisplayAction();
			Customer customer = orderInputDisplayAction.execute(custId);
			System.out.println(customer.getCustName());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
