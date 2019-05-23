
/**
 * Write a description of class Snake here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RattleSnake extends Animal
{
    private String nameDescription = "You hear some rattling nearby, and spot a rattlesnake 5 feet from you. What should you do?";
    private String solution = "slowly move away from the area of the sound and do not make any sudden moves. Again, most snake bites happen when people are messing with the animals. And even if you think a snake is dead or that you killed it, stay away from it. Freshly killed snakes can still bite and inject venom.";
    private String mcSolution = "Slowly move away from the animal and do not make any sudden movements.";
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
    public String getMC()
    {
        return mcSolution;
    }
}
