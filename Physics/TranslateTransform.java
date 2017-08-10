package Physics;


/**
 * Has a function to transform shapes
 *
 * @author the Comerstar
 * @version v1
 */
public class TranslateTransform
{
    public TranslateTransform()
    {
        //
    }
    
    /**
     * translates the given point by the vector movex,movey
     * 
     * @param ptx,pty the point one wants to translate
     * @param movex,movey, the vector one wants to translate the point by
     */
    public static double[] Translate(double ptx, double pty, double movex, double movey)
    {
        double[] result = {ptx + movex + movey, pty + movex + movey};
        return result;
    }
}