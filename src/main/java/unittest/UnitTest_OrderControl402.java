package unittest;

import java.util.ArrayList;

import action.DeliveryConfirmAction;
import model.OrderDetail;

public class UnitTest_OrderControl402 {
	public static void main(String[] args) {
		DeliveryConfirmAction deliveryConfirmAction = new DeliveryConfirmAction();
		try {
			//項番1：正常データでコンストラクタをテスト
			ArrayList<OrderDetail>  list1 = deliveryConfirmAction.execute(1);
			System.out.println(list1.size());
			System.out.println(list1.get(0).getNo());
			System.out.println("---------------------");
			
			//項番2：異常データでコンストラクタをテスト
			ArrayList<OrderDetail>  list2 = deliveryConfirmAction.execute(0);
			System.out.println(list2.size());
			System.out.println("---------------------");
			
			//項番3：DBを停止して、項番1のデータでコンストラクタをテスト
			System.out.println(list1.size());
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
