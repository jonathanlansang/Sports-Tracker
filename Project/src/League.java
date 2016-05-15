import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by alimousa on 5/14/16.
 */
public class League {

    private Player[] players;
    private Team[] teams;


    public League() {
        ArrayList<String> teamList = getTeamLinks();
        int numPlayers = 0;
        teams = new Team[teamList.size()];

        for (int i = 0; i < players.length; i++) {
            teams[i] = new Team(teamList.get(i));
            numPlayers += teams[i].getPlayers().length;
        }

        players = new Player[numPlayers];

        for (int i = 0; i < teams.length; i++) {
            for (int j = 0; j < teams[i].getPlayers().length; j++)
                players[i + j] = players[j];
        }

    }

    /**
     * Returns an ArrayList<String> with links to all the Team pages on Basketball Reference
     *
     * @return ArrayList<String> with links to all the Team pages on Basketball Reference
     */
    public ArrayList<String> getTeamLinks() {
        ArrayList<String> links = new ArrayList<String>();
        try {
            Document d = Jsoup.connect("http://www.basketball-reference.com/leagues/NBA_2016.html").get();
            Elements elements = d.select("table[id$=team]").select("a");
            for (Element e : elements) {
                if (e.attr("abs:href").indexOf("http://www.basketball-reference.com/teams/") == 0)
                    links.add(e.attr("abs:href"));
            }
        } catch (IOException i) {
            System.out.println(i);
        }
        return links;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Team[] getTeams() {
        return teams;
    }

}
