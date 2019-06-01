import chn.util.*;
/**
 * BlueJ Terminal Version
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
        Game game;
        System.out.println("[GAME NAME HERE]\n");
        System.out.println("What is your name?");
        System.out.print(">> ");
        name = keyboard.readLine();
        System.out.println();
        game = new Game(name);
    }
}
