package dao;

import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StatisticsDao {

    public int countBorrowedByReader(String readerNumber) {
        String sql = "SELECT COUNT(*) FROM borrow_book WHERE reader_number = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, readerNumber);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int countBookTotalByCategory(String category) {
        String sql = "SELECT COUNT(*) FROM book WHERE category = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, category);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int countBorrowedTimesByBook(String bookNumber) {
        String sql = "SELECT COUNT(*) FROM borrow_book WHERE book_number = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, bookNumber);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}
