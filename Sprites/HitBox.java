package Sprites;
import Physics.RotationTransform;
import Physics.TranslateTransform;
import Physics.Round;

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
            if (Double.isNaN(a))
            {
                a = 0;
            }
            if (Double.isNaN(b))
            {
                b = 0;
            }

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
            if (Double.isNaN(a))
            {
                a = 0;
            }
            if (Double.isNaN(b))
            {
                b = 0;
            }
            if (a * (xcor - absx(i)) + b * (ycor - absy(i)) <= 0)
            {
                //System.out.print("Fail ");
                //System.out.println(i);
                failed[i] = false;
            }
            else
            {
                 failed[i] = true;
                 //System.out.println("Corner In Not Fail");
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
        //System.out.println("<collide>----------------------------------------------------------------");
        for (int i = 0; i < HB.xlength();i++)
        {
            if (isPointIn(HB.absx(i),HB.absy(i)))
            {
                boolean[] failed = PointInFail(HB.absoldx(i), HB.absoldy(i));
                //System.out.println("point in");

                for (int j = 0; j < failed.length; j++)
                {
                    //System.out.println("jloop");
                    if (!failed[j])
                    {
                        //System.out.println("jloop if");
                        double x0 = HB.absoldx(i);
                        double x1 = HB.absx(i);
                        double x2 = absx((j + 1) % xcoordinates.length);
                        double x3 = absx(j);
                        double y0 = HB.absoldy(i);
                        double y1 = HB.absy(i);
                        double y2 = absy((j + 1) % ycoordinates.length);
                        double y3 = absy(j);
                        //System.out.print("x0: ");
                        //System.out.print(x0);
                        //System.out.print(" x1: ");
                        //System.out.print(x1);
                        //System.out.print(" x2: ");
                        //System.out.print(x2);
                        //System.out.print(" x3: ");
                        //System.out.println(x3);
                        //System.out.print("y0: ");
                        //System.out.print(y0);
                        //System.out.print(" y1: ");
                        //System.out.print(y1);
                        //System.out.print(" y2: ");
                        //System.out.print(y2);
                        //System.out.print(" y3: ");
                        //System.out.println(y3);
                        if (Math.abs(x0 - x1) < 0.0001)
                        {
                            double m = (y3 - y2)/(x3 - x2);
                            //System.out.print("m: ");
                            //System.out.println(m);
                            x = x0;
                            y = m*(x0 - x3) + y3;
                            //System.out.print("x: ");
                            //System.out.println(x);
                            //System.out.print("y: ");
                            //System.out.println(y);
                        }
                        else if (Math.abs(x2 - x3) < 0.0001)
                        {
                            double m = (y1 - y0)/(x1 - x0);
                            //System.out.print("m: ");
                            //System.out.println(m);
                            x = x2;
                            y = m*(x2 - x1) + y1;
                            //System.out.print("x: ");
                            //System.out.println(x);
                            //System.out.print("y: ");
                            //System.out.println(y);
                        }
                        else
                        {
                            double m = (y1 - y0)/(x1 - x0);
                            double m2 = (y3 - y2)/(x3 - x2);
                            //System.out.print("m: ");
                            //System.out.println(m);
                            //System.out.print("m2: ");
                            //System.out.println(m2);
                            x = (y0 - y2 + m2*x2 - m*x0)/(m2 - m);
                            y = (m*y2 - m2*y0 + m*m2*x0 - m*m2*x2)/(m - m2);
                            if (Math.abs(m2 - m) < 0.001)
                            {
                                x = -123456789.1;
                                y = -123456789.1;
                            }
                            //System.out.print("x: ");
                            //System.out.println(x);
                            //System.out.print("y: ");
                            //System.out.println(y);
                        }
                        //System.out.print("HB.x: ");
                        //System.out.println(HB.x);
                        //System.out.print("HB.y: ");
                        //System.out.println(HB.y);
                        x = Round.round(x,1);
                        y = Round.round(y,1);
                        boolean[] fails = PointInFail(x,y);
                        boolean failedv = true;
                        if (Math.abs(x + 123456789.1) < 0.001)
                        {
                            failedv = false;
                        }
                        else if (Math.abs(y + 123456789.1) < 0.001)
                        {
                            failedv = false;
                        }
                        for (int k = 0; k < fails.length; k++)
                        {
                            if(!fails[k] && k != j)
                            {
                                //System.out.println(fails[k]);
                                failedv = false;
                                //System.out.println("kfail");
                                //System.out.print("k: ");
                                //System.out.println(k);
                                //System.out.print("j: ");
                                //System.out.println(j);
                                //System.out.println(!fails[k] && k != j);
                                break;
                            }
                        }
                        if (failedv)
                        {
                            // System.out.print("Before X =:" );
                            // System.out.println(HB.x);
                            // System.out.print("Before Y =:" );
                            // System.out.println(HB.y);
                            
                            // System.out.print("Before AF X:");
                            // System.out.println(HB.AT.getTranslateX());
                            // System.out.print("Before AF Y:");
                            // System.out.println(HB.AT.getTranslateY());
                            HB.changeXY(x - HB.absx(i), y - HB.absy(i));
                            if (HB.Physics != null)
                            {
                                double xm = HB.Physics.getXMomentum();
                                double ym = HB.Physics.getYMomentum();
                                double b = -(absx((j+1) % xcoordinates.length) - absx(j));
                                double a =  (absy((j+1) % ycoordinates.length) - absy(j));
                                double ab = Math.sqrt(a*a + b*b);
                                // System.out.print("a: ");
                                // System.out.print(a);
                                // System.out.print(" b: ");
                                // System.out.println(b);
                                a = a/ab;
                                b = b/ab;
                                if (Double.isNaN(a))
                                {
                                    a = 0;
                                }
                                if (Double.isNaN(b))
                                {
                                    b = 0;
                                }
                                double l = xm*a + ym*b;
                                System.out.print("xm: ");
                                System.out.print(xm);
                                System.out.print(" ym: ");
                                System.out.println(ym);
                                System.out.print("a/ab: ");
                                System.out.print(a);
                                System.out.print(" b/ab: ");
                                System.out.println(b);
                                System.out.print("l: ");
                                System.out.println(l);
                                HB.Physics.changeMomentum(Round.round(a*-l,1), Round.round(b*-l,1));
                            }
                            double x4 = HB.absx(i);
                            double x5 = HB.absx((i + 1) % HB.xcoordinates.length);
                            double x6 = absx(j);
                            double x7 = absx((j + 1) % xcoordinates.length);
                            double y4 = HB.absy(i);
                            double y5 = HB.absy((i + 1) % HB.xcoordinates.length);
                            double y6 = absy(j);
                            double y7 = absy((j + 1) % ycoordinates.length);
                            System.out.print("x4: ");
                            System.out.print(x4);
                            System.out.print(" x5: ");
                            System.out.print(x5);
                            System.out.print(" x6: ");
                            System.out.print(x6);
                            System.out.print(" x7: ");
                            System.out.println(x7);
                            System.out.print("y4: ");
                            System.out.print(y4);
                            System.out.print(" y5: ");
                            System.out.print(y5);
                            System.out.print(" y6: ");
                            System.out.print(y6);
                            System.out.print(" y7: ");
                            System.out.println(y7);
                            double xx;
                            double yy;
                            if (Math.abs(x4 - x5) < 0.0001)
                            {
                                double m = (y7 - y6)/(x7 - x6);
                                //System.out.print("m: ");
                                //System.out.println(m);
                                xx = x4;
                                yy = m*(x4 - x7) + y7;
                                //System.out.print("xx: ");
                                //System.out.println(xx);
                                //System.out.print("yy: ");
                                //System.out.println(yy);
                            }
                            else if (Math.abs(x6 - x7) < 0.0001)
                            {
                                double m = (y5 - y4)/(x5 - x4);
                                //System.out.print("m: ");
                                //System.out.println(m);
                                xx = x6;
                                yy = m*(x6 - x5) + y5;
                                //System.out.print("xx: ");
                                //System.out.println(xx);
                                //System.out.print("yy: ");
                                //System.out.println(yy);
                            }
                            else
                            {
                                double m = (y5 - y4)/(x5 - x4);
                                double m2 = (y7 - y6)/(x7 - x6);
                                //System.out.print("m: ");
                                //System.out.println(m);
                                //System.out.print("m2: ");
                                //System.out.println(m2);
                                xx = (y4 - y6 + m2*x6 - m*x4)/(m2 - m);
                                yy = (m*y6 - m2*y4 + m*m2*x4 - m*m2*x6)/(m - m2);
                                //System.out.print("xx: ");
                                //System.out.println(xx);
                                //System.out.print("yy: ");
                                //System.out.println(yy);
                                if (Math.abs(m2 - m) < 0.001)
                                {
                                    xx = -123456789.1;
                                    yy = -123456789.1;
                                }
                                if (Double.isNaN(xx))
                                {
                                    xx = -123456789.1;
                                }
                                if (Double.isNaN(yy))
                                {
                                    yy = -123456789.1;
                                }
                                // System.out.print("xx: ");
                                // System.out.println(xx);
                                // System.out.print("yy: ");
                                // System.out.println(yy);
                            }
                            if (Double.isNaN(xx))
                            {
                                xx = -123456789.1;
                            }
                            if (Double.isNaN(yy))
                            {
                                yy = -123456789.1;
                            }
                            xx = Round.round(xx,1);
                            yy = Round.round(yy,1);
                            boolean[] fails2 = PointInFail(xx,yy);
                            // System.out.print("xx: ");
                            // System.out.println(xx);
                            // System.out.print("yy: ");
                            // System.out.println(yy);
                            boolean failedu = true;
                            if (Math.abs(xx + 123456789.1) < 0.001)
                            {
                                failedu = false;
                            }
                            else if (Math.abs(yy + 123456789.1) < 0.001)
                            {
                                failedu = false;
                            }
                            for (int n = 0; n < fails.length; n++)
                            {
                                if((!fails2[n]) && n != j)
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
                                if (this.Physics != null)
                                {
                                    Physics.xgrav;
                                    Physics.ygrav;
                                }
                                // System.out.print("xxx1: ");
                                // System.out.print(xxx1);
                                // System.out.print(" yyy1: ");
                                // System.out.println(yyy1);
                                // System.out.print("xxx2: ");
                                // System.out.print(xxx2);
                                // System.out.print(" yyy2: ");
                                // System.out.println(yyy2);
                                double l1 = Math.sqrt(xxx1*xxx1 + yyy1*yyy1);
                                double l2 = Math.sqrt(xxx2*xxx2 + yyy2*yyy2);
                                double[] thetacos = RotationTransform.rotation_angle(
                                xxx1/l1,
                                yyy1/l1,
                                xxx2/l2,
                                yyy2/l2);
                                // // System.out.println("££££££££££££££££££££££");
                                // // System.out.print("XX:");
                                // // System.out.println(xx);
                                // // System.out.print("YY:");
                                // // System.out.println(yy);
                                // // System.out.print("X:");
                                // // System.out.println(HB.x);
                                // // System.out.print("Y:");
                                // // System.out.println(HB.y);
                                // // System.out.println("££££££££££££££££££££££");
                                System.out.println("OLD Corners -------------------------------------");
                                for( int l = 0; l < HB.xcoordinates.length; l++ )
                                {
                                    System.out.println(l);
                                    System.out.print("[");
                                    System.out.print(HB.absx(l));
                                    System.out.print(",");
                                    System.out.print(HB.absy(l));
                                    System.out.println("]");
                                }
                                System.out.println("OLD Corners END -------------------------------------");
                                // // System.out.println("Corners ;;;;;;;;;");
                                for (int l = 0; l < HB.xcoordinates.length; l++)
                                {
                                    double[] RotatedHB = RotationTransform.rotate(HB.absx(l),
                                    HB.absy(l),xx,yy,thetacos);
                                    corners[2*l] = Round.round(RotatedHB[0],1);// - HB.x();
                                    corners[2*l + 1] = Round.round(RotatedHB[1],1);// - HB.y();
                                    // // System.out.print(" Corner ");
                                    // // System.out.print( l );
                                    // // System.out.print("[");
                                    // // System.out.print(corners[2*l]);
                                    // // System.out.print(",");
                                    // // System.out.print(corners[2*l+1]);
                                    // // System.out.println("]");
                                }
                                //System.out.println("Corners ;;;;;;;;;");
                                HB.x = corners[0];
                                HB.y = corners[1];
                                for (int l = 0; l < HB.xcoordinates.length; l++)
                                {
                                    corners[2*l] -= HB.x;
                                    corners[2*l+1] -= HB.y;
                                }
                                HB.updateHitbox(corners);
                                System.out.println("New Corners -------------------------------------");
                                for( int l = 0; l < HB.xcoordinates.length; l++ )
                                {
                                    System.out.println(l);
                                    System.out.print("[");
                                    System.out.print(HB.absx(l));
                                    System.out.print(",");
                                    System.out.print(HB.absy(l));
                                    System.out.println("]");
                                }
                                System.out.println("New Corners END -------------------------------------");
                                // System.out.print("thetacos[0]: ");
                                // System.out.print(thetacos[0]);
                                // System.out.print(" thetacos[1]: ");
                                // System.out.print(thetacos[1]);
                                double theta = Math.acos(thetacos[0]);
                                // System.out.print(" theta: ");
                                // System.out.println(theta);
                                // System.out.print("X =:" );
                                // System.out.println(HB.x);
                                // System.out.print("Y =:" );
                                // System.out.println(HB.y);
                                HB.rotate(theta,xx,yy);
                                // // // // // // System.out.print(" AF X:");
                                // // // // // // System.out.println(HB.AT.getTranslateX());
                                // // // // // // System.out.print(" AF Y:");
                                // // // // // // System.out.println(HB.AT.getTranslateY());
                                // // // // // // System.out.println("******************************");
                                HB.setTheta(theta);
                                if (thetacos[1] < 0)
                                {
                                    HB.setTheta(theta * -1);
                                }
                            }
                        }
                    }
                }
            }
        }
        // if (HB.x() != HB.AT.getTranslateX())
        // {
            // System.out.print("HB.x(): ");
            // System.out.println(HB.x());
            // System.out.print("ATX: ");
            // System.out.println(HB.AT.getTranslateX());
        // }
        // if (HB.y() != HB.AT.getTranslateY())
        // {
            // System.out.print("HB.y(): ");
            // System.out.println(HB.y());
            // System.out.print("ATY: ");
            // System.out.println(HB.AT.getTranslateY());
        // }
        // System.out.println("</collide>----------------------------------------------------------------");
    }
}
