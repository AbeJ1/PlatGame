package Physics;
import java.lang.*;


/**
 * contains funtions to rotate points
 *
 * @author Ancient Wisdom
 * @version v1
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
     * Calculates the angle between the target and the original vector
     * 
     * @param (x0,y0) is the vector of the original perpendicular direction vector
     * @param (x1,y1) is the vector of the target perpendicular direction vector
     * 
     * @return ( cos_theta, sin_theta )
     */
    public static double[] rotation_angle( double x0, double y0, double x1, double y1)
    {
        double cos_theta = x0*x1 + y0*y1;
        double sin_theta = x0*y1 - x1*y0;
        
        double[] result = {cos_theta, sin_theta};
        return result;
    }    
        
    /**
     * Rotates a point aroun a fixed point
     * 
     * @param (ptx,pty) the point that one wants to rotate
     * @param (fixx,fixy) the point that is being rotated around
     * @param an array containing cos_theta and sin_theta of the rotation vector Can be created by rotation_angle
     * @return returns an array containing the coordinates of the rotated points
     */
    public static double[] rotate(double ptx, double pty, double fixx, double fixy, double[] theta)
    {
        double x = ptx - fixx;
        double y = pty - fixy;
        double [] result = {theta[0] * x + theta[1] * y + fixx, -theta[1] * x + theta[0] *y + fixy};
        return result;
    }
}