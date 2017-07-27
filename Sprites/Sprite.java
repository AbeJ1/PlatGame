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
    public Sprite()
    {
        this.visible = true;
    }
    
    public void update(String URL,double x,double y, double scale)
    {
        // initialise instance variables
        this.scale = scale;
        this.x = x;
        this.y = y;
        this.img = getImgfromURL(URL);
        visible = true;
    }
    
    public void update(Image img,double x,double y, double scale)
    {
        // initialise instance variables
        this.scale = scale;
        this.x = x;
        this.y = y;
        this.img = img;
        visible = true;
    }
    
    public void update(double x,double y, double scale)
    {
        // initialise instance variables
        this.scale = scale;
        this.x = x;
        this.y = y;
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
    public Image getImgfromURL(String URL)
    {
        URL imgURL = getClass().getClassLoader().getResource(URL);
        ImageIcon icon = null;
        if(imgURL != null)
        {
            icon = new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file:" + URL);
        }
        return icon.getImage();
    }
    
    public void changeImg(String URL)
    {
        this.img = getImgfromURL(URL);
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
    
    public void draw (Graphics g, double winscale) 
    {
        int img_width = img.getWidth(null);
        int img_height = img.getHeight(null);
        g.drawImage(img, (int)x, (int)y, img_width+(int)x, img_height+(int)y,
                    0, 0, img_width, img_height, null);
    }
}
