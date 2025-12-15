package unittest;

import java.util.ArrayList;

import dao.OrderRegisterDBAccess;
import model.Customer;
import model.Item;
import model.OrderDetail;
import model.Tax;

public class UnitTest_OrderControl307 {

	public static void main(String[] args) {
		try {
			ArrayList<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
			Customer customer = new Customer(1, "青木まゆみ", "アオキマユミ", "09012345678", "東京都千代田区神田小川町1-1-1");
			Item item = new Item("D001", "コーラ", null, 120);
			Tax tax = new Tax(3, 0.10, null);
			OrderDetail orderDetail = new OrderDetail(0, customer, item, "2014-03-27", 2, tax, 1);
			orderDetailList.add(orderDetail);
			OrderRegisterDBAccess orDao = new OrderRegisterDBAccess();
			orDao.registerOrder(orderDetailList);
			System.out.println("項番１成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		try {
			//	項番2：異常データでテスト
			ArrayList<OrderDetail> orderDetailList2 = new ArrayList<OrderDetail>();
			Customer customer2 = new Customer();
			Item item2 = new Item();
			OrderDetail orderDetail2 = new OrderDetail(0, customer2, item2, null, 0, new Tax(), 0);
			orderDetailList2.add(orderDetail2);
			OrderRegisterDBAccess orDao = new OrderRegisterDBAccess();
			orDao.registerOrder(orderDetailList2);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			System.out.println("項番２成功");
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

}
