package Client;

import com.google.gson.Gson;
import forms.AuthenticationForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class SignInFrame extends JFrame implements ActionListener {
    private final JPasswordField password;
    private final JTextField username;
    private final JLabel labelPassword;
    private final JLabel labelUsername;
    private final JLabel message;
    private final JLabel title;
    private final JButton btn;
    private final JButton resetBtn;
    private final JCheckBox showPassword;
    private final ClientConnection Conn;
    private AuthenticationForm authForm;
    private final Gson gson;

    SignInFrame() {
        gson = new Gson();
        Conn = ClientConnection.instance;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(800, 600);
        this.setTitle("Cinema_0371");
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        title = new JLabel("Войти в систему");
        title.setBounds(300, 100, 300, 40);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.black);

        labelUsername = new JLabel("Логин");
        labelUsername.setBounds(200, 150, 100, 40);
        labelUsername.setFont(new Font("Arial", Font.BOLD, 20));
        labelUsername.setForeground(Color.black);

        labelPassword = new JLabel("Пароль");
        labelPassword.setBounds(200, 200, 100, 40);
        labelPassword.setFont(new Font("Arial", Font.BOLD, 20));
        labelPassword.setForeground(Color.black);

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

        resetBtn = new JButton("Сброс");
        resetBtn.setBounds(500, 300, 100, 40);
        resetBtn.setFont(new Font("Arial", Font.BOLD, 16));
        resetBtn.setBackground(Color.red);
        resetBtn.setForeground(Color.black);
        resetBtn.addActionListener(this);

        this.add(title);
        this.add(labelUsername);
        this.add(labelPassword);
        this.add(username);
        this.add(password);
        this.add(btn);
        this.add(resetBtn);
        this.add(showPassword);
        this.add(message);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btn) {
            //добавить проверку на пустые поля и длину строки

            authForm = new AuthenticationForm(username.getText(), String.valueOf(password.getPassword()), false);
            String json = gson.toJson(authForm);
            String name = username.getText();

            Conn.sendToServer(json);
            String line = Conn.receiveFromServer();

            authForm = gson.fromJson(line, AuthenticationForm.class);

            String msg;
            if (authForm.isValid) {
                if (Objects.equals(authForm.getRole(), "USER")) {
                    UserFrame userFrame = new UserFrame();
                } else {
                    AdminPanel adminPanel = new AdminPanel();
                }
                this.dispose();
            } else {
                msg = "Неверный логин или пароль";
                message.setForeground(Color.red);
                message.setText(msg);
            }
        }
        if (evt.getSource() == resetBtn) {
            username.setText("");
            password.setText("");
        }
        if (evt.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                password.setEchoChar((char) 0);
            } else {
                password.setEchoChar('•');
            }
        }
    }
}
