package Sprites;


/**
 * Write a description of class Tile here.
 *
 * @author Comerstar
 * @version v0.1
 */
public class Tile extends Sprite
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
       
    }
    
    public Tile(double x, double y,double scale)
    {
        // initialise instance variables
        super();
        init_images();
        update(x,y,scale);
    }
    
    
}
