package jointest;

import java.util.ArrayList;

import dao.DeliveryConfirmDBAccess;
import model.OrderDetail;

public class JoinTest_OrderControl404 {

	public static void main(String[] args) {
		try {
		    DeliveryConfirmDBAccess dcDao = new DeliveryConfirmDBAccess();
		    ArrayList<OrderDetail> list = dcDao.searchDeliveryByCustId(1);
		    System.out.println(list.get(0).getTax().getTaxId());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			}


	}

}
