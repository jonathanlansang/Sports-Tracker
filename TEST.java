import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathanlansang on 5/23/16.
 */
public class TEST {

    public Team[] loadTeams() {

        League nba = new League();
        Team[] team = nba.getTeams();
        return team;
    }

    public void runner() {

        final List<Team> data = new ArrayList<Team>();

        int i=0;
        for (Team t : loadTeams()) {
            data.add(t);
            System.out.println(data.get(i));
            i++;
        }

    }
    public static void main(String[] args) {
        TEST n = new TEST();
        n.runner();

    }


}


