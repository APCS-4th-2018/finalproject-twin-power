/**
 * Write a description of class Game here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Game
{
    private int days, distance;
    private final int WIN_DISTANCE = 1000;
    private Player player;

    public Game(String name)
    {
        player = new Player(name);
        while(player.isAlive() && distance >= WIN_DISTANCE)
        {
            turn();
        }
    }
    
    
    private void turn()
    {
    }
}
