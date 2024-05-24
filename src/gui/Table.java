package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Table extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLayeredPane layeredPane;
    private DefaultListModel<String> tableListModel;
    private JList<String> tableList;
    private JButton[] tableButtons;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Table frame = new Table();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Table() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 406);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        layeredPane = new JLayeredPane();
        layeredPane.setBounds(150, 5, 620, 550);
        contentPane.add(layeredPane);

        JLabel lblBackground = new JLabel("");
        lblBackground.setBounds(59, 0, 574, 331);
        ImageIcon img1 = new ImageIcon(getClass().getResource("/rsz_borde.png"));
        lblBackground.setIcon(img1);
        layeredPane.add(lblBackground, JLayeredPane.DEFAULT_LAYER);

        JButton reserverButton = new JButton("Reserver");
        reserverButton.setBounds(403, 330, 100, 30);
        layeredPane.add(reserverButton);
        reserverButton.addActionListener(e -> reserverButton());

        JButton backButton = new JButton("Annuller");
        backButton.setBounds(513, 330, 100, 30);
        layeredPane.add(backButton);
        backButton.addActionListener(e -> backButton());

        tableButtons = new JButton[11]; 
        createTableButtons();

        JPanel listPanel = new JPanel();
        listPanel.setBounds(5, 5, 135, 550);
        contentPane.add(listPanel);
        listPanel.setLayout(new BorderLayout(0, 0));

        tableListModel = new DefaultListModel<>();
        tableList = new JList<>(tableListModel);
        JScrollPane scrollPane = new JScrollPane(tableList);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        initializeTableStatuses();
    }

    private void createTableButtons() {
        int[][] positions = {
            {50, 118}, {50, 185}, {50, 278}, {350, 50},
            {200, 185}, {300, 278}, {250, 150}, {350, 150},
            {400, 200}, {450, 250}, {500, 300} 
        };

        Dimension buttonSize = new Dimension(45, 45);

        for (int i = 0; i < tableButtons.length; i++) {
            JButton button = new JButton(String.valueOf(i + 1));
            button.setBounds(positions[i][0], positions[i][1], buttonSize.width, buttonSize.height);
            button.setBackground(Color.GREEN);
            final int index = i;
            button.addActionListener(e -> toggleTableStatus(index));
            tableButtons[i] = button;
            layeredPane.add(button, JLayeredPane.PALETTE_LAYER);
        }
    }

    private void initializeTableStatuses() {
        for (int i = 0; i < tableButtons.length; i++) {
            tableListModel.addElement("Table " + (i + 1) + ": Available");
        }
    }

    private void toggleTableStatus(int index) {
        JButton button = tableButtons[index];
        if (button.getBackground() == Color.GREEN) {
            button.setBackground(Color.RED);
            tableListModel.set(index, "Table " + (index + 1) + ": Booked");
        } else {
            button.setBackground(Color.GREEN);
            tableListModel.set(index, "Table " + (index + 1) + ": Available");
        }
    }
    
    private void reserverButton() {
        UpdatedCreateOrder updatedCreateOrder = new UpdatedCreateOrder(null);
        updatedCreateOrder.setVisible(true);
        dispose();
    }
    
    private void backButton() {
        Menu menu = new Menu();
        menu.setVisible(true);
        dispose();
    }
}
