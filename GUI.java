import javax.swing.*;
import java.awt.*;

/**
 * Created by alimousa on 5/22/16.
 */
public class GUI {

    private JFrame frame;
    private JComboBox comboBox;
    private String[] teams;
    private League nba;


    private JTable table;

    GUI(League nba) {
        this.nba = nba;
        frame = new JFrame("Stats");
        teams = new String[nba.getTeamNames().length+1];

        teams[0]= " ";
        for(int i = 1; i <= nba.getTeams().length;i++)
            teams[i] = nba.getTeamNames()[i-1];

        comboBox = new JComboBox(teams);
        comboBox.addItemListener(new ItemChangeListener(this));
        //DO STUFF

        Team t = nba.getTeams()[0]; //Team t should be the selected item of the combobox

        table = new JTable(t.getStatsTable(), CSV.getHeader().split(","));
        JScrollPane scrollPane = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));

        //DROP HITS GET MONEY, RUN IT!
        frame.add(scrollPane);
        frame.add(comboBox, 1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);

    }

    public JFrame getFrame() {
        return frame;
    }

    public JComboBox getComboBox() {
        return comboBox;
    }

    public String[] getTeams() {
        return teams;
    }

    public League getNba() {
        return nba;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

}
