/**
 * Created by alimousa on 5/4/16.
 * <p>
 * Created by jonathanlansang on 5/13/16.
 * <p>
 * Created by jonathanlansang on 5/13/16.
 * <p>
 * Created by jonathanlansang on 5/13/16.
 * <p>
 * Created by jonathanlansang on 5/13/16.
 * <p>
 * Created by jonathanlansang on 5/13/16.
 * <p>
 * Created by jonathanlansang on 5/13/16.
 */
/**
 * Created by jonathanlansang on 5/13/16.
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Team {
    private Player[] players;
    private String teamLink;
    private String name;
    private String games;
    private String minutesPlayed;
    private String fieldGoals;
    private String fieldGoalAttempts;
    private String fieldGoalPercentage;
    private String threePointers;
    private String threePointersAttempted;
    private String threePointPercentage;
    private String twoPointers;
    private String twoPointersAttempted;
    private String twoPointPercentage;
    private String effectiveFieldGoalPercentage;
    private String freeThrows;
    private String freeThrowsAttempted;
    private String freeThrowPercentage;
    private String offensiveRebounds;
    private String defensiveRebounds;
    private String totalRebounds;
    private String assists;
    private String steals;
    private String blocks;
    private String turnovers;
    private String personalFouls;
    private String points;


    public Team(String link) {
        teamLink = link;
        ArrayList<String> stats = getStats();
        //ArrayList<String> playersList = getPlayerLinks(link);

        name = stats.get(stats.size() - 1);
        games = stats.get(0);
        minutesPlayed = stats.get(1);
        fieldGoals = stats.get(2);
        fieldGoalAttempts = stats.get(3);
        fieldGoalPercentage = stats.get(4);
        threePointers = stats.get(5);
        threePointersAttempted = stats.get(6);
        threePointPercentage = stats.get(7);
        twoPointers = stats.get(8);
        twoPointersAttempted = stats.get(9);
        twoPointPercentage = stats.get(10);
        effectiveFieldGoalPercentage = stats.get(11);
        freeThrows = stats.get(12);
        freeThrowsAttempted = stats.get(13);
        freeThrowPercentage = stats.get(14);
        offensiveRebounds = stats.get(15);
        defensiveRebounds = stats.get(16);
        totalRebounds = stats.get(17);
        assists = stats.get(18);
        steals = stats.get(19);
        blocks = stats.get(20);
        turnovers = stats.get(21);
        personalFouls = stats.get(22);
        points = stats.get(23);

        ArrayList<String> playersList = getPlayerLinks(link);

        players = new Player[playersList.size()];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(playersList.get(i));
        }
    }

    public Team(ArrayList<String[]> statistics) {
        String[] stats = statistics.get(1);

        name = stats[0];
        games = stats[5];
        minutesPlayed = stats[7];
        fieldGoals = stats[8];
        fieldGoalAttempts = stats[9];
        fieldGoalPercentage = stats[10];
        threePointers = stats[11];
        threePointersAttempted = stats[12];
        threePointPercentage = stats[13];
        twoPointers = stats[14];
        twoPointersAttempted = stats[15];
        twoPointPercentage = stats[16];
        effectiveFieldGoalPercentage = stats[17];
        freeThrows = stats[18];
        freeThrowsAttempted = stats[19];
        freeThrowPercentage = stats[20];
        offensiveRebounds = stats[21];
        defensiveRebounds = stats[22];
        totalRebounds = stats[23];
        assists = stats[24];
        steals = stats[25];
        blocks = stats[26];
        turnovers = stats[27];
        personalFouls = stats[28];
        points = stats[29];

        players = new Player[statistics.size() - 2];
        for (int i = 2; i < statistics.size(); i++) {
            players[i - 2] = new Player(statistics.get(i));
        }
        if (players.length != 0)
            doSort();
    }

    /**
     * Grabs stats from the team's link on Basketball Reference
     * @return an ArrayList<String> With many stats about the team
     */
    public ArrayList<String> getStats() {
        ArrayList<String> stats = new ArrayList<String>();
        try {
            Document d = Jsoup.connect(teamLink).get();
            Elements elements = d.select("tr[class$=stat_average no_ranker]").select("td");
            for (Element e : elements) {
                String s = Jsoup.parse(e.toString()).text();
                if (!s.equals("") && !s.equals("Team Totals"))
                    stats.add(s);
            }
            Elements elements1 = d.select("h1");
            String x = elements1.text();
            x = x.substring(0, x.indexOf(" Roster and Stats"));
            stats.add(x);
        } catch (IOException i) {
            System.out.println(i.getMessage());
        }
        System.out.println("Getting stats for Team");
        return stats;
    }

    /**
     * Returns an ArrayList<String> of links to player's pages on Basketball Reference
     * @param teamLink A link to the team's page on Basketball Reference
     * @return an ArrayList<String> With links to player's pages
     */
    public ArrayList<String> getPlayerLinks(String teamLink) {
        ArrayList<String> links = new ArrayList<String>();
        try {
            Document d = Jsoup.connect(teamLink).get();
            Elements elements = d.select("table[id$=roster]").select("a");
            for (Element e : elements) {
                if (e.attr("abs:href").indexOf("http://www.basketball-reference.com/players/") == 0)
                    links.add(e.attr("abs:href"));
            }
        } catch (IOException i) {
            System.out.println(i.getMessage());
        }
        System.out.println("Getting Player Links for Team: " + name);
        return links;
    }

    public void doSort() {
        quickSort(players, 0, players.length - 1);
    }

    private void quickSort(Player[] list, int first, int last) {
        int g = first, h = last;
        int pivot = (last + first) / 2;
        Player pivotValue = list[pivot];
        do {
            while (list[g].compareTo(pivotValue) < 0)
                g++;
            while (list[h].compareTo(pivotValue) > 0)
                h--;
            if (g <= h) {
                Player temp = list[g];
                list[g] = list[h];
                list[h] = temp;
                g++;
                h--;
            }
        } while (g < h);
        if (h > first)
            quickSort(list, first, h);
        if (g < last)
            quickSort(list, g, last);
    }


    /**
     * Returns an array of Player objects consisting of players on the team
     *
     * @return an array of Player objects consisting of players on the team
     */
    public Player[] getPlayers() {
        return players;
    }

    public String getGames() {
        return games;
    }

    public String getName() {
        return name;
    }

    public String getMinutesPlayed() {
        return minutesPlayed;
    }

    public String getFieldGoals() {
        return fieldGoals;
    }

    public String getFieldGoalAttempts() {
        return fieldGoalAttempts;
    }

    public String getFieldGoalPercentage() {
        return fieldGoalPercentage;
    }

    public String getThreePointers() {
        return threePointers;
    }

    public String getThreePointersAttempted() {
        return threePointersAttempted;
    }

    public String getThreePointPercentage() {
        return threePointPercentage;
    }

    public String getTwoPointers() {
        return twoPointers;
    }

    public String getTwoPointersAttempted() {
        return twoPointersAttempted;
    }

    public String getTwoPointPercentage() {
        return twoPointPercentage;
    }

    public String getEffectiveFieldGoalPercentage() {
        return effectiveFieldGoalPercentage;
    }

    public String getFreeThrows() {
        return freeThrows;
    }

    public String getFreeThrowsAttempted() {
        return freeThrowsAttempted;
    }

    public String getFreeThrowPercentage() {
        return freeThrowPercentage;
    }

    public String getOffensiveRebounds() {
        return offensiveRebounds;
    }

    public String getDefensiveRebounds() {
        return defensiveRebounds;
    }

    public String getTotalRebounds() {
        return totalRebounds;
    }

    public String getAssists() {
        return assists;
    }

    public String getSteals() {
        return steals;
    }

    public String getBlocks() {
        return blocks;
    }

    public String getTurnovers() {
        return turnovers;
    }

    public String getPersonalFouls() {
        return personalFouls;
    }

    public String getPoints() {
        return points;
    }

    public String toString() {
        String rtn = "";
        String[] statTypes = CSV.getHeader().split(",");
        for (String s : statTypes) {
            rtn += s + "\t\t\t\t";
        }
        rtn += "\n";
        for (Player p : players) {
            rtn += p.toString() + "\n";
        }
        return rtn;
    }

}
