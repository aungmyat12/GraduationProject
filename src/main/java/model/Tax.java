/**
 * クラス名：	Tax
 * 概要　　：	消費税情報
 * 作成者名：	ウー
 * 作成日　：	2025/12/02
 * 修正者名：
 * 修正日　：
 */

package model;
//消費税を表すクラス
public class Tax {
	// 消費税ID（複数の消費税率を管理する場合用）
    private int taxId;
    // 消費税率（例：0.1 など）
    private double rate;
    // 消費税率の終了日（この税率がいつまで有効か）
    private String endDate;
    public Tax() {}
    // コンストラクタ：各属性をセットする
    public Tax(int taxId, double rate, String endDate) {
        setTaxId(taxId);
        setRate(rate);
        setEndDate(endDate);
    }
    
    // -------------------------
    // setter / getter メソッド
    // -------------------------
	// setterとは属性を記録するために使う
	// getterとは属性はprivateで直接アクセスできないからgetを使ってアクセスする

    // taxId をセットする
    public void setTaxId(int taxId) {
        this.taxId = taxId;
    }
    // taxId を返す
    public int getTaxId() {
        return taxId;
    }

    // rate をセットする
    public void setRate(double rate) {
        this.rate = rate;
    }
    // rate を返す
    public double getRate() {
        return rate;
    }

    // endDate をセットする
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    // endDate を返す
    public String getEndDate() {
        return endDate;
    }

}
