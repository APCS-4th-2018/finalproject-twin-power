/**
 * A Food class that implements the Item interface
 *
 * @author  Julia Du
 * @version 06/01/19
 */
public class Food implements Item
{
    private String name;
    
    /**
     * Constructor for the Food class
     * @param str   the name of the Food
     */
    public Food(String str)
    {
        name = str;
    }
    
    /**
     * Returns the name of the Food
     * @return name
     */
    public String getName(){return name;}
    
    /**
     * Uses the Food on a specified Player
     * @param player    the Player object the Food will be used on
     */
    public void useItem(Player player)
    {
        player.changeHunger(2);
    }
    
    /**
     * Returns the name of the Food
     * @return name
     */
    public String toString(){return name;}
}
