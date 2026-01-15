/**
 * クラス名：	Customer
 * 概要　　：	顧客情報
 * 作成者名：   ウー
 * 作成日　：   2025/10/31
 * 修正者名：   ウー
 * 修正日　：   2025/11/05
 */

package model;

import java.io.Serializable;

public class Customer implements Serializable {
	private int custId;      //顧客ID
	private String custName; //顧客名
	private String kana;     //カナ
	private String tel;      //電話番号
	private String address;  //住所
	public Customer() {}
	public Customer(int custId, String custName, String kana, String tel, String address) {
		setCustId(custId); // custId属性に記録する
		setCustName(custName); // custName属性に記録する
		setKana(kana); // kana属性に記録する
		setTel(tel); // tel属性に記録する
		setAddress(address); // address属性に記録する
	}

	public int getCustId() { // 属性はprivateで直接アクセスできないからgetを使ってアクセスする
		return custId;
	}
	public void setCustId(int custId) { // 属性を記録するために使う
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getKana() {
		return kana;
	}
	public void setKana(String kana) {
		this.kana = kana;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
