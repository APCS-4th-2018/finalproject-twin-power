/**
 * A Water class that implements the Item interface
 *
 * @author  Julia Du
 * @version 06/01/19
 */
public class Water implements Item
{
    private String name;
    
    /**
     * Constructor for the Water class
     * @param str   the name of the Water
     */
    public Water(String str)
    {
        name = str;
    }
    
    /**
     * Returns the name of the Water
     * @return name
     */
    public String getName(){return name;}
    
    /**
     * Uses the Water on a specified Player
     * @param player    the Player object the Water will be used on
     */
    public void useItem(Player player)
    {
        player.changeThirst(1.5);
    }
    
    /**
     * Returns the name of the Water
     * @return name
     */
    public String toString(){return name;}
}