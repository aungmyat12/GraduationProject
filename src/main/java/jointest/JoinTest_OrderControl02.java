package jointest;

import java.util.ArrayList;

import dao.CustomerSearchDBAccess;
import model.Customer;

public class JoinTest_OrderControl02 {

	public static void main(String[] args) throws Exception {
		CustomerSearchDBAccess dao = new CustomerSearchDBAccess(); 
		ArrayList<Customer> list1 = dao.searchCustomerByTel("09012345678");
		if (list1.size() != 0) {
			System.out.println(list1.get(0).getCustId());
			System.out.println(list1.get(0).getCustName());
			System.out.println(list1.get(0).getKana());
			System.out.println(list1.get(0).getTel());
			System.out.println(list1.get(0).getAddress());
			System.out.println("---------------------");
		}
		ArrayList<Customer> list2 = dao.searchCustomerByKana("イトウハナエ");
		if (list2.size() != 0) {
			System.out.println(list2.get(0).getCustId());
			System.out.println(list2.get(0).getCustName());
			System.out.println(list2.get(0).getKana());
			System.out.println(list2.get(0).getTel());
			System.out.println(list2.get(0).getAddress());
			System.out.println("---------------------");
		}
		 ArrayList<Customer> list3 = dao.searchCustomer("0326457513", "シンディサンタナ");
		if (list3.size() != 0) {
			System.out.println(list3.get(0).getCustId());
			System.out.println(list3.get(0).getCustName());
			System.out.println(list3.get(0).getKana());
			System.out.println(list3.get(0).getTel());
			System.out.println(list3.get(0).getAddress());
			System.out.println("---------------------");
		}
	}

}
