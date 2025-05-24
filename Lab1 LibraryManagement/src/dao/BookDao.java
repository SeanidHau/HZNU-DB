package dao;

import model.Book;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    public boolean addBook(Book book) {
        String sql = "INSERT INTO book (book_number, category, book_name, publisher, author, price, book_total, inventory) " +
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

    public boolean deleteBookByNumber(String bookNumber) {
        String sql = "DELETE FROM book WHERE book_number = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, bookNumber);
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            System.out.println("删除图书失败！");
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateBook(Book book) {
        String sql = "UPDATE book SET category = ?, book_name = ?, publisher = ?, author = ?, price = ?, book_total = ?, inventory = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, book.getCategory());
            pstmt.setString(2, book.getBookName());
            pstmt.setString(3, book.getPublisher());
            pstmt.setString(4, book.getAuthor());
            pstmt.setDouble(5, book.getPrice());
            pstmt.setInt(6, book.getBookTotal());
            pstmt.setInt(7, book.getInventory());

            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public Book findBookByNumber(String bookNumber) {
        String sql = "SELECT * FROM book WHERE book_number = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, bookNumber);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Book book = new Book();
                    book.setBookNumber(rs.getString("book_number"));
                    book.setCategory(rs.getString("category"));
                    book.setBookName(rs.getString("book_name"));
                    book.setPublisher(rs.getString("publisher"));
                    book.setAuthor(rs.getString("author"));
                    book.setPrice(rs.getDouble("price"));
                    book.setBookTotal(rs.getInt("book_total"));
                    book.setInventory(rs.getInt("inventory"));
                    return book;
                }
            }
        } catch (Exception e) {
            System.out.println("查询失败！");
            e.printStackTrace();
        }

        return null;
    }

    public List<Book> findBooksByName(String keyword) {
        List<Book> resultList = new ArrayList<>();
        String sql = "SELECT * FROM book WHERE book_name LIKE ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + keyword + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book();
                    book.setBookNumber(rs.getString("book_number"));
                    book.setCategory(rs.getString("category"));
                    book.setBookName(rs.getString("book_name"));
                    book.setPublisher(rs.getString("publisher"));
                    book.setAuthor(rs.getString("author"));
                    book.setPrice(rs.getDouble("price"));
                    book.setBookTotal(rs.getInt("book_total"));
                    book.setInventory(rs.getInt("inventory"));
                    resultList.add(book);
                }
            }
        } catch (Exception e) {
            System.out.println("模糊查询失败！");
        }

        return resultList;
    }
}
