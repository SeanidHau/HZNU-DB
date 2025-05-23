package app;

import dao.BorrowDao;
import model.BorrowRecord;

import java.util.Scanner;
import java.util.List;

public class ListBorrowedMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BorrowDao borrowDao = new BorrowDao();

        System.out.println("=== 图书管理系统 - 查询读者借书记录 ===");

        System.out.print("请输入读者编号：");
        String readerNumber = scanner.nextLine();

        List<BorrowRecord> records = borrowDao.findBorrowedByReader(readerNumber);

        if (records.isEmpty()) {
            System.out.println("没有借书记录！");
        } else {
            System.out.println("借书记录：");
            for(BorrowRecord record : records) {
                System.out.println("----------------------------------------");
                System.out.println("书号：" + record.getBookNumber());
                System.out.println("书名：" + record.getBookName());
                System.out.println("出版社：" + record.getPublisher());
                System.out.println("借书时间：" + record.getBorrowTime());
            }
        }
    }
}
