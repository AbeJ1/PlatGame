package Animation;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import Sprites.*;
import java.util.Iterator;
import java.awt.event.*;
import java.util.Random;
import java.util.ArrayList;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * A GamePanel
 * 
 * @author Comerstar
 * @version v0.1
 */
public class GamePanel extends JPanel implements Runnable
{
    private boolean playing;
    private int state;
    private int level;
    private Background b;
    public static final int DELAY = 33;
    public static final int MENUVAL = 0;
    public static final int LEVELVAL = 1;
    public static final int WORLDVAL = 2;
    
    private Thread animator;
    
    private Player playerA;
    private Tile tileA;
    
    /**
     * Constructor for objects of class GamePanel
     */
    public GamePanel()
    {
        //
        addKeyListener(new TAdapter());
        setFocusable(true);
        this.b = new Background(0,0,"Resources/Swords.png",0,0,0,5);
        gameInit();
    }
    
    public void gameInit() {
        playerA = new Player();
        tileA = new Tile(100, 0,1);
        
        if (animator == null ){
            animator = new Thread(this);
            animator.start();
        }
    }
    
    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;
        playing = true;
        state = 0;
        //states
        //0 Menu
        //1 Level
        //2 World
        beforeTime = System.currentTimeMillis();
        while (playing) {
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;
            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
            playerA.updateState();
            tileA.Collide(playerA);
            //if (tileA.testCollide(playerA))
            //{
                //break;
            //}
            repaint();
            beforeTime = System.currentTimeMillis();
            playerA.increaseAT();
            playerA.setAnimationImage();
        }
        
    }
    
    @Override
    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, getSize().width, getSize().height);
        try{
            drawBackground(g);
            playerA.draw(g,1);
            tileA.draw(g,1);
        }
        catch(Exception e)
        {
            if (this.b == null)
            {
                System.out.println("Null Background");
            }
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
    public void drawBackground (Graphics g) 
    {
        g.drawImage(b.getImg(),0 ,0,getSize().width,getSize().height,(int)b.getx(),(int)b.gety(),getSize()
        .width/(int)b.getScale()+(int)b.getx(),getSize().height/(int)b.getScale()+(int)b.gety(),null);
    }
    
    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e){
            playerA.keyReleased(e);
        }
        
        @Override 
        public void keyPressed(KeyEvent e){
            playerA.keyPressed(e);
        }
    }
}
