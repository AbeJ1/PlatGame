package Sprites;


/**
 * Write a description of class HitBox here.
 *
 * @author Comerstar
 * @version v0.1
 */
public class HitBox
{
    private double x;
    private double y;
    private double width;
    private double height;
    private double ex;//(edge x)
    private double ey;//(edge y)
    private double xs;
    private double ys;
    private double slx;
    private double sly;
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;
    private double x4;
    private double y4;

    /**
     * Constructor for objects of class HitBox
     * 
     * @param x the x position
     * @param y the y position
     * @param width the width of the hitbox
     * @param height the height of the hitbox
     */
    public HitBox(double x, double y, double width, double height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.ex = this.x + this.width;
        this.ey = this.y + this.height;
        this.xs = 0;
        this.ys = 0;
        this.slx = 0;
        this.sly = 0;
    }
    
    public HitBox(double x, double y, double width, double height,double xs, double ys, double slx,double sly)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.ex = this.x + this.width;
        this.ey = this.y + this.height;
        this.xs = xs;
        this.ys = ys;
        this.slx = slx;
        this.sly = sly;
        this.x2 = 0.000000000001;
        this.y2 = 0.000000000001;
        this.x3 = 0.000000000001;
        this.y3 = 0.000000000001;
        this.x4 = 0.000000000001;
        this.y4 = 0.000000000001;
        this.x1 = slx;
        this.y1 = sly;
        if (this.x >= this.x && this.x <= this.ex && this.y >= this.y && 
            this.y <= this.ey && (this.x * this.xs) + this.slx <= (this.y * this.ys) + this.sly)
        {
            this.x2 = this.x;
            this.y2 = this.y;
        }
        if (this.ex >= this.x && this.ex <= this.ex && this.ey >= this.y && 
            this.ey <= this.ey && (this.ex * this.xs) + this.slx <= (this.ey * this.ys) + this.sly)
        {
            if (this.x2 == 0.000000000001 && this.y2 == 0.000000000001)
            {
                this.x2 = this.ex;
                this.y2 = this.ey;
            }
            else
            {
                this.x3 = this.ex;
                this.y3 = this.ey;
            }
        }
        if (this.x >= this.x && this.x <= this.ex && this.ey >= this.y && 
            this.ey <= this.ey && (this.x * this.xs) + this.slx <= (this.ey * this.ys) + this.sly)
        {
            if (this.x2 == 0.000000000001 && this.y2 == 0.000000000001)
            {
                this.x2 = this.x;
                this.y2 = this.ey;
            }
            else if (this.x3 == 0.000000000001 && this.y3 == 0.000000000001)
            {
                this.x3 = this.x;
                this.y3 = this.ey;
            }
            else
            {
                this.x4 = this.x;
                this.y4 = this.ey;
            }
        }
        
