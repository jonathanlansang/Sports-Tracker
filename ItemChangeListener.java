import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by alimousa on 5/22/16.
 */
public class ItemChangeListener implements ItemListener {
    private GUI gui;

    ItemChangeListener(GUI gui1) {
        gui = gui1;
    }

    public void itemStateChanged(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.SELECTED) {
            String team = (String) event.getItem();
            Team t = gui.getNba().getTeams()[gui.getNba().binarySearch(team)];
            gui.setTable(new JTable(t.getStatsTable(), CSV.getHeader().split(",")));

            JFrame newFrame = new JFrame(t.getName());
            JScrollPane scrollPane = new JScrollPane(gui.getTable());
            gui.getTable().setPreferredScrollableViewportSize(gui.getTable().getPreferredScrollableViewportSize());
            gui.getTable().setFillsViewportHeight(true);

            newFrame.add(scrollPane);


            newFrame.pack();
            newFrame.setVisible(true);

        }
    }
}
