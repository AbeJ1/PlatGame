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
            
            b = -(absxcoordinates((i+1) % xcoordinates.length) - absxcoordinates(i));
            a =  (absycoordinates((i+1) % ycoordinates.length) - absycoordinates(i));

            if (a * (xcor - this.xcoordinates[i]) + b * (ycor - this.ycoordinates[i]) <= 0)
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
            
            b = -(absxcoordinates((i+1) % xcoordinates.length) - absxcoordinates(i));
            a =  (absycoordinates((i+1) % ycoordinates.length) - absycoordinates(i));

            if (a * (xcor - this.xcoordinates[i]) + b * (ycor - this.ycoordinates[i]) <= 0)
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
            if (isPointIn(HB.absxcoordinates(i),HB.absycoordinates(i)))
            {
                boolean[] failed = PointInFail(OHB.absxcoordinates(i), OHB.absycoordinates(i));
                for (int j = 0; j < failed.length; j++)
                {
                    if (!failed[j])
                    {
                        double x0 = OHB.absxcoordinates(i);
                        double x1 = HB.absxcoordinates(i);
                        double x2 = absxcoordinates(j);
                        double x3 = absxcoordinates((j + 1) % xcoordinates.length);
                        double y0 = OHB.absycoordinates(i);
                        double y1 = HB.absycoordinates(i);
                        double y2 = absycoordinates(j);
                        double y3 = absycoordinates((j + 1) % ycoordinates.length);
                        double m = (y1 - y0)/(x1 - x0);
                        double m2 = (y3 - y2)/(x3 - x2);
                        x = (y0 - y2 + m2*x2 - m*x0)/(m2 - m);
                        y = (m*y2 - m2*y0 + m*m2*x0 - m*m2*x2)/(m - m2);
                        boolean[] fails = PointInFail(x,y);
                        for (int k = 0; k < fails.length; k++)
                        {
                            if((!fails[k]) || k == j)
                            {
                                break;
                            }
                            hbx = HB.absxcoordinates(i);
                            hby = HB.absycoordinates(i);
                            for (int l = 0; l < HB.xlength(); l++)
                            {
                                double[] translatedHB = TranslateTransform.Translate(HB.xcoordinates[l],HB.ycoordinates[l]
                                ,x - hbx,y - hby);
                                corners[l] = translatedHB[0];
                                corners[l + 1] = translatedHB[1];
                            }
                            NHB = new HitBox(corners);
                            double x4 = NHB.absxcoordinates(i);
                            double x5 = NHB.absxcoordinates((i + 1) % NHB.xcoordinates.length);
                            double x6 = absxcoordinates(j);
                            double x7 = absxcoordinates((j + 1) % this.xcoordinates.length);
                            double y4 = NHB.absycoordinates(i);
                            double y5 = NHB.absycoordinates((i + 1) % NHB.xcoordinates.length);
                            double y6 = absycoordinates(j);
                            double y7 = absycoordinates((j + 1) % this.ycoordinates.length);
                            double m3 = (y5 - y4)/(x5 - x0);
                            double m4 = (y7 - y6)/(x7 - x6);
                            double xx = (y4 - y6 + m4*x6 - m*x4)/(m4 - m3);
                            double yy = (m3*y6 - m4*y4 + m3*m4*x4 - m3*m4*x6)/(m3 - m4);
                            boolean[] fails2 = PointInFail(xx,yy);
                            for (int n = 0; n < fails.length; n++)
                            {
                                if((!fails2[n]) || n == j)
                                {
                                    break;
                                }
                                double[] theta = RotationTransform.rotation_angle(NHB.xcoordinates[(k + 1) % NHB.xcoordinates.length] - 
                                NHB.xcoordinates[k],NHB.ycoordinates[(k + 1) % NHB.ycoordinates.length] - 
                                NHB.xcoordinates[k],this.xcoordinates[(j+1) % this.xcoordinates.length] - 
                                this.xcoordinates[j],this.ycoordinates[(j+1) % this.xcoordinates.length] - 
                                this.ycoordinates[j]);
                                for (int l = 0; l < HB.xcoordinates.length; l++)
                                {
                                    double[] translatedHB = TranslateTransform.Translate(HB.xcoordinates[l],HB.ycoordinates[l],x - hbx,y - hby);
                                    corners[l] = translatedHB[0];
                                    corners[l + 1] = translatedHB[1];
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
