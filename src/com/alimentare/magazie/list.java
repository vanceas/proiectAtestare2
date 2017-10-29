package com.alimentare.magazie;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class list extends JFrame{
    JButton backButton;
    Statement myStmt;
    Vector data, row, columnNames;

    public list(Statement myStmt){
    this.myStmt = myStmt;
    this.setTitle("List");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(700,400);
    this.setLocationRelativeTo(null);
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainPanel.setBackground(Color.CYAN);

    JPanel listPanel =new JPanel();
    columnNames = new Vector();
    columnNames.add("nr");
    columnNames.add("Material");
    columnNames.add("Cumparator");
    columnNames.add("nrColi");

        try {
            ResultSet myRs = myStmt.executeQuery("select * from depozit");
            data = new Vector();
            while (myRs.next()) {
                row = new Vector();
                row.add(myRs.getString(1));
                row.add(myRs.getString(2).substring(0,15));
                row.add(myRs.getString(3).substring(0,15));
                row.add(myRs.getString(4));
                data.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JTable jTable = new JTable(data, columnNames);
        jTable.setRowHeight(jTable.getRowHeight() + 10);
        jTable.setFont(new Font("Arial", Font.PLAIN, 14));
        JTableHeader header = jTable.getTableHeader();
        header.setFont(new Font("Arial", Font.PLAIN,18));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        listPanel.add(scrollPane);
        mainPanel.add(listPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,10,20));
        backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100,40));
        backButton.setFont(new Font("Arial", Font.PLAIN,16));

        ListenForButton listenForButton = new ListenForButton();
        backButton.addActionListener(listenForButton);
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        this.add(mainPanel);
        this.setVisible(true);

    }
    private class ListenForButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == backButton){
                new workWindow(myStmt);
                dispose();
            }
        }
    }
}
