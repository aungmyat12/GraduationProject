package jointest;

import java.util.ArrayList;

import action.DeliveryConfirmAction;
import model.OrderDetail;

public class JoinTest_OrderControl406 {

	public static void main(String[] args) {
		int custId = 1;
		try {
			DeliveryConfirmAction deliveryConfirmAction = new DeliveryConfirmAction();
			ArrayList<OrderDetail> deliveryData = deliveryConfirmAction.execute(custId);
			System.out.println(deliveryData.size());
			System.out.println("--------------------");
			System.out.println(deliveryData.get(0).getNo());
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		

	}

}
