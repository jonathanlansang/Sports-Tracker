import java.io.*;
import java.util.ArrayList;

/**
 * Created by alimousa on 5/18/16.
 */
public class CSV {
    private static final String DELIMITER = ",";
    private static final String NEWLINE = "\n";
    private static final String HEADER = "Name,Age,Team,League,Position,Games,Games Started,Minutes Played,Field Goals,"
            + "Field Goal Attempts,Field Goal Percentage,Three Pointers,Three Pointers Attempted,Three Point Percentage,"
            + "Two Pointers,Two Pointers Attempted,Two Point Percentage,Effective Field Goal Percentage,Free Throws,"
            + "Free Throws Attempted,Free Throw,Offensive Rebounds,Defensive Rebounds,Total Rebounds,Assists,Steals,Blocks,Turnovers,Personal Fouls,Points";
    private static final int NUMTEAMS = 30;

    CSV() {

    }

    public static String getHeader() {
        return HEADER;
    }

    /**
     * Writes all the team's stats and the players on the
     * team's stats to the CSV file with name "TeamName.csv"
     *
     * @param team Team Object to be recordeds
     */
    public String write(Team team) {
        String rtn = System.getProperty("file.separator") + System.getProperty("user.dir") + System.getProperty("file.separator")
                + "Teams" + System.getProperty("file.separator") + team.getName() + ".csv";
        Player[] players = team.getPlayers();
        ArrayList<String> teamStats = team.getStats();

        teamStats.add(0, teamStats.remove(teamStats.size() - 1));
        teamStats.add(1, "");
        teamStats.add(2, team.getName());
        teamStats.add(3, "NBA");
        teamStats.add(4, "");
        teamStats.add(6, "");

        FileWriter f = null;
        try {
            f = new FileWriter(rtn);

            f.append(HEADER);
            f.append(NEWLINE);

            for (String stat : teamStats) {
                f.append(stat);
                f.append(DELIMITER);
            }

            f.append(NEWLINE);

            for (Player p : players) {
                ArrayList<String> stats = p.getStats();
                for (int i = 0; i <= 29; i++) {
                    f.append(stats.get(i));
                    f.append(DELIMITER);
                }
                f.append(NEWLINE);
            }

        } catch (Exception e) {
            System.out.println("CSV Write Error\n");
            e.printStackTrace();
        } finally {
            try {
                f.flush();
                f.close();
            } catch (Exception e) {
                System.out.println("Flush/Close CSV Write error");
                e.printStackTrace();
            }
        }
        return rtn;
    }

    public ArrayList<String[]> read(String filename) {
        BufferedReader fileReader = null;
        ArrayList<String[]> returns = new ArrayList<String[]>();
        try {
            int count = 0;
            String line = "";
            fileReader = new BufferedReader(new FileReader(filename));
            String[] stats = null;

            while ((line = fileReader.readLine()) != null) {
                stats = line.split(",");
                returns.add(stats);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return returns;
    }
}
