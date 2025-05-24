package gui;

import dao.ManagerDao;
import model.Manager;
import gui.MainMenuGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryLoginGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("图书管理系统 - 管理员登录");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        JLabel titleLabel = new JLabel("欢迎登录图书管理系统");
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        titleLabel.setBounds(90, 10, 250, 30);
        panel.add(titleLabel);

        JLabel userLabel = new JLabel("管理员编号:");
        userLabel.setBounds(50, 60, 100, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField();
        userText.setBounds(150, 60, 180, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setBounds(50, 100, 100, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(150, 100, 180, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("登录");
        loginButton.setBounds(150, 150, 80, 30);
        panel.add(loginButton);

        JLabel messageLabel = new JLabel("");
        messageLabel.setBounds(50, 190, 300, 25);
        messageLabel.setForeground(Color.RED);
        panel.add(messageLabel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String managerId = userText.getText().trim();
                String password = new String(passwordText.getPassword());

                ManagerDao managerDao = new ManagerDao();
                Manager manager = managerDao.login(managerId, password);
                // 调用已有的登录逻辑（此处简化）
                if (manager != null) {
                    JOptionPane.showMessageDialog(frame, "登录成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    MainMenuGUI.showMainMenu(manager.getName());
                    frame.dispose();
                } else {
                    messageLabel.setText("账号或密码错误，请重试！");
                }
            }
        });

        frame.setVisible(true);
    }
}
