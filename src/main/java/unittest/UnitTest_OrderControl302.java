package unittest;

import model.Tax;

public class UnitTest_OrderControl302 {

	public static void main(String[] args) {
		// 【1】正常値でコンストラクタをテスト
        Tax tax1 = new Tax(3, 0.10, null);
        System.out.println(tax1.getTaxId());
        System.out.println("---------------------");

        // 【2】異常値（0、0.0、null）でコンストラクタをテスト
        Tax tax2 = new Tax(0, 0.0, null);
        System.out.println(tax2.getTaxId());
        System.out.println("---------------------");

        // 【3】taxId を変更（正常値）
        tax2.setTaxId(2);
        System.out.println(tax2.getTaxId());
        System.out.println("---------------------");

        // 【4】taxId を 0 に変更（異常値）
        tax2.setTaxId(0);
        System.out.println(tax2.getTaxId());
        System.out.println("---------------------");

        // 【5】taxId のゲッター確認
        int taxId1 = tax1.getTaxId();
        System.out.println(taxId1);
        System.out.println("---------------------");
        // 【6】taxId のゲッター確認
        int taxId2 = tax2.getTaxId();
        System.out.println(taxId2);
        System.out.println("---------------------");

        // 【7】rate を変更（正常値）
        tax2.setRate(0.08);
        System.out.println(tax2.getRate());
        System.out.println("---------------------");

        // 【8】rate を 0 に変更（異常値許容）
        tax2.setRate(0.0);
        System.out.println(tax2.getRate());
        System.out.println("---------------------");

        // 【9】rate のゲッター確認
        double rate1 = tax1.getRate();
        System.out.println(rate1);
        System.out.println("---------------------");

        // 【10】rate のゲッター確認
        double rate2 = tax2.getRate();
        System.out.println(rate2);
        System.out.println("---------------------");

        // 【11】endDate を変更（正常値）
        tax2.setEndDate("2019-09-30");
        System.out.println(tax2.getEndDate());
        System.out.println("---------------------");

        // 【12】endDate を null に変更
        tax2.setEndDate(null);
        System.out.println(tax2.getEndDate());
        System.out.println("---------------------");

        // 【13】endDate のゲッター確認
        String endDate1 = tax1.getEndDate();
        System.out.println(endDate1);
        System.out.println("---------------------");
        // 【14】endDate のゲッター確認
        String endDate2 = tax2.getEndDate();
        System.out.println(endDate2);
        System.out.println("---------------------");
	}

}
