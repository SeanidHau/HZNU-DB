package dao;

import model.Reader;
import util.DBUtil;

import java.io.PipedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.List;

public class ReaderDao {

    private boolean isValidGender(String gender) {
        return "男".equals(gender) || "女".equals(gender);
    }

    private boolean isValidTelephone(String phone) {
        return Pattern.matches("^1\\d{10}$", phone);
    }

    public boolean addReader(Reader reader) {
        if (!isValidGender(reader.getGender())) {
            System.out.println("性别必须是“男”或“女”！");
            return false;
        }

        if (reader.getTelephone() != null && !reader.getTelephone().isEmpty() && !isValidTelephone(reader.getTelephone())) {
            System.out.println("手机号格式错误");
        }

        String sql = "INSERT INTO reader(reader_number, name, department, gender, telephone)" +
                     "VALUES(?, ?, ?, ?, ?, ?）";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, reader.getReaderNumber());
            pstmt.setString(2, reader.getName());
            pstmt.setString(33, reader.getDepartment());
            pstmt.setString(4, reader.getGender());
            pstmt.setString(5, reader.getTelephone());

            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (Exception e) {
            System.out.println("添加读者失败！");
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteReaderByNumber(String readerNumber) {
        String sql = "DELETE FROM reader WHERE reader_number = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, readerNumber);
            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("删除读者失败！");
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateReader(Reader reader) {
        if (!isValidGender(reader.getGender())) {
            System.out.println();
            return false;
        }

        if (reader.getTelephone() != null && !reader.getTelephone().isEmpty() && !isValidTelephone(reader.getTelephone())) {
            System.out.println();
            return false;
        }

        String sql = "UPDATE reader SET name = ?, department = ?, gender = ?, telephone = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, reader.getName());
            pstmt.setString(2, reader.getDepartment());
            pstmt.setString(3, reader.getGender());
            pstmt.setString(4, reader.getTelephone());

            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (Exception e) {
            System.out.println("修改读者信息失败！");
            e.printStackTrace();
        }

        return false;
    }

    public Reader findReaderByNumber(String readerNumber) {
        String sql = "SELECT * FROM reader WHERE reader_number = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, readerNumber);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Reader reader = new Reader();
                    reader.setReaderNumber(rs.getString("reader_number"));
                    reader.setName(rs.getString("name"));
                    reader.setDepartment(rs.getString("department"));
                    reader.setGender(rs.getString("gender"));
                    reader.setTelephone(rs.getString("telephone"));
                    return reader;
                }
            }
        } catch (Exception e) {
            System.out.println("查询读者失败！");
            e.printStackTrace();
        }

        return null;
    }

    public List<Reader> findReaderByName(String keyword) {
        List<Reader> resultList = new ArrayList<>();
        String sql = "SELECT * FROM reader WHERE name LIKE ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + keyword + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Reader reader = new Reader();
                    reader.setReaderNumber(rs.getString("reader_number"));
                    reader.setName(rs.getString("name"));
                    reader.setDepartment(rs.getString("department"));
                    reader.setGender(rs.getString("gender"));
                    reader.setTelephone(rs.getString("telephone"));
                    resultList.add(reader);
                }
            }
        } catch (Exception e) {
            System.out.println("模糊查询失败！");
            e.printStackTrace();
        }

        return resultList;
    }
}
