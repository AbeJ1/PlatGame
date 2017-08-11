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
    public double xt = 0;
    public double yt = 0;

    /**
     * Constructor for objects of class HitBox
     */
    public HitBox(double[] corners)
    {
        super();
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
    
    public double absx(int i)
    {
        return xcoordinates[i] + x;
    }
    
    public double absy(int i)
    {
        return xcoordinates[i] + y;
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
        for (int i = 0; i < xcoordinates.length;i++)
        {
            b = -(absx((i+1) % xcoordinates.length) - absx(i));
            a =  (absy((i+1) % ycoordinates.length) - absy(i));

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
        for (int i = 0; i < xcoordinates.length;i++)
        {
            b = -(absx((i+1) % xcoordinates.length) - absx(i));
            a =  (absy((i+1) % ycoordinates.length) - absy(i));

            if (a * (xcor - absx(i)) + b * (ycor - absy(i)) <= 0)
            {
                //System.out.print("Fail ");
                //System.out.println(i);
                failed[i] = false;
            }
            else
            {
                 failed[i] = true;
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
    
    public HitBox Collide (HitBox HB, HitBox OHB)
    {
        double[] c = {0,0};
        HitBox NHB = OHB;
        double x = 0;
        double y = 0;
        double hbx = 0;
        double hby = 0;
        double[] corners = new double[2 * HB.xlength()];
        for (int i = 0; i < HB.xlength();i++)
        {
            if (isPointIn(HB.absx(i),HB.absy(i)))
            {
                boolean[] failed = PointInFail(OHB.absx(i), OHB.absy(i));

                for (int j = 0; j < failed.length; j++)
                {
                    if (!failed[j])
                    {
                        double x0 = OHB.absx(i);
                        double x1 = HB.absx(i);
                        double x2 = absx(j);
                        double x3 = absx((j + 1) % xcoordinates.length);
                        double y0 = OHB.absy(i);
                        double y1 = HB.absy(i);
                        double y2 = absy(j);
                        double y3 = absy((j + 1) % this.ycoordinates.length);

                        double m = (y1 - y0)/(x1 - x0);
                        double m2 = (y3 - y2)/(x3 - x2);
                        x = (y0 - y2 + m2*x2 - m*x0)/(m2 - m);
                        y = (m*y2 - m2*y0 + m*m2*x0 - m*m2*x2)/(m - m2);
                        boolean[] fails = PointInFail(x,y);
                        boolean failedv = true;
                        for (int k = 0; k < fails.length; k++)
                        {
                            if((!fails[k]) || k == j)
                            {
                                failedv = false;
                            }

                            
                        }
                        if (failedv)
                        {
                            hbx = HB.absx(i);
                            hby = HB.absy(i);
                            this.xt = hbx;
                            this.yt = hby;
                            for (int l = 0; l < HB.xcoordinates.length; l++)
                            {
                                double[] tHB = TranslateTransform.Translate(HB.x,
                                HB.y,x - hbx,y - hby);
                                NHB.changeXY(tHB[0],tHB[1]);
                            }
                            double x4 = NHB.absx(i);
                            double x5 = NHB.absx((i + 1) % NHB.xcoordinates.length);
                            double x6 = absx(j);
                            double x7 = absx((j + 1) % xcoordinates.length);
                            double y4 = NHB.absy(i);
                            double y5 = NHB.absy((i + 1) % NHB.xcoordinates.length);
                            double y6 = absy(j);
                            double y7 = absy((j + 1) % ycoordinates.length);

                            double m3 = (y5 - y4)/(x5 - x0);
                            double m4 = (y7 - y6)/(x7 - x6);
                            double xx = (y4 - y6 + m4*x6 - m*x4)/(m4 - m3);
                            double yy = (m3*y6 - m4*y4 + m3*m4*x4 - m3*m4*x6)/(m3 - m4);
                            boolean[] fails2 = PointInFail(xx,yy);
                            boolean failedu = true;
                            for (int n = 0; n < fails.length; n++)
                            {
                                if((!fails2[n]) || n == j)
                                {
                                    failedu = false;
                                }                                
                            }
                            if (failedu)
                            {
                                double xxx1 = NHB.absx((j + 1) % NHB.xlength()) - 
                                NHB.absx(j);
                                double yyy1 = NHB.absy((j + 1) % NHB.ycoordinates.length) - 
                                NHB.absy(j);
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
                                    double[] RotatedHB = RotationTransform.rotate(NHB.absx(l),
                                    NHB.absy(l),xx,yy,thetacos);
                                    corners[l] = RotatedHB[0];
                                    corners[l + 1] = RotatedHB[1];
                                }
                                NHB = new HitBox(corners);
                                this.theta = Math.acos(thetacos[0]);
                                if (thetacos[1] < 0)
                                {
                                    this.theta *= -1;
                                }
                            }
                        }
                    }
                }
            }
        }
        return NHB;
    }
}
