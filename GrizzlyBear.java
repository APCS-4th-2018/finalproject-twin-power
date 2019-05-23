
/**
 * Write a description of class Bear here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GrizzlyBear extends Animal
{
    private String mcSolution = "Don't run and stand tall. If the animal charges at you, get down to the ground and play dead.";
    private String nameDescription = "This grizzly doesn't have a friendly look on its face. What should you do?";
    private String solution = "Do not run and stand tall. If the bear keeps charging, fall down and lace your fingers over the back of your neck to protect it. Guard your stomach by lying flat on the ground or by assuming a fetal position, with knees tucked under your chin. Don't move."
        + "Play dead. Even if the bear starts to attack, it's likely trying to neutralize you as a threat. And since you'll never outrun or overpower it, faking death is your best bet at this point. Even if it walks away, don't get up. Grizzlies are known to linger and make sure you're dead, so stay down for at least 20 minutes.";

    /**
     * Constructor for objects of class Bear
     */
    public GrizzlyBear()
    {
    }

    /**
     * 
     */
    public boolean compareTo(String str)
    {
        return str.equals(mcSolution);
        
    }
    public String getDescript()
    {
        return nameDescription;
    }
    public String getSolution()
    {
        return solution;
    }
}
