package jointest;

import java.util.ArrayList;

import action.OrderRegisterAction;
import model.Customer;
import model.Item;
import model.OrderDetail;

public class JoinTest_OrderControl327 {

	public static void main(String[] args) {
		try {
			ArrayList<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
			Customer customer = new Customer(1,"青木まゆみ","アオキマユミ","09012345678","東京都千代田区神田小川町1-1-1");
			Item item = new Item("D001", "コーラ", null, 120);
			OrderDetail orderDetail = new OrderDetail(0, customer, item, "2014-03-27",2, null, 1);
			orderDetailList.add(orderDetail);
			OrderRegisterAction orderRegisterAction = new OrderRegisterAction();
			ArrayList<OrderDetail> list = orderRegisterAction.execute(orderDetailList);

			System.out.println(list.size());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
