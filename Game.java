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
public class Game
{
<<<<<<< HEAD
    ConsoleIO keyboard = new ConsoleIO();
    private int days, distance;
    private final int WIN_DISTANCE = 1000;
    private Player player;
    private ArrayList <Animal> animal = new ArrayList <Animal>();
   
    
    public Game(String name)
    {
        player = new Player(name);
        
        //construct and array of different animals for random generation each turn
        GrizzlyBear gb = new GrizzlyBear();
        RattleSnake rs = new RattleSnake();
        MountainLion ml = new MountainLion();
        animal.add(gb);
        animal.add(rs);
        animal.add(ml);
        
        System.out.println("Welcome " + player.getName());
=======
    private int day, distance, time;
    private final int WIN_DISTANCE = 100;
    private Player player;
    private ArrayList<Item> inventory;
    private ConsoleIO keyboard;
    private ArrayList <Animal> animal = new ArrayList <Animal>();

    public Game(String name)
    {
        player = new Player(name);
        inventory = new ArrayList<Item>();
        keyboard = new ConsoleIO();
        day = time = distance = 0;
        //construct and array of different animals for random generation each turn
        BlackBear bb = new BlackBear();
        RattleSnake rs = new RattleSnake();
        MountainLion ml = new MountainLion();
        animal.add(bb);
        animal.add(rs);
        animal.add(ml);
        
        System.out.println("Welcome, " + player.getName());
        //explain the game here
>>>>>>> Julia
        while(player.isAlive() && distance < WIN_DISTANCE)
        {
            turn();
        }
        endGame();
    }
    
    //represents one turn
    private void turn()
    {
<<<<<<< HEAD
=======
        int choice;
        System.out.println();
        printStatus();
        System.out.println("(1) Travel");
        System.out.println("(2) Rest");
        System.out.println("(3) Forage for supplies");
        System.out.println("(4) View and use items in the inventory");
        System.out.print(">> ");
        choice = keyboard.readInt();
        while(choice > 4 || choice < 1)
        {
            System.out.println("Invalid choice");
            System.out.print(">> ");
            choice = keyboard.readInt();
        }
        System.out.println();
        switch(choice)
        {
            case 1 :
                choiceTravel();
                break;
            case 2 :
                choiceRest();
                break;
            case 3 :
                choiceForage();
                break;
            case 4 :
                choiceInventory();
                break;  
        }
    }
    
    //choice of traveling
    private void choiceTravel()
    {
        int hours;
        //ask how many hours
        System.out.println("How many hours will you travel for?");
        System.out.print(">> ");
        hours = keyboard.readInt();
        //reprompt if not valid number
        while(hours < 0)
        {
            System.out.println("Invalid choice");
            System.out.print(">> ");
            hours = keyboard.readInt();
        }
        //move time forward, change distance, change hunger/thirst, chance event
        timeForward(hours);
        distance += hours * player.getSpeed();
        player.changeHunger(-(double)hours/4);
        player.changeThirst(-(double)hours/2);
        chanceEvent();
    }
    
    //choice of resting
    private void choiceRest()
    {
        int hours;
        //ask how many hours
        System.out.println("How many hours will you rest for?");
        System.out.print(">> ");
        hours = keyboard.readInt();
        //reprompt if not valid number
        while(hours < 0)
        {
            System.out.println("Invalid choice");
            System.out.print(">> ");
            hours = keyboard.readInt();
        }
        //move the time forward, regen health
        timeForward(hours);
        player.changeHealth(player.getHunger() * hours);
    }
    
    //choice of foraging
    private void choiceForage()
    {
        int hours;
        //ask how many hours
        System.out.println("How many hours will you forage for?");
        System.out.print(">> ");
        hours = keyboard.readInt();
        //reprompt if not valid number
        while(hours < 0)
        {
            System.out.println("Invalid choice");
            System.out.print(">> ");
            hours = keyboard.readInt();
        }
        //move the time forward, change hunger/thirst, add items
        timeForward(hours);
        player.changeHunger(-(double)hours/10);
        player.changeThirst(-(double)hours/8);
        addItems(hours);
    }
    
    //choice of viewing/using inventory
    private void choiceInventory()
    {
        int choice = 1;
        //while the player wants to continue using items
        while(choice != 0)
        {
            //print the inventory
            printInventory();
            //ask which item will be used
            System.out.println("\nWhich item would you like to use? (Type 0 to exit)");
            System.out.print(">> ");
            choice = keyboard.readInt();
            //reprompt user if not valid number
            while(choice < 0 || choice > inventory.size())
            {
                System.out.println("Invalid choice");
                System.out.print(">> ");
                choice = keyboard.readInt();
            }
            //use the item
            if(choice != 0)
                useItem(choice-1);
        }
    }
    
    //prints the inventory
    private void printInventory()
    {
        System.out.println("\nInventory");
        for(int count = 0; count < inventory.size(); count++)
        {
            System.out.println("(" + (count + 1) + ")" + inventory.get(count).getName());
        }
    }
    
    //determines a chance event when continuing
    private void chanceEvent()
    {
>>>>>>> Julia
        int num = (int)(Math.random()*11);
        if(num <= 5)
        {
            String mcA = "";
            String mcB = "";
            String mcC = "";
            String mcD = "";
            
            int num1 = (int)(Math.random()*10);
            
            
            if(num1 < 3)
            {
                String d = animal.get(0).getDescript();//blackbear
                //multiple choice questionnaire
                System.out.println(mcA + "\n" + mcB + "\n" +  mcC + "\n" + mcD);
                String str = keyboard.readLine();//input answer choice
                
                if (str.equals("A") || str.equals("a"))
                {
                    if (animal.get(0).compareTo(mcA))
                    {
                        }
                    }
            }
        }
<<<<<<< HEAD
        //print out the choices possible
        //call other methods depending on the choice
=======
    }
    
    //uses a specific item at an index
    private void useItem(int index)
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
                System.out.println("You found a(n) " + item.getName());
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
    
    //prints the end game message
    private void endGame()
    {
        //prints out the correct message
        if(player.isAlive())
        {
            System.out.println("\n[WIN MESSAGE]");
            System.out.println("You traveled " + WIN_DISTANCE + " kilometers in " + day + " days and " + time + "hours");
        }
        else
        {
            System.out.println("\n[LOST MESSAGE]");
            System.out.println("You had " + (WIN_DISTANCE - distance) + " kilometers left");
        }
>>>>>>> Julia
    }
}
