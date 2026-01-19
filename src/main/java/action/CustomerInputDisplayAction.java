/**
 * クラス名：	CustomerInputDisplayAction
 * 概要　　：	顧客情報表示アクション
 * 作成者名：	ウー
 * 作成日　：	2025/1/15
 * 修正者名：
 * 修正日　：
 */

package action;

import dao.CustomerInputDisplayDBAccess;
import model.Customer;

public class CustomerInputDisplayAction {
	/**
     * 指定されたcustIdをもとに顧客情報を取得して返すメソッド
     *
     * param custId 文字列で渡された顧客ID
     * return Customer オブジェクト（該当顧客が存在すればその情報）
     * throws Exception 処理中にエラーが発生した場合
     */
	public Customer execute(String custId) throws Exception {
		// 文字列で渡された顧客IDを int に変換
		int intCustId = Integer.parseInt(custId);
		CustomerInputDisplayDBAccess cidDao = new CustomerInputDisplayDBAccess(); // 顧客情報取得DAO
		Customer customer = cidDao.searchCustomerById(intCustId); // 顧客IDを使って顧客情報を検索

		return customer; // 取得した顧客情報を返す
	}
}
