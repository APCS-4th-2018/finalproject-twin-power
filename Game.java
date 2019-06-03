import chn.util.*;
import java.util.*;
import apcslib.*;
/**
 * Representation of a single game
 *
 * @author Julia Du
 * @author
 * @version 06/01/19
 */
import java.util.*;
import java.lang.Math;
import chn.util.*;
public class Game
{
    private int day, time;
    private double distance;
    private final int WIN_DISTANCE = 100;
    private Player player;
    private ArrayList<Item> inventory;
    private ArrayList<Animal> animal;
    
    public Game(String name)
    {
        player = new Player(name);
        inventory = new ArrayList<Item>();
        inventory.add(new Water("canteen of water"));
        day = 0;
        time = 0;
        distance = 0;
        
        //construct and array of different animals for random generation each turn        
        animal = new ArrayList <Animal>();
        animal.add(new MountainLion());
        animal.add(new RattleSnake());
        animal.add(new GrizzlyBear());
        animal.add(new BlackBear());
    }
    
    /**
     * When Player chooses to continue traveling
     * @param hours     the number of hours the Player will travel for
     */
    public void choiceTravel(int hours)
    {
        //move time forward, change distance, change hunger/thirst, chance event
        timeForward(hours);
        distance += hours * getSpeed();
        player.changeHunger(-(double)hours/5);
        player.changeThirst(-(double)hours/2);
        player.changeHealth(-(double)hours/10);
        //chanceEvent();
    }
    
    //returns the speed of the player, which depends on their health and inventory
    private double getSpeed()
    {
        double speed = ((double)player.getHealth() / 5) - (((Water)inventory.get(0)).getAmount()) * 0.1;
        if(speed <= 0.1)
            return 0.1;
        else
            return speed;
    }
    
    //determines a chance event when continuing
    public Animal event()
    {
        int random = (int)(Math.random()*20);
        if(random == 1) //event occurs
        {
            random = (int)(Math.random()*animal.size());
            return animal.get(random);
        }
        return null; //no event
    }
    
    public List getAnimals() { return animal;}
    
    public boolean correctChoice(int choice, Animal a) { return a.equals(animal.get(choice));}
    
    public void killPlayer() { player.changeHealth(-8);}
    
    /**
     * When Player chooses to rest
     * @param hours     the number of hours the Player will rest for
     */
    public void choiceRest(int hours)
    {
        //move the time forward, regen health
        timeForward(hours);
        player.changeHealth(player.getHunger() * hours / 2);
        player.changeHunger(-(double)hours/20);
        player.changeThirst(-(double)hours/10);
    }
    
    /**
     * When Player chooses to forage for supplies
     * @param hours     the number of hours the Player will forage for
     */
    public void choiceForage(int hours)
    {
        //move the time forward, change hunger/thirst, add items
        timeForward(hours);
        player.changeHunger(-(double)hours/10);
        player.changeThirst(-(double)hours/5);
        addRandomItems(hours);
    }
    //determines which items are found while foraging
    private void addRandomItems(int hours)
    {
        //for every hour
        for(int h = 0; h < hours; h++)
        {
            int numItems = (int) (3 * Math.random());
            //the number of items found is by chance
            for(int n = 0; n < numItems; n++)
            {
                double random = Math.random();
                //either find food or water
                if(random < 0.5)
                    inventory.add(addRandomFood());
                else
                    ((Water)inventory.get(0)).changeAmount(1);
            }
        }
    }
    //determines the name of the food found
    private Food addRandomFood()
    {
        String[] names = {"apple", "berries", "acorns", "pine nuts", "grasshopper", "lizard", "mushroom"};
        String chosen = names[(int)(Math.random() * names.length)];
        return new Food(chosen);
    }
    
    /**
     * Uses a specific Item on the Player
     * @param index     the index of the Item in inventory
     */
    public void useItem(int index)
    {
        if(index >= 0)
        {
            inventory.get(index).useItem(player);
            if(index != 0)
                inventory.remove(index);
        }
    }
    
    /**
     * Returns the current inventory of the Player
     * @return inventory    an ArrayList of Items
     */
    public List inventoryList()
    {
        return inventory;
    }
    
    //moves the time forward
    private void timeForward(int hours)
    {
        time += hours;
        if(time >= 24)
        {
            time = time % 24;
            day++;
        }
    }
    
    private void forceUpdate()
    {
        Water temp = (Water)inventory.get(0);
        inventory.remove(0);
        inventory.add(0, temp);
    }
    /**
     * Returns the current distance traveled by the Player
     * @return distance
     */
    public double getDistance() { return distance;}
    /**
     * Returns the current day in the Game
     * @return day
     */
    public int getDay() { return day;}
    /**
     * Returns the current time in the Game
     * @return time
     */
    public int getTime() { return time;}

    public Player getPlayer() { return player;}
    
    /**
     * Determines whether or not the game has ended (if the Player died or reached the destination)
     * @return end  a boolean representing whether or not the game has ended
     */
    public boolean endGame()
    {
        boolean end; 
        if(!player.isAlive() || distance >= WIN_DISTANCE)
            end = true;
        else
            end = false;
        return end;
    }

    /**
     * Returns the distance needed to win the game.
     */
    public int getWinDistance()
    {
        return WIN_DISTANCE;
    }
}
