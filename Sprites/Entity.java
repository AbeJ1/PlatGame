package Sprites;
import java.awt.*;
import Physics.PhysicsEngine;

/**
 * This is a sprite with health and AI 
 *
 * @author Comerstar
 * @version v0.1
 */
public class Entity extends Sprite
{
    protected int health;
    protected int fullHP;
    protected PhysicsEngine Physics;
    /**
     * Constructor for objects of class Entity
     */
    public Entity(String s,double x,double y, double xgrav, double ygrav, double xm, double ym, int hp, 
    int FHP,double maxxm,double maxym, double scale)
    {
        // initialise instance variables
        super(s,x,y,scale);
        health = hp;
        fullHP = FHP;
        Physics = new PhysicsEngine(x,y,xgrav,ygrav,xm,ym,maxxm,maxym);
    }
    
    /**
     * changes the objects health
     * 
     * @param Hp is the amount you add to its health
     */
    public void changeHP(int HP)
    {
        health += HP;
    }
    
    /**
     * this changes the HP and prevents the HP being larger than the max HP
     * 
     * @param HP is how much the health increases by
     */
    public void limitedChangeHP(int HP)
    {
        changeHP(HP);
        if (health > fullHP)
        {
            restoreAllHealth();
        }
    }
    
    /**
     * Restores health to max health
     */
    public void restoreAllHealth ()
    {
        health = fullHP;
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
