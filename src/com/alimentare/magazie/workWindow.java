package com.alimentare.magazie;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Statement;

public class workWindow extends JFrame {
    JButton stocButton, registerButton, listButton;
    BufferedImage img;
    Statement myStmt;

    public workWindow(Statement myStmt) {
        this.myStmt = myStmt;
        this.setTitle("PalDepozit");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900,600);
        this.setLocationRelativeTo(null);
        JLabel mainLabel = new JLabel();
        mainLabel.setSize(900,600);
        try {
            img = ImageIO.read(new File("C:\\users\\alexialida\\desktop\\palback.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(mainLabel.getWidth(), mainLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        mainLabel.setIcon(imageIcon);
        mainLabel.setLayout(new BorderLayout());
        this.setContentPane(mainLabel);
        JLabel welcomeLabel = new JLabel("PAL LAMINAT", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Castellar",Font.ITALIC,28));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,20));
        buttonPanel.setBackground(Color.lightGray);
        stocButton = new JButton("Stoc");
        stocButton.setPreferredSize(new Dimension(150,40));
        stocButton.setFont(new Font("Arial", Font.PLAIN,18));

        registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(150,40));
        registerButton.setFont(new Font("Arial", Font.PLAIN,18));

        listButton = new JButton("List");
        listButton.setPreferredSize(new Dimension(150,40));
        listButton.setFont(new Font("Arial", Font.PLAIN,18));

        ListenForButton listenForButton = new ListenForButton();
        stocButton.addActionListener(listenForButton);
        registerButton.addActionListener(listenForButton);
        listButton.addActionListener(listenForButton);

        buttonPanel.add(stocButton);
        buttonPanel.add(registerButton);
        buttonPanel.add(listButton);

        mainLabel.add(buttonPanel,BorderLayout.WEST);
        mainLabel.add(welcomeLabel);

        this.pack();
        this.setVisible(true);

    }
    private class ListenForButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == stocButton){
                new workWindow(myStmt);
                dispose();
            }else if (e.getSource() == registerButton) {
                new depozit(myStmt);
                dispose();
            }else if (e.getSource() == listButton){
                new list (myStmt);
                dispose();
            }
        }
    }

}
