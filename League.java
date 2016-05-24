import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by alimousa on 5/14/16.
 */
public class League {
    private static final String teamFolder = System.getProperty("user.dir") + System.getProperty("file.separator") +
            System.getProperty("file.separator") + "Teams" +
            System.getProperty("file.separator");
    private Team[] teams;
    private ArrayList<String> teamFiles;


    public League() {
        ArrayList<String> teamList = getTeamLinks();
        teamFiles = new ArrayList<String>();
        teams = new Team[teamList.size()];

        for (int i = 0; i < teams.length; i++) {
            teams[i] = new Team(teamList.get(i));
            saveData(teams[i]);
        }

    }

    /**
     * Loads data locally into League object if parameter passed is "local"
     *
     * @param s
     */
    public League(String s) {
        getTeamFiles();
        if (s.equals("local")) {
            loadFromData();
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
        System.out.println("Getting Team Links");
        return links;
    }

    /**
     * Populates InstanceVar teamFiles with a list of CSV files that hold team stats
     */
    private void getTeamFiles() {
        File f = new File(teamFolder);
        teamFiles = new ArrayList<String>(Arrays.asList(f.list()));
    }

    /**
     * Returns array of all Teams
     *
     * @return array of all Teams
     */
    public Team[] getTeams() {
        return teams;
    }

    /**
     * Records all the data retrieved from the internet that was stored in team objects
     * Also saves all filenames for the teams to the list "teamFiles"
     */
    public void saveData(Team t) {
        CSV csv = new CSV();
        System.out.println("Saving Team Data!");
        teamFiles.add(csv.write(t));
    }

    /**
     * Build team array with team objects using the teamFiles (CSV's)
     */
    public void loadFromData() {
        System.out.println("Loading Team Data!");
        teams = new Team[teamFiles.size()];
        CSV csv = new CSV();
        for (int i = 0; i < teamFiles.size(); i++)
            teams[i] = new Team(csv.read(teamFolder + teamFiles.get(i)));
    }

    /**
     * Print stats for every team
     */
    public void printStats() {
        for (Team t : teams)
            System.out.println(t);
    }
}
