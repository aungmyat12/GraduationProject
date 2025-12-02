/**
 * クラス名：	OrderDetail
 * 概要　　：	注文明細情報
 * 作成者名：	ウー
 * 作成日　：	2025/12/02
 * 修正者名：
 * 修正日　：
 */

package model;

public class OrderDetail {
	private long no;
	private Customer customer;
	private Item item;
	private String orderDate;
	private int quantity;
	private Tax tax;
	private int status;
	public OrderDetail(long no, Customer customer, Item item, String orderDate,
			int quantity, Tax tax, int status) {
		setNo(no);
		setCustomer(customer);
		setItem(item);
		setOrderDate(orderDate);
		setQuantity(quantity);
		setTax(tax);
		setStatus(status);
	}
	public void setNo(long no) {
		this.no = no;
	}
	public long getNo() {
		return no;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Item getItem() {
		return item;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setTax(Tax tax) {
		this.tax = tax;
	}
	public Tax getTax() {
		return tax;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getStatus() {
		return status;
	}

}
