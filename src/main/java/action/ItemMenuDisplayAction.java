/**
 * クラス名：	ItemMenuDisplayAction
 * 概要　　：	商品情報表示アクション
 * 作成者名：	ウー	
 * 作成日　：	2025/12/03
 * 修正者名：
 * 修正日　：
 */

package action;

import java.util.ArrayList;

import dao.ItemMenuDisplayDBAccess;
import dao.TaxSearchDBAccess;
import model.Item;
import model.OrderControlUtility;
import model.Tax;

public class ItemMenuDisplayAction {

	public String[][] execute()  throws Exception {
		String[][] itemData = new String[0][0]; 

		ItemMenuDisplayDBAccess imdDao = new ItemMenuDisplayDBAccess();
		ArrayList<Item> list = imdDao.searchAllItem();
		if (list != null && list.size() != 0) { // リストにデータがあるかチェック
			TaxSearchDBAccess dao = new TaxSearchDBAccess();
			Tax tax = dao.searchCurrentTax();
			if (tax != null) {
				double rate = tax.getRate();
				for (Item item: list) {
					int priceWithTax = (int)Math.floor(item.getPrice() * (1 + rate));
					item.setPrice(priceWithTax);
				}
			}
			
			itemData = OrderControlUtility.itemToArray(list); // リストを配列化したデータを代入
		}
		return itemData;
	}
}
