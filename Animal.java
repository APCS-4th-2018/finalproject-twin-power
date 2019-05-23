/**
 * Write a description of class Animal here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Animal
{
    private String nameDescription;
    private String solution;
    public Animal ()
    {
    }
    
    public abstract boolean compareTo(String str);
    public abstract String getDescript();

}