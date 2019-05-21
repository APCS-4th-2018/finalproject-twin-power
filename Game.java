/**
 * Representation of a single game
 *
 * @author Julia Du
 * @version 05/21/19
 */
public class Game
{
    private int days, distance;
    private final int WIN_DISTANCE = 1000;
    private Player player;

    public Game(String name)
    {
        player = new Player(name);
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
}
