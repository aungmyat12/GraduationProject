/**
 * クラス名：	ItemDeleteDBAccess
 * 概要　　：	商品情報削除DAO
 * 作成者名：	ウー
 * 作成日　：	2026/01/16
 * 修正者名：	
 * 修正日　：
 */
package action;

import dao.ItemDeleteDBAccess;

public class ItemDeleteAction {
	/*
	 * param itemId 削除する商品情報
	 * return 削除件数
	 * throws 削除情報の未発見
	 */
	public int execute(String itemId) throws Exception {
		ItemDeleteDBAccess dao = new ItemDeleteDBAccess();
		return dao.deleteItem(itemId);
	}

}