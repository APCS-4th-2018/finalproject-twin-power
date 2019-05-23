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
    ConsoleIO keyboard = new ConsoleIO();
    private int days, distance;
    private final int WIN_DISTANCE = 1000;
    private Player player;
    private ArrayList <Animal> animal = new ArrayList <Animal>();
   
    
    public Game(String name)
    {
        player = new Player(name);
        
        //construct and array of different animals for random generation each turn
        BlackBear bb = new BlackBear();
        RattleSnake rs = new RattleSnake();
        MountainLion ml = new MountainLion();
        animal.add(bb);
        animal.add(rs);
        animal.add(ml);
        
        System.out.println("Welcome " + player.getName());
        while(player.isAlive() && distance < WIN_DISTANCE)
        {
            turn();
        }
    }
    
    private void turn()
    {
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
        //print out the choices possible
        //call other methods depending on the choice
    }
}
