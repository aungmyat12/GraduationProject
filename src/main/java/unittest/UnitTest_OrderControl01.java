package unittest;
import model.Customer;

public class UnitTest_OrderControl01 {
	public static void main(String[] args) {
		try {
			// 1: 正常データでコンストラクタをテスト
			Customer customer1 = new Customer(1, "青木まゆみ", "アオキマユミ", "09012345678", "東京都千代田区神田小川町1-1-1");
			System.out.println(customer1.getCustId());
			System.out.println("---------------------");
			// 2: 全てnull・0のデータでコンストラクタをテスト
			Customer customer2 = new Customer(0, null, null, null, null);
			System.out.println(customer2.getCustId());
			System.out.println("---------------------");
			// 3: custIdを2に変更
			customer2.setCustId(2);
			System.out.println(customer2.getCustId());
			System.out.println("---------------------");
			// 4: custIdを0に変更
			customer2.setCustId(0);
			System.out.println(customer2.getCustId());
			System.out.println("---------------------");
			// 5: custIdのゲッターをテスト
			int custId = customer2.getCustId();
			System.out.println(custId);
			System.out.println("---------------------");
			// 6: custNameを「青木まゆみ」に設定
			customer2.setCustName("青木まゆみ");
			System.out.println(customer2.getCustName());
			System.out.println("---------------------");
			// 7: custNameをnullに設定
			customer2.setCustName(null);
			System.out.println(customer2.getCustName());
			System.out.println("---------------------");
			// 8: custNameのゲッターをテスト
			String custName = customer2.getCustName();
			System.out.println(custName);
			System.out.println("---------------------");
			// 9: kanaを「アオキマユミ」に設定
			customer2.setKana("アオキマユミ");
			System.out.println(customer2.getKana());
			System.out.println("---------------------");
			// 10: kanaをnullに設定
			customer2.setKana(null);
			System.out.println(customer2.getKana());
			System.out.println("---------------------");
			// 11: kanaのゲッターをテスト
			String kana = customer2.getKana();
			System.out.println(kana);
			System.out.println("---------------------");
			// 12: telを「09012345678」に設定
			customer2.setTel("09012345678");
			System.out.println(customer2.getTel());
			System.out.println("---------------------");
			// 13: telをnullに設定
			customer2.setTel(null);
			System.out.println(customer2.getTel());
			System.out.println("---------------------");
			// 14: telのゲッターをテスト
			String tel = customer2.getTel();
			System.out.println(tel);
			System.out.println("---------------------");
			// 15: addressを「東京都千代田区神田小川町1-1-1」に設定
			customer2.setAddress("東京都千代田区神田小川町1-1-1");
			System.out.println(customer2.getAddress());
			System.out.println("---------------------");
			// 16: addressをnullに設定
			customer2.setAddress(null);
			System.out.println(customer2.getAddress());
			System.out.println("---------------------");
			// 17: addressのゲッターをテスト
			String address = customer2.getAddress();
			System.out.println(address);
			System.out.println("---------------------");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
