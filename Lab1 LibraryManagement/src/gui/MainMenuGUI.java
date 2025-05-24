package gui;

import gui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuGUI {
    public static void showMainMenu(String managerName) {
        JFrame frame = new JFrame("图书管理系统 - 主菜单");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel welcomeLabel = new JLabel("欢迎，管理员 " + managerName + "！", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        panel.add(welcomeLabel);

        JButton bookButton = new JButton("📚 图书管理");
        JButton readerButton = new JButton("👤 读者管理");
        JButton borrowButton = new JButton("📥 借书");
        JButton returnButton = new JButton("📤 还书");
        JButton statsButton = new JButton("📊 统计功能");
        JButton exitButton = new JButton("退出系统");

        panel.add(bookButton);
        panel.add(readerButton);
        panel.add(borrowButton);
        panel.add(returnButton);
        panel.add(statsButton);
        panel.add(exitButton);

        frame.add(panel);

        // 示例事件绑定
        bookButton.addActionListener(e -> BookManagerGUI.showBookManager());
        readerButton.addActionListener(e -> ReaderManagerGUI.showReaderManager());
        borrowButton.addActionListener(e -> BorrowManagerGUI.showBorrowManager());
        returnButton.addActionListener(e -> ReturnManagerGUI.showReturnManager());
        statsButton.addActionListener(e -> StatisticsManagerGUI.showStatisticsManager());
        exitButton.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }
}