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