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
    private final int WIN_DISTANCE = 1000;
    private Player player;
    private ArrayList inventory;
    private ConsoleIO keyboard;

    public Game(String name)
    {
        player = new Player(name);
        inventory = new ArrayList();
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
        System.out.println();
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
        chanceEvent();
    }
    private void choiceRest()
    {
    }
    private void choiceForage()
    {
    }
    private void choiceInventory()
    {
        System.out.println("Inventory");
        for(Object item : inventory)
        {
            System.out.println(item);
        }
    }
    
    private void chanceEvent()
    {
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
        System.out.print("Time: " + time + ":00\n");
        System.out.println(player);
        System.out.println();
    }
    private void endGame()
    {
        
    }
}
