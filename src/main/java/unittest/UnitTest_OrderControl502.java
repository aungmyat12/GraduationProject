package unittest;

import action.DeliveryCompleteAction;

public class UnitTest_OrderControl502 {

	public static void main(String[] args) {
		try {
			DeliveryCompleteAction dcAction = new DeliveryCompleteAction();
			int[] testCustId = {1, 10, 0, 28, -1};
			
			for(int custId : testCustId) {
				int result = dcAction.execute(custId);
				System.out.println("顧客ID：" + custId);
				System.out.println("更新件数：" + result);
				System.out.println("--------------------");
			}
			// DB停止
			int result1 = dcAction.execute(1);
			System.out.println("顧客ID：" + 1);
			System.out.println("更新件数：" + result1);
			System.out.println("--------------------");
			// SQL Exception
			int result2 = dcAction.execute(1);
			System.out.println("顧客ID：" + 1);
			System.out.println("更新件数：" + result2);
			System.out.println("--------------------");
		} catch (Exception e) {
			// その他の例外
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

}
