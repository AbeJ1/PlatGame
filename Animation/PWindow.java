package Animation;
import javax.swing.JFrame;
import java.awt.EventQueue;

/**
 * A Jframe that is the Window
 * It does the basic Window stuff
 * 
 * @author Comerstar
 * @version v0.1
 */
public class PWindow extends JFrame
{
    
    
    /**
     * Constructor for objects of class Window
     */
    public PWindow()
    {
        init();
    }
    
    /**
     * Initializes the settings for the window
     */
    private void init(){
        // initialise instance variables
        add(new GamePanel());

        setResizable(true);
        setSize(600,400);
        pack();
        
        setTitle("Platformer Game");    
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * Creates a window and ensures it is visible
     */
    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() 
        {
            
            @Override
            public void run() {                
                JFrame ex = new PWindow();
                ex.setVisible(true);                
            }
        }
        );
    }
}
