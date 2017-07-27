package Sprites;
import java.util.ArrayList;
import java.awt.*;

/**
 * Write a description of class Enemy here.
 *
 * @author Comerstar
 * @version v0.1
 */
public class Enemy extends Entity
{
    private ArrayList <Image> IdleAn;//standing animation
    private ArrayList <Image> WalkAn;//walking animation
    private ArrayList <Image> AttAn;//attacking animation
    private ArrayList <Image> FlyAn;//flying animation
    private ArrayList <Image> DieAn;//dying animation
    private ArrayList <HitBox> Hitboxes;//hitboxes usually one
    private int animationTimer;
    private int animationFull;
    
    public Enemy()
    {
        super();
    }
    
    /**
     * Constructor for objects of class Enemy
     */
    public void update(String s,double x,double y, double xgrav, double ygrav, double xm, double ym, int hp, int FHP,
    double maxxm, double maxym, int animationFull,double scale,String JSONURL)
    {
        update(s,x,y, xgrav, ygrav, xm, ym, hp, FHP,maxxm,maxym,scale);
        this.animationFull = animationFull;
        animationTimer = 0;
    }
    
    /**
     * Increases the animation timer
     * resets it if it reaches the max Animation value
     */
    public void increaseAT ()
    {
        animationTimer++;
        animationTimer = animationTimer%animationFull;
    }
}
