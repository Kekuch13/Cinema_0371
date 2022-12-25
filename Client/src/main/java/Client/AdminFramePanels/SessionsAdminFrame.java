package Client.AdminFramePanels;

import Client.AdminFramePanels.TableModels.SessionsTableModel;
import Client.AdminPanel;
import Client.ClientConnection;
import Entities.Film;
import Entities.Session;
import com.google.gson.Gson;
import forms.DeleteSessionForm;
import forms.SessionsForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SessionsAdminFrame extends JFrame implements ActionListener {
    ClientConnection Conn;
    JTable sessionsTable;
    SessionsTableModel stm = new SessionsTableModel();
    Film deletedSessionFilm;
    JLabel titleLabel, durLabel;
    JButton backward, deleteBtn;
    private Gson gson;

    public SessionsAdminFrame(Film film) {
        deletedSessionFilm = film;

        Conn = ClientConnection.instance;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(800, 600);
        this.setTitle("Сеансы: " + film.getTitle());
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        SessionsForm sessionsForm = new SessionsForm();

        gson = new Gson();
        sessionsForm.setFilm_id(film.getFilm_id());
        sessionsForm.setDuration(film.getDuration());
        String json = gson.toJson(sessionsForm);
        Conn.sendToServer(json);

        sessionsTable = new JTable(stm);
        JScrollPane sessionsTableScrollPage = new JScrollPane(sessionsTable);

        String line = Conn.receiveFromServer();
        sessionsForm = gson.fromJson(line, SessionsForm.class);
        ArrayList<Session> sessionsFormList = sessionsForm.sessions;

        int duration = sessionsForm.getDuration();
        for (int i = 0; i < sessionsFormList.size(); i++) {
            String hallField = sessionsFormList.get(i).getHall_id() + ": " + sessionsForm.getHall_name().get(i);
            stm.addData(new String[]{String.valueOf(sessionsFormList.get(i).getDate()), String.valueOf(sessionsFormList.get(i).getTime()), hallField});
        }

        sessionsTableScrollPage.setBounds(70, 130, 650, 320);

        this.add(sessionsTableScrollPage);

        titleLabel = new JLabel("Фильм: " + film.getTitle());
        titleLabel.setBounds(20, 20, 250, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.black);

        durLabel = new JLabel("Длительность: " + sessionsForm.getDuration() / 60 + " ч " + sessionsForm.getDuration() % 60 + " мин");
        durLabel.setBounds(20, 70, 250, 40);
        durLabel.setFont(new Font("Arial", Font.BOLD, 16));
        durLabel.setForeground(Color.black);

        backward = new JButton("Назад");
        backward.setBounds(20, 500, 150, 40);
        backward.setFont(new Font("Arial", Font.BOLD, 16));
        backward.setBackground(Color.gray);
        backward.setForeground(Color.black);
        backward.addActionListener(this);

        deleteBtn = new JButton("Удалить");
        deleteBtn.setBounds(550, 500, 200, 40);
        deleteBtn.setFont(new Font("Arial", Font.BOLD, 16));
        deleteBtn.setBackground(Color.red);
        deleteBtn.setForeground(Color.black);
        deleteBtn.addActionListener(this);


        //todo добавить лейбл фильма
        this.add(titleLabel);
        this.add(durLabel);
        this.add(backward);
        this.add(deleteBtn);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == backward) {
            AdminPanel adminPanel = new AdminPanel();
            this.dispose();
        }
        if (evt.getSource() == deleteBtn) {
            String hall = stm.getValueAt(sessionsTable.getSelectedRow(), 2).toString();
            DeleteSessionForm deleteSessionForm = new DeleteSessionForm(
                    java.sql.Date.valueOf(stm.getValueAt(sessionsTable.getSelectedRow(), 0).toString()),
                    java.sql.Time.valueOf(stm.getValueAt(sessionsTable.getSelectedRow(), 1).toString()),
                    Integer.parseInt(hall.substring(0, hall.indexOf(':')))
            );
            gson = new Gson();
            Conn = ClientConnection.instance;
            String json = gson.toJson(deleteSessionForm);
            Conn.sendToServer(json);
            SessionsAdminFrame sessionsAdminFrame = new SessionsAdminFrame(deletedSessionFilm);
            this.dispose();
        }
    }
}
