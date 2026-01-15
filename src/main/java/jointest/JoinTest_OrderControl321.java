package jointest;

import java.util.ArrayList;

import dao.OrderRegisterDBAccess;
import model.Customer;
import model.Item;
import model.OrderDetail;
import model.Tax;

public class JoinTest_OrderControl321 {

	public static void main(String[] args) {
		try {
			ArrayList<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
			Customer customer = new Customer(1,"青木まゆみ","アオキマユミ","09012345678","東京都千代田区神田小川町1-1-1");
			Item item = new Item("D001", "コーラ", null, 120);
			Tax tax = new Tax(3, 0.10, null);
			OrderDetail orderDetail = new OrderDetail(0, customer, item, "2014-03-27",2, tax, 1);
			orderDetailList.add(orderDetail);
		    OrderRegisterDBAccess oRDao = new OrderRegisterDBAccess();
		    oRDao.registerOrder(orderDetailList);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
