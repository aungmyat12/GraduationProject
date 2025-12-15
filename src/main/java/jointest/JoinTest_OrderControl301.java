package jointest;

import java.util.ArrayList;

import model.Item;
import model.OrderControlUtility;

public class JoinTest_OrderControl301 {

	public static void main(String[] args) {
		ArrayList<Item> list = new ArrayList<Item>();
		Item item = new Item("D001", "コーラ", null, 120);
		list.add(item);
		String[][] tableData = OrderControlUtility.itemToArray(list);
		System.out.println("項目1～5");
		System.out.println(tableData[0][0]);
		System.out.println(tableData[0][1]);
		System.out.println(tableData[0][2]);
		System.out.println(tableData[0][4]);

	}

}
