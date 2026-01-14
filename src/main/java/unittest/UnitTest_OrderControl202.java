package unittest;

import action.CustomerModifyAction;
import model.Customer;

public class UnitTest_OrderControl202 {

	public static void main(String[] args) {
		try {
			CustomerModifyAction action = new CustomerModifyAction();
			
			//1_[正常系] 顧客ID 最小値
			Customer customer1 = new Customer(1, "青木まゆみ", "アオキマユミ", "09012345678", "東京都千代田区神田小川町1-1-1");
			int result1 = action.execute(customer1);
			System.out.println("更新件数:" + result1);
			
			//2_[正常系] 顧客ID 最大値
			Customer customer2 = new Customer(27, "山縣亮太", "ヤマガタリョウタ", "0326457513", "東京都千代田区神田駿河台1-1-1");
			int result2 = action.execute(customer2);
			System.out.println("更新件数:" + result2);
			
			//3_[正常系] 顧客ID 中央値
			Customer customer3 = new Customer(14, "鈴木大地", "スズキダイチ", "09024681357", "東京都千代田区神田小川町2-4-1");
			int result3 = action.execute(customer3);
			System.out.println("更新件数:" + result3);
			
			//4_[異常系] 顧客ID 下限限界値
			Customer customer4 = new Customer(0, "青木まゆみ", "アオキマユミ", "09012345678", "東京都千代田区神田小川町1-1-1");
			int result4 = action.execute(customer4);
			System.out.println("更新件数:" + result4);
			
			//5_[異常系] 顧客ID 上限限界値
			Customer customer5 = new Customer(28, "山縣亮太", "ヤマガタリョウタ", "0326457513", "東京都千代田区神田駿河台1-1-1");
			int result5 = action.execute(customer5);
			System.out.println("更新件数:" + result5);
			
			//6_[異常系] 顧客ID 負数
			Customer customer6 = new Customer(-1, "青木まゆみ", "アオキマユミ", "09012345678", "東京都千代田区神田小川町1-1-1");
			int result6 = action.execute(customer6);
			System.out.println("更新件数:" + result6);
			
			//7_[異常系] 顧客ID null
			Customer customer7 = new Customer(28, "山縣亮太", "ヤマガタリョウタ", "0326457513", "東京都千代田区神田駿河台1-1-1");
			int result7 = action.execute(customer7);
			System.out.println("更新件数:" + result7);
			
			//8_【異常系】顧客名 空文字
			Customer customer8 = new Customer(-1, "", "アオキマユミ", "09012345678", "東京都千代田区神田小川町1-1-1");
			int result8 = action.execute(customer8);
			System.out.println("更新件数:" + result8);
			
			//9_【異常系】顧客名 null
			Customer customer9 = new Customer(28, "null", "ヤマガタリョウタ", "0326457513", "東京都千代田区神田駿河台1-1-1");
			int result9 = action.execute(customer9);
			System.out.println("更新件数:" + result9);
			
			//10_【異常系】顧客名 最大長超過
			Customer customer10 = new Customer(1, "ああああああああああああああああ", "アオキマユミ", "09012345678", "東京都千代田区神田小川町1-1-1");
			int result10 = action.execute(customer10);
			System.out.println("更新件数:" + result10);
			
			//11_【異常系】顧客名 記号混在
			Customer customer11 = new Customer(-1, "青木@まゆみ", "アオキマユミ", "09012345678", "東京都千代田区神田小川町1-1-1");
			int result11 = action.execute(customer11);
			System.out.println("更新件数:" + result11);
			
			//12_【正常系】顧客名カナ 半角カナ
			Customer customer12 = new Customer(1, "青木まゆみ", "ｱｵｷﾏﾕﾐ", "09012345678", "東京都千代田区神田小川町1-1-1");
			int result12 = action.execute(customer12);
			System.out.println("更新件数:" + result12);
			
			//13_【異常系】顧客名カナ 空文字
			Customer customer13 = new Customer(1, "青木まゆみ", "", "09012345678", "東京都千代田区神田小川町1-1-1");
			int result13 = action.execute(customer13);
			System.out.println("更新件数:" + result13);
			
			//14_【異常系】顧客名カナ null
			Customer customer14 = new Customer(1, "青木まゆみ", "null", "09012345678", "東京都千代田区神田小川町1-1-1");
			int result14 = action.execute(customer14);
			System.out.println("更新件数:" + result14);
			
			//15_【異常系】顧客名カナ ひらがな
			Customer customer15 = new Customer(1, "青木まゆみ", "あおきまゆみ", "09012345678", "東京都千代田区神田小川町1-1-1");
			int result15 = action.execute(customer15);
			System.out.println("更新件数:" + result15);
			
			//16_【異常系】顧客名カナ 漢字
			Customer customer16 = new Customer(1, "青木まゆみ", "青木まゆみ", "09012345678", "東京都千代田区神田小川町1-1-1");
			int result16 = action.execute(customer16);
			System.out.println("更新件数:" + result16);
			
			//17_【異常系】顧客名カナ 英字混在
			Customer customer17 = new Customer(1, "青木まゆみ", "aokiマユミ", "09012345678", "東京都千代田区神田小川町1-1-1");
			int result17 = action.execute(customer17);
			System.out.println("更新件数:" + result17);
			
			//18_【正常系】電話番号 固定電話
			Customer customer18 = new Customer(15, "渡部香生子", "ワタナベカナコ", "0314142135", "東京都千代田区神田神保町1-1-1");
			int result18 = action.execute(customer18);
			System.out.println("更新件数:" + result18);
			
			//19_【異常系】電話番号 空文字
			Customer customer19 = new Customer(1, "青木まゆみ", "アオキマユミ", "", "東京都千代田区神田小川町1-1-1");
			int result19 = action.execute(customer19);
			System.out.println("更新件数:" + result19);
			
			//20_【異常系】電話番号 null
			Customer customer20 = new Customer(1, "青木まゆみ", "アオキマユミ", "null", "東京都千代田区神田小川町1-1-1");
			int result20 = action.execute(customer20);
			System.out.println("更新件数:" + result20);
			
			//21_【正常系】電話番号 ハイフンあり
			Customer customer21 = new Customer(1, "青木まゆみ", "アオキマユミ", "090-1234-5678", "東京都千代田区神田小川町1-1-1");
			int result21 = action.execute(customer21);
			System.out.println("更新件数:" + result21);
			
			//22_【異常系】電話番号 桁不足
			Customer customer22 = new Customer(1, "青木まゆみ", "アオキマユミ", "09012", "東京都千代田区神田小川町1-1-1");
			int result22 = action.execute(customer22);
			System.out.println("更新件数:" + result22);
			
			//23_【異常系】電話番号 桁超過
			Customer customer23 = new Customer(1, "青木まゆみ", "アオキマユミ", "09012345678910", "東京都千代田区神田小川町1-1-1");
			int result23 = action.execute(customer23);
			System.out.println("更新件数:" + result23);
			
			//24_【異常系】電話番号 全角数字
			Customer customer24 = new Customer(1, "青木まゆみ", "アオキマユミ", "０９０１２３４５６７８", "東京都千代田区神田小川町1-1-1");
			int result24 = action.execute(customer24);
			System.out.println("更新件数:" + result24);
			
			//25_【異常系】住所 ひらがなのみ
			Customer customer25 = new Customer(1, "青木まゆみ", "アオキマユミ", "03012345678", "とうきょうとちよだくかんだおがわちょう1-1-1");
			int result25 = action.execute(customer25);
			System.out.println("更新件数:" + result25);
			
			//26_【異常系】住所 空文字
			Customer customer26 = new Customer(1, "青木まゆみ", "アオキマユミ", "03012345678", "");
			int result26 = action.execute(customer26);
			System.out.println("更新件数:" + result26);
			
			//27_【異常系】住所 null記号混在
			Customer customer27 = new Customer(1, "青木まゆみ", "アオキマユミ", "03012345678", "null");
			int result27 = action.execute(customer27);
			System.out.println("更新件数:" + result27);

			//28_【異常系】 DB接続切断
			Customer customer28 = new Customer(1, "青木まゆみ", "アオキマユミ", "09012345678", "東京都千代田区神田小川町1-1-1");
			int result28 = action.execute(customer28);
			System.out.println("更新件数:" + result28);
			
			//29_【異常系】 SQLException
			Customer customer29 = new Customer(1, "青木まゆみ", "アオキマユミ", "09012345678", "東京都千代田区神田小川町1-1-1");
			int result29 = action.execute(customer29);
			System.out.println("更新件数:" + result29);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
