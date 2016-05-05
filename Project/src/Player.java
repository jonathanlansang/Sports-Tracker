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

    Player(String link) {
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
}

