package Client;

import Entities.Film;
import com.google.gson.Gson;
import forms.FilmsForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminPanel extends JFrame implements ActionListener {

    ClientConnection Conn;
    private Gson gson;

    JTable filmsTable;
    JButton schedule, addFilm, redactFilm, deleteFilm, createSession;

    AdminPanel(){
        Conn = ClientConnection.instance;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(800, 600);
        this.setTitle("Фильмы");
        this.setLocationRelativeTo(null);
        //this.setLayout(null);
        this.setLayout(new GridBagLayout());

        FilmsForm filmsForm = new FilmsForm();

        gson = new Gson();
        String json = gson.toJson(filmsForm);
        Conn.sendToServer(json);


        FilmsTableModel ftm = new FilmsTableModel();
        filmsTable = new JTable(ftm);
        JScrollPane filmsTableScrollPage = new JScrollPane(filmsTable);
        filmsTableScrollPage.setPreferredSize(new Dimension(600, 400));
        /*int tableSize = Conn.receiveFromServer();*/

        String line = Conn.receiveFromServer();
        filmsForm = gson.fromJson(line, FilmsForm.class);
        ArrayList<Film> filmsFormList = filmsForm.films;


        for (int i = 0; i < filmsFormList.size(); i++) {
            ftm.addData(new String[]{String.valueOf(filmsFormList.get(i).getFilm_id()), filmsFormList.get(i).getTitle(), String.valueOf(filmsFormList.get(i).getDuration()), filmsFormList.get(i).getGenre(), String.valueOf(filmsFormList.get(i).getYear()), filmsFormList.get(i).getCountry()});
        }


        this.add(filmsTableScrollPage, new GridBagConstraints(1, 1, 2, 2, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(100, 20, 100,20), 0, 0));


        /*schedule = new JButton("Расписание сеансов");
        schedule.setBounds(20, 20, 250, 40);
        schedule.setFont(new Font("Arial", Font.BOLD, 16));
        schedule.setBackground(Color.blue);
        schedule.setForeground(Color.black);
        schedule.addActionListener(this);

        addFilm = new JButton("Добавить");
        addFilm.setBounds(20, 500, 150, 40);
        addFilm.setFont(new Font("Arial", Font.BOLD, 16));
        addFilm.setBackground(Color.green);
        addFilm.setForeground(Color.black);
        addFilm.addActionListener(this);

        redactFilm = new JButton("Редактировать");
        redactFilm.setBounds(330, 500, 200, 40);
        redactFilm.setFont(new Font("Arial", Font.BOLD, 16));
        redactFilm.setBackground(Color.gray);
        redactFilm.setForeground(Color.black);
        redactFilm.addActionListener(this);

        deleteFilm = new JButton("Удалить");
        deleteFilm.setBounds(550, 500, 200, 40);
        deleteFilm.setFont(new Font("Arial", Font.BOLD, 16));
        deleteFilm.setBackground(Color.red);
        deleteFilm.setForeground(Color.black);
        deleteFilm.addActionListener(this);


        this.add(schedule);
        this.add(addFilm);
        this.add(redactFilm);
        this.add(deleteFilm);*/

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
