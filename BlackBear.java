
/**
 * Write a description of class Bear here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BlackBear extends Animal
{
    
    private String nameDescription;
    private String solution = "Do not run. If you are in a group, "
                               + "gather together, stand tall, and make noise.";

    /**
     * Constructor for objects of class Bear
     */
    public BlackBear()
    {
    }

    /**
     * 
     */
    public boolean compareTo(String str)
    {
        return str.equals(solution);
        
    }
    public String getDescript()
    {
        return nameDescription;
    }
}
