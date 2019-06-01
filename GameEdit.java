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
public class GameEdit
{
    ConsoleIO keyboard = new ConsoleIO();
    private int day, time;
    private double distance;
    private final int WIN_DISTANCE = 100;
    private Player player;
    private ArrayList<Item> inventory;
    private ArrayList<Animal> animal;
    private GrizzlyBear gb = new GrizzlyBear();
    private RattleSnake rs = new RattleSnake();
    private MountainLion ml = new MountainLion();
    
    public GameEdit(String name)
    {
        player = new Player(name);
        inventory = new ArrayList<Item>();
        inventory.add(new Water("canteen of water"));
        keyboard = new ConsoleIO();
        day = 1;
        time = 0;
        distance = 0;
        
        //construct and array of different animals for random generation each turn        
        animal = new ArrayList <Animal>();
        animal.add(gb);
        animal.add(rs);
        animal.add(ml);
    }
    
    /**
     * When Player chooses to continue traveling
     * @param hours     the number of hours the Player will travel for
     */
    public void choiceTravel(int hours)
    {
        //move time forward, change distance, change hunger/thirst, chance event
        timeForward(hours);
        distance += hours * player.getSpeed();
        player.changeHunger(-(double)hours/5);
        player.changeThirst(-(double)hours/2);
        player.changeHealth(-(double)hours/10);
        //chanceEvent();
    }
    //returns the speed of the player, which depends on their health and inventory
    private double getSpeed()
    {
        return (player.getHealth()/2) / (inventory.size() * ((Water)inventory.get(0)).getAmount() + 1);
    }
    //determines a chance event when continuing
    private void chanceEvent()
    {
        int num = (int)(Math.random()*11);
        if(num <= 5)
        {
            String mcA = "A. " + gb.getMC();//grizzlybear solution
            String mcB = "B. " + rs.getMC();//rattlesnake solution
            String mcC = "C. " + ml.getMC();//mountain lion solution
            String mcD = "D. " + "Run away as fast as you can";
            
            int num1 = (int)(Math.random()*10);
            
            if(num1 < 3)//Grizzly Bear Attack

            {
                String d = animal.get(0).getDescript();//grizzlybear
                System.out.println(d);
                //multiple choice questionnaire
                System.out.println(mcA + "\n" + mcB + "\n" +  mcC + "\n" + mcD);
                String str = keyboard.readLine();//input answer choice
                
                if (!str.equals("A") && !str.equals("a"))//incorrect answer choice
                {
                    player.changeHealth(-10.0);//player dies
                    System.out.println("Incorrect, you got attacked by a Grizzly Bear and got killed: " +gb.getSolution());
                }
                    else
                    {
                        System.out.print("Correct!" + gb.getSolution());
                    }
        
            }
            else
                if(num1 >=3 && num1 < 6)//rattlesnake
                {
                    String d = animal.get(1).getDescript();//rattlesnake
                    System.out.println(d);
                    //multiple choice questionnaire
                    System.out.println(mcA + "\n" + mcB + "\n" +  mcC + "\n" + mcD);
                    String str = keyboard.readLine();//input answer choice
                
                    if (!str.equals("B") && !str.equals("b"))//incorrect answer choice
                    {
                        player.changeHealth(-10.0);//player dies
                        System.out.println("Incorrect, you got bit by a Rattlesnake and got killed: " + rs.getSolution());
                    }
                        else
                        {
                            System.out.print("Correct!" + rs.getSolution());
                        }
                }
                else
                    if(num1 >= 6)//mountainlion
                    {
                        String d = animal.get(2).getDescript();//mountainlion
                        System.out.println(d);
                        //multiple choice questionnaire
                        System.out.println(mcA + "\n" + mcB + "\n" +  mcC + "\n" + mcD);
                        String str = keyboard.readLine();//input answer choice
                
                            if (!str.equals("C") && !str.equals("c"))//incorrect answer choice
                            {
                                player.changeHealth(-10.0);//player dies
                                System.out.println("Incorrect, you got attacked by a Mountain Lion and got killed: " + ml.getSolution());
                            }
                                else
                                {
                                    System.out.print("Correct!" + ml.getSolution());
                                }
                    }
        }
    }
    
    /**
     * When Player chooses to rest
     * @param hours     the number of hours the Player will rest for
     */
    public void choiceRest(int hours)
    {
        //move the time forward, regen health
        timeForward(hours);
        player.changeHealth(player.getHunger() * hours);
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
                {
                    ((Water)inventory.get(0)).changeAmount(1);
                }
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
        inventory.get(index).useItem(player);
        if(index != 0)
        {
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
    /**
     * Returns the current health of the Player
     */
    public double getHealth() { return player.getHealth();}
    /**
     * Returns the current hunger level of the Player
     */
    public double getHunger() { return player.getHunger();}
    /**
     * Returns the current thirst level of the Player
     */
    public double getThirst() { return player.getThirst();}
    
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
     * Returns a String representation of the ending game message
     * Displays the distance traveled and time passed if won
     * Displays the distance traveled if lost
     */
    public String endingMessage()
    {
        if(player.isAlive())
            return "YOU WON\n" + "You traveled " + WIN_DISTANCE + " km in " +
                      day + " days and " + time + " hours";
        else
            return "YOU DIED\n" + "You traveled " + distance + " km";
    }
}
