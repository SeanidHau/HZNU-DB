package app;

import model.Book;
import dao.BookDao;

import java.util.Scanner;

public class FindBookMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookDao bookDao = new BookDao();

        System.out.println("=== 图书管理系统 - 按编号精确查询图书 ===");

        System.out.print("请输入书号（book_number）：");
        String bookNumber = scanner.nextLine();

        Book book = bookDao.findBookByNumber(bookNumber);

        if (book != null) {
            System.out.println("查询结果：");
            System.out.println("书号：" + book.getBookNumber());
            System.out.println("类别：" + book.getCategory());
            System.out.println("书名：" + book.getBookName());
            System.out.println("出版社：" + book.getPublisher());
            System.out.println("作者：" + book.getAuthor());
            System.out.println("价格：" + book.getPrice());
            System.out.println("总藏书量：" + book.getBookTotal());
            System.out.println("库存：" + book.getInventory());
        } else {
            System.out.println("未找到图书！");
        }
    }

}
