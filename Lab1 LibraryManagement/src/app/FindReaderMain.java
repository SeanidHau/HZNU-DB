package app;

import model.Reader;
import dao.ReaderDao;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class FindReaderMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReaderDao readerDao = new ReaderDao();

        System.out.println("=== 图书馆里系统 - 精确查询读者信息 ===");

        System.out.print("请输入读者编号：");
        String readerNumber = scanner.nextLine();

        Reader reader = readerDao.findReaderByNumber(readerNumber);

        if (reader != null) {
            System.out.println("查询结果：");
            System.out.println("读者编号：" + reader.getReaderNumber());
            System.out.println("姓名：" + reader.getName());
            System.out.println("单位：" + reader.getDepartment());
            System.out.println("性别：" + reader.getGender());
            System.out.println("手机号：" + reader.getTelephone());
        } else {
            System.out.println("未找到该读者！");
        }
    }
}
