/**
 * クラス名：	Item
 * 概要　　：	商品情報
 * 作成者名：	ウー
 * 作成日　：	2025/12/02
 * 修正者名：
 * 修正日　：
 */

package model;

public class Item {
	private String itemId;
	private String itemName;
	private String size;
	private int price;
	public Item(String itemId, String itemName, String size, int price) {
		setItemId(itemId);
		setItemName(itemName);
		setSize(size);
		setPrice(price);
	
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemId() {
		return itemId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemName() {
		return itemName;
	}

	public void setSize(String size) {
		this.size = size;
	}
	public String getSize() {
		return size;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	public int getPrice() {
		return price;
	}

}
