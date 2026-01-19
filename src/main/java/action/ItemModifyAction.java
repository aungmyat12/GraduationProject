/**
 * クラス名：	ItemModifyAction
 * 概要　　：	商品情報表示アクション
 * 作成者名：	ウー	
 * 作成日　：	2025/01/16
 * 修正者名：
 * 修正日　：
 */
package action;

import dao.ItemModifyDBAccess;
import model.Item;

public class ItemModifyAction {

    /**
     * 商品情報を更新する
     * @param item 更新後の商品情報
     * @return 更新件数（1:成功 / 0:失敗）
     * @throws Exception
     */
	public int execute(Item item) throws Exception {
        ItemModifyDBAccess dao = new ItemModifyDBAccess();

        return dao.modifyItem(item);
    }
}