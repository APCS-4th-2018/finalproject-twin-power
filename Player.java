import apcslib.*;
import chn.util.*;
/**
 * Representation of the Player
 *
 * @author Julia Du
 * @version 05/21/19
 */
public class Player
{
    private double health, hunger, thirst;
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
     * @return health   a double ranging from 0-10
     */
    public double getHealth(){return health;}
    /**
     * Returns the hunger level of the player
     * @return hunger   a double ranging from 0-10
     */
    public double getHunger(){return hunger;}
    /**
     * Returns the thirst level of the player
     * @return thirst   a double ranging from 0-10
     */
    public double getThirst(){return thirst;}
    /**
     * Returns the speed of the player, in km/hr, depending on the current health
     */
    public double getSpeed() {return health/5;}
    /**
     * Returns the name of the player
     * @return name     the name of the player
     */
    public String getName(){return name;}
    
    /**
     * Changes the player's health by a specific amount
     * @param amount    the amount to be changed by
     */
    public void changeHealth(double amount)
    {
        health += amount;
        if(health < 0)
            health = 0;
        if(health > 10)
            health = 10;
    }
    /**
     * Changes the player's hunger level by a specific amount
     * If the hunger level is lowered to under zero, health is lowered instead
     * @param amount    the amount to be changed by
     */
    public void changeHunger(double amount)
    {
        hunger += amount;
        if(hunger < 0)
        {
            health += hunger/2;
            hunger = 0;
        }
        if(hunger > 10)
            hunger = 10;
    }
    /**
     * Changes the player's thirst level by a specific amount
     * If the thirst level is lowered to under zero, health is lowered instead
     * @param amount    the amount to be changed by
     */
    public void changeThirst(double amount)
    {
        thirst += amount;
        if(thirst < 0)
        {
            health += thirst;
            thirst = 0;
        }
        if(thirst > 10)
            thirst = 10;
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
        return "Health: " + Format.left(health, 4, 2) + "\nHunger: " + Format.left(hunger, 4, 2) + "\nThirst: " + Format.left(thirst, 4, 2);
    }
}
