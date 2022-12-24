package Client;

import Entities.Film;
import com.google.gson.Gson;
import forms.AuthenticationForm;
import forms.TableChangeForm;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class AddingFilmDialog extends JDialog implements ActionListener {
    ClientConnection Conn;
    private Gson gson;
    JLabel labelTitle, labelGenre, labelCountry, labelYear, labelDuration, msg;
    JFormattedTextField year;
    JTextField title, genre, country, duration;
    JButton addingBtn, resetBtn, cancel;
    AddingFilmDialog() throws ParseException {
        this.setSize(500, 500);
        this.setTitle("Добавление");
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        labelTitle = new JLabel("Название");
        labelTitle.setBounds(50, 50, 100, 40);
        labelTitle.setFont(new Font("Arial", Font.BOLD, 20));
        labelTitle.setForeground(Color.black);

        labelGenre = new JLabel("Жанр");
        labelGenre.setBounds(50, 100, 100, 40);
        labelGenre.setFont(new Font("Arial", Font.BOLD, 20));
        labelGenre.setForeground(Color.black);

        labelCountry = new JLabel("Страна");
        labelCountry.setBounds(50, 150, 100, 40);
        labelCountry.setFont(new Font("Arial", Font.BOLD, 20));
        labelCountry.setForeground(Color.black);

        labelYear = new JLabel("Год");
        labelYear.setBounds(50, 200, 100, 40);
        labelYear.setFont(new Font("Arial", Font.BOLD, 20));
        labelYear.setForeground(Color.black);

        labelDuration = new JLabel("Длительность");
        labelDuration.setBounds(50, 250, 150, 40);
        labelDuration.setFont(new Font("Arial", Font.BOLD, 20));
        labelDuration.setForeground(Color.black);

        title = new JTextField();
        title.setBounds(200, 50, 250, 40);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.black);

        genre = new JTextField();
        genre.setBounds(200, 100, 250, 40);
        genre.setFont(new Font("Arial", Font.BOLD, 20));
        genre.setForeground(Color.black);

        country = new JTextField();
        country.setBounds(200, 150, 250, 40);
        country.setFont(new Font("Arial", Font.BOLD, 20));
        country.setForeground(Color.black);

        MaskFormatter yearFormat = new MaskFormatter("####");
        year = new JFormattedTextField(yearFormat);
        year.setBounds(200, 200, 250, 40);
        year.setFont(new Font("Arial", Font.BOLD, 20));
        year.setForeground(Color.black);

        duration = new JTextField();
        duration.setBounds(200, 250, 250, 40);
        duration.setFont(new Font("Arial", Font.BOLD, 20));
        duration.setForeground(Color.black);

        msg = new JLabel("");
        msg.setBounds(50, 300, 500, 40);
        msg.setFont(new Font("Arial", Font.BOLD, 20));
        msg.setForeground(Color.red);

        addingBtn = new JButton("Добавить");
        addingBtn.setBounds(50, 350, 130, 40);
        addingBtn.setFont(new Font("Arial", Font.BOLD, 16));
        addingBtn.setBackground(Color.blue);
        addingBtn.setForeground(Color.black);
        addingBtn.addActionListener(this);

        cancel = new JButton("Отмена");
        cancel.setBounds(50, 400, 130, 40);
        cancel.setFont(new Font("Arial", Font.BOLD, 16));
        cancel.setBackground(Color.gray);
        cancel.setForeground(Color.black);
        cancel.addActionListener(this);

        resetBtn = new JButton("Сброс");
        resetBtn.setBounds(350, 350, 100, 40);
        resetBtn.setFont(new Font("Arial", Font.BOLD, 16));
        resetBtn.setBackground(Color.red);
        resetBtn.setForeground(Color.black);
        resetBtn.addActionListener(this);

        this.add(labelTitle);
        this.add(labelGenre);
        this.add(labelCountry);
        this.add(labelYear);
        this.add(labelDuration);
        this.add(title);
        this.add(genre);
        this.add(country);
        this.add(year);
        this.add(duration);
        this.add(msg);
        this.add(addingBtn);
        this.add(cancel);
        this.add(resetBtn);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == addingBtn) {
            //добавить проверку на пустые поля и длину строки
            msg.setText("");
            if (title.getText().equals("")){
                msg.setText("Некорректное название");
            } else if (genre.getText().equals("")){
                msg.setText("Некорректный жанр");
            } else if (country.getText().equals("")){
                msg.setText("Некорректная страна");
            } else if (year.getText().contains(" ") || (Integer.parseInt(year.getText()) > 2100) || (Integer.parseInt(year.getText()) < 1900)) {
                msg.setText("Некорректный год");
            } else if ((!duration.getText().matches("[-+]?\\d+")) || (Integer.parseInt(duration.getText()) > 720) || (Integer.parseInt(duration.getText()) < 1)){
                msg.setText("Некорректная длительность");
            } else {
                Film addedFilm = new Film(title.getText(), Integer.parseInt(year.getText()), genre.getText(), Integer.parseInt(duration.getText().toString()), country.getText());
                TableChangeForm tableChangeForm = new TableChangeForm("add", addedFilm);

                gson = new Gson();
                Conn = ClientConnection.instance;
                String json = gson.toJson(tableChangeForm);
                Conn.sendToServer(json);

                AdminPanel adminframe = new AdminPanel();
                this.dispose();

            }
        }
        if (evt.getSource() == cancel) {
            AdminPanel adminPanel = new AdminPanel();
            this.dispose();
        }
        if (evt.getSource() == resetBtn) {
            title.setText("");
            genre.setText("");
            country.setText("");
            duration.setText("");
            year.setText("");
        }
    }
}