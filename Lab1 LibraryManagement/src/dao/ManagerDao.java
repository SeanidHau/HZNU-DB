package dao;

import model.Manager;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ManagerDao {

    //验证管理员ID和密码是否匹配
    public Manager login(String managerId, String password) {
        String sql = "SELECT * FROM manager WHERE manager_id = ? AND password = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, managerId);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Manager manager = new Manager();
                    manager.setManagerId(rs.getString("manager_id"));
                    manager.setName(rs.getString("name"));
                    manager.setTelephone(rs.getString("telephone"));
                    manager.setPassword(rs.getString("password"));
                    return manager;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
