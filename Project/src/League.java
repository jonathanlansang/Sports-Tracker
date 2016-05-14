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
    Player[] players;
    Team[] teams;

    League() {

    }

    public ArrayList<String> getTeams() {
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
}
