package app;

import dao.ManagerDao;

import java.util.Scanner;

public class ChangePasswordMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ManagerDao managerDao = new ManagerDao();

        System.out.println("=== 管理员密码修改 ===");

        System.out.print("请输入管理员编号：");
        String managerId = scanner.nextLine();

        System.out.print("请输入当前密码：");
        String oldPwd = scanner.nextLine();

        System.out.print("请输入新密码：");
        String newPwd = scanner.nextLine();

        boolean success = managerDao.updatePassword(managerId, oldPwd, newPwd);

        if (success) {
            System.out.println("修改密码成功！");
        } else {
            System.out.println("密码修改失败， 请检查编号或密码是否错误！");
        }
    }
}
