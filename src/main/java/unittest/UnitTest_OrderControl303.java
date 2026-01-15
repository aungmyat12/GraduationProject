package unittest;

import model.Customer;
import model.Item;
import model.OrderDetail;
import model.Tax;

public class UnitTest_OrderControl303 {

	public static void main(String[] args) {
		try {
			Customer customer1 = new Customer(1, "青木まゆみ", "アオキマユミ", "09012345678", "東京都千代田区神田小川町1-1-1");
	        Item item1 = new Item("D001", "コーラ", null, 120);
	        Tax tax1 = new Tax(1, 0.05, "2014-03-31");

	        // 【1】正常値でコンストラクタをテスト
	        OrderDetail orderDetail1 = new OrderDetail(1, customer1, item1, "2014-03-27", 2, tax1, 1);
	        System.out.println(orderDetail1.getNo());
	        System.out.println("---------------------");
	        // 【1】正常値でコンストラクタをテスト
	        OrderDetail orderDetail2 = new OrderDetail(0, null, null, null, 0, null, 0);
	        System.out.println(orderDetail2.getNo());
	        System.out.println("---------------------");
	        OrderDetail orderDetail3 = new OrderDetail();
	       
            // 【3】no を変更
	        orderDetail3.setNo(2);
            System.out.println(orderDetail3.getNo());
            System.out.println("---------------------");

            // 【4】no を 0 に変更
            orderDetail3.setNo(0);
            System.out.println(orderDetail3.getNo());
            System.out.println("---------------------");
	        System.out.println(orderDetail1.getNo());
	        System.out.println("---------------------");
	        System.out.println(orderDetail2.getNo());
	        System.out.println("---------------------");

            // 【5】customer を変更
			Customer customer2 = new Customer(2, "伊藤華英", "イトウハナエ", "09023456781", "東京都千代田区神田小川町2-1-1");
            orderDetail3.setCustomer(customer2);
            System.out.println(orderDetail3.getCustomer().getCustId());
            System.out.println("---------------------");

            // 【6】customer を 0 に変更
            orderDetail3.setCustomer(null);
            System.out.println(orderDetail3.getCustomer());
            System.out.println("---------------------");
	        System.out.println(orderDetail1.getCustomer().getCustId());
	        System.out.println("---------------------");
	        System.out.println(orderDetail2.getCustomer());
	        System.out.println("---------------------");

            // 【7】item を変更
            Item item2 = new Item("D002", "烏龍茶", null, 120);
            orderDetail3.setItem(item2);
            System.out.println(orderDetail3.getItem().getItemId());
            System.out.println("---------------------");

            // 【8】item を null に変更
            orderDetail3.setItem(null);
            System.out.println(orderDetail3.getItem());
            System.out.println("---------------------");
	        System.out.println(orderDetail1.getItem().getItemId());
	        System.out.println("---------------------");
	        System.out.println(orderDetail2.getItem());
	        System.out.println("---------------------");

            // 【9】orderDate を変更
            orderDetail3.setOrderDate("2014-03-27");
            System.out.println(orderDetail3.getOrderDate());
            System.out.println("---------------------");

            // 【10】orderDate を null に変更
            orderDetail3.setOrderDate(null);
            System.out.println(orderDetail3.getOrderDate());
            System.out.println("---------------------");
	        System.out.println(orderDetail1.getOrderDate());
	        System.out.println("---------------------");
	        System.out.println(orderDetail2.getOrderDate());
	        System.out.println("---------------------");

            // 【11】quantity を変更
            orderDetail3.setQuantity(1);
            System.out.println(orderDetail3.getQuantity());
            System.out.println("---------------------");

            // 【12】quantity を 0 に変更
            orderDetail3.setQuantity(0);
            System.out.println(orderDetail3.getQuantity());
            System.out.println("---------------------");
	        System.out.println(orderDetail1.getQuantity());
	        System.out.println("---------------------");
	        System.out.println(orderDetail2.getQuantity());
	        System.out.println("---------------------");

            // 【13】tax を変更
	        Tax tax2 = new Tax(1, 0.05, "2014-03-31");
            orderDetail3.setTax(tax2);
            System.out.println(orderDetail3.getTax().getTaxId());
            System.out.println("---------------------");

            // 【14】tax を null に変更
            orderDetail3.setTax(null);
            System.out.println(orderDetail3.getTax());
            System.out.println("---------------------");
	        System.out.println(orderDetail1.getTax().getTaxId());
	        System.out.println("---------------------");
	        System.out.println(orderDetail2.getTax());
	        System.out.println("---------------------");

            // 【15】status を 1 に変更
            orderDetail3.setStatus(1);
            System.out.println(orderDetail3.getStatus());
            System.out.println("---------------------");

            // 【16】status を 0 に変更
            orderDetail3.setStatus(0);
            System.out.println(orderDetail3.getStatus());
            System.out.println("---------------------");
	        System.out.println(orderDetail1.getStatus());
	        System.out.println("---------------------");
	        System.out.println(orderDetail2.getStatus());
	        System.out.println("---------------------");

	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	    }
	}

}
