package jointest;

import java.util.ArrayList;

import dao.ItemMenuDisplayDBAccess;
import model.Item;

public class JoinTest_OrderControl305 {

	public static void main(String[] args) {
		try {
			ItemMenuDisplayDBAccess dao = new ItemMenuDisplayDBAccess();
			ArrayList<Item> list = dao.searchAllItem();
			System.out.println(list.get(0).getItemId());
			System.out.println(list.get(0).getItemName());
			System.out.println(list.get(0).getSize());
			System.out.println(list.get(0).getPrice());
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

}
