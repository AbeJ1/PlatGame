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
     * Initialise all image arrays
     */
    
    private void init_images()
    {
       IdleAn = new ArrayList<>();
       WalkAn = new ArrayList<>();
       AttAn = new ArrayList<>();
       FlyAn = new ArrayList<>();
       DieAn = new ArrayList<>();
       
       IdleAn.add(getImgfromURL("Resources/ASS1.png"));
       IdleAn.add(getImgfromURL("Resources/ASS2.png"));
       WalkAn.add(getImgfromURL("Resources/ASS1.png"));
       WalkAn.add(getImgfromURL("Resources/ASS2.png"));
       AttAn.add(getImgfromURL("Resources/ASS1.png"));
       AttAn.add(getImgfromURL("Resources/ASS2.png"));
       FlyAn.add(getImgfromURL("Resources/ASS1.png"));
       FlyAn.add(getImgfromURL("Resources/ASS2.png"));
       DieAn.add(getImgfromURL("Resources/ASS1.png"));
       DieAn.add(getImgfromURL("Resources/ASS2.png"));
    }
    
    /**
     * update params
     * 
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
    private void init_param()
    {
       double x = 0.0;
       double y = 0.0;
       double xgrav = 1.0;
       double ygrav = 1.0;
       double xm = 0.0;
       double ym = 0.0;
       int hp = 5;
       int FHP = 10;
       double maxxm = 10.0;
       double maxym = 10.0;
       double scale = 5.0;
       update(x,y, xgrav, ygrav, xm, ym, hp, FHP,maxxm,maxym,scale); 
    }
    
    public Player(double[] corners)
    {
        super(corners);
        init_param();
        init_images();
        animationTimer = 0;
        this.animationFull = animationFull;
        this.img = IdleAn.get(0);
        this.HB = new HitBox(new double[] {});
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
    
    /**
     * sets the state of the player
     * 
     * @param State the new State
     */
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
            //this.Physics.addtoXM(-2.0);
            this.x += 5;
            System.out.println("LEFT");
            System.out.println(this.x);
        }

        if (key == KeyEvent.VK_RIGHT) {
            //this.Physics.addtoXM(2.0);
            this.x -= 5;
            System.out.println("RIGHT");
            System.out.println(this.x);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        
    }
}
