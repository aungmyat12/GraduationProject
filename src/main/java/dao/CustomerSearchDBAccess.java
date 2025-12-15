package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Customer;

public class CustomerSearchDBAccess extends ControlDBAccess{
	// 電話番号で顧客を検索するメソッド
	public ArrayList<Customer> searchCustomerByTel(String tel) throws Exception {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Customer> list = new ArrayList<Customer>();
		try{
			if(con != null) {
				String sql = "SELECT CUSTID, CUSTNAME, KANA, ADDRESS FROM customer WHERE TEL=?";  // SQL文の準備（プレースホルダを使う）
				pstmt = con.prepareStatement(sql); // SQLクエリを実行するためのPreparedStatementを作成
				pstmt.setString(1, tel); // プレースホルダに実際の電話番号をセット
				rs = pstmt.executeQuery();  // SQLクエリを実行して、結果をResultSetに格納
				while(rs.next() == true) {
					int custId = rs.getInt("CUSTID");
					String custName = rs.getString("CUSTNAME");
					String kana = rs.getString("KANA");
					String address = rs.getString("ADDRESS");
					Customer customer = new Customer(custId, custName, kana, tel, address);
					list.add(customer); // 検索結果をリストに追加
				}
			}
		} catch(SQLException e) {
			// DB接続エラー
			throw new Exception("DB接続処理に失敗しました！管理者に連絡してください。", e);
		} finally {
			// ResultSetとPreparedStatementを閉じる
			try{
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException e) {
				throw new Exception("DB切断時にエラーが発生しました。", e);
			}
			try{
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				throw new Exception("DB切断時にエラーが発生しました。", e);
			}
		}
		closeConnection(con); // DB接続を閉じる
		return list;
	}
	// 氏名カナで顧客を検索するメソッド
	public ArrayList<Customer> searchCustomerByKana(String kana) throws Exception {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Customer> list = new ArrayList<Customer>();
		try{
			if(con != null) {
				String sql = "SELECT CUSTID, CUSTNAME, KANA, TEL, ADDRESS FROM customer WHERE KANA LIKE ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + kana + "%"); // 部分探しのLIKEを使う("%"は0文字以上の任意の文字列)
				rs = pstmt.executeQuery();
				while(rs.next() == true) {
					int custId = rs.getInt("CUSTID");
					String custName = rs.getString("CUSTNAME");
					String rs_kana = rs.getString("KANA");
					String tel = rs.getString("TEL");
					String address = rs.getString("ADDRESS");
					Customer customer = new Customer(custId, custName, rs_kana, tel, address);
					list.add(customer);
				}
			}
		} catch(SQLException e) {
			throw new Exception("DB接続処理に失敗しました！管理者に連絡してください。", e);
		} finally {
			try{
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException e) {
				throw new Exception("DB切断時にエラーが発生しました。", e);
			}
			try{
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				throw new Exception("DB切断時にエラーが発生しました。", e);
			}
		}
		closeConnection(con);
		return list;
	}
	// 電話番号と氏名カナ両方で顧客を検索するメソッド
	public ArrayList<Customer> searchCustomer(String tel, String kana) throws Exception {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Customer> list = new ArrayList<Customer>();
		try{
			if(con != null) {
				String sql = "SELECT CUSTID, CUSTNAME, KANA, ADDRESS FROM customer WHERE TEL=? AND KANA LIKE ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, tel);
				pstmt.setString(2, "%" + kana + "%");
				rs = pstmt.executeQuery();
				while(rs.next() == true) {
					int custId = rs.getInt("CUSTID");
					String custName = rs.getString("CUSTNAME");
					kana = rs.getString("KANA");
					String address = rs.getString("ADDRESS");
					Customer customer = new Customer(custId, custName, kana, tel, address);
					list.add(customer);
				}
			}
		} catch(SQLException e) {
			throw new Exception("DB接続処理に失敗しました！管理者に連絡してください。", e);
		} finally {
			try{
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException e) {
				throw new Exception("DB切断時にエラーが発生しました。", e);
			}
			try{
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				throw new Exception("DB切断時にエラーが発生しました。", e);
			}
		}
		closeConnection(con);
		return list;
	}
}
