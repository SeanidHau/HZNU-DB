package app;

import dao.ManagerDao;
import model.Manager;

import java.util.Scanner;

public class LoginMain {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        ManagerDao dao = new ManagerDao();

        System.out.println("=== 图书管理系统 - 管理员登录 ===");

        System.out.print("请输入管理员编号：");
        String id = scanner.nextLine();

        System.out.print("请输入密码：");
        String password = scanner.nextLine();

        Manager manager = dao.login(id, password);

        if (manager != null) {
            System.out.println("登录成功，欢迎管理员" + manager.getName());
        } else {
            System.out.println("登录失败，账号或密码错误！");
        }
    }
}
