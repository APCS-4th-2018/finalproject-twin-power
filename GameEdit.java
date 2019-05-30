import chn.util.*;
import java.util.*;
import apcslib.*;
/**
 * Representation of a single game
 *
 * @author Julia Du
 * @version 05/21/19
 */
import java.util.*;
import java.lang.Math;
import chn.util.*;
public class GameEdit
{
    ConsoleIO keyboard = new ConsoleIO();
    private int day, distance, time;
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
        keyboard = new ConsoleIO();
        day = 1;
        time = 0;
        distance = 0;
        
        //construct and array of different animals for random generation each turn        
        animal = new ArrayList <Animal>();
        animal.add(gb);
        animal.add(rs);
        animal.add(ml);
        
        System.out.println("Welcome, " + player.getName());
        //while(player.isAlive() && distance < WIN_DISTANCE)
        {
            //turn();
        }
        endGame();
    }
    
    //choice of traveling
    public void choiceTravel(int hours)
    {
        //move time forward, change distance, change hunger/thirst, chance event
        timeForward(hours);
        distance += hours * player.getSpeed();
        player.changeHunger(-(double)hours/4);
        player.changeThirst(-(double)hours/2);
        //chanceEvent();
    }
    
    //choice of resting
    public void choiceRest(int hours)
    {
        //move the time forward, regen health
        timeForward(hours);
        player.changeHealth(player.getHunger() * hours);
    }
    
    //choice of foraging
    public void choiceForage(int hours)
    {
        //move the time forward, change hunger/thirst, add items
        timeForward(hours);
        player.changeHunger(-(double)hours/10);
        player.changeThirst(-(double)hours/8);
        addItems(hours);
    }
    
    //prints the inventory
    public String printInventory()
    {
        String text = "Inventory";
        for(int count = 0; count < inventory.size(); count++)
        {
            text += "\n(" + (count + 1) + ") " + inventory.get(count).getName();
        }
        return text;
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
    
    //uses a specific item at an index
    public void useItem(int index)
    {
        inventory.get(index).useItem(player);
        inventory.remove(index);
    }
    
    //determines which items are found while foraging
    private void addItems(int hours)
    {
        //for every hour
        for(int h = 0; h < hours; h++)
        {
            int numItems = (int) (3 * Math.random());
            //the number of items found is by chance
            for(int n = 0; n < numItems; n++)
            {
                Item item;
                double random = Math.random();
                //either find food or water
                if(random < 0.5)
                    item = new Food("apple");
                else
                    item = new Water("bottle of water");
                inventory.add(item);
            }
        }
    }
    
    //moves the time foward
    private void timeForward(int hours)
    {
        time += hours;
        if(time >= 24)
        {
            time = time % 24;
            day++;
        }
    }
    
    //prints the current day, time, distance, and player status
    private void printStatus()
    {
        System.out.println("Day " + day);
        System.out.println("Time: " + time + ":00");
        System.out.println(WIN_DISTANCE - distance + " kilometers left");
        System.out.println(player);
        System.out.println();
    }
    
    public int getDistance() { return distance;}
    public int getDay() { return day;}
    public int getTime() { return time;}
    public double getHealth() { return player.getHealth();}
    public double getHunger() { return player.getHunger();}
    public double getThirst() { return player.getThirst();}
    
    //returns whether or not game has ended or not
    public boolean endGame()
    {
        if(!player.isAlive() || distance >= WIN_DISTANCE)
            return true;
        else
            return false;
    }
    
    public String endingMessage()
    {
        if(player.isAlive())
            return "YOU WON\n" + "You traveled " + WIN_DISTANCE + " in " +
                      day + " days and " + time + " hours";
        else
            return "YOU DIED\n" + "You traveled " + distance;
    }
}
