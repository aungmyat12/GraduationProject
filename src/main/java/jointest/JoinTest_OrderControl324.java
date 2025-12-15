package jointest;

import dao.TaxSearchDBAccess;
import model.Tax;

public class JoinTest_OrderControl324 {

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
