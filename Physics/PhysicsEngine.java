package Physics;


/**
 * Write a description of class PhysicsEingine here.
 *
 * @author Comerstar
 * @version v0.1
 */
public class PhysicsEngine
{
    // instance variables - replace the example below with your own
    private double xgrav;
    private double ygrav;
    private double xm;
    private double ym;
    private double x;
    private double y;
    private double maxx;
    private double maxy;
    private double maxxm;
    private double maxym;
    /**
     * Constructor for objects of class PhysicsEngine
     */
    public PhysicsEngine(double xgrav,double ygrav, double x, double y, double xm, double ym,double maxxm, 
    double maxym)
    {
        this.xgrav = xgrav;
        this.ygrav = ygrav;
        this.xm = xm;
        this.ym = ym;
        this.x = x;
        this.y = y;
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
    
    public void setXM(double x)
    {
        xm = x;
    }
    
    public void setYM(double y)
    {
        ym = y;
    }
    
    public void addtoXM(double x)
    {
        xm += x;
    }
    
    public void addtoYM(double y)
    {
        ym += y;
    }
    
    public double getXM()
    {
        return this.xm;
    }
    
    public double getYM()
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
     * This changes the position by the current gravity vector
     */
    public void applyPosGravity()
    {
        x += xgrav;
        y += ygrav;
    }
    
    public void applyMomGravity()
    {
        xm += xgrav;
        ym += ygrav;
    }
    
    /**
     * This changes the position by the momentum
     */
    public void updatePosition()
    {
        x += xm;
        y += ym;
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
    
    public double getX()
    {
        return this.x;
    }
    
    public double getY()
    {
        return this.y;
    }
}
