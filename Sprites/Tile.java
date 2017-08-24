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
    
    /**
     * Constructor for objects of class Tile
     */
    
    private void init_images(int type)
    {
       if (type == 1)
       {
           img = getImgfromURL("Resources/Wood Full Tile.png");
           double h = img.getHeight(null);
           double w = img.getWidth(null);
           updateHitbox(new double[]{0,0,0,h,w,h,w,0});
       }
       else if (type == 2)
       {
           img = getImgfromURL("Resources/Wood Diagonal BL.png");
           double h = img.getHeight(null);
           double w = img.getWidth(null);
           updateHitbox(new double[]{0,0,0,h,w,h});
       }
    }
    
    public Tile(double x, double y,double scale, int type)
    {
       super();
       init_images(type);
       update(x,y,scale);
    }
    
    
}
