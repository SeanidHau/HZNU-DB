package app;

import dao.StatisticsDao;

import java.util.Scanner;

public class CountReaderBorrowMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StatisticsDao statisticsDao = new StatisticsDao();

        System.out.println("=== 统计 - 读者当前借书数量 ===");

        System.out.print("请输入读者编号：");
        String readerNumber = scanner.nextLine();

        int result = statisticsDao.countBorrowedByReader(readerNumber);
        System.out.println("当前借书本数为：" + result);
    }
}
