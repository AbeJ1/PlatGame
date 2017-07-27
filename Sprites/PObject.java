package Sprites;
import java.awt.*;
import javax.swing.*;
import java.net.URL;

/**
 * This is a sprite with physics
 * 
 * @author Comerstar 
 * @version v0.1
 */
public class PObject extends Sprite
{
    //gravity vector
    private double xgrav;
    private double ygrav;
    private double xm;
    private double ym;
    /**
     * Constructor for objects of class Object
     * 
     * @param s the Path of the image the PObject will be
     * @param x the x location of the PObject
     * @param y the y location of the PObject
     * @param xgrav is the gravity vector in the x direction
     * @param ygrav is the gravity vector in the y direction
     * @param 
     */
    public PObject(String s,double x,double y, double xgrav, double ygrav, double xm, double ym,double scale)
    {
        super();
        update(s,x,y,scale);
        this.xgrav = xgrav;
        this.ygrav = ygrav;
        this.xm = xm;
        this.ym = ym;
    }
    
    public void die()
    {
        
    }
    
    /**
     * this changes the image to an image found from an URL. It has to load the image
     * 
     * @param URL this is the path to the image
     */
    public void changeImage(String URL)
    {
        this.changeImg(URL);
    }
    
    /**
     * this changes the image to an image that is already loaded
     * 
     * @param img this is the image
     */
    public void changeImage(Image img)
    {
        this.changeImg(img);
    }
}
