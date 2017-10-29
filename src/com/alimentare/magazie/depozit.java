package com.alimentare.magazie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;


public class depozit extends JFrame {
    JButton backButton, registerButton;
    JLabel numeMaterialLabel, numeCumparatorLabel, nrColiLabel;
    JTextField numeMaterialField, numeCumparatorField;
    JList nrColiJList;
    DefaultListModel nrColiV = new DefaultListModel();
    Statement myStmt;

    public depozit(Statement myStmt){
        this.myStmt = myStmt;
        this.setTitle("Depozit");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900,600);
        this.setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.YELLOW);
        JPanel regPanel = new JPanel();
        regPanel.setBackground(Color.GRAY);
        regPanel.setLayout(new FlowLayout(FlowLayout.LEFT,20,30));

        numeMaterialLabel = new JLabel("Material");
        numeMaterialLabel.setFont(new Font("Arial",Font.ITALIC,16));
        regPanel.add(numeMaterialLabel);

        numeMaterialField = new JTextField(20);
        numeMaterialField.setFont(new Font("Arial",Font.PLAIN,16));
        regPanel.add(numeMaterialField);

        numeCumparatorLabel = new JLabel("Cumparator");
        numeCumparatorLabel.setFont(new Font("Arial",Font.ITALIC,16));
        numeCumparatorLabel.setLocation(16,18);
        regPanel.add(numeCumparatorLabel);

        numeCumparatorField = new JTextField(20);
        numeCumparatorField.setFont(new Font("Arial",Font.PLAIN,16));
        regPanel.add(numeCumparatorField);

        nrColiLabel = new JLabel("nrColi");
        nrColiLabel.setFont(new Font("Arial",Font.ITALIC,16));
        nrColiLabel.setLayout(new FlowLayout(FlowLayout.LEFT));
        regPanel.add(nrColiLabel);

        Integer[] nrColi = {1,2,3,4,5,6,7,8,9,10};
        for (Integer nr:nrColi){
            nrColiV.addElement(nr);
        }
        nrColiJList = new JList(nrColiV);
        nrColiJList.setFont(new Font("Arial",Font.PLAIN,16));
        nrColiJList.setVisibleRowCount(5);
        regPanel.add(nrColiJList);
        mainPanel.add(regPanel,BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,10,20));

        backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100,40));
        backButton.setFont(new Font("Arial",Font.PLAIN,16));

        registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(100,40));
        registerButton.setFont(new Font("Arial",Font.PLAIN,16));

        ListenForButton listenForButton = new ListenForButton();
        backButton.addActionListener(listenForButton);
        registerButton.addActionListener(listenForButton);

        buttonPanel.add(backButton);
        buttonPanel.add(registerButton);


        mainPanel.add(buttonPanel, BorderLayout.EAST);
        this.add(mainPanel);
        this.setVisible(true);

    }
    private class ListenForButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == backButton){
                new workWindow(myStmt);
            }else if (e.getSource() == registerButton){
                Material material = new Material(mainApp.materialCount, numeMaterialField.getText(),
                        numeCumparatorField.getText(),(int)nrColiJList.getSelectedValue());
                material.saveMaterial(myStmt);
                mainApp.materialCount++;
                new workWindow(myStmt);
                dispose();
            }
        }
    }
}
