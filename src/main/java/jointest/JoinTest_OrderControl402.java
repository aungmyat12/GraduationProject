package jointest;

import java.util.ArrayList;

import dao.DeliveryConfirmDBAccess;
import model.OrderDetail;

public class JoinTest_OrderControl402 {

	public static void main(String[] args) {
		try {
	       int custId = 1;
	       DeliveryConfirmDBAccess dcDao = new DeliveryConfirmDBAccess();
	        ArrayList<OrderDetail> list = dcDao.searchDeliveryByCustId(custId);
	        System.out.println(list.get(0).getCustomer().getCustId());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}
}
