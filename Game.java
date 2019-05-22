import chn.util.*;
import java.util.*;
/**
 * Representation of a single game
 *
 * @author Julia Du
 * @version 05/21/19
 */
public class Game
{
    private int day, distance, time;
    private final int WIN_DISTANCE = 1000;
    private Player player;
    private ArrayList inventory;

    public Game(String name)
    {
        player = new Player(name);
        inventory = new ArrayList();
        day = time = distance = 0;
        System.out.println("Welcome " + player.getName());
        while(player.isAlive() && distance < WIN_DISTANCE)
        {
            turn();
        }
    }
    
    private void turn()
    {
        printStatus();
        //print out the choices possible
        //call other methods depending on the choice
    }
    
    private void choiceGo()
    {
    }
    private void choiceRest()
    {
    }
    private void choiceForage()
    {
    }
    
    private void timeForward(int hours)
    {
        time += hours;
        if(time >= 24)
        {
            time = time % 24;
            day++;
        }
    }
    private void printStatus()
    {
        System.out.println("Day " + day);
        System.out.print("Time: " + time + ":00");
    }
    private void endGame()
    {
    }
}
