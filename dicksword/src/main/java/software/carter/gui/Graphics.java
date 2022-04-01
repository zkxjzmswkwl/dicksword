package software.carter.gui;

import javax.swing.*;

import java.awt.Color;

import com.formdev.flatlaf.FlatDarculaLaf;

import software.carter.database.Messages;
import software.carter.local.Auth;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// I really do not know how to use Swing.

public class Graphics implements Runnable {
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JTextField msgInput;
    private JList msgList;

    private void setupFlatlaf() {
        FlatDarculaLaf.setup();
    }

    @Override
    public void run() {
        setupFlatlaf();

        frame = new JFrame();
        panel = new JPanel();
        msgInput = new JTextField();

        DefaultListModel<String> listModel = new DefaultListModel<>();
        msgList = new JList<>(listModel);
        msgList.setFont(new Font("Verdana", Font.PLAIN, 14));
        msgList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        msgList.setBackground(Color.darkGray);

        msgInput.setBackground(Color.darkGray);
        msgInput.setForeground(Color.lightGray);
        msgInput.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout());
        panel.add(msgList, BorderLayout.PAGE_START);
        panel.add(msgInput, BorderLayout.PAGE_END);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Dicksword - Discord, but with *more* dicks.");
        frame.setSize(1280, 720);
        frame.setVisible(true);

        msgInput.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() != KeyEvent.VK_ENTER) 
                    return;

                String msgText = msgInput.getText();

                if (msgText.length() < 3)
                    return;

                System.out.println("FUCKKKK");
                listModel.addElement(Auth.s().getUsername() + " : " + msgInput.getText());
                msgInput.setText("");
                Messages.insertMessage(1, 2, msgText);
            }
        });
    }
}
