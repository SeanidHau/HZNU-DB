package app;

import dao.BookDao;
import model.Book;

import java.util.List;
import java.util.Scanner;

public class FuzzySearchBookMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookDao bookDao = new BookDao();

        System.out.println("=== 图书管理系统 - 模糊查询书名 ===");

        System.out.print("请输入书名关键字：");
        String keyword = scanner.nextLine();

        List<Book> books = bookDao.findBooksByName(keyword);

        if (books.isEmpty()) {
            System.out.println("未找到匹配的书籍。");
        } else {
            System.out.println("查询到 " + books.size() + " 本图书：");
            for (Book book : books) {
                System.out.println("--------------------------------------------------");
                System.out.println("书号：" + book.getBookNumber());
                System.out.println("类别：" + book.getCategory());
                System.out.println("书名：" + book.getBookName());
                System.out.println("出版社：" + book.getPublisher());
                System.out.println("作者：" + book.getAuthor());
                System.out.println("价格：" + book.getPrice());
                System.out.println("总藏书量：" + book.getBookTotal());
                System.out.println("库存：" + book.getInventory());
            }
        }
    }
}
