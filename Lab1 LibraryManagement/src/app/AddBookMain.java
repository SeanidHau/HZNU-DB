package app;

import dao.BookDao;
import model.Book;

import java.util.Scanner;
public class AddBookMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookDao bookDao = new BookDao();

        System.out.println("=== 图书管理系统 - 添加图书 ===");

        System.out.println("请输入书号（book_number）：");
        String bookNumber = scanner.nextLine();

        System.out.println("请输入类别（category）：");
        String category = scanner.nextLine();

        System.out.println("请输入书号（book_name）：");
        String bookName = scanner.nextLine();

        System.out.println("请输入出版社（publisher）：");
        String publisher = scanner.nextLine();

        System.out.println("请输入作者（author）：");
        String author = scanner.nextLine();

        System.out.println("请输入价格（price）：");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.println("请输入总藏书量（book_total）：");
        int bookTotal = Integer.parseInt(scanner.nextLine());

        System.out.println("请输入库存（inventory）：");
        int inventory = Integer.parseInt(scanner.nextLine());

        Book book = new Book(bookNumber, category, bookName, publisher, author, price, bookTotal, inventory);

        boolean success = bookDao.addBook(book);
        if (success) {
            System.out.println("图书添加成功！");
        } else {
            System.out.println("图书添加失败！");
        }
    }
}
