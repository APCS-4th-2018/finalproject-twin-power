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

    public Game(String name)
    {
        player = new Player(name);
        day = time = distance = 0;
        System.out.println("Welcome " + player.getName());
        while(player.isAlive() && distance < WIN_DISTANCE)
        {
            turn();
        }
    }
    
    private void turn()
    {
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
    private void endGame()
    {
    }
}
