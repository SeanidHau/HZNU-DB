package dao;

import model.BorrowRecord;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BorrowDao {

    public boolean borrowBook(String readerNumber, String bookNumber) {
        String countSql = "SELECT COUNT(*) FROM borrow_book WHERE reader_number = ? ";
        String inventorySql = "SELECT inventory FROM book WHERE book_number = ?";
        String insertSql = "INSERT INTO borrow_book (reader_number, book_number, borrow_time) VALUES(?, ?, NOW())";
        String updateInventorySql = "UPDATE book SET inventory = inventory - 1 WHERE book_number = ?";

        try (Connection conn = DBUtil.getConnection()) {

            try (PreparedStatement countStmt = conn.prepareStatement(countSql)) {
                countStmt.setString(1, bookNumber);
                ResultSet rs = countStmt.executeQuery();
                if (rs.next() && rs.getInt(1) >= 15) {
                    System.out.println("借书失败，已接满15本！");
                    return false;
                }
            }

            try (PreparedStatement inventoryStmt = conn.prepareStatement(inventorySql)) {
                inventoryStmt.setString(1, bookNumber);
                ResultSet rs = inventoryStmt.executeQuery();
                if (rs.next() && rs.getInt(1) <= 0) {
                    System.out.println("借书失败，图书库存不足！");
                    return false;
                }
            }

            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                insertStmt.setString(1, readerNumber);
                insertStmt.setString(2, bookNumber);
                insertStmt.executeUpdate();
            }

            try (PreparedStatement updateInventoryStmt = conn.prepareStatement(updateInventorySql)) {
                updateInventoryStmt.setString(1, bookNumber);
                updateInventoryStmt.executeUpdate();
            }

            return true;

        } catch (Exception e) {
            System.out.println("借书操作失败！");
            e.printStackTrace();
        }

        return false;
    }

    public List<BorrowRecord> findBorrowedByReader(String readerNumber) {
        List<BorrowRecord> list = new ArrayList<>();
        String sql = "SELECT b.book_number, b.book_name, b.publisher, br.borrow_time " +
                     "FROM borrow_book br JOIN book b ON br.book_number = b.book_number " +
                     "WHERE br.reader_number = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, readerNumber);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                BorrowRecord record = new BorrowRecord();
                record.setReaderNumber(readerNumber);
                record.setBookNumber(rs.getString("book_number"));
                record.setBookName(rs.getString("book_name"));
                record.setPublisher(rs.getString("publisher"));
                record.setBorrowTime(rs.getTimestamp("borrow_time"));
                list.add(record);
            }
        } catch (Exception e) {
            System.out.println("查询借书记录失败！");
            e.printStackTrace();
        }

        return list;
    }
}
