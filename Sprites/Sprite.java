package Sprites;
import java.awt.*;
import javax.swing.*;
import java.net.URL;
import java.awt.geom.AffineTransform;
import Physics.PhysicsEngine;

/**
 * Abstract class that is the basic sprite, an image on the screen with a hitbox
 *
 * @author Comerstar
 * @version v0.1
 */
public class Sprite
{
    // instance variables - replace the example below with your own
    protected Image img;
    protected double x;
    protected double y;
    protected double oldx;
    protected double oldy;
    protected boolean visible;
    protected double scale;
    protected double theta;
    protected AffineTransform AT;
    protected AffineTransform AR;
    protected PhysicsEngine Physics;

    /**
     * Constructor for objects of class Sprite
     */
    public Sprite()
    {
        this.visible = true;
        AT = new AffineTransform();
        AR = new AffineTransform();
    }
    
    public void update(String URL,double x,double y, double scale)
    {
        // initialise instance variables
        this.scale = scale;
        this.x = x;
        this.y = y;
        oldx = x;
        oldy = y;
        this.img = getImgfromURL(URL);
        AT.setToTranslation(this.x - AR.getTranslateX(),this.y - AR.getTranslateY());
        //AT.translate(this.x - AT.getTranslateX(),this.y - AT.getTranslateY());
        //AT.scale(scale-AT.getScaleX(),scale-AT.getScaleY());
        visible = true;
    }
    
    public void update(Image img,double x,double y, double scale)
    {
        // initialise instance variables
        this.scale = scale;
        this.x = x;
        this.y = y;
        oldx = x;
        oldy = y;
        this.img = img;
        visible = true;
        AT.setToTranslation(this.x - AR.getTranslateX(),this.y - AR.getTranslateY());
        //AT.translate(this.x - AT.getTranslateX(),this.y - AT.getTranslateY());
        //AT.scale(scale-AT.getScaleX(),scale-AT.getScaleY());
    }
    
    public void update(double x,double y, double scale)
    {
        // initialise instance variables
        this.scale = scale;
        this.x = x;
        this.y = y;
        oldx = x;
        oldy = y;
        visible = true;
        AT.setToTranslation(this.x - AR.getTranslateX(),this.y - AR.getTranslateY());
        //AT.translate(this.x - AT.getTranslateX(),this.y - AT.getTranslateY());
        //AT.scale(scale-AT.getScaleX(),scale-AT.getScaleY());
    }
    
    public void changeXY (double x, double y)
    {
        // System.out.print("Before x ");
        // System.out.print(this.x);
        // System.out.print(" Before y ");
        // System.out.println(this.y);
        //System.out.print("After x ");
        //System.out.println(x);
        //System.out.print("old x ");
        //System.out.println(oldx);
        this.x += x;
        this.y += y;
        // System.out.print("After x ");
        // System.out.print(this.x);
        // System.out.print(" After y ");
        // System.out.println(this.y);
        // AT.setToTranslation(this.x - AR.getTranslateX(),this.y - AR.getTranslateY());
        //AT.translate(this.x - AT.getTranslateX(),this.y - AT.getTranslateY());
    }
    
    public double getScale()
    {
        //AT.scale(scale-AT.getScaleX(),scale-AT.getScaleY());
        return this.scale;
    }
    
    public double x()
    {
        return x;
    }
    
    public double y()
    {
        return y;
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
    
    public void rotate(double theta, double fixx, double fixy)
    {
        //AT.translate(-fixx,-fixy);
        // System.out.print("BB AT X: ");
        // System.out.println(AT.getTranslateX());
        // System.out.print("BB AT Y: ");
        // System.out.println(AT.getTranslateY());
        // AT.setToTranslation(this.x - AR.getTranslateX(),this.y - AR.getTranslateY());
        //AT.translate(this.x - AT.getTranslateX(),this.y - AT.getTranslateY());
        AR.rotate(theta,fixx,fixy);
        //AR.rotate(theta);
        //AT.translate(fixx,fixy);
        // System.out.print("fixx: ");
        // System.out.println(fixx);
        // System.out.print("fixy: ");
        // System.out.println(fixy);
        // System.out.print("After AT X: ");
        // System.out.println(AT.getTranslateX());
        // System.out.print("After AT Y: ");
        // System.out.println(AT.getTranslateY());
        // AT.setToTranslation(this.x - AR.getTranslateX(),this.y - AR.getTranslateY());
        //AT.setToTranslation(this.x,this.y);
        // System.out.print("this.x - ATX: ");
        // System.out.println(this.x - AT.getTranslateX());
        // System.out.print("this.y - ATY: ");
        // System.out.println(this.y - AT.getTranslateY());
        // System.out.print("this.x: ");
        // System.out.println(this.x);
        // System.out.print("this.y: ");
        // System.out.println(this.y);
        // System.out.print("After AT Trans X: ");
        // System.out.println(AT.getTranslateX());
        // System.out.print("After AT Trans Y: ");
        // System.out.println(AT.getTranslateY());
        // System.out.print("theta: ");
        // System.out.println(theta);
        // System.out.print("this.theta: ");
        // System.out.println(this.theta);
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
        AT.setToTranslation(this.x - AR.getTranslateX(),this.y - AR.getTranslateY());
    }
    
    public void draw (Graphics g, double winscale) 
    {
        int img_width = img.getWidth(null);
        int img_height = img.getHeight(null);
        g.drawImage(img, (int)x, (int)y, img_width+(int)x, img_height+(int)y,
                    0, 0, img_width, img_height, null);
    }
    
    
    public void drawAT (Graphics2D g, double winscale) 
    {
        //AT.setToTranslation(this.x,this.y);
        AT.setToTranslation(this.x - AR.getTranslateX(),this.y - AR.getTranslateY());
        AR.preConcatenate(AT);
        g.drawImage(img, AR, null);
        //System.out.print("ATX: ");
        //AT.translate(this.x - AT.getTranslateX(),this.y - AT.getTranslateY());
        //System.out.println(AT.getTranslateX());
        //System.out.print("ATY: ");
        //AT.translate(this.x - AT.getTranslateX(),this.y - AT.getTranslateY());
        //System.out.println(AT.getTranslateY());
        //System.out.print("x: ");
        //System.out.println(x);
        //System.out.print("y: ");
        //System.out.println(y);
        //System.out.print("x == ATX: ");
        //AT.translate(this.x - AT.getTranslateX(),this.y - AT.getTranslateY());
        //System.out.println(x == AT.getTranslateX());
        //System.out.print("y == ATY: ");
        //AT.translate(this.x - AT.getTranslateX(),this.y - AT.getTranslateY());
        //System.out.println(y == AT.getTranslateY());
    }
    
    public void setXY( double x, double y, double oldx, double oldy )
    {
        this.x = x;
        this.y = y;
        this.oldx = oldx;
        this.oldy = oldy;
        AT.translate(this.x - AT.getTranslateX(),this.y - AT.getTranslateY());
    }
    
    public PhysicsEngine getPhysics()
    {
        return this.Physics;
    }
}
