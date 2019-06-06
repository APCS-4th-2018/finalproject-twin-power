/**
 * Here is the abstract class for all Animal types.
 *
 * @author Ian Xu
 * @version Twin Power
 */
public abstract class Animal
{
    protected String nameDescription;
    protected String solution;
    
    /**
     * Creates an Animal object with the specified description and solution.
     */
    public Animal (String nd, String s)
    {
        nameDescription = nd;
        solution = s;
    }
    
    /**
     * Returns the description of the Animal.
     * @return  Description.
     */
    public String getDescript() { return nameDescription;}
    
    /**
     * Returns the solution of the Animal.
     * 
     * @return  Solution.
     */
    public String getSolution() { return solution;}
    
    /**
     * Returns a String representation of the Animal object.
     * 
     * @return  String representation.
     */
    public String toString() { return getSolution();}
}
