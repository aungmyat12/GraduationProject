/**
 * クラス名：	ItemAddAction
 * 概要　　：	商品追加アクション
 * 作成者名：	加藤蒼唯
 * 作成日　：	2026/01/15
 * 修正者名：
 * 修正日　：
 */
package action;

import dao.ItemAddDBAccess;
import model.Item;

public class ItemAddAction {
	/*
	 * param item 追加する商品情報
	 * return 追加件数
	 * throws 追加情報の未発見
	 */
	public int execute(Item item) throws Exception {
		
		//顧客情報新規DAOの生成
		ItemAddDBAccess iaDAO = new ItemAddDBAccess();
		if (iaDAO.existsItemId(item.getItemId())) {
            return -1; // 既に存在
        }
		int result = iaDAO.addItem(item);
		
		return result;
	}
}
