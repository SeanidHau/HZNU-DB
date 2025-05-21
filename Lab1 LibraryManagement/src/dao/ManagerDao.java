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

    public boolean updatePassword(String managerId, String oldPwd, String newPwd) {
        String sqlCheck = "SELECT * FROM manager WHERE manager_id = ? AND password = ?";
        String sqlUpdate = "UPADTE manager SET password = ? WHERE manager_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(sqlCheck)) {

            //验证旧密码
            checkStmt.setString(1, managerId);
            checkStmt.setString(2, oldPwd);

            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    //验证通过
                    try (PreparedStatement updateStmt = conn.prepareStatement(sqlUpdate)) {
                        updateStmt.setString(1, newPwd);
                        updateStmt.setString(2, managerId);
                        int result = updateStmt.executeUpdate();
                        return result > 0;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("密码修改失败！");
            e.printStackTrace();
        }

        return false;
    }
}
