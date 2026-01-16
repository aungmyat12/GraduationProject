package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Item;

public class ItemModifyDBAccess extends ControlDBAccess {

    /**
     * 商品情報更新処理
     */
	public int modifyItem(Item item) throws Exception {
        Connection con = createConnection();
        PreparedStatement pstmt = null;
        int count = 0;

        try {
            String sql =
                "UPDATE ITEM SET ITEMNAME = ?, SIZE = ?, PRICE = ? " +
                "WHERE ITEMID = ?";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, item.getItemName());
            pstmt.setString(2, item.getSize());
            pstmt.setInt(3, item.getPrice());
            pstmt.setString(4, item.getItemId());

            count = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("商品編集処理に失敗しました。", e);
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
            closeConnection(con);
        }

        return count;
    }
}