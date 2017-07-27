package Sprites;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.ImageIcon;
import Physics.PhysicsEngine;

/**
 * Write a description of class Player here.
 *
 * @author Comerstar
 * @version v0.1
 */
public class Player extends Entity
{
    private ArrayList <Image> IdleAn;//standing animation
    private ArrayList <Image> WalkAn;//walking animation
    private ArrayList <Image> AttAn;//attacking animation
    private ArrayList <Image> FlyAn;//flying animation
    private ArrayList <Image> DieAn;//dying animation
    private HitBox HB;
    private int animationTimer;//where in the animation the player is in
    private int animationFull;//the point in which the animation restarts
    private int state;//the current state of the player e.g. in ninja power up
    private int AnimationState;
    public static final int NORMAL_STATE = 0;
    
    /**
     * The constructor for class Player
     * 
     * @param s the URL of the first image
     * @param x the initial x position of the player
     * @param y the initial y position of the player
     * @param xgrav the initial gravity in the x direction
     * @param ygrav the initial gravity in the y direction
     * @param xm the initial momentum of the player in the x direction
     * @param ym the initial momentum of the player in the y direction
     * @param hp the initial health of the player
     * @param FHP the maximum HP of the player
     * @param maxxm the maximum x momentum
     * @param maxym the maximum y momentum
     * @param the starting max Animation
     */
    public Player(String s,double x,double y, double xgrav, double ygrav, double xm, double ym, int hp, int FHP,
    double maxxm, double maxym, int animationFull,double scale)
    {
        super(s,x,y, xgrav, ygrav, xm, ym, hp, FHP,maxxm,maxym,scale);
        animationTimer = 0;
        this.animationFull = animationFull;
        this.HB = new HitBox(x,y,img.getWidth(null),img.getHeight(null));
        this.AnimationState = 0;
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
    
    /**
     * this changes the state of the player
     * 
     * @param state the new player state
     */
    public void setState(int State)
    {
        this.state = State;
    }
    
    public void setAnimationState(int State)
    {
        this.AnimationState = State;
    }
    
    /**
     * occurs when a key is pressed and moves the player
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            this.Physics.addtoXM(-2.0);
        }

        if (key == KeyEvent.VK_RIGHT) {
            this.Physics.addtoXM(2.0);
        }
    }

    public void keyReleased(KeyEvent e) {

    }
}
