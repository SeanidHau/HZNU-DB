package app;

import dao.ReturnDao;

import java.util.Scanner;

public class ReturnBookMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReturnDao returnDao = new ReturnDao();

        System.out.println("=== 图书管理系统 - 还书 ===");

        System.out.print("请输入读者编号：");
        String readerNumber = scanner.nextLine();

        System.out.println("请输入书号：");
        String bookNumber = scanner.nextLine();

        boolean success = returnDao.returnBook(readerNumber, bookNumber);

        if (success) {
            System.out.println("还书成功！");
        } else {
            System.out.println("还书失败！");
        }
    }
}
