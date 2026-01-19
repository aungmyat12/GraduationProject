/**
 * クラス名：	ItemInputDisplayAction
 * 概要　　：	商品情報入力画面表示アクション
 * 作成者名：	ウー
 * 作成日　：	2025/01/16
 * 修正者名：
 * 修正日　：
 */

package action;

import dao.ItemInputDisplayDBAccess;
import model.Item;

public class ItemInputDisplayAction {
	/**
     * 指定された顧客IDをもとに顧客情報を取得して返すメソッド
     *
     * param custId 文字列で渡された顧客ID
     * return Customer オブジェクト（該当顧客が存在すればその情報）
     * throws Exception 処理中にエラーが発生した場合
     */
	public Item execute(String itemId) throws Exception {
		// 文字列で渡された顧客IDを int に変換
		ItemInputDisplayDBAccess iidDao = new ItemInputDisplayDBAccess(); // 顧客情報取得DAO
		Item item = iidDao.searchItemById(itemId); // 顧客IDを使って顧客情報を検索

		return item; // 取得した顧客情報を返す
	}
}
