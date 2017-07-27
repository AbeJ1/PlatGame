package Animation;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import Sprites.*;

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
    
    private Player playerA = new Player();
    
    /**
     * Constructor for objects of class GamePanel
     */
    public GamePanel()
    {
        //
        this.b = new Background(0,0,"Resources/Swords.png",0,0,0,5);
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
            repaint();
            beforeTime = System.currentTimeMillis();
            while (state == 0)
            {
                
            }
            while (state == 1)
            {
                
            }
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
        }
        catch(Exception e)
        {
            if (this.b == null)
            {
                System.out.println("Null Background");
            }
        }
    }
    
    public void drawBackground (Graphics g) 
    {
        g.drawImage(b.getImg(),0 ,0,getSize().width,getSize().height,(int)b.getx(),(int)b.gety(),getSize()
        .width/(int)b.getScale()+(int)b.getx(),getSize().height/(int)b.getScale()+(int)b.gety(),null);
    }
}
