package jointest;

import java.util.ArrayList;

import dao.OrderConfirmDBAccess;
import model.OrderDetail;

public class JoinTest_OrderControl316 {

	public static void main(String[] args) {
		try {
			int custId = 1;
			OrderConfirmDBAccess ocDao = new OrderConfirmDBAccess();
			ArrayList<OrderDetail> orderDetailList = ocDao.searchOrderByCustId(1);
			System.out.println(orderDetailList.size());
			System.out.println(orderDetailList.get(0).getItem().getItemId());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
