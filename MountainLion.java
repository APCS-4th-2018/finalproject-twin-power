
/**
 * Write a description of class MountainLion here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MountainLion extends Animal
{
    private String nameDescription;
    private String solution = "";

    /**
     * Constructor for objects of class Bear
     */
    public MountainLion()
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
