package app;

import dao.ReaderDao;
import model.Reader;

import java.util.Scanner;

public class AddReaderMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReaderDao readerDao = new ReaderDao();

        System.out.println("=== 图书管理系统 - 添加读者 ===");

        System.out.print("请输入读者编号：");
        String readerNumber = scanner.nextLine();

        System.out.print("请输入姓名：");
        String name = scanner.nextLine();

        System.out.print("请输入单位：");
        String department = scanner.nextLine();

        System.out.print("请输入性别：");
        String gender = scanner.nextLine();

        System.out.print("请输入手机号：");
        String telephone = scanner.nextLine();

        Reader reader = new Reader(readerNumber, name, department, gender, telephone);

        boolean success = readerDao.addReader(reader);

        if (success) {
            System.out.println("读者添加成功！");
        } else {
            System.out.println("添加失败，请检查输入！");
        }
    }
}
