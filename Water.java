/**
 * Write a description of class Food here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Water implements Item
{
    private String name;
    public Water(String str)
    {
        name = str;
    }
    public String getName(){return name;}
    public void useItem(Player player)
    {
        player.changeThirst(2);
    }
}