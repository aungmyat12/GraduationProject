/**
 * クラス名：	RegisterAction
 * 概要　　：	ユーザ登録するアクション
 * 作成者名：	ウー
 * 作成日　：	2026/01/19
 * 修正者名：
 * 修正日　：
 */
package action;
import java.security.MessageDigest;
import java.util.regex.Pattern;

import dao.RegisterDBAccess;
import model.User;

public class RegisterAction {
	/*
	 * param user 登録する顧客情報
	 * return 登録件数
	 * throws 登録情報の未発見
	 */
	public int execute(User user) throws Exception {
		boolean valid =
                Pattern.matches("^.{1,50}$", user.getName()) &&
                Pattern.matches("^[A-Za-z0-9._%+-]+@gmail\\.com$", user.getEmail());

        if (!valid) {
            return 0; // 入力形式エラー
        }

        RegisterDBAccess dao = new RegisterDBAccess();

        // メール重複チェック
        if (dao.emailExists(user.getEmail())) {
            return -1; // メール重複
        }

        // パスワードをハッシュ化
        String hashed = hash(user.getPassword());
        user.setPassword(hashed);

        // 登録実行
        return dao.insertUser(user);
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
