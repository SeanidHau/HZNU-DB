package app;

import dao.BookDao;
import model.Book;

import java.util.Scanner;

public class UpdateBookMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookDao bookDao = new BookDao();

        System.out.println("=== 图书管理系统 - 修改图书信息 ===");

        System.out.print("请输入要修改的书号（book_numebr）：");
        String bookNUmber = scanner.nextLine();

        System.out.print("请输入类别（category）：");
        String category = scanner.nextLine();

        System.out.print("请输入书号（book_name）：");
        String bookName = scanner.nextLine();

        System.out.print("请输入出版社（publisher）：");
        String publisher = scanner.nextLine();

        System.out.print("请输入作者（author）：");
        String author = scanner.nextLine();

        System.out.print("请输入价格（price）：");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("请输入总藏书量（book_total）：");
        int bookTotal = Integer.parseInt(scanner.nextLine());

        System.out.print("请输入库存（inventory）：");
        int inventory = Integer.parseInt(scanner.nextLine());

        Book book = new Book(bookNUmber, category, bookName, publisher, author, price, bookTotal, inventory);

        boolean success = bookDao.updateBook(book);
        if (success) {
            System.out.println("图书信息更新成功！");
        } else {
            System.out.println("图书信息更新失败！");
        }
    }
}
