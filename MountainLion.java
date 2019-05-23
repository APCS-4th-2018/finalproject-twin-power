<<<<<<< HEAD

=======
>>>>>>> Julia
/**
 * Write a description of class MountainLion here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MountainLion extends Animal
{
<<<<<<< HEAD
    private String nameDescription = "A mountain lion has spotted you, and is ready for attack. What should you do?";
    private String mcSolution = "Do not run away, and face the animal making loud noises.";
    private String solution = "Do not run away. This may trigger an attack."
        + "Never turn your back to a lion."
        + "Maintain constant eye contact."
        + "Make loud noises, yell, wave your arms."
        + "Make yourself look bigger with your clothing or other objects.";
=======
    private String nameDescription;
    private String solution = "";
>>>>>>> Julia

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
<<<<<<< HEAD
        return str.equals(mcSolution);
=======
        return str.equals(solution);
>>>>>>> Julia
        
    }
    public String getDescript()
    {
        return nameDescription;
    }
<<<<<<< HEAD
    public String getSolution()
    {
        return solution;
    }
=======
>>>>>>> Julia
}
