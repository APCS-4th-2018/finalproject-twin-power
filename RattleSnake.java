
/**
 * Write a description of class Snake here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RattleSnake extends Animal
{
    private String nameDescription;
    private String solution = "";

    /**
     * Constructor for objects of class Bear
     */
    public RattleSnake()
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
