package Physics;


/**
 * has various rounding functions
 *
 * @author Comerstar
 * @version v0.1
 */
public class Round
{
    

    /**
     * Constructor for objects of class Round
     */
    public Round()
    {
        //
    }

    /**
     * returns x rounded to p decimal places
     *
     * @param  x, the value to be rounded
     * @param p, how many decimal places to round to
     * @return the rounded x value
     */
    public static double round(double x, int p)
    {
        x = x * Math.pow(10,p);
        int y = (int)Math.round(x);
        x = (double) y;
        x = x/(double)Math.pow(10.0,p);
        return x;
    }
}
