package Physics;
import static java.lang.Math.*;

/**
 * This handles the physics such as gravity
 *
 * @author Comerstar
 * @version v0.1
 * 
 */
public class PhysicsEngine
{
    // xgrav is the gravity in x direction
    // xm is the momentum in x direction
    // xpos is the x position
    // maxx is the max x posiiton
    // maxxm is the momentum limit in x direction
    private double xgrav = 0.0;
    private double ygrav = 0.0;
    private double xm = 0.0;
    private double ym = 0.0;
    private double maxx = 0.0;
    private double maxy = 0.0;
    private double maxxm = 0.0;
    private double maxym = 0.0;
    /**
     * Constructor for objects of class PhysicsEngine
     */
    public PhysicsEngine(double xgrav,double ygrav,double xm, double ym,double maxx, double maxy, double maxxm, 
    double maxym)
    {
        this.xgrav = xgrav;
        this.ygrav = ygrav;
        this.xm = xm;
        this.ym = ym;
        this.maxx = maxx;
        this.maxy = maxy;
        this.maxxm = maxxm;
        this.maxym = maxym;
    }
    
    /**
     * This function changes the momentum to x,y
     * 
     * @param x is a double that is how much the momentum in the x direction increases
     * @param y is a double that is how much the momentum in the y direction increases
     */
    public void changeMomentum( double x,double y)
    {
        xm += x;
        ym += y;
    }
    
    /**
     * This function updates the momentum to x,y
     * 
     * @param x is a double that is the new x momentum
     * @param y is a double that is the new y momentum
     */
    public void setMomentum( double x,double y)
    {
        xm = x;
        ym = y;
    }
    
    public double getXMomentum()
    {
        return this.xm;
    }
    
    public double getYMomentum()
    {
        return this.ym;
    }
    
    /**
     * This simply sets momentum to 0 in any direction
     */
    public void stop()
    {
        setMomentum(0,0);
    }
    
    /**
     * Update momentum by the current gravity vector
     */
    public void updateMomentum(double x, double y)
    {
        double txm = max(min(x + xm + xgrav,maxx),0.0);
        double tym = max(min(y + ym + ygrav,maxy),0.0);
        xm = max(min(txm-x,maxxm),-maxxm);
        ym = max(min(tym-y,maxym),-maxym);
    }
    
    /**
     * This function sets the gravity vector
     * 
     * @param the amount of gravity in the x direction
     * @param the amount of gravity in the y direction
     */
    public void setGravity (double xGrav, double yGrav) 
    {
        xgrav = xGrav;
        ygrav = yGrav;
    }
    
}
