/**
 * Representation of the Player
 *
 * @author Julia Du
 * @version 05/21/19
 */
public class Player
{
    private int health, hunger, thirst;
    private String name;
    
    /**
     * Constructor to create a new Player object with a specific name
     * 
     * @param str   the name of the player
     */
    public Player(String str)
    {
        name = str;
        health = hunger = thirst = 10;
    }
    
    /**
     * Returns the health level of the player
     * @return health   an int ranging from 1-10
     */
    public int getHealth(){return health;}
    /**
     * Returns the hunger level of the player
     * @return hunger   an int ranging from 1-10
     */
    public int getHunger(){return hunger;}
    /**
     * Returns the thirst level of the player
     * @return thirst   an int ranging from 1-10
     */
    public int getThirst(){return thirst;}
    public int getSpeed() {return health/2;}
    /**
     * Returns the name of the player
     * @return name     the name of the player
     */
    public String getName(){return name;}
    
    /**
     * Changes the player's health by a specific amount
     * @param amount    the amount to be changed by
     */
    public void changeHealth(int amount)
    {
        health -= amount;
        if(health < 0)
            health = 0;
    }
    /**
     * Changes the player's hunger level by a specific amount
     * If the hunger level is lowered to under zero, health is lowered instead
     * @param amount    the amount to be changed by
     */
    public void changeHunger(int amount)
    {
        hunger -= amount;
        if(hunger < 0)
        {
            health -= 1;
            hunger = 0;
        }
    }
    /**
     * Changes the player's thirst level by a specific amount
     * If the thirst level is lowered to under zero, health is lowered instead
     * @param amount    the amount to be changed by
     */
    public void changeThirst(int amount)
    {
        thirst -= amount;
        if(thirst < 0)
        {
            health -= 2;
            thirst = 0;
        }
    }
    
    /**
     * Determines whether or not the player is still alive
     * @return  true if s/he is alive, false if not
     */
    public boolean isAlive() {return health > 0;}
    
    /**
     * Returns a string that has the name, health, hunger, and thirst of the Player
     */
    public String toString()
    {
        return name + "\nHealth: " + health + "\nHunger: " + hunger + "\nThirst: " + thirst;
    }
}
