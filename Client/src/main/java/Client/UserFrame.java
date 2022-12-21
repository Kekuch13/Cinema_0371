package Client;

import Client.UserFramePanels.FilmsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserFrame extends JFrame implements ActionListener {
    JPanel mainPanel;
    FilmsPanel filmsPanel;
    //SessionsPanel sessionsPanel;
    //TicketsPanel ticketsPanel;
    JPanel sessionsPanel;
    JPanel ticketsPanel;
    JButton btn1;
    JButton btn2;
    CardLayout cl = new CardLayout();

    // Films
    // Sessions - храним только для выбранного фильма и обновляем при изменении фильма
    // Tickets - храним только для выбранного сеанса и обновляем при смене сеанса

    public UserFrame() {
        mainPanel = new JPanel();
        mainPanel.setSize(800, 600);
        mainPanel.setLayout(cl);

        btn1 = new JButton("toFilms");
        btn1.setBounds(300, 300, 100, 40);
        btn1.setFont(new Font("Arial", Font.BOLD, 16));
        btn1.setBackground(Color.white);
        btn1.setForeground(Color.black);

        btn2 = new JButton("Back");
        btn2.setBounds(500, 300, 100, 40);
        btn2.setFont(new Font("Arial", Font.BOLD, 16));
        btn2.setBackground(Color.yellow);
        btn2.setForeground(Color.black);

        filmsPanel = new FilmsPanel();
        filmsPanel.setBackground(Color.red);
        //filmsPanel.setSize(800, 600);
        //filmsPanel.setLayout(null);
        filmsPanel.add(btn1);

        //sessionsPanel = new SessionsPanel();
        sessionsPanel = new JPanel();
        sessionsPanel.setBackground(Color.green);
        sessionsPanel.setSize(800, 600);
        sessionsPanel.setLayout(null);
        sessionsPanel.add(btn2);

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cl.show(mainPanel, "2");
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cl.show(mainPanel, "1");
            }
        });

        mainPanel.add(filmsPanel, "1");
        mainPanel.add(sessionsPanel, "2");
        cl.show(mainPanel, "1");

        this.getContentPane().add(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setTitle("Cinema_0371");
        this.setLocationRelativeTo(null);
        //this.setLayout(cl);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    // getFilms
    // getSessions - обновляем под каждый фильм сеансы
    // getTickets - обновляем под каждый сеанс билеты

}
