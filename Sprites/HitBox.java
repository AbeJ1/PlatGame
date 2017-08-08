package Sprites;
import java.util.ArrayList;

/**
 * Write a description of class HitBox here.
 *
 * @author Comerstar
 * @version v0.1
 */
public class HitBox
{

    private double[] xcoordinates;
    private double[] ycoordinates;

    /**
     * Constructor for objects of class HitBox
     */
    public HitBox(double[] corners)
    {
        xcoordinates = new double[corners.length/2];
        ycoordinates = new double[corners.length/2];
        for (int i = 0; i < corners.length; i += 2)
        {
            //System.out.println(corners[i]);
            //System.out.println(corners[i+1]);
            this.xcoordinates[i/2] = corners[i];
            this.ycoordinates[i/2] = corners[i+1];
        }
    }
    
    public void ChangeHitbox (double[] corners)
    {
        xcoordinates = new double[corners.length/2];
        ycoordinates = new double[corners.length/2];
        for (int i = 0; i < corners.length; i += 2)
        {
            //System.out.println(corners[i]);
            //System.out.println(corners[i+1]);
            this.xcoordinates[i/2] = corners[i];
            this.ycoordinates[i/2] = corners[i+1];
        }
    }
    
    public boolean testCollide(double[] xcor, double[] ycor)
    {
        boolean collide = false;
        double b = 1;
        double a = 0;
        for (int j = 0; j < xcor.length;j++)
        {
            boolean inside = true;
                
            inside = isPointIn(xcor[j],ycor[j]);

            //System.out.println("TestComplete");
            if (inside)
            {
                collide = true;
                //System.out.println("Corner In");
                break;
            }
        }
        return collide;
    }
    
    public boolean testCollide(HitBox HB)
    {
        boolean collide = false;
        double[] xcor = HB.xcoordinates;
        double[] ycor = HB.ycoordinates;
        for (int j = 0; j < xcor.length;j++)
        {
            boolean inside = true;
                
            inside = isPointIn(xcor[j],ycor[j]);

            //System.out.println("TestComplete");
            if (inside)
            {
                collide = true;
                //System.out.println("Corner In");
                break;
            }
        }
        return collide;
    }
    
    public boolean isPointIn(double xcor,double ycor)
    {
        boolean inside = true;
        double a;
        double b;
        for (int i = 0; i < xcoordinates.length;i++)
        {
            
            b = -(xcoordinates[(i+1) % xcoordinates.length] - xcoordinates[i]);
            a =  (ycoordinates[(i+1) % ycoordinates.length] - ycoordinates[i]);

            if (a * (xcor - this.xcoordinates[i]) + b * (ycor - this.ycoordinates[i]) <= 0)
            {
                inside = false;
                //System.out.print("Fail ");
                //System.out.println(i);
            }
                
        }
        return(inside);
    }

}
