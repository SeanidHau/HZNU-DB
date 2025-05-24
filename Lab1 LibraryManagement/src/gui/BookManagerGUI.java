package gui;

import dao.BookDao;
import model.Book;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BookManagerGUI {
    public static void showBookManager() {
        JFrame frame = new JFrame("图书管理模块");
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel title = new JLabel("图书管理功能", SwingConstants.CENTER);
        title.setFont(new Font("微软雅黑", Font.BOLD, 18));

        JButton addBookBtn = new JButton("添加图书");
        JButton deleteBookBtn = new JButton("删除图书");
        JButton updateBookBtn = new JButton("修改图书");
        JButton searchBookBtn = new JButton("查询图书");

        panel.add(title);
        panel.add(addBookBtn);
        panel.add(deleteBookBtn);
        panel.add(updateBookBtn);
        panel.add(searchBookBtn);

        frame.add(panel);

        // 示例事件绑定
        addBookBtn.addActionListener(e -> showAddBookDialog(frame));
        deleteBookBtn.addActionListener(e -> showDeleteBookDialog(frame));
        updateBookBtn.addActionListener(e -> showUpdateBookDialog(frame));
        searchBookBtn.addActionListener(e -> showSearchBookDialog(frame));

        frame.setVisible(true);
    }

    private static void showAddBookDialog(JFrame parent) {
        JTextField numberField = new JTextField();
        JTextField categoryField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField publisherField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField totalField = new JTextField();
        JTextField inventoryField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.add(new JLabel("书号:")); panel.add(numberField);
        panel.add(new JLabel("类别：")); panel.add(categoryField);
        panel.add(new JLabel("书名:")); panel.add(nameField);
        panel.add(new JLabel("作者:")); panel.add(authorField);
        panel.add(new JLabel("出版社:")); panel.add(publisherField);
        panel.add(new JLabel("价格:")); panel.add(priceField);
        panel.add(new JLabel("总藏书量:")); panel.add(totalField);
        panel.add(new JLabel("库存:")); panel.add(inventoryField);

        int result = JOptionPane.showConfirmDialog(parent, panel, "添加图书", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                Book book = new Book();
                book.setBookNumber(numberField.getText().trim());
                book.setCategory(categoryField.getText().trim());
                book.setBookName(nameField.getText().trim());
                book.setAuthor(authorField.getText().trim());
                book.setPublisher(publisherField.getText().trim());
                book.setPrice(Double.parseDouble(priceField.getText().trim()));
                book.setBookTotal(Integer.parseInt(totalField.getText().trim()));
                book.setInventory(Integer.parseInt(inventoryField.getText().trim()));

                BookDao dao = new BookDao();
                boolean success = dao.addBook(book);

                if (success) {
                    JOptionPane.showMessageDialog(parent, "添加图书成功！");
                } else {
                    JOptionPane.showMessageDialog(parent, "添加失败，请检查输入或数据库状态！", "错误", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(parent, "输入格式错误，请检查数据类型！", "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void showDeleteBookDialog(JFrame parent) {
        JTextField numberField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.add(new JLabel("请输入要删除的书号:"));
        panel.add(numberField);

        int result = JOptionPane.showConfirmDialog(parent, panel, "删除图书", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String bookNumber = numberField.getText().trim();
            if (bookNumber.isEmpty()) {
                JOptionPane.showMessageDialog(parent, "书号不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            BookDao dao = new BookDao();
            boolean success = dao.deleteBookByNumber(bookNumber);

            if (success) {
                JOptionPane.showMessageDialog(parent, "图书删除成功！");
            } else {
                JOptionPane.showMessageDialog(parent, "删除失败，书号可能不存在。", "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void showUpdateBookDialog(JFrame parent) {
        JTextField numberField = new JTextField();
        JTextField categoryField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField publisherField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField totalField = new JTextField();
        JTextField inventoryField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.add(new JLabel("书号（用于查找）:")); panel.add(numberField);
        panel.add(new JLabel("类别：")); panel.add(categoryField);
        panel.add(new JLabel("书名:")); panel.add(nameField);
        panel.add(new JLabel("作者:")); panel.add(authorField);
        panel.add(new JLabel("出版社:")); panel.add(publisherField);
        panel.add(new JLabel("价格:")); panel.add(priceField);
        panel.add(new JLabel("总藏书量:")); panel.add(totalField);
        panel.add(new JLabel("库存:")); panel.add(inventoryField);

        int result = JOptionPane.showConfirmDialog(parent, panel, "修改图书", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                Book book = new Book();
                book.setBookNumber(numberField.getText().trim());
                book.setCategory(categoryField.getText().trim());
                book.setBookName(nameField.getText().trim());
                book.setAuthor(authorField.getText().trim());
                book.setPublisher(publisherField.getText().trim());
                book.setPrice(Double.parseDouble(priceField.getText().trim()));
                book.setBookTotal(Integer.parseInt(totalField.getText().trim()));
                book.setInventory(Integer.parseInt(inventoryField.getText().trim()));

                BookDao dao = new BookDao();
                boolean success = dao.updateBook(book);

                if (success) {
                    JOptionPane.showMessageDialog(parent, "图书更新成功！");
                } else {
                    JOptionPane.showMessageDialog(parent, "更新失败，请确认书号存在。", "错误", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(parent, "输入格式错误，请检查数据类型！", "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void showSearchBookDialog(JFrame parent) {
        JTextField inputField = new JTextField();
        JRadioButton byNumber = new JRadioButton("按书号精确查询");
        JRadioButton byName = new JRadioButton("按书名模糊查询", true);
        ButtonGroup group = new ButtonGroup();
        group.add(byNumber);
        group.add(byName);

        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.add(new JLabel("请输入查询关键字："));
        panel.add(inputField);
        panel.add(byNumber);
        panel.add(byName);

        int result = JOptionPane.showConfirmDialog(parent, panel, "查询图书", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String keyword = inputField.getText().trim();
            BookDao dao = new BookDao();

            if (byNumber.isSelected()) {
                Book book = dao.findBookByNumber(keyword);
                if (book == null) {
                    JOptionPane.showMessageDialog(parent, "未找到该图书。", "结果", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("书号: ").append(book.getBookNumber()).append("\n")
                            .append("类别：").append(book.getCategory()).append("\n")
                            .append("书名: ").append(book.getBookName()).append("\n")
                            .append("作者: ").append(book.getAuthor()).append("\n")
                            .append("出版社: ").append(book.getPublisher()).append("\n")
                            .append("价格: ").append(book.getPrice()).append("\n")
                            .append("库存: ").append(book.getInventory()).append("\n");
                    JOptionPane.showMessageDialog(parent, sb.toString(), "查询结果", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                List<Book> books = dao.findBooksByName(keyword);
                if (books.isEmpty()) {
                    JOptionPane.showMessageDialog(parent, "未找到相关图书。", "结果", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (Book book : books) {
                        sb.append("书号: ").append(book.getBookNumber()).append("\n")
                                .append("类别：").append(book.getCategory()).append("\n")
                                .append("书名: ").append(book.getBookName()).append("\n")
                                .append("作者: ").append(book.getAuthor()).append("\n")
                                .append("出版社: ").append(book.getPublisher()).append("\n")
                                .append("价格: ").append(book.getPrice()).append("\n")
                                .append("库存: ").append(book.getInventory()).append("\n")
                                .append("-----------------------------\n");
                    }
                    JTextArea area = new JTextArea(sb.toString());
                    area.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(area);
                    scrollPane.setPreferredSize(new Dimension(400, 300));
                    JOptionPane.showMessageDialog(parent, scrollPane, "查询结果", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
}
