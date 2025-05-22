package app;

import dao.ReaderDao;
import model.Reader;

import java.util.Scanner;
import java.util.List;

public class FuzzySearchReaderMain {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        ReaderDao readerDao = new ReaderDao();

        System.out.println("=== 图书管理系统 - 模糊查询读者信息 ===");

        System.out.print("请输入读者姓名关键字：");
        String keyword = scanner.nextLine();

        List<Reader> readers = readerDao.findReaderByName(keyword);

        if (readers.isEmpty()) {
            System.out.println("未找到匹配的读者！");
        } else {
            System.out.println("查询到 " + readers.size() + " 位读者：");
            for (Reader reader : readers) {
                System.out.println("--------------------------------------------------");
                System.out.println("编号：" + reader.getReaderNumber());
                System.out.println("姓名：" + reader.getName());
                System.out.println("单位：" + reader.getDepartment());
                System.out.println("性别：" + reader.getGender());
                System.out.println("电话：" + reader.getTelephone());
            }
        }
    }
}
