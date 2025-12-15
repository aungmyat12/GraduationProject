package unittest;

import java.sql.Connection;

import dao.ControlDBAccess;

public class UnitTest_OrderControl304 extends ControlDBAccess {

	public static void main(String[] args) {
	    Connection con = null;
	    try {
			 // ControlDBAccess のインスタンスを作成
	        UnitTest_OrderControl304 dao = new UnitTest_OrderControl304();

	        // インスタンスメソッドを呼び出す
	        con = dao.createConnection();
	        System.out.println(con);
	        dao.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}