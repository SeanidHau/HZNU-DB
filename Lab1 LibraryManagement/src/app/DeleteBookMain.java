package app;

import dao.BookDao;

import java.util.Scanner;

public class DeleteBookMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookDao bookDao = new BookDao();

        System.out.println("=== 图书管理系统 - 删除图书 ===");

        System.out.print("请输入要删除的书号（book_number)：");
        String bookNumber = scanner.nextLine();

        boolean success = bookDao.deleteBookByBookNumber(bookNumber);
        if (success) {
            System.out.println("图书删除成功！");
        } else {
            System.out.println("图书删除失败，可能是书号不存在！");
        }
    }
}
