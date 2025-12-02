package jointest;

import action.CustomerSearchAction;

public class JoinTest_OrderControl03 {

	public static void main(String[] args) {
		CustomerSearchAction customerSearchAction = new CustomerSearchAction();
		try {
			// 1. 電話番号のみ
			String[] data1 = { "09012345678", "" };
			String[][] customerData1 = customerSearchAction.execute(data1);
			System.out.println(customerData1.length);
			System.out.println(customerData1[0][0]);
			System.out.println(customerData1[0][1]);
			System.out.println(customerData1[0][2]);
			System.out.println(customerData1[0][3]);
			System.out.println("---------------------");
			// 2. カナのみ
			String[] data2 = { "", "アオキ" };
			String[][] customerData2 = customerSearchAction.execute(data2);
			System.out.println(customerData2.length);
			System.out.println(customerData2[0][0]);
			System.out.println(customerData2[0][1]);
			System.out.println(customerData2[0][2]);
			System.out.println(customerData2[0][3]);
			System.out.println("---------------------");
			// 3. 両方一致
			String[] data3 = { "09012345678", "アオキ" };
			String[][] customerData3 = customerSearchAction.execute(data3);
			System.out.println(customerData3.length);
			System.out.println(customerData3[0][0]);
			System.out.println(customerData3[0][1]);
			System.out.println(customerData3[0][2]);
			System.out.println(customerData3[0][3]);
			System.out.println("---------------------");
			// 4. 不一致
			String[] data4 = { "0120345678", "カトウ" };
			String[][] customerData4 = customerSearchAction.execute(data4);
			System.out.println(customerData4.length);
			System.out.println("---------------------");
			// 5. 前後に空白
			String[] data5 = {" 09012345678 ", " アオキ " };
			String[][] customerData5 = customerSearchAction.execute(data5);
			System.out.println(customerData5.length);
			System.out.println(customerData5[0][0]);
			System.out.println(customerData5[0][1]);
			System.out.println(customerData5[0][2]);
			System.out.println(customerData5[0][3]);
			System.out.println("---------------------");
			// 6. SQLインジェクション
			String[] data6 = { "' OR '1'='1", "" };
			String[][] customerData6 = customerSearchAction.execute(data6);
			System.out.println(customerData6.length);
			System.out.println("---------------------");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
