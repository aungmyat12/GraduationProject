/**
 * クラス名：	ItemMenuDisplayDBAccess
 * 概要　　：	商品情報表示DAO
 * 作成者名：	ウー
 * 作成日　：	2025/12/02
 * 修正者名：	
 * 修正日　：
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Item;

public class ItemMenuDisplayDBAccess extends ControlDBAccess  {
	/**
     * 全ての商品情報を取得して ArrayList<Item> として返す。
     *
     * return  商品情報リスト
     * throws Exception DB検索に失敗した場合
     */
    public ArrayList<Item> searchAllItem() throws Exception {

        // DB接続の確立
        Connection con = createConnection();

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // 取得した商品データを格納するリスト
        ArrayList<Item> list = new ArrayList<Item>();

        try {
            // SQL 文の準備
            pstmt = con.prepareStatement(
                "SELECT ITEMID, ITEMNAME, SIZE, PRICE FROM ITEM"
            );

            // SQL 実行
            rs = pstmt.executeQuery();

            // 結果セットから商品情報を1件ずつ取り出して Item オブジェクトに変換
            while (rs.next() == true) {

                String itemId = rs.getString("ITEMID");
                String itemName = rs.getString("ITEMNAME");
                String size = rs.getString("SIZE");
                int price = rs.getInt("PRICE");

                // Item オブジェクト生成
                Item item = new Item(itemId, itemName, size, price);

                // リストに追加
                list.add(item);
            }

        } catch (SQLException e) {
            // SQL 実行エラー
            throw new Exception("商品検索処理に失敗しました！管理者に連絡してください。");
        } finally {
            // ResultSet のクローズ（null チェック付き）
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    // DB切断に失敗した場合
    				throw new Exception("DB切断時にエラーが発生しました。", e);
                }
            }

            // PreparedStatement のクローズ
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    // DB切断に失敗した場合
    				throw new Exception("DB切断時にエラーが発生しました。", e);
                }
            }
        }

        // DB接続のクローズ
        closeConnection(con);

        return list;
    }
}
