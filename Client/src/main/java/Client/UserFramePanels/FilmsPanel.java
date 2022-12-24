package Client.UserFramePanels;

import Entities.Film;

import javax.swing.*;
import java.awt.*;

public class FilmsPanel extends JPanel  {
    public JList<Film> filmList;
    public DefaultListModel<Film> filmsModel = new DefaultListModel<>();

    public FilmsPanel() {
        this.setSize(800, 600);
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        filmList = new JList<>();
        filmList.setModel(filmsModel);
        filmList.setBackground(new Color(255, 178, 102));
        filmList.setModel(filmsModel);
        filmList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        filmList.setFixedCellHeight(150);
        filmList.setFixedCellWidth(195);
        filmList.setVisibleRowCount(0);
        DefaultListCellRenderer renderer =  (DefaultListCellRenderer) filmList.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);

        this.add(new JScrollPane(filmList));
    }
}
