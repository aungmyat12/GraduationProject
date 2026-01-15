/**
 * クラス名：	Item
 * 概要　　：	商品情報
 * 作成者名：	ウー
 * 作成日　：	2025/12/02
 * 修正者名：
 * 修正日　：
 */

package model;
//商品を表すクラス
public class Item {
	private String itemId; // 商品ID
	private String itemName; // 商品名
	private String size; // 商品のサイズ
	private int price; // 商品の単価
	public Item() {}
	// コンストラクタ：インスタンス生成時に各情報をセットする
	public Item(String itemId, String itemName, String size, int price) {
		setItemId(itemId);	// itemId属性に記録する
		setItemName(itemName);	// itemName属性に記録する
		setSize(size);	// size属性に記録する
		setPrice(price);	// price属性に記録する
	}
	// -------------------------
    // setter / getter メソッド
    // -------------------------
	// setterとは属性を記録するために使う
	// getterとは属性はprivateで直接アクセスできないからgetを使ってアクセスする
	
	 // itemId をセットする
	public void setItemId(String itemId) {	
		this.itemId = itemId;	
	}
	// itemId を取得する
	public String getItemId() {	
		return itemId;
	}
	// itemName をセットする
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	// itemName を取得する
	public String getItemName() {
		return itemName;
	}
	// size をセットする
	public void setSize(String size) {
		this.size = size;
	}
	// size を取得する
	public String getSize() {
		return size;
	}
	// price をセットする
	public void setPrice(int price) {
		this.price = price;
	}
	// price を取得する
	public int getPrice() {
		return price;
	}

}
