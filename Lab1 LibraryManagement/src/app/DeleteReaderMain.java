package app;

import dao.ReaderDao;

import java.util.Scanner;

public class DeleteReaderMain {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        ReaderDao readerDao = new ReaderDao();

        System.out.println("=== 图书管理系统 - 删除读者 ===");

        System.out.print("请输入要删除的读者编号：");
        String readerNumber = scanner.nextLine();

        boolean success = readerDao.deleteReaderByNumber(readerNumber);

        if (success) {
            System.out.println("读者删除成功！");
        } else {
            System.out.println("删除失败，可能该读者编号不存在！");
        }
    }
}
