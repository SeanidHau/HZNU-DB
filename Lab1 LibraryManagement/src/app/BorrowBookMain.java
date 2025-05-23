package app;

import dao.BorrowDao;

import java.util.Scanner;

public class BorrowBookMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BorrowDao borrowDao = new BorrowDao();

        System.out.println("=== 图书管理系统 - 借书 ===");

        System.out.print("请输入读者编号：");
        String readerNumber = scanner.nextLine();

        System.out.print("请输入书号：");
        String bookNumber = scanner.nextLine();

        boolean success = borrowDao.borrowBook(readerNumber, bookNumber);

        if (success) {
            System.out.println("借书成功！");
        } else {
            System.out.println("借书失败！");
        }
    }
}
