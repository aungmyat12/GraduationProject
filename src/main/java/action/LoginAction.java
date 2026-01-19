/**
 * クラス名：	CustomerModifyAction
 * 概要　　：	顧客情報変更アクション
 * 作成者名：	藤代
 * 作成日　：	2026/01/09
 * 修正者名：
 * 修正日　：
 */
package action;

import java.security.MessageDigest;

import dao.LoginDBAccess;
import model.User;

public class LoginAction {

    public User execute(User user) throws Exception {
        LoginDBAccess dao = new LoginDBAccess();

        // パスワードをハッシュ化
        String hashed = hash(user.getPassword());
        user.setPassword(hashed);
        return dao.login(user);
    }
    private String hash(String password) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] bytes = md.digest(password.getBytes());
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
}