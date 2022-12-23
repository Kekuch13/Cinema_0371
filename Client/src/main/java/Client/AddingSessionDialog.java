package Client;

import Entities.Film;
import Entities.Session;
import com.google.gson.Gson;
import forms.CreateSessionForm;
import forms.TableChangeForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class AddingSessionDialog extends JDialog implements ActionListener {
    ClientConnection Conn;
    private Gson gson;
    JLabel labelTitle, labelDate, labelTime, labelHall, msg;
    JTextField title, dateField, timeField, hallField;
    JButton addingBtn, resetBtn;

    int film_idOfAddedSession;

    AddingSessionDialog(String filmTitle, int film_id) {
        film_idOfAddedSession = film_id;
        this.setSize(500, 400);
        this.setTitle("Добавление");
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        labelTitle = new JLabel("Фильм: " + filmTitle);
        labelTitle.setBounds(50, 50, 450, 40);
        labelTitle.setFont(new Font("Arial", Font.BOLD, 20));
        labelTitle.setForeground(Color.black);

        labelDate = new JLabel("Дата (дд.мм.гггг)");
        labelDate.setBounds(50, 100, 200, 40);
        labelDate.setFont(new Font("Arial", Font.BOLD, 20));
        labelDate.setForeground(Color.black);

        labelTime = new JLabel("Время (чч:мм)");
        labelTime.setBounds(50, 150, 200, 40);
        labelTime.setFont(new Font("Arial", Font.BOLD, 20));
        labelTime.setForeground(Color.black);

        labelHall = new JLabel("Номер зала");
        labelHall.setBounds(50, 200, 200, 40);
        labelHall.setFont(new Font("Arial", Font.BOLD, 20));
        labelHall.setForeground(Color.black);

        dateField = new JTextField();
        dateField.setBounds(250, 100, 200, 40);
        dateField.setFont(new Font("Arial", Font.BOLD, 20));
        dateField.setForeground(Color.black);

        timeField = new JTextField();
        timeField.setBounds(250, 150, 200, 40);
        timeField.setFont(new Font("Arial", Font.BOLD, 20));
        timeField.setForeground(Color.black);

        hallField = new JTextField();
        hallField.setBounds(250, 200, 200, 40);
        hallField.setFont(new Font("Arial", Font.BOLD, 20));
        hallField.setForeground(Color.black);

        msg = new JLabel("");
        msg.setBounds(50, 250, 500, 40);
        msg.setFont(new Font("Arial", Font.BOLD, 20));
        msg.setForeground(Color.red);

        addingBtn = new JButton("Добавить");
        addingBtn.setBounds(50, 300, 130, 40);
        addingBtn.setFont(new Font("Arial", Font.BOLD, 16));
        addingBtn.setBackground(Color.blue);
        addingBtn.setForeground(Color.black);
        addingBtn.addActionListener(this);

        resetBtn = new JButton("Сброс");
        resetBtn.setBounds(350, 300, 100, 40);
        resetBtn.setFont(new Font("Arial", Font.BOLD, 16));
        resetBtn.setBackground(Color.red);
        resetBtn.setForeground(Color.black);
        resetBtn.addActionListener(this);

        this.add(labelTitle);
        this.add(labelDate);
        this.add(labelTime);
        this.add(labelHall);
        this.add(dateField);
        this.add(timeField);
        this.add(hallField);
        this.add(msg);
        this.add(addingBtn);
        this.add(resetBtn);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == addingBtn) {
            //добавить проверку на пустые поля и длину строки
            msg.setText("");
            if (dateField.equals("")){
                msg.setText("Некорректная дата");
            } else if (timeField.equals("")){
                msg.setText("Некорректное время");
            } else if (hallField.equals("")){
                msg.setText("Некорректный зал");
            } else {
                SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
                SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
                Session addedSession;

                try {
                    Date sqlDate = new java.sql.Date(formatter.parse(dateField.getText()).getTime());
                    Time sqlTime = new java.sql.Time(timeFormatter.parse(timeField.getText()).getTime());
                    addedSession = new Session(sqlDate, sqlTime, Integer.parseInt(hallField.getText()), film_idOfAddedSession);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                CreateSessionForm createSessionForm = new CreateSessionForm(addedSession);

                gson = new Gson();
                Conn = ClientConnection.instance;
                String json = gson.toJson(createSessionForm);
                Conn.sendToServer(json);

                AdminPanel adminframe = new AdminPanel();
                this.dispose();

            }
        }
        if (evt.getSource() == resetBtn) {
            title.setText("");
            dateField.setText("");
            timeField.setText("");
            hallField.setText("");
        }
    }
}
