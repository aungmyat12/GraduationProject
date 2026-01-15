package jointest;

import dao.DeliveryCompleteDBAccess;

public class JoinTest_OrderControl501 {

	public static void main(String[] args) throws Exception {
		try {
			DeliveryCompleteDBAccess dcDao = new DeliveryCompleteDBAccess();
			int result = dcDao.completeDeliveryByCustId(1);
			System.out.println("更新件数:" + result);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
