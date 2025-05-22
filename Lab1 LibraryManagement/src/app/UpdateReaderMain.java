package app;

import dao.ReaderDao;
import model.Reader;

import java.util.Scanner;

public class UpdateReaderMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReaderDao readerDao = new ReaderDao();

        System.out.println("=== 图书管理系统 - 修改读者信息 ===");

        System.out.print("请输入要修改的读者编号：");
        String readerNumber = scanner.nextLine();

        System.out.print("请输入新的姓名：");
        String name = scanner.nextLine();

        System.out.print("请输入新的单位：");
        String department = scanner.nextLine();

        System.out.print("请输入新的性别：");
        String gender = scanner.nextLine();

        System.out.print("请输入新的手机号：");
        String telephone = scanner.nextLine();

        Reader reader = new Reader(readerNumber, name, department, gender, telephone);

        boolean success = readerDao.updateReader(reader);

        if (success) {
            System.out.println("读者信息更新成功!");
        } else {
            System.out.println("读者信息更新失败， 请检查输入是否正确！");
        }
    }
}
