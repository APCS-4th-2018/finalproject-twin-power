import chn.util.*;
/**
 * Write a description of class Driver here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Driver
{
    public static void main(String[] args)
    {
        ConsoleIO keyboard = new ConsoleIO();
        String name;
        GameEdit game;
        System.out.println("[GAME NAME HERE]\n");
        System.out.println("What is your name?");
        System.out.print(">> ");
        name = keyboard.readLine();
        System.out.println();
        game = new GameEdit(name);
    }
}
