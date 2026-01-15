package unittest;

import dao.TaxSearchDBAccess;
import model.Tax;

public class UnitTest_OrderControl308 {

	public static void main(String[] args) {
		try {
			TaxSearchDBAccess tsDao = new TaxSearchDBAccess();
			Tax tax = tsDao.searchCurrentTax();
			System.out.println(tax.getTaxId());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
