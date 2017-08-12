package Sprites;


/**
 * Write a description of class Tile here.
 *
 * @author Comerstar
 * @version v0.1
 */
public class Tile extends HitBox
{
    // instance variables - replace the example below with your own
    private int type;
    private HitBox hb;
    
    /**
     * Constructor for objects of class Tile
     */
    
    private void init_images()
    {
       img = getImgfromURL("Resources/Wood Full Tile.png");
       double h = img.getHeight(null);
       double w = img.getWidth(null);
       updateHitbox(new double[]{0,0,0,h,h,w,w,0});
    }
    
    public Tile(double x, double y,double scale)
    {
       super();
       init_images();
       update(x,y,scale);
    }
    
    
}
