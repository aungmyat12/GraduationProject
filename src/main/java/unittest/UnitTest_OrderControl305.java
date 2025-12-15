package unittest;

import java.util.ArrayList;

import dao.ItemMenuDisplayDBAccess;
import model.Item;

public class UnitTest_OrderControl305 {

	public static void main(String[] args) {
		try {
			ItemMenuDisplayDBAccess imDao = new ItemMenuDisplayDBAccess();
			ArrayList<Item> list = imDao.searchAllItem();
			System.out.println(list.size());
			System.out.println(list.get(0).getItemId());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
