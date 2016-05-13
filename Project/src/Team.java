/**
 * Created by alimousa on 5/4/16.
 */
/**
 * Created by jonathanlansang on 5/13/16.
 */
import java.util.*;

public class Team {

    private String name;
    private Player[] player;
    private String location;
    private double wins;
    private int loss;

    public Team(//ali wants to change format of constructor){

        name = teamName;
        player = myPlayers;
        location = teamLocation;
        wins = myWins;
        loss = myLosses;
    }
    public String getName(){
        return name;
    }
    public getPlayer(String playerName){

    }
    public String printPlayerName(ArrayList<Player> name){
        String playerNames= "";
        for(int i=0; i<name.size(); i++)
        {
            playerNames += name.//add the getName method in player class
        }

    }
    public String getLocation(){
        return location;
    }
    public String getRecord(){
        return wins + "-" + loss;
    }
    public String toString(){
        location + " " + name +"/nRecord: " + getRecord() +
    }
}
