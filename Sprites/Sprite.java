package Sprites;
import java.awt.*;
import javax.swing.*;
import java.net.URL;

/**
 * Abstract class that is the basic sprite, an image on the screen with a hitbox
 *
 * @author Comerstar
 * @version v0.1
 */
public abstract class Sprite
{
    // instance variables - replace the example below with your own
    protected Image img;
    protected double x;
    protected double y;
    private int intx;
    private int inty;
    protected boolean visible;
    protected double scale;

    /**
     * Constructor for objects of class Sprite
     */
    public Sprite(String URL,double x,double y, double scale)
    {
        // initialise instance variables
        this.scale = scale;
        this.x = x;
        this.y = y;
        URL imgURL = getClass().getClassLoader().getResource(URL);
        ImageIcon icon = null;
        if(imgURL != null)
        {
            icon = new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file:" + URL);
        }
        this.img = icon.getImage();
        visible = true;
    }
    
        public double getScale()
    {
        return this.scale;
    }
    
    /**
     * this means that the sprite will no longer be drawn
     */
    public void die()
    {
        visible = false;
    }
    
    public boolean getVisible()
    {
        return(this.visible);
    }
    
    /**
     * changes the image to a given URL. It has to load the image
     * 
     * @param URL the URL of the image
     */
    public void changeImg(String URL)
    {
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
    
    /**
     * Changes the image of the sprite it does not have to load the image
     * 
     * @param the image thay is to be the new image
     */
    public void changeImg(Image img)
    {
        this.img = img;
    }
    
    /**
     * returns the sprites image
     * 
     * @return the current image
     */
    public Image getImage()
    {
        return(this.img);
    }
    
    public void changePos (double x, double y)
    {
        this.x = x;
        this.y = y;
    }
}
