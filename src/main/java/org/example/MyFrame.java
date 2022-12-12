package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {
    JPasswordField password;
    JTextField username;
    JLabel label_password, label_username, message, title;
    JButton btn, reset_btn;
    JCheckBox showPassword;

    MyFrame () {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setTitle("CinemaXD");
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        title = new JLabel("Войти в систему");
        title.setBounds(300, 100, 300, 40);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.black);

        label_username = new JLabel("Логин");
        label_username.setBounds(200, 150, 100, 40);
        label_username.setFont(new Font("Arial", Font.BOLD, 20));
        label_username.setForeground(Color.black);

        label_password = new JLabel("Пароль");
        label_password.setBounds(200, 200, 100, 40);
        label_password.setFont(new Font("Arial", Font.BOLD, 20));
        label_password.setForeground(Color.black);

        message = new JLabel();
        message.setBounds(300, 350, 300, 40);
        message.setFont(new Font("Arial", Font.BOLD, 20));
        message.setForeground(Color.green);

        username = new JTextField();
        username.setBounds(300, 150, 300, 40);
        username.setFont(new Font("Arial", Font.BOLD, 20));
        username.setForeground(Color.black);

        password = new JPasswordField();
        password.setBounds(300, 200, 300, 40);
        password.setFont(new Font("Arial", Font.BOLD, 20));
        password.setForeground(Color.black);

        showPassword = new JCheckBox("Показать пароль");
        showPassword.setBounds(300, 250, 300, 40);
        showPassword.setFont(new Font("Arial", Font.BOLD, 16));
        showPassword.setForeground(Color.black);
        showPassword.addActionListener(this);

        btn = new JButton("Войти");
        btn.setBounds(300, 300, 100, 40);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setBackground(Color.blue);
        btn.setForeground(Color.black);
        btn.addActionListener(this);

        reset_btn = new JButton("Сброс");
        reset_btn.setBounds(500, 300, 100, 40);
        reset_btn.setFont(new Font("Arial", Font.BOLD, 16));
        reset_btn.setBackground(Color.red);
        reset_btn.setForeground(Color.black);
        reset_btn.addActionListener(this);

        this.add(title);
        this.add(label_username);
        this.add(label_password);
        this.add(username);
        this.add(password);
        this.add(btn);
        this.add(reset_btn);
        this.add(showPassword);
        this.add(message);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource() == btn) {
            JOptionPane.showMessageDialog(this, "Успешный вход");
            String msg = "Успешный вход";
            message.setText(msg);
        }
        if(evt.getSource() == reset_btn) {
            username.setText("");
            password.setText("");
        }
        if(evt.getSource() == showPassword) {
            if(showPassword.isSelected()) {
                password.setEchoChar((char) 0);
            } else {
                password.setEchoChar('*');
            }
        }
    }
}
