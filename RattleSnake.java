<<<<<<< HEAD

=======
>>>>>>> Julia
/**
 * Write a description of class Snake here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RattleSnake extends Animal
{
<<<<<<< HEAD
    private String nameDescription = "You hear some rattling nearby, and spot a rattlesnake 5 feet from you. What should you do?";
    private String solution = "slowly move away from the area of the sound and do not make any sudden moves. Again, most snake bites happen when people are messing with the animals. And even if you think a snake is dead or that you killed it, stay away from it. Freshly killed snakes can still bite and inject venom.";
    private String mcSolution = "Slowly move away from the animal and do not make any sudden movements.";
=======
    private String nameDescription;
    private String solution = "";

>>>>>>> Julia
    /**
     * Constructor for objects of class Bear
     */
    public RattleSnake()
    {
    }
<<<<<<< HEAD
=======

>>>>>>> Julia
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
