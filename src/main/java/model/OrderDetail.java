/**
 * クラス名：	OrderDetail
 * 概要　　：	注文明細情報
 * 作成者名：	ウー
 * 作成日　：	2025/12/02
 * 修正者名：
 * 修正日　：
 */

package model;
//注文の詳細を表すクラス
public class OrderDetail {
	// 注文番号（ユニークな値）
    private long no;
    // 注文した顧客情報
    private Customer customer;
    // 注文された商品情報
    private Item item;
    // 注文日（例 "2024-01-01"）
    private String orderDate;
    // 注文数量
    private int quantity;
    // 適用される消費税率
    private Tax tax;
    // 注文ステータス（例：1=未配達、0=配達済み など）
    private int status;
    public OrderDetail() {}
    // コンストラクタ：注文情報をまとめてセットする
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

    // 注文番号をセットする
    public void setNo(long no) {
        this.no = no;
    }
    // 注文番号を取得する
    public long getNo() {
        return no;
    }

    // 顧客情報をセットする
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    // 顧客情報を取得する
    public Customer getCustomer() {
        return customer;
    }

    // 商品情報をセットする
    public void setItem(Item item) {
        this.item = item;
    }
    // 商品情報を取得する
    public Item getItem() {
        return item;
    }

    // 注文日をセットする
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    // 注文日を取得する
    public String getOrderDate() {
        return orderDate;
    }

    // 注文数量をセットする
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    // 注文数量を取得する
    public int getQuantity() {
        return quantity;
    }

    // 税情報をセットする
    public void setTax(Tax tax) {
        this.tax = tax;
    }
    // 税情報を取得する
    public Tax getTax() {
        return tax;
    }

    // ステータスをセットする
    public void setStatus(int status) {
        this.status = status;
    }
    // ステータスを取得する
    public int getStatus() {
        return status;
    }

}
