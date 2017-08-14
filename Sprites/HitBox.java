package Sprites;
import Physics.RotationTransform;
import Physics.TranslateTransform;

/**
 * Write a description of class HitBox here.
 *
 * @author Comerstar
 * @version v0.1
 */
public class HitBox extends Sprite
{

    private double[] xcoordinates;
    private double[] ycoordinates;
    public double theta = 0;
    public double xr = 0;
    public double yr = 0;

    /**
     * Constructor for objects of class HitBox
     */
    public HitBox()
    {
        super();
    }
    
    public HitBox(double[] corners )
    {
        super();
        updateHitbox(corners);
    }
    
    public void updateHitbox (double[] corners)
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
    
    public void copyHB (HitBox HB)
    {
        xcoordinates = HB.xcoordinates;
        ycoordinates = HB.ycoordinates;
    }
    
    public void setTheta(double theta)
    {
        this.theta = theta;
    }
    
    public void changeR(double x,double y)
    {
        xr = x;
        yr = y;
    }
    
    public double absx(int i)
    {
        return xcoordinates[i] + x;
    }
    
    public double absy(int i)
    {
        return ycoordinates[i] + y;
    }
    
    public double absoldx(int i)
    {
        return xcoordinates[i] + oldx;
    }
    
    public double absoldy(int i)
    {
        return ycoordinates[i] + oldy;
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
        for (int j = 0; j < HB.xlength();j++)
        {
            boolean inside = true;
                
            inside = isPointIn(HB.absx(j),HB.absy(j));

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
        //System.out.print("xcor ");
        //System.out.println(xcor);
        //System.out.print("ycor ");
        //System.out.println(ycor);
        for (int i = 0; i < xcoordinates.length;i++)
        {
            b = -(absx((i+1) % xcoordinates.length) - absx(i));
            a =  (absy((i+1) % ycoordinates.length) - absy(i));
            //System.out.print("absx ");
            //System.out.println(absx(i));
            //System.out.print("absy ");
            //System.out.println(absy(i));

            if (a * (xcor - absx(i)) + b * (ycor - absy(i)) <= 0)
            {
                inside = false;
                //System.out.print("Fail ");
                //System.out.println(i);
                
                break;
            }
                
        }
        return(inside);
    }
    
    public double absxcoordinates(int i)
    {
        return xcoordinates[i] + x;
    }
    
    public double absycoordinates(int i)
    {
        return ycoordinates[i] + y;
    }
    
    public boolean[] PointInFail(double xcor,double ycor)
    {
        double a;
        double b;
        boolean[] failed = new boolean[xcoordinates.length];
        //System.out.print("xcor ");
        //System.out.println(xcor);
        //System.out.print("ycor ");
        //System.out.println(ycor);
        for (int i = 0; i < xcoordinates.length;i++)
        {
            b = -(absx((i+1) % xcoordinates.length) - absx(i));
            a =  (absy((i+1) % ycoordinates.length) - absy(i));
            //System.out.print("absx ");
            //System.out.println(absx(i));
            //System.out.print("absy ");
            //System.out.println(absy(i));
            if (a * (xcor - absx(i)) + b * (ycor - absy(i)) <= 0)
            {
                //System.out.print("Fail ");
                //System.out.println(i);
                failed[i] = false;
            }
            else
            {
                 failed[i] = true;
                 System.out.println("Corner In Not Fail");
            }
        }
        return(failed);
    }
    
    public int xlength()
    {
        return xcoordinates.length;
    }
    
    public int ylength()
    {
        return ycoordinates.length;
    }
    
    public void Collide (HitBox HB)
    {
        double[] c = {0,0};
        
        double x = 0;
        double y = 0;
        double hbx = 0;
        double hby = 0;
        double[] corners = new double[2 * HB.xlength()];
        for (int i = 0; i < HB.xlength();i++)
        {
            if (isPointIn(HB.absx(i),HB.absy(i)))
            {
                boolean[] failed = PointInFail(HB.absoldx(i), HB.absoldy(i));
                System.out.println("point in");

                for (int j = 0; j < failed.length; j++)
                {
                    System.out.println("jloop");
                    if (failed[j])
                    {
                        System.out.println("jloop if");
                        double x0 = HB.absoldx(i);
                        double x1 = HB.absx(i);
                        double x2 = absx(j);
                        double x3 = absx((j + 1) % xcoordinates.length);
                        double y0 = HB.absoldy(i);
                        double y1 = HB.absy(i);
                        double y2 = absy(j);
                        double y3 = absy((j + 1) % ycoordinates.length);
                        if (x0 == x1)
                        {
                            double m = (y3 - y2)/(x3 - x2);
                            x = x0;
                            y = m*(x0 - x3) - y1;
                        }
                        else if (x2 == x3)
                        {
                            double m = (y1 - y0)/(x1 - x0);
                            x = x2;
                            y = m*(x2 - x1) - y3;
                        }
                        else
                        {
                            double m = (y1 - y0)/(x1 - x0);
                            double m2 = (y3 - y2)/(x3 - x2);
                            x = (y0 - y2 + m2*x2 - m*x0)/(m2 - m);
                            y = (m*y2 - m2*y0 + m*m2*x0 - m*m2*x2)/(m - m2);
                        }
                        boolean[] fails = PointInFail(x,y);
                        boolean failedv = true;
                        for (int k = 0; k < fails.length; k++)
                        {
                            System.out.print("k: ");
                            System.out.println(k);
                            System.out.print("j: ");
                            System.out.println(j);
                            if(!fails[k] && k != j)
                            {
                                System.out.println(fails[k]);
                                failedv = false;
                                System.out.println("kfail");
                                break;
                            }
                        }
                        if (failedv)
                        {
                            hbx = HB.absx(i);
                            hby = HB.absy(i);
                            System.out.println("failedv is true");
                            HB.changeXY(x,y);
                            double x4 = HB.absx(i);
                            double x5 = HB.absx((i + 1) % HB.xcoordinates.length);
                            double x6 = absx(j);
                            double x7 = absx((j + 1) % xcoordinates.length);
                            double y4 = HB.absy(i);
                            double y5 = HB.absy((i + 1) % HB.xcoordinates.length);
                            double y6 = absy(j);
                            double y7 = absy((j + 1) % ycoordinates.length);
                            double xx;
                            double yy;
                            if (x4 == x5)
                            {
                                double m = (y7 - y6)/(x7 - x6);
                                xx = x4;
                                yy = m*(x4 - x7) - y5;
                            }
                            else if (x6 == x7)
                            {
                                double m = (y5 - y4)/(x5 - x4);
                                xx = x6;
                                yy = m*(x6 - x5) - y7;
                            }
                            else
                            {
                                double m = (y5 - y4)/(x5 - x4);
                                double m2 = (y7 - y6)/(x7 - x6);
                                xx = (y0 - y2 + m2*x2 - m*x0)/(m2 - m);
                                yy = (m*y2 - m2*y0 + m*m2*x0 - m*m2*x2)/(m - m2);
                            }
                            boolean[] fails2 = PointInFail(xx,yy);
                            boolean failedu = true;
                            for (int n = 0; n < fails.length; n++)
                            {
                                if((!fails2[n]) && !(n == j))
                                {
                                    failedu = false;
                                }                                
                            }
                            if (failedu)
                            {
                                double xxx1 = HB.absx((j + 1) % HB.xlength()) - 
                                HB.absx(j);
                                double yyy1 = HB.absy((j + 1) % HB.ycoordinates.length) - 
                                HB.absy(j);
                                double xxx2 = absx((j+1) % this.xcoordinates.length) - 
                                absx(j);
                                double yyy2 = absy((j+1) % this.xcoordinates.length) - 
                                absy(j);
                                double[] thetacos = RotationTransform.rotation_angle(
                                Math.sqrt(1 - yyy1 * yyy1),
                                Math.sqrt(1 - xxx1 * xxx1),
                                Math.sqrt(1 - yyy2 * yyy2),
                                Math.sqrt(1 - xxx2 * xxx2));
                                for (int l = 0; l < HB.xcoordinates.length; l++)
                                {
                                    double[] RotatedHB = RotationTransform.rotate(HB.absx(l),
                                    HB.absy(l),xx,yy,thetacos);
                                    corners[l] = RotatedHB[0];
                                    corners[l + 1] = RotatedHB[1];
                                }
                                
                                HB.setTheta(Math.acos(thetacos[0]));
                                if (thetacos[1] < 0)
                                {
                                    HB.setTheta(HB.theta * -1);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
