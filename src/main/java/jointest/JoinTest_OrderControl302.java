package jointest;

import dao.TaxSearchDBAccess;
import model.Tax;

public class JoinTest_OrderControl302   {

	public static void main(String[] args) {
		try {
	        TaxSearchDBAccess dao = new TaxSearchDBAccess();
	        Tax tax = dao.searchCurrentTax();
	        System.out.println(tax.getTaxId());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

}
