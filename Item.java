/**
 * Interface for Items in the Game
 *
 * @author  Julia Du
 * @version 06/01/19
 */
public interface Item
{
    /**
     * Returns the name of the Item
     */
    public String getName();
    
    /**
     * Uses the Item on a specified Player
     * @param player    a Player object the Item will be used on
     */
    public void useItem(Player player);
    
    /**
     * Returns the name of the Item
     */
    public String toString();
}
