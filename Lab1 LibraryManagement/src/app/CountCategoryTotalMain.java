package app;

import dao.StatisticsDao;

import java.util.Scanner;

public class CountCategoryTotalMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StatisticsDao statisticsDao = new StatisticsDao();

        System.out.println("=== 统计 - 某类别图书总藏书量 ===");

        System.out.print("请输入类别：");
        String category = scanner.nextLine();

        int result = statisticsDao.countBookTotalByCategory(category);
        System.out.println("类别为：" + category + " 的总藏书量为：" + result);
    }
}
