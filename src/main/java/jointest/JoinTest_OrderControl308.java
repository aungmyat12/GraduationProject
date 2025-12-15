package jointest;

import action.ItemMenuDisplayAction;

public class JoinTest_OrderControl308 {

	public static void main(String[] args) {
		try {
			ItemMenuDisplayAction itemMenuDisplayAction = new ItemMenuDisplayAction();
			String[][] itemData = itemMenuDisplayAction.execute();
			System.out.println(itemData[0][4]);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
