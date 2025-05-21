package dao;

import model.Book;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BookDao {
    public boolean addBook(Book book) {
        String sql = "INSERT INTO book (book_number, category, book_name, publisher, author, price, book_total, telephone) " +
                     "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, book.getBookNumber());
            pstmt.setString(2, book.getCategory());
            pstmt.setString(3, book.getBookName());
            pstmt.setString(4, book.getPublisher());
            pstmt.setString(5, book.getAuthor());
            pstmt.setDouble(6, book.getPrice());
            pstmt.setInt(7, book.getBookTotal());
            pstmt.setInt(8, book.getInventory());

            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (Exception e) {
            System.out.println("添加图书失败！");
            e.printStackTrace();
        }

        return false;
    }
}
