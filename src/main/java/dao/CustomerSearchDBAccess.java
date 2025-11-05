package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Customer;

public class CustomerSearchDBAccess {
	private Connection createConnection() throws Exception {
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:65534/KIDDA_LA",
					"user1",
					"pass1");
		} catch(ClassNotFoundException e) {
			System.out.println("JDBCドライバが見つかりません。");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB接続時にエラーが発生しました。");
			e.printStackTrace();
		}
		return con;
	}
	private void closeConnection(Connection con) {
		try{
			if(con != null) {
				con.close();
			}
		} catch(SQLException e) {
			System.out.println("DB切断時にエラーが発生しました。");
			e.printStackTrace();
		}
	}
	public ArrayList<Customer> searchCustomerByTel(String tel) throws Exception {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Customer> list = new ArrayList<Customer>();
		try{
			if(con != null) {
				String sql = "SELECT CUSTID, CUSTNAME, KANA, ADDRESS FROM customer WHERE tel=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, tel);
				rs = pstmt.executeQuery();
				while(rs.next() == true) {
					int custId = rs.getInt("CUSTID");
					String custName = rs.getString("CUSTNAME");
					String kana = rs.getString("KANA");
					String address = rs.getString("ADDRESS");
					Customer customer = new Customer(custId, custName, kana, tel, address);
					list.add(customer);
				}
			}
		} catch(SQLException e) {
			System.out.println(
					"DB切断時にエラーが発生しました（商品検索）。");
			e.printStackTrace();
		} finally {
			try{
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try{
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
		closeConnection(con);
		return list;
	}
	public ArrayList<Customer> searchCustomerByKana(String kana) throws Exception {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Customer> list = new ArrayList<Customer>();
		try{
			if(con != null) {
				String sql = "SELECT CUSTID, CUSTNAME, KANA, TEL, ADDRESS FROM customer WHERE kana=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, kana);
				rs = pstmt.executeQuery();
				while(rs.next() == true) {
					int custId = rs.getInt("CUSTID");
					String custName = rs.getString("CUSTNAME");
					String tel = rs.getString("TEL");
					String address = rs.getString("ADDRESS");
					Customer customer = new Customer(custId, custName, kana, tel, address);
					list.add(customer);
				}
			}
		} catch(SQLException e) {
			System.out.println(
					"DB切断時にエラーが発生しました（商品検索）。");
			e.printStackTrace();
		} finally {
			try{
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try{
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
		closeConnection(con);
		return list;
	}
	public ArrayList<Customer> searchCustomer(String tel, String kana) throws Exception {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Customer> list = new ArrayList<Customer>();
		try{
			if(con != null) {
				String sql = "SELECT CUSTID, CUSTNAME, KANA, ADDRESS FROM customer WHERE tel=? AND kana=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, tel);
				pstmt.setString(2, kana);
				rs = pstmt.executeQuery();
				while(rs.next() == true) {
					int custId = rs.getInt("CUSTID");
					String custName = rs.getString("CUSTNAME");
					String address = rs.getString("ADDRESS");
					Customer customer = new Customer(custId, custName, kana, tel, address);
					list.add(customer);
				}
			}
		} catch(SQLException e) {
			System.out.println(
					"DB切断時にエラーが発生しました（商品検索）。");
			e.printStackTrace();
		} finally {
			try{
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try{
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
		closeConnection(con);
		return list;
	}
}
