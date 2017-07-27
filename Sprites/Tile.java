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
    private int xscreen;
    private int yscreen;
    private int type;
    
    /**
     * Constructor for objects of class Tile
     */
    public Tile(String s,double x, double y, int xscreen, int yscreen,double scale)
    {
        // initialise instance variables
        super();
        update(s,x,y,scale);
        this.xscreen = xscreen;
        this.yscreen = yscreen;
    }
    
    /**
     * returns this tiles x screen location
     * 
     * @return the x screen value
     */
    public int getXscreen()
    {
        return this.xscreen;
    }
    
    /**
     * returns this tiles y screen location
     * 
     * @return the y screen value
     */
    public int getYscreen()
    {
        return this.yscreen;
    }
}
