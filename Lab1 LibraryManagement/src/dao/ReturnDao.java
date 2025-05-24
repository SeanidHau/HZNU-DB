package dao;

import model.ReturnRecord;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReturnDao {

    public boolean returnBook(String readerNumber, String bookNumber) {
        String checkSql = "SELECT * FROM borrow_book WHERE reader_number = ? AND book_number = ?";
        String insertSql = "INSERT INTO return_book (reader_number, book_number, return_time) VALUES (?, ?, NOW())";
        //String deleteSql = "DELETE FROM borrow_book WHERE reader_number = ? AND book_number = ?";
        String updateSql = "UPDATE book SET inventory = inventory + 1 WHERE book_number = ?";

        try (Connection conn = DBUtil.getConnection()) {
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

                checkStmt.setString(1, readerNumber);
                checkStmt.setString(2, bookNumber);
                ResultSet rs = checkStmt.executeQuery();

                if (!rs.next()) {
                    System.out.println("还书失败，该读者未借阅此书！");
                    return false;
                }
            }

            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

                insertStmt.setString(1, readerNumber);
                insertStmt.setString(2, bookNumber);
                insertStmt.executeUpdate();
            }

            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

                updateStmt.setString(1, bookNumber);
                updateStmt.executeUpdate();
            }

            return true;

        } catch (Exception e) {
            System.out.println("还书失败！");
            e.printStackTrace();
        }

        return false;
    }

    public List<ReturnRecord> findReturnedByReader(String readerNumber) {
        List<ReturnRecord> list = new ArrayList<>();
        String sql = "SELECT r.reader_number, b.book_number, b.book_name, b.publisher, r.return_time " +
                     "FROM return_book r JOIN book b ON r.book_number = b.book_number " +
                     "WHERE r.reader_number = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, readerNumber);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ReturnRecord record = new ReturnRecord();
                record.setReaderNumber(readerNumber);
                record.setBookNumber(rs.getString("book_number"));
                record.setBookName(rs.getString("book_name"));
                record.setPublisher(rs.getString("publisher"));
                record.setReturnTime(rs.getTimestamp("return_time"));
                list.add(record);
            }

        } catch (Exception e) {
            System.out.println("查询还书记录失败！");
            e.printStackTrace();
        }

        return list;
    }
}
