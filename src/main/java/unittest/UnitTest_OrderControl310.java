package unittest;

import java.util.ArrayList;

import action.OrderRegisterAction;
import model.Customer;
import model.Item;
import model.OrderDetail;

public class UnitTest_OrderControl310 {

	public static void main(String[] args) {
		OrderRegisterAction orderRegisterAction = new OrderRegisterAction();
		try {
			//項番1：正常データでコンストラクタをテスト
			ArrayList<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
			Customer customer = new Customer(1, "青木まゆみ", "アオキマユミ", "09012345678", "東京都千代田区神田小川町1-1-1");
			Item item = new Item("D001", "コーラ", null, 120);
			OrderDetail orderDetail = new OrderDetail(0, customer, item, "2014-03-27", 2, null, 1);
			orderDetailList.add(orderDetail);
			ArrayList<OrderDetail> list1 = orderRegisterAction.execute(orderDetailList);
			System.out.println(list1.size());
			System.out.println(list1.get(0).getNo());
			System.out.println("---------------------");
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		try {
			//項番1：正常データでコンストラクタをテスト
			ArrayList<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
			Customer customer = new Customer(1, "青木まゆみ", "アオキマユミ", "09012345678", "東京都千代田区神田小川町1-1-1");
			Item item = new Item("D001", "コーラ", null, 120);
			OrderDetail orderDetail = new OrderDetail(0, customer, item, "2014-03-27", -1, null, 1);
			orderDetailList.add(orderDetail);
			ArrayList<OrderDetail> list1 = orderRegisterAction.execute(orderDetailList);
			System.out.println(list1.size());
			System.out.println(list1.get(0).getNo());
			System.out.println("---------------------");
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		try {
			//項番2：異常データでコンストラクタをテスト
			ArrayList<OrderDetail> orderDetailList2 = new ArrayList<OrderDetail>();
			Customer customer2 = new Customer();
			Item item2 = new Item();
			OrderDetail orderDetail2 = new OrderDetail(0, customer2, item2, null, 0, null, 0);
			orderDetailList2.add(orderDetail2);
			ArrayList<OrderDetail> list2 = orderRegisterAction.execute(orderDetailList2);
			System.out.println(list2.size());
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.println(e.getMessage());			
		}

	}

}
