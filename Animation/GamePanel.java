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
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.net.URL;
import java.awt.geom.AffineTransform;
import Physics.RotationTransform;


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
    private Tile tileB;
    private Image img;
    private AffineTransform AT;
    
    /**
     * Constructor for objects of class GamePanel
     */
    public GamePanel()
    {
        //
        addKeyListener(new TAdapter());
        setFocusable(true);
        this.b = new Background(0,0,"Resources/Swords.png",0,0,0,5);
        //jsonTest();
        gameInit();
    }
    
    public void jsonTest()
    {
        JSONParser parser = new JSONParser();
        try
        {
            FileReader FD = new FileReader("Resources/testdata");
            Object obj = parser.parse(FD);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray playerdata = (JSONArray) jsonObject.get("player");
            JSONArray tiledata = (JSONArray) jsonObject.get("tile");
            Iterator<Double> iterator = playerdata.iterator();
            while (iterator.hasNext()){
                System.out.println(iterator.next());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void gameInit() {
        playerA = new Player();
        tileA = new Tile(100, 100,1,1);
        tileB = new Tile(200, 100,1,2);
        //playerA.updateHitbox(new double[]{0,0,0,50,30,50,30,0});
        if (animator == null ){
            animator = new Thread(this);
            animator.start();
        }
    }
    
    /**
     * changes the image to a given URL. It has to load the image
     * 
     * @param URL the URL of the image
     */
    public Image getImgfromURL(String URL)
    {
        URL imgURL = getClass().getClassLoader().getResource(URL);
        ImageIcon icon = null;
        if(imgURL != null)
        {
            icon = new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file:" + URL);
        }
        return icon.getImage();
    }
    
    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;
        playing = true;
        state = 0;
        img = getImgfromURL("Resources/Wood Diagonal BR.png");
        AT = new AffineTransform();
        //states
        //0 Menu
        //1 Level
        //2 World
        beforeTime = System.currentTimeMillis();
        AT.rotate(Math.toRadians(45),50,50);
        AT.translate(100,0);
        //AT.rotate(Math.toRadians(45),50,50);
        double tx = 0;
        double ty = 0;
        while (playing) {
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;
            int collides = 5;
            //AT.translate(100,0);
            
            //AT.translate(100,0);
            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
            playerA.updateState();
            //playerA.updateHitbox(new double[]{0,0,0,50,30,50,30,0});
            tileA.Collide(playerA);
            tileB.Collide(playerA);
            
            // System.out.print("ATXR: ");
            // System.out.println(AT.getTranslateX());
            // System.out.print("ATYR: ");
            // System.out.println(AT.getTranslateY());
            double[] theta = RotationTransform.rotation_angle(0,1,1/Math.sqrt(2),1/Math.sqrt(2));
            double[] results = RotationTransform.rotate(tx,ty,50,50,theta);
            //System.out.print("results[0]: ");
            //System.out.println(results[0]);
            //System.out.print("results[1]: ");
            //System.out.println(results[1]);
            tx = results[0];
            ty = results[1];
            
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
            Graphics2D g2 = (Graphics2D) g;
            drawBackground(g);
            playerA.drawAT(g2,1);
            //System.out.print("xm: ");
            //System.out.println(playerA.getPhysics().getXMomentum());
            //System.out.print("ym: ");
            //System.out.println(playerA.getPhysics().getYMomentum());
            tileA.draw(g,1);
            tileB.draw(g,1);
            g2.drawImage(img,AT,null);
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
