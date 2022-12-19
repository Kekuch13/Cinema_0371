package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPanel extends JFrame implements ActionListener {

    ClientConnection Conn;

    JTable films;
    JButton schedule, addFilm, redactFilm, deleteFilm, createSession;

    ///////////////////////
    private Object[][] array = new String[][] {{ "Сахар" , "кг", "1.5" },
            { "Мука"  , "кг", "4.0" },
            { "Молоко", "л" , "2.2" }};
    // Заголовки столбцов
    private Object[] columnsHeader = new String[] {"Наименование", "Ед.измерения",
            "Количество"};
    ////////////////////////

    AdminPanel(){
        Conn = ClientConnection.instance;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(800, 600);
        this.setTitle("Фильмы");
        this.setLocationRelativeTo(null);
        this.setLayout(null);



        films = new JTable(array, columnsHeader);
        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(new JScrollPane(films));

        schedule = new JButton("Расписание сеансов");
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
        this.add(deleteFilm);
        this.add(films);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
