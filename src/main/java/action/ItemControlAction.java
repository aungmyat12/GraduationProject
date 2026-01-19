/**
 * クラス名：	ItemControlAction
 * 概要　　：	商品追加アクション
 * 作成者名：	ウー
 * 作成日　：	2026/01/16
 * 修正者名：
 * 修正日　：
 */
package action;

import java.util.ArrayList;

import dao.ItemControlDisplayDBAccess;
import model.Item;
import model.OrderControlUtility;

public class ItemControlAction {

	public String[][] execute() throws Exception {
		String[][] itemData = new String[0][0]; // 商品の配列を作る
		ArrayList<Item> list = new ArrayList<Item>(); // リストを作成
		ItemControlDisplayDBAccess dao = new ItemControlDisplayDBAccess(); // メソッドを呼び出すためのインスタンス化
		list = dao.searchAllItem();
		if (list.size() != 0 && list != null) { // リストにデータがあるかチェック
			itemData = OrderControlUtility.itemToArray(list); // リストを配列化したデータを代入
		}
		return itemData;
	}
}
