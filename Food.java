
/**
 * Write a description of class Food here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Food implements Item
{
    private int amount;
    private String name;
    public Food(String str, int num)
    {
        name = str;
        amount = num;
    }
    public int getAmount(){return amount;}
    public String getName(){return name;}
}
