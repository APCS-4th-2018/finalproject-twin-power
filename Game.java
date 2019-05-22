import chn.util.*;
import java.util.*;
/**
 * Representation of a single game
 *
 * @author Julia Du
 * @version 05/21/19
 */
public class Game
{
    private int day, distance, time;
    private final int WIN_DISTANCE = 100;
    private Player player;
    private ArrayList<Item> inventory;
    private ConsoleIO keyboard;

    public Game(String name)
    {
        player = new Player(name);
        inventory = new ArrayList<Item>();
        keyboard = new ConsoleIO();
        day = time = distance = 0;
        System.out.println("Welcome " + player.getName());
        while(player.isAlive() && distance < WIN_DISTANCE)
        {
            turn();
        }
    }
    
    private void turn()
    {
        int choice;
        printStatus();
        System.out.println("(1) Continue");
        System.out.println("(2) Rest");
        System.out.println("(3) Forage");
        System.out.println("(4) View Inventory");
        System.out.print(">> ");
        choice = keyboard.readInt();
        while(choice > 4 || choice < 1)
        {
            System.out.println("Invalid choice");
            System.out.print(">> ");
            choice = keyboard.readInt();
        }
        System.out.println("\n");
        switch(choice)
        {
            case 1 :
                choiceGo();
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
    
    private void choiceGo()
    {
        int hours;
        System.out.println("How many hours will you travel for?");
        System.out.print(">> ");
        hours = keyboard.readInt();
        while(hours < 0)
        {
            System.out.println("Invalid choice");
            System.out.print(">> ");
            hours = keyboard.readInt();
        }
        timeForward(hours);
        distance += hours * player.getSpeed();
        player.changeHunger(-(double)hours/4);
        player.changeThirst(-(double)hours/2);
        chanceEvent();
    }
    private void choiceRest()
    {
        int hours;
        System.out.println("How many hours will you rest for?");
        System.out.print(">> ");
        hours = keyboard.readInt();
        while(hours < 0)
        {
            System.out.println("Invalid choice");
            System.out.print(">> ");
            hours = keyboard.readInt();
        }
        timeForward(hours);
        player.changeHealth(player.getHunger() * hours);
    }
    private void choiceForage()
    {
        int hours;
        System.out.println("How many hours will you forage for?");
        System.out.print(">> ");
        hours = keyboard.readInt();
        while(hours < 0)
        {
            System.out.println("Invalid choice");
            System.out.print(">> ");
            hours = keyboard.readInt();
        }
        timeForward(hours);
        player.changeHunger(-(double)hours/8);
        player.changeThirst(-(double)hours/4);
        addItems(hours);
        //add random items to inventory, the number of items depending on how long you forage
    }
    private void choiceInventory()
    {
        System.out.println("Inventory");
        for(int count = 0; count < inventory.size(); count++)
        {
            System.out.println("(" + (count + 1) + ")" + inventory.get(count).getName());
        }
        int choice = 1;
        while(choice != 0)
        {
            System.out.println("Use item? (Type 0 to exit)");
            System.out.print(">> ");
            choice = keyboard.readInt();
            while(choice < 0 || choice > inventory.size())
            {
                System.out.println("Invalid choice");
                System.out.print(">> ");
                choice = keyboard.readInt();
            }
            useItem(choice);
        }
    }
    
    private void chanceEvent()
    {
    }
    private void useItem(int index)
    {
        //"use the item"
        inventory.remove(index);
        
    }
    private void addItems(int hours)
    {
        //items you get depend on number of hours and chance
        //50-50 chance of getting food/water
        //you get one
    }
    private void timeForward(int hours)
    {
        time += hours;
        if(time >= 24)
        {
            time = time % 24;
            day++;
        }
    }
    private void printStatus()
    {
        System.out.println("Day " + day);
        System.out.println("Time: " + time + ":00");
        System.out.println(WIN_DISTANCE - distance + " kilometers left");
        System.out.println(player);
        System.out.println();
    }
    private void endGame()
    {
        if(player.isAlive())
        {
            System.out.println("[WIN MESSAGE]");
            System.out.println("You traveled " + WIN_DISTANCE + " in " + day + " days");
        }
        else
        {
            System.out.println("[LOST MESSAGE]");
            System.out.println("You had " + (WIN_DISTANCE - distance) + " kilometers left");
        }
    }
}
