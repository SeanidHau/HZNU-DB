package app;

import dao.StatisticsDao;

import java.util.Scanner;

public class CountBookBorrowedTimesMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StatisticsDao statisticsDao = new StatisticsDao();

        System.out.println("=== 统计 - 某本书的借阅次数 ===");

        System.out.print("请输入书号：");
        String bookNumber = scanner.nextLine();

        int result = statisticsDao.countBorrowedTimesByBook(bookNumber);
        System.out.println("书号为：" + bookNumber + " 的图书被借阅的次数为：" + result);
    }
}
