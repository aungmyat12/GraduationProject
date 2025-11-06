package unittest;

import action.CustomerSearchAction;

//単体番号3 execute()メソッド　ドライバクラス
public class UnitTest_OrderControl03 {

	public static void main(String[] args) {
		CustomerSearchAction customerSearchAction = new CustomerSearchAction();
		try {
			String[] data1 = { "09012345678", "" };
			String[][] customerData1 = customerSearchAction.execute(data1);
			System.out.println(customerData1.length);
			System.out.println(customerData1[0][0]);
			System.out.println("---------------------");
			String[] data2 = { "", "アオキ" };
			String[][] customerData2 = customerSearchAction.execute(data2);
			System.out.println(customerData2.length);
			System.out.println(customerData2[0][0]);
			System.out.println("---------------------");
			String[] data3 = { "09012345678", "アオキ" };
			String[][] customerData3 = customerSearchAction.execute(data3);
			System.out.println(customerData3.length);
			System.out.println(customerData3[0][0]);
			System.out.println("---------------------");
			String[] data4 = { "0120345678", "カトウ" };
			String[][] customerData4 = customerSearchAction.execute(data4);
			System.out.println(customerData4.length);
			System.out.println("---------------------");
			String[] data5 = { "", "" };
			String[][] customerData5 = customerSearchAction.execute(data5);
			System.out.println(customerData5.length);
			System.out.println("---------------------");
			String[] data6 = {" 09012345678 ", " アオキ " };
			String[][] customerData6 = customerSearchAction.execute(data6);
			System.out.println(customerData6.length);
			System.out.println(customerData6[0][0]);
			System.out.println("---------------------");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
