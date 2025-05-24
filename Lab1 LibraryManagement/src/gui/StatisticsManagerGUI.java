package gui;

import dao.StatisticsDao;

import javax.swing.*;
import java.awt.*;

public class StatisticsManagerGUI {
    public static void showStatisticsManager() {
        JFrame frame = new JFrame("📈 统计分析模块");
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel title = new JLabel("统计功能", SwingConstants.CENTER);
        title.setFont(new Font("微软雅黑", Font.BOLD, 18));

        JButton readerBorrowBtn = new JButton("👤 查询读者借书总数");
        JButton categoryBookBtn = new JButton("📚 查询类别图书数量");
        JButton bookBorrowBtn = new JButton("📖 查询图书被借次数");

        panel.add(title);
        panel.add(readerBorrowBtn);
        panel.add(categoryBookBtn);
        panel.add(bookBorrowBtn);

        frame.add(panel);

        readerBorrowBtn.addActionListener(e -> {
            String readerNum = JOptionPane.showInputDialog(frame, "请输入读者编号：");
            if (readerNum != null && !readerNum.trim().isEmpty()) {
                int count = new StatisticsDao().countBorrowedByReader(readerNum.trim());
                JOptionPane.showMessageDialog(frame, "该读者共借书：" + count + " 本");
            }
        });

        categoryBookBtn.addActionListener(e -> {
            String category = JOptionPane.showInputDialog(frame, "请输入图书类别：");
            if (category != null && !category.trim().isEmpty()) {
                int count = new StatisticsDao().countBookTotalByCategory(category.trim());
                JOptionPane.showMessageDialog(frame, "该类别下共有图书：" + count + " 本");
            }
        });

        bookBorrowBtn.addActionListener(e -> {
            String bookNum = JOptionPane.showInputDialog(frame, "请输入图书编号：");
            if (bookNum != null && !bookNum.trim().isEmpty()) {
                int count = new StatisticsDao().countBorrowedTimesByBook(bookNum.trim());
                JOptionPane.showMessageDialog(frame, "该图书被借阅次数为：" + count + " 次");
            }
        });

        frame.setVisible(true);
    }
}
