package action;

import java.util.ArrayList;

import dao.ItemControlDisplayDBAccess;
import model.Item;
import model.OrderControlUtility;

public class ItemControlAction {

	public String[][] execute() throws Exception {
		String[][] itemData = new String[0][0]; // お客様の配列を作る
		ArrayList<Item> list = new ArrayList<Item>(); // リストを作成
		ItemControlDisplayDBAccess dao = new ItemControlDisplayDBAccess(); // メソッドを呼び出すためのインスタンス化
		list = dao.searchAllItem();
		if (list.size() != 0 && list != null) { // リストにデータがあるかチェック
			itemData = OrderControlUtility.itemToArray(list); // リストを配列化したデータを代入
		}
		return itemData;
	}
}
