package Physics;
import java.lang.*;


/**
 * Write a description of class RotationTransform here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */


public class RotationTransform
{
    /**
     * Constructor for objects of class RotationTransform
     */
    public RotationTransform()
    {
        //
    }

    /**
     * (x0,y0) is the vector of the original perpendicular direction vector
     * (x1,y1) is the vector of the target perpendicular direction vector
     * output is ( cos_theta, sin_theta )
     */
    public static double[] rotation_angle( double x0, double y0, double x1, double y1)
    {
        double cos_theta = x0*x1 + y0*y1;
        double sin_theta = x0*y1 - x1*y0;
        
        double[] result = {cos_theta, sin_theta};
        return result;
    }    
        
    public static double[] rotate(double ptx, double pty, double fixx, double fixy, double[] theta)
    {
        double x = ptx - fixx;
        double y = pty - fixy;
        double [] result = {theta[0] * x + theta[1] * y + fixx, -theta[1] * x + theta[0] *y + fixy};
        return result;
    }
}