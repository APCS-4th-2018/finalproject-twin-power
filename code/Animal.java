/**
 * Write a description of class Animal here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Animal
{
    protected String nameDescription;
    protected String solution;
    protected String explanation;

    public Animal (String nd, String s, String e)
    {
        nameDescription = nd;
        solution = s;
        explanation = e;
    }
    
    public String getDescript() { return nameDescription;}
    public String getSolution() { return solution;}
    public String toString() { return getSolution();}
}
