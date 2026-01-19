/**
 * クラス名：	CustomerModifyAction
 * 概要　　：	顧客情報変更アクション
 * 作成者名：	藤代
 * 作成日　：	2026/01/09
 * 修正者名：
 * 修正日　：
 */
package action;

import dao.LoginDBAccess;
import model.User;

public class GoogleLoginAction {

    public User execute(String email, String name) throws Exception {

        LoginDBAccess dao = new LoginDBAccess();

        // 既存ユーザー検索
        User user = dao.findByEmail(email);
        if (user == null) {
            // 未登録 → 自動登録
            user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setProvider("GOOGLE");

            dao.insertGoogleUser(user);

            // 再取得
            user = dao.findByEmail(email);
        }

        return user;
    }
}