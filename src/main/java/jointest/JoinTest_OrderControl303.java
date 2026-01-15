package jointest;

import dao.TaxSearchDBAccess;
import model.Tax;

public class JoinTest_OrderControl303 {

	public static void main(String[] args) {
		TaxSearchDBAccess dao = new TaxSearchDBAccess();
        try {
			Tax tax = dao.searchCurrentTax();
			System.out.println(tax.getTaxId());
			System.out.println(tax.getRate());
			System.out.println(tax.getEndDate());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}


	}

}
