/**
 * クラス名：	Customer
 * 概要　　：	顧客情報
 * 作成者名：
 * 作成日　：
 * 修正者名：
 * 修正日　：
 */

package model;

import java.io.Serializable;

public class Customer implements Serializable {
	private int custId;      //顧客ID
	private String custName; //顧客名
	private String kana;     //カナ
	private String tel;      //電話番号
	private String address;  //住所
	public Customer(int custId, String custName, String kana, String tel, String address) {
		this.custId = custId; // setCustId(custId);
		this.custName = custName; // setCustName(custName);
		this.kana = kana; // setKana(kana);
		this.tel = tel; // setTel(tel);
		this.address = address; // setAddress(address);
	}

	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
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
