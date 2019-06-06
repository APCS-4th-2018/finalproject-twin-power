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

    public Animal (String nd, String s)
    {
        nameDescription = nd;
        solution = s;
    }
    
    public String getDescript() { return nameDescription;}
    public String getSolution() { return solution;}
    public String toString() { return getSolution();}
}
