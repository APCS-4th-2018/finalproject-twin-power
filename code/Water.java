/**
 * A Water class that implements the Item interface
 *
 * @author  Julia Du
 * @version 06/01/19
 */
public class Water implements Item
{
    private String name;
    private int amount;
    /**
     * Constructor for the Water class
     * @param str   the name of the Water
     */
    public Water(String str)
    {
        name = str;
        amount = 0;
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
        if(amount > 0)
        {
            player.changeThirst(4);
            amount--;
        }
    }
    
    /**
     * 
     */
    public int getAmount()
    {
        return amount;
    }
    
    public void changeAmount(int liters)
    {
        amount += liters;
        if(amount > 5)
        {
            amount = 5;
        }
    }
    
    /**
     * Returns the name of the Water
     * @return name
     */
    public String toString(){return name + " (" + amount + " L)";}
}