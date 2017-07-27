package Animation;
import java.awt.*;
import javax.swing.*;
import java.net.URL;

/**
 * Write a description of class Background here.
 *
 * @author Comerstar
 * @version v0.1
 */
public class Background
{
    private double x;
    private double y;
    private Image img;
    private double scroll;
    private double xs;
    private double ys;
    private double scale;
    
    /**
     * Constructor for objects of class Background
     */
    public Background(double x,double y,String URL,double scroll,double xs, double ys, double scale)
    {
        this.x = x;
        this.y = y;
        this.scroll = scroll;
        this.scale = scale;
        URL imgURL = getClass().getClassLoader().getResource(URL);
        ImageIcon icon = null;
        if(imgURL != null)
        {
            icon = new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file:" + URL);
        }
        this.img = icon.getImage();
    }
    
    public void update() 
    {
        x += xs;
        y += ys;
    }
    
    public void setXS(double xs)
    {
        this.xs = xs;
    }
    
    public void setYS(double ys)
    {
        this.ys = ys;
    }
    
    public void setXYS(double xs, double ys)
    {
        setXS(xs);
        setYS(ys);
    }
    
    public void changeImage (String URL)
    {
        URL imgURL = getClass().getClassLoader().getResource(URL);
        ImageIcon icon = null;
        if(imgURL != null)
        {
            icon = new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file:" + URL);
            System.out.println("Couldn't find file:" + URL);
        }
        this.img = icon.getImage();
    }
    
    public void changeImage (Image img)
    {
        this.img = img;
    }
    
    public double getScale()
    {
        return this.scale;
    }
    
    public double getx()
    {
        return this.x;
    }
    
    public double gety()
    {
        return this.y;
    }
    
    public Image getImg()
    {
        return this.img;
    }
}
