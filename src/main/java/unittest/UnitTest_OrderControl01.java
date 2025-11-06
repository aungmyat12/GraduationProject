package unittest;
import model.Customer;

public class UnitTest_OrderControl01 {
	public static void main(String[] args) {
		try {
			// 1
			Customer customer1 = new Customer(1, "青木まゆみ", "アオキマユミ", "09012345678", "東京都千代田区神田小川町1-1-1");
			System.out.println(customer1.getCustId());
			// 2
			Customer customer2 = new Customer(0, null, null, null, null);
			System.out.println(customer2.getCustId());
			// 3
			customer2.setCustId(2);
			System.out.println(customer2.getCustId());
			// 4
			customer2.setCustId(0);
			System.out.println(customer2.getCustId());
			// 5
			int custId = customer2.getCustId();
			System.out.println(custId);
			// 6
			customer2.setCustName("青木まゆみ");
			System.out.println(customer2.getCustName());
			// 7
			customer2.setCustName(null);
			System.out.println(customer2.getCustName());
			// 8
			String custName = customer2.getCustName();
			System.out.println(custName);
			// 9
			customer2.setKana("アオキマユミ");
			System.out.println(customer2.getKana());
			// 10
			customer2.setKana(null);
			System.out.println(customer2.getKana());
			// 11
			String kana = customer2.getKana();
			System.out.println(kana);
			// 12
			customer2.setTel("09012345678");
			System.out.println(customer2.getTel());
			// 13
			customer2.setTel(null);
			System.out.println(customer2.getTel());
			// 14
			String tel = customer2.getTel();
			System.out.println(tel);
			// 15
			customer2.setAddress("東京都千代田区神田小川町1-1-1");
			System.out.println(customer2.getAddress());
			// 16
			customer2.setAddress(null);
			System.out.println(customer2.getAddress());
			// 17
			String address = customer2.getAddress();
			System.out.println(address);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
