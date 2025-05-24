package gui;

import model.Reader;
import dao.ReaderDao;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ReaderManagerGUI {
    public static void showReaderManager() {
        JFrame frame = new JFrame("读者管理模块");
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel title = new JLabel("读者管理功能", SwingConstants.CENTER);
        title.setFont(new Font("微软雅黑", Font.BOLD, 18));

        JButton addReaderBtn = new JButton("添加读者");
        JButton deleteReaderBtn = new JButton("删除读者");
        JButton updateReaderBtn = new JButton("修改读者");
        JButton searchReaderBtn = new JButton("查询读者（编号/姓名）");

        panel.add(title);
        panel.add(addReaderBtn);
        panel.add(deleteReaderBtn);
        panel.add(updateReaderBtn);
        panel.add(searchReaderBtn);

        frame.add(panel);

        addReaderBtn.addActionListener(e -> showAddReaderDialog(frame));
        deleteReaderBtn.addActionListener(e -> showDeleteReaderDialog(frame));
        updateReaderBtn.addActionListener(e -> showUpdateReaderDialog(frame));
        searchReaderBtn.addActionListener(e -> showSearchReaderDialog(frame));

        frame.setVisible(true);
    }

    private static void showAddReaderDialog(JFrame parent) {
        JTextField numberField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField deptField = new JTextField();
        JTextField genderField = new JTextField();
        JTextField phoneField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.add(new JLabel("读者编号:")); panel.add(numberField);
        panel.add(new JLabel("姓名:")); panel.add(nameField);
        panel.add(new JLabel("单位:")); panel.add(deptField);
        panel.add(new JLabel("性别:")); panel.add(genderField);
        panel.add(new JLabel("手机号:")); panel.add(phoneField);

        int result = JOptionPane.showConfirmDialog(parent, panel, "添加读者", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Reader reader = new Reader(numberField.getText().trim(), nameField.getText().trim(),
                    deptField.getText().trim(), genderField.getText().trim(), phoneField.getText().trim());
            boolean success = new ReaderDao().addReader(reader);
            JOptionPane.showMessageDialog(parent, success ? "添加成功！" : "添加失败，请检查输入！");
        }
    }

    private static void showDeleteReaderDialog(JFrame parent) {
        JTextField numberField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("输入要删除的读者编号:"));
        panel.add(numberField);

        int result = JOptionPane.showConfirmDialog(parent, panel, "删除读者", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            boolean success = new ReaderDao().deleteReaderByNumber(numberField.getText().trim());
            JOptionPane.showMessageDialog(parent, success ? "删除成功！" : "删除失败，请检查编号！");
        }
    }

    private static void showUpdateReaderDialog(JFrame parent) {
        JTextField numberField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField deptField = new JTextField();
        JTextField genderField = new JTextField();
        JTextField phoneField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.add(new JLabel("读者编号:")); panel.add(numberField);
        panel.add(new JLabel("姓名:")); panel.add(nameField);
        panel.add(new JLabel("单位:")); panel.add(deptField);
        panel.add(new JLabel("性别:")); panel.add(genderField);
        panel.add(new JLabel("手机号:")); panel.add(phoneField);

        int result = JOptionPane.showConfirmDialog(parent, panel, "修改读者信息", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Reader reader = new Reader(numberField.getText().trim(), nameField.getText().trim(),
                    deptField.getText().trim(), genderField.getText().trim(), phoneField.getText().trim());
            boolean success = new ReaderDao().updateReader(reader);
            JOptionPane.showMessageDialog(parent, success ? "修改成功！" : "修改失败，请检查输入！");
        }
    }

    private static void showSearchReaderDialog(JFrame parent) {
        JTextField inputField = new JTextField();
        JRadioButton byNumber = new JRadioButton("按编号查询");
        JRadioButton byName = new JRadioButton("按姓名模糊查询", true);
        ButtonGroup group = new ButtonGroup();
        group.add(byNumber);
        group.add(byName);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("请输入查询关键字："));
        panel.add(inputField);
        panel.add(byNumber);
        panel.add(byName);

        int result = JOptionPane.showConfirmDialog(parent, panel, "查询读者", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            ReaderDao dao = new ReaderDao();
            String keyword = inputField.getText().trim();

            if (byNumber.isSelected()) {
                Reader reader = dao.findReaderByNumber(keyword);
                if (reader != null) {
                    JOptionPane.showMessageDialog(parent,
                            "编号: " + reader.getReaderNumber() + "\n" +
                                    "姓名: " + reader.getName() + "\n" +
                                    "单位: " + reader.getDepartment() + "\n" +
                                    "性别: " + reader.getGender() + "\n" +
                                    "手机号: " + reader.getTelephone());
                } else {
                    JOptionPane.showMessageDialog(parent, "未找到读者！");
                }
            } else {
                List<Reader> list = dao.findReaderByName(keyword);
                if (list.isEmpty()) {
                    JOptionPane.showMessageDialog(parent, "未找到相关读者！");
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (Reader r : list) {
                        sb.append("编号: ").append(r.getReaderNumber()).append("\n")
                                .append("姓名: ").append(r.getName()).append("\n")
                                .append("单位: ").append(r.getDepartment()).append("\n")
                                .append("性别: ").append(r.getGender()).append("\n")
                                .append("手机号: ").append(r.getTelephone()).append("\n")
                                .append("---------------------\n");
                    }
                    JTextArea textArea = new JTextArea(sb.toString());
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setPreferredSize(new Dimension(400, 300));
                    JOptionPane.showMessageDialog(parent, scrollPane, "查询结果", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
}
