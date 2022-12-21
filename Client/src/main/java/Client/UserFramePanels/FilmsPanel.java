package Client.UserFramePanels;

import Entities.Film;

import javax.swing.*;
import java.util.ArrayList;

public class FilmsPanel extends JPanel  {
    ArrayList<Film> films;
    String[] data = { "Chrome", "Firefox", "Internet Explorer", "Safari",
            "Opera", "Morrowind", "Oblivion", "NFS", "Half Life 2",
            "Hitman", "Morrowind", "Oblivion", "NFS", "Half Life 2",
            "Hitman", "Morrowind", "Oblivion", "NFS", "Half Life 2",
            "Hitman", "Morrowind", "Oblivion", "NFS", "Half Life 2",
            "Hitman", "Morrowind", "Oblivion", "NFS", "Half Life 2",
            "Hitman", "IL-2", "CMR", "NFS Undercover",
            "Star Wars", "Call of Duty", "IL-2", "CMR",
            "NFS Undercover", "Star Wars", "Call of Duty",
            "IL-2", "CMR", "NFS Undercover", "Star Wars",
            "Call of Duty", "IL-2", "CMR", "NFS Undercover",
            "Star Wars", "Call of Duty", "IL-2", "CMR",
            "NFS Undercover", "Star Wars", "Call of Duty",
            "IL-2", "CMR", "NFS Undercover", "Star Wars",
            "Call of Duty", "Arena", "Dagerfall", "MS Office",
            "Open Office", "Windows", "Arena", "Dagerfall",
            "MS Office", "Open Office", "Windows", "Arena",
            "Dagerfall", "MS Office", "Open Office", "Windows",
            "Arena", "Dagerfall", "MS Office", "Open Office",
            "Windows", "Mac OS", "Ubuntu"
    };
    JList<Film> filmsList;
    JList<String> southList;
    JScrollPane southScroll;

    public FilmsPanel() {
        this.setSize(800, 600);
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        southList = new JList<String>(data);
        //southList.setBounds(10, 10, 200, 200);
        southList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        southList.setFixedCellHeight(300);
        southList.setFixedCellWidth(200);
        DefaultListCellRenderer renderer =  (DefaultListCellRenderer)southList.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        southList.setVisibleRowCount(0);

        southScroll = new JScrollPane(southList);

        this.add(southScroll);
    }
}
