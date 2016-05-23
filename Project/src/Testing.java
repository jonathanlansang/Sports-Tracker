import javax.swing.*;

/**
 * Created by alimousa on 5/16/16.
 */
public class Testing extends JFrame {

    public static void main(String[] args) {
        League nba = new League("local");

        GUI gui = new GUI(nba);
    }
}
