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

    /**
     * @param link
     * @return
     * @TODO Finish this method
     */
    public ArrayList<String> getStats(String link) {
        ArrayList<String> stats = new ArrayList<String>();
        Document d;
        try {
            d = Jsoup.connect(link).get();
            //@TODO like specifically here, finish this part where you take stats from the html page
            Elements elements = d.select("table[class$=sortable  stats_table]").select("tbody");
            for (Element e : elements.select("tr")) {
                for (Element f : e.select("td")) {
                    String s = Jsoup.parse(f.toString()).text();
                    System.out.println(s);
                    stats.add(s);
                }
            }
        } catch (IOException i) {
            System.out.println(i);
        }

        return stats;
    }

    public String printPlayerName(ArrayList<Player> name) {
        String playerNames = "";
        for (int i = 0; i < name.size(); i++) {
            playerNames += name.//add the getName method in player class
        }

    }

    public String getLocation() {
        return location;
    }

    public String getRecord() {
        return wins + "-" + loss;
    }

    public String toString() {
        location + " " + name + "/nRecord: " + getRecord() +
    }
}x
