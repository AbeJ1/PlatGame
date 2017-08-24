package Sprites;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SpriteTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SpriteTest
{
    /**
     * Default constructor for test class SpriteTest
     */
    public SpriteTest()
    {
    }
    
    public boolean CollisionTest(double px, double py, double oldpx, double oldpy, double tx, double ty,
                                 double[] phitbox)
    {
        Player playerA = new Player();
        Tile tileA = new Tile(tx, ty,1,1);
        playerA.updateHitbox(phitbox);
        playerA.setXY(px, py, oldpx, oldpy);
        boolean collide = tileA.testCollide(playerA);
        System.out.print("Colliding is ");
        System.out.println(collide);
        return collide;
    }
    
    public HitBox CollisionTestFail(double px, double py, double oldpx, double oldpy, double tx, double ty,
                                 double[] phitbox)
    {
        Player playerA = new Player();
        Tile tileA = new Tile(tx, ty,1,1);
        playerA.updateHitbox(phitbox);
        playerA.setXY(px, py, oldpx, oldpy);
        tileA.Collide(playerA);
        return playerA;
    }
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void Collision()
    {
        System.out.println("Collision");
        CollisionTest(101,140,100,140,130,100,new double[]{0,0,0,50,30,50,30,0});
        CollisionTestFail(101,140,100,140,130,100,new double[]{0,0,0,50,30,50,30,0});
    }
    
    @Test
    public void CollisionWrong()
    {
        System.out.println("CollisionWrong");
        CollisionTest(101,100,99,100,130,130,new double[]{0,0,0,50,50,30,30,0});
        CollisionTestFail(101,100,99,100,130,130,new double[]{0,0,0,50,50,30,30,0});
    }
}

