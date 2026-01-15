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

	/**
	 * 商品情報を取得し、消費税を適用した価格に変換した上で
	 * 2次元配列形式のデータにして返す。
	 *
	 * return 商品情報の2次元配列
	 * throws Exception 処理中にエラーが発生した場合
	 */
	public String[][] execute() throws Exception {

	    // 戻り値初期化（空の2次元配列）
	    String[][] itemData = new String[0][0];

	    // 商品情報取得DAO
	    ItemMenuDisplayDBAccess imdDao = new ItemMenuDisplayDBAccess();
	    // 商品一覧の取得
	    ArrayList<Item> list = imdDao.searchAllItem();

	    // 商品リストにデータがある場合のみ処理を続行
	    if (list != null && list.size() != 0) {
	    	// 消費税率取得
	        TaxSearchDBAccess dao = new TaxSearchDBAccess();
	        Tax tax = dao.searchCurrentTax();
	        double rate = tax.getRate();
	        // Item のリストを2次元配列に変換
	        itemData = OrderControlUtility.itemToArray(list);
	        for (String[] row : itemData) {     // 行
	        	// row[4] が “消費税抜価格の文字列”
	            int price = Integer.parseInt(row[4]);

	            // 消費税込計算
	            int priceWithTax = (int) Math.floor(price * (1 + rate));

	            // カンマ付フォーマット
	            String formatted = String.format("%,d", priceWithTax);

	            // ここを上書き
	            row[4] = formatted;
	        }
	    }

	    // 結果を返す
	    return itemData;
	}
}
