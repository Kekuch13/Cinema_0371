package Client;

import Entities.Film;
import com.google.gson.Gson;
import forms.FilmsForm;
import forms.TableChangeForm;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class AdminPanel extends JFrame implements ActionListener {

    ClientConnection Conn;
    private Gson gson;

    JTable filmsTable;
    JButton schedule, addFilm, deleteFilm, createSession;

    FilmsTableModel ftm = new FilmsTableModel();

    AdminPanel(){
        Conn = ClientConnection.instance;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(800, 600);
        this.setTitle("Фильмы");
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        FilmsForm filmsForm = new FilmsForm();

        gson = new Gson();
        String json = gson.toJson(filmsForm);
        Conn.sendToServer(json);

        filmsTable = new JTable(ftm);
        JScrollPane filmsTableScrollPage = new JScrollPane(filmsTable);

        String line = Conn.receiveFromServer();
        filmsForm = gson.fromJson(line, FilmsForm.class);
        ArrayList<Film> filmsFormList = filmsForm.films;


        for (int i = 0; i < filmsFormList.size(); i++) {
            ftm.addData(new String[]{String.valueOf(filmsFormList.get(i).getFilm_id()), filmsFormList.get(i).getTitle(), filmsFormList.get(i).getDuration() / 60 + " ч " + filmsFormList.get(i).getDuration() % 60 + " мин ", filmsFormList.get(i).getGenre(), String.valueOf(filmsFormList.get(i).getYear()), filmsFormList.get(i).getCountry()});
        }

        filmsTableScrollPage.setBounds(70, 80, 650, 370);

        this.add(filmsTableScrollPage);


        schedule = new JButton("Расписание сеансов");
        schedule.setBounds(20, 20, 250, 40);
        schedule.setFont(new Font("Arial", Font.BOLD, 16));
        schedule.setBackground(Color.blue);
        schedule.setForeground(Color.black);
        schedule.addActionListener(this);

        createSession = new JButton("Добавить сеанс");
        createSession.setBounds(300, 20, 250, 40);
        createSession.setFont(new Font("Arial", Font.BOLD, 16));
        createSession.setBackground(Color.blue);
        createSession.setForeground(Color.black);
        createSession.addActionListener(this);

        addFilm = new JButton("Добавить");
        addFilm.setBounds(20, 500, 150, 40);
        addFilm.setFont(new Font("Arial", Font.BOLD, 16));
        addFilm.setBackground(Color.green);
        addFilm.setForeground(Color.black);
        addFilm.addActionListener(this);

        /*redactFilm = new JButton("Редактировать");
        redactFilm.setBounds(330, 500, 200, 40);
        redactFilm.setFont(new Font("Arial", Font.BOLD, 16));
        redactFilm.setBackground(Color.gray);
        redactFilm.setForeground(Color.black);
        redactFilm.addActionListener(this);*/

        deleteFilm = new JButton("Удалить");
        deleteFilm.setBounds(550, 500, 200, 40);
        deleteFilm.setFont(new Font("Arial", Font.BOLD, 16));
        deleteFilm.setBackground(Color.red);
        deleteFilm.setForeground(Color.black);
        deleteFilm.addActionListener(this);


        this.add(schedule);
        this.add(createSession);
        this.add(addFilm);
        //this.add(redactFilm);
        this.add(deleteFilm);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if ((event.getSource() == deleteFilm) && (filmsTable.getSelectedRow() != -1)){
            int[] setOfID = ftm.getSelectedID(filmsTable.getSelectedRows());

            TableChangeForm tableChangeForm = new TableChangeForm("delete", setOfID);
            String json = gson.toJson(tableChangeForm);
            Conn.sendToServer(json);
            AdminPanel adminFrame = new AdminPanel();
            this.dispose();
        }
        if ((event.getSource() == schedule) && (filmsTable.getSelectedRow() != -1)){
            //открыть расписание сеансов
        }
        if ((event.getSource() == createSession) && (filmsTable.getSelectedRow() != -1)){
            try {
                AddingSessionDialog addingSessionDialog = new AddingSessionDialog((String) filmsTable.getValueAt(filmsTable.getSelectedRow(), 1), Integer.parseInt(filmsTable.getValueAt(filmsTable.getSelectedRow(), 0).toString()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            //добавить сеанс
        }
        if (event.getSource() == addFilm){
            try {
                AddingFilmDialog addFilmDialog = new AddingFilmDialog();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            this.dispose();
        }
        if (event.getSource() == schedule){
            SessionsAdminFrame sessionsAdminFrame = new SessionsAdminFrame(Integer.parseInt(filmsTable.getValueAt(filmsTable.getSelectedRow(), 0).toString()), filmsTable.getValueAt(filmsTable.getSelectedRow(), 1).toString());
            this.dispose();
        }
    }
}
