package gui;

import dao.BorrowDao;

import javax.swing.*;
import java.awt.*;

public class BorrowManagerGUI {
    public static void showBorrowManager() {
        JFrame frame = new JFrame("借书模块");
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel title = new JLabel("借书操作", SwingConstants.CENTER);
        title.setFont(new Font("微软雅黑", Font.BOLD, 18));

        JTextField readerField = new JTextField();
        JTextField bookField = new JTextField();

        panel.add(title);
        panel.add(labeledPanel("读者编号:", readerField));
        panel.add(labeledPanel("图书编号:", bookField));

        JButton borrowBtn = new JButton("借阅图书");
        panel.add(borrowBtn);

        borrowBtn.addActionListener(e -> {
            String readerNumber = readerField.getText().trim();
            String bookNumber = bookField.getText().trim();

            if (readerNumber.isEmpty() || bookNumber.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "请完整填写读者编号和图书编号！", "提示", JOptionPane.WARNING_MESSAGE);
                return;
            }

            BorrowDao dao = new BorrowDao();
            boolean success = dao.borrowBook(readerNumber, bookNumber);

            if (success) {
                JOptionPane.showMessageDialog(frame, "借书成功！");
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "借书失败，库存不足或超过借阅上限！", "失败", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    private static JPanel labeledPanel(String labelText, JTextField field) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(labelText), BorderLayout.WEST);
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }
}