        if (this.ex >= this.x && this.ex <= this.ex && this.y >= this.y && 
            this.y <= this.ey && (this.ex * this.xs) + this.slx <= (this.y * this.ys) + this.sly)
        {
            if (this.x2 == 0.000000000001 && this.y2 == 0.000000000001)
            {
                this.x2 = this.ex;
                this.y2 = this.y;
            }
            else if (this.x3 == 0.000000000001 && this.y3 == 0.000000000001)
            {
                this.x3 = this.ex;
                this.y3 = this.y;
            }
            else
            {
                this.x4 = this.ex;
                this.y4 = this.y;
            }
        }
    }
    
    /**
     * Constructor for objects of class HitBox
     * 
     * @param x the x position
     * @param y the y position
     * @param x2 the second x value larger than x
     * @param y2 the second y value larger than y
     * @param r used to differ the two constructors has no purpose
     */
    public HitBox(double x, double y, double x2, double y2,int r)
    {
        this.x = x;
        this.y = y;
        this.width = x2 - x;
        this.height = y2 - y;
        this.ex = x2;
        this.ey = y2;
    }
    
    /**
     * Changes the value of x and updates other hitbox related variables
     * 
     * @param x is the new value of x
     */
    public void changeX(double x)
    {
        this.x = x;
        this.ex = this.x + this.width;
    }
    
    /**
     * Changes the value of width and updates other hitbox related variables
     * 
     * @param width is the new value of width
     */
    public void changeWidth(double width)
    {
        this.width = width;
        this.ex = this.x + this.width;
    }
    
    /**
     * Changes the value of height and updates other hitbox related variables
     * 
     * @param height is the new height
     */
    public void changeHeight(double height)
    {
        this.height = height;
        this.ey = this.y + this.height;
    }
    
    /**
     * updates the values of ex, mx, ey, my
     */
    public void updateEM()
    {
        this.ex = this.x + this.width;
        this.ey = this.y + this.height;
    }
    
    /**
     * Changes the value of y and updates other hitbox related variables
     * 
     * @param y is the new value of y
     */
    public void changeY(double y)
    {
        this.y = y;
        this.ey = this.y + this.height;
    }
    
    /**
     * changes x and y and fully updates the hitbox
     * 
     * @param x the new x value
     * @param y the new y value
     */
    public void changeXY(double x, double y)
    {
        changeX(x);
        changeY(y);
    }
    
    /**
     * returns the x of a hitbox
     * 
     * @return the x value
     */
    public double getx()
    {
        return (this.x);
    }
    
    /**
     * returns the y of a hitbox
     * 
     * @return the y value
     */
    public double gety()
    {
        return (this.y);
    }
    
    /**
     * returns the x of a hitbox
     * 
     * @return the x value
     */
    public double getex()
    {
        return (this.ex);
    }
    
    /**
     * returns the y of a hitbox
     * 
     * @return the y value
     */
    public double getey()
    {
        return (this.ey);
    }
    
    /**
     * returns the Width of a hitbox
     * 
     * @return the Width value
     */
    public double getWidth()
    {
        return (this.width);
    }
    
    /**
     * returns the Height of a hitbox
     * 
     * @return the Height value
     */
    public double getHeight()
    {
        return (this.height);
    }
    
    /**
     * returns whether to hitboxes collide or not
     * 
     * @param hitbox which we sre testing collides with this
     * @return whether the hitboxes collide or not
     */
    public boolean testCollide(HitBox HB)
    {
        boolean collide = false;
        //if (HB.x > this.x  - HB.width)
        //{
        //    System.out.println("HB.x > this.x - HB.width");
        //}
        //if (HB.x < this.ex)
        //{
        //    System.out.println("HB.x < this.ex");
        //}
        //if (HB.y > this.y  - HB.height)
        //{
        //    System.out.println("HB.y > this.y - HB.height");
        //}
        //if (HB.y < this.ey)
        //{
        //    System.out.println("HB.y < this.y");
        //}
        if (this.xs == 0 && this.ys == 0 && HB.xs == 0 && HB.ys == 0)
        {
            if (HB.x > this.x  - HB.width && HB.x < this.ex && HB.y > this.y  - HB.height && 
            HB.y < this.ey)
            {
                collide = true;
            }
        }
        else if (this.xs == 0 && this.ys == 0)
        {
            if (this.x > HB.x  - this.width && this.x < HB.ex && this.y > HB.y  - this.height && 
            this.y < HB.ey && (this.x * HB.xs) + HB.slx + this.width < (this.y * HB.ys) + HB.sly + 
            this.height)
            {
                collide = true;
            }
        }
        else if (HB.xs == 0 && HB.ys == 0)
        {
            if (HB.x > this.x  - HB.width && HB.x < this.ex && HB.y > this.y  - HB.height && 
            HB.y < this.ey && (HB.x * this.xs) + this.slx + HB.width < (HB.y * this.ys) + this.sly + 
            HB.height)
            {
                collide = true;
            }
        }
        else
        {
            if ((HB.x1 > this.x && HB.x1 < this.ex && HB.y1 > this.y && HB.y1 < this.ey && (HB.x1 * this.xs) 
            + this.slx < (HB.y1 * this.ys) + this.sly) && (HB.x2 > this.x  - HB.width && HB.x2 < this.ex && 
            HB.y2 > this.y  - HB.height && HB.y2 < this.ey && (HB.x2 * this.xs) + this.slx + HB.width < (
            HB.y2 * this.ys) + this.sly + HB.height) && (HB.x3 > this.x  - HB.width && HB.x3 < this.ex && 
            HB.y3 > this.y  - HB.height && HB.y3 < this.ey && (HB.x3 * this.xs) + this.slx + HB.width < (
            HB.y3 * this.ys) + this.sly + HB.height)&& (HB.x4 > this.x  - HB.width && HB.x4 < this.ex && 
            HB.y4 > this.y  - HB.height && HB.y4 < this.ey && (HB.x4 * this.xs) + this.slx + HB.width < (
            HB.y4 * this.ys) + this.sly + HB.height))
            {
                collide = true;
            }
        }
        return (collide);
    }
}
