
/**
 * Write a description of class Food here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Food implements Item
{
    private String name;
    public Food(String str)
    {
        name = str;
    }
    public String getName(){return name;}
    public void useItem(Player player)
    {
        player.changeHunger(1.5);
    }
    public String toString(){return name;}
}
