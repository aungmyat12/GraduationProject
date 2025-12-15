package unittest;

import action.ItemMenuDisplayAction;

public class UnitTest_OrderControl309 {

	public static void main(String[] args) {
		try {
			ItemMenuDisplayAction orderRegisterAction = new ItemMenuDisplayAction();
			String[][] itemData = orderRegisterAction.execute();
			System.out.println(itemData[0][0]);
			System.out.println(itemData[0][1]);
			System.out.println(itemData[0][2]);
			System.out.println(itemData[0][4]);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
