package unittest;

import model.Item;

public class UnitTest_OrderControl301 {

	public static void main(String[] args) {
		try {
			// 【1】正常値でコンストラクタをテスト
            Item item1 = new Item("P001", "マルゲリータ", "M", 1700);
            System.out.println(item1.getItemId());
            System.out.println("---------------------");

            // 【2】全て null・0 のデータでコンストラクタをテスト
            Item item2 = new Item(null, null, null, 0);
            System.out.println(item2.getItemId());
            System.out.println("---------------------");

            Item item3 = new Item();

            // 【3】itemId を変更（正常値）
            item3.setItemId("P002");
            System.out.println(item3.getItemId());
            System.out.println("---------------------");

            // 【4】itemId を null に変更（異常値）
            item3.setItemId(null);
            System.out.println(item3.getItemId());
            System.out.println("---------------------");

            // 【5】itemId のゲッター確認（正常データ）
            String itemId1 = item1.getItemId();
            System.out.println(itemId1);
            System.out.println("---------------------");

            // 【6】itemId のゲッター確認（null データ）
            String itemId2 = item2.getItemId();
            System.out.println(itemId2);
            System.out.println("---------------------");

            // 【7】itemName を変更（正常値）
            item3.setItemName("イタリアンバジル");
            System.out.println(item3.getItemName());
            System.out.println("---------------------");

            // 【8】itemName を null に変更（異常値）
            item3.setItemName(null);
            System.out.println(item3.getItemName());
            System.out.println("---------------------");

            // 【9】itemName のゲッター確認
            String itemName1 = item1.getItemName();
            System.out.println(itemName1);
            System.out.println("---------------------");

            // 【10】itemName のゲッター確認（null データ）
            String itemName2 = item2.getItemName();
            System.out.println(itemName2);
            System.out.println("---------------------");

            // 【11】size を変更（正常値）
            item3.setSize("L");
            System.out.println(item3.getSize());
            System.out.println("---------------------");

            // 【12】size を null に変更（異常値）
            item3.setSize(null);
            System.out.println(item3.getSize());
            System.out.println("---------------------");

            // 【13】size のゲッター確認
            String size1 = item1.getSize();
            System.out.println(size1);
            System.out.println("---------------------");

            // 【14】size のゲッター確認（null データ）
            String size2 = item2.getSize();
            System.out.println(size2);
            System.out.println("---------------------");

            // 【15】price を変更（正常値）
            item3.setPrice(2800);
            System.out.println(item3.getPrice());
            System.out.println("---------------------");

            // 【16】price を 0 に変更
            item3.setPrice(0);
            System.out.println(item3.getPrice());
            System.out.println("---------------------");

            // 【17】price のゲッター確認
            int price1 = item1.getPrice();
            System.out.println(price1);
            System.out.println("---------------------");

            // 【18】price のゲッター確認（0 データ）
            int price2 = item2.getPrice();
            System.out.println(price2);
            System.out.println("---------------------");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
