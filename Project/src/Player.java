import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by alimousa on 5/4/16.
 */
public class Player {
    private String position;
    private String games;
    private String gamesStarted;
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

    public Player(String link) {
        ArrayList<String> stats = getStats(link);

        position = stats.get(1);
        games = stats.get(5);
        gamesStarted = stats.get(6);
        minutesPlayed = stats.get(7);
        fieldGoals = stats.get(8);
        fieldGoalAttempts = stats.get(9);
        fieldGoalPercentage = stats.get(10);
        threePointers = stats.get(11);
        threePointersAttempted = stats.get(12);
        threePointPercentage = stats.get(13);
        twoPointers = stats.get(14);
        twoPointersAttempted = stats.get(15);
        twoPointPercentage = stats.get(16);
        effectiveFieldGoalPercentage = stats.get(17);
        freeThrows = stats.get(18);
        freeThrowsAttempted = stats.get(19);
        freeThrowPercentage = stats.get(20);
        offensiveRebounds = stats.get(21);
        defensiveRebounds = stats.get(22);
        totalRebounds = stats.get(23);
        assists = stats.get(24);
        steals = stats.get(25);
        blocks = stats.get(26);
        turnovers = stats.get(27);
        personalFouls = stats.get(28);
        points = stats.get(29);
    }

    /**
     * Grabs stats from the player's link on Basketball Reference
     *
     * @param link A link to the player's page on Basketball Reference
     * @return an ArrayList<String> With many stats about the player
     */
    public ArrayList<String> getStats(String link) {
        ArrayList<String> stats = new ArrayList<String>();
        Document d;
        try {
            d = Jsoup.connect(link).get();
            Elements elements = d.select("tr[id$=totals.2016]");
            for (Element e : elements.select("td")) {
                for (Element f : e.select("td")) {
                    String s = Jsoup.parse(f.toString()).text();
                    stats.add(s);
                }
            }
        } catch (IOException i) {
            System.out.println(i);
        }

        return stats;
    }

    public String getPosition() {
        return position;
    }

    public String getGames() {
        return games;
    }

    public String getGamesStarted() {
        return gamesStarted;
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

}

