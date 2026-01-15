package jointest;

import action.CustomerSearchAction;

public class JoinTest_OrderControl04 {

	public static void main(String[] args) throws Exception {
		CustomerSearchAction customerSearchAction = new CustomerSearchAction();
		String[] data = {"09012345678", ""};
;		String[][] customerData = customerSearchAction.execute(data);
	}

}
