package unittest;

import java.util.ArrayList;

import dao.OrderConfirmDBAccess;
import model.OrderDetail;

public class UnitTest_OrderControl306 {

	public static void main(String[] args) {
		try {
			int custId1 = 1;
			OrderConfirmDBAccess ocDao = new OrderConfirmDBAccess();
			ArrayList<OrderDetail> orderDetailList1 = ocDao.searchOrderByCustId(custId1);
			System.out.println(orderDetailList1.size());
			System.out.println(orderDetailList1.get(0).getNo());
			System.out.println("---------------------");
			int custId2 = 0;
			ArrayList<OrderDetail> orderDetailList2 = ocDao.searchOrderByCustId(custId2);
			System.out.println(orderDetailList2.size());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
