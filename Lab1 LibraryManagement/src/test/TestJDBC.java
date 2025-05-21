package test;

import util.DBUtil;
import java.sql.Connection;

public class TestJDBC {
    public static void main(String[] args) {
        try {
            Connection conn = DBUtil.getConnection();
            if (conn != null) {
                System.out.println("数据库连接成功！");
                DBUtil.close(conn);
            } else {
                System.out.println("数据库连接失败！");
            }
        } catch (Exception e) {
            System.out.println("发生异常，连接失败！");
            e.printStackTrace();
        }
    }
}
