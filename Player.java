/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    private int health, hunger, thirst;
    private String name;
    
    public Player(String str)
    {
        name = str;
        health = hunger = thirst = 10;
    }
    
    public int getHealth(){return health;}
    public int getHunger(){return hunger;}
    public int getThirst(){return thirst;}
    
    public void changeHealth(int amount)
    {
        health -= amount;
        if(health < 0)
            health = 0;
    }
    public void changeHunger(int amount)
    {
        hunger -= amount;
        if(hunger < 0)
        {
            health -= 1;
            hunger = 0;
        }
    }
    public void changeThirst(int amount)
    {
        thirst -= amount;
        if(thirst < 0)
        {
            health -= 2;
            thirst = 0;
        }
    }
    
    public boolean isAlive() {return health > 0;}
    
    public String toString()
    {
        return name + "\nHealth: " + health + "\nHunger: " + hunger + "\nThirst: " + thirst;
    }
}
