/**
 * Created by alimousa on 5/4/16.
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
    private Player[] player;
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
    private String pointsPerGame;


    public Team(String link) {
        ArrayList<String> stats = getStats(link);
        //@TODO set values for all the instance vars given
        //@TODO the getStats method works (start at index0 and go until done)
    }

    private ArrayList<String> getStats(String link) {
        ArrayList<String> stats = new ArrayList<String>();
        try {
            Document d = Jsoup.connect(link).get();
            Elements elements = d.select("tr[class$=stat_average no_ranker]").select("td");
            for (Element e : elements) {
                String s = Jsoup.parse(e.toString()).text();
                stats.add(s);
            }
        } catch (IOException i) {
            System.out.println(i.getMessage());
        }

        return stats;
    }

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

        return links;
    }
}
