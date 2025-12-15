package unittest;

import java.util.ArrayList;

import dao.DeliveryConfirmDBAccess;
import model.OrderDetail;

//単体番号1 searchDeliveryByCustId() メソッド　ドライバクラス
public class UnitTest_OrderControl401 {
	public static void main(String[] args) {
		DeliveryConfirmDBAccess dao = new DeliveryConfirmDBAccess();
		try {
			//項番1：正常データでコンストラクタをテスト
			ArrayList<OrderDetail>  list1 = dao.searchDeliveryByCustId(1);
			System.out.println(list1.size());
			System.out.println(list1.get(0).getNo());
			System.out.println("---------------------");
			
			//項番2：異常データでコンストラクタをテスト
			ArrayList<OrderDetail>  list2 = dao.searchDeliveryByCustId(0);
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
