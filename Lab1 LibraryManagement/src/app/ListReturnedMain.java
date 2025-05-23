package app;

import model.ReturnRecord;
import dao.ReturnDao;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class ListReturnedMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReturnDao returnDao = new ReturnDao();

        System.out.println("=== 图书管理系统 - 查询还书记录 ===");

        System.out.print("请输入读者编号：");
        String readerNumber = scanner.nextLine();

        List<ReturnRecord> records = returnDao.findReturnedByReader(readerNumber);

        if (records.isEmpty()) {
            System.out.println("没有还书记录！");
        } else {
            System.out.println("还书记录如下：");
            for (ReturnRecord r : records) {
                System.out.println("----------------------------------------");
                System.out.println("书号：" + r.getBookNumber());
                System.out.println("书名：" + r.getBookName());
                System.out.println("出版社：" + r.getPublisher());
                System.out.println("还书时间：" + r.getReturnTime());
            }
        }
    }
}
