package jointest;

import java.util.ArrayList;

import dao.ItemMenuDisplayDBAccess;
import model.Item;

public class JoinTest_OrderControl304 {

	public static void main(String[] args) {
		try {
			ItemMenuDisplayDBAccess dao = new ItemMenuDisplayDBAccess();
			ArrayList<Item> list = dao.searchAllItem();
			System.out.println(list.get(0).getItemId());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

}
