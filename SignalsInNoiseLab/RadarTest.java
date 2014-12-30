import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Scanner;

/**
 * The test class RadarTest.
 *
 * @author  Annika Dugad
 * @version 15 Dec 2014
 */
public class RadarTest
{
    /**
     * Default constructor for test class RadarTest
     */
    public RadarTest()
    {
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
    public void testState()
    {
        Radar radar = new Radar(100,100);
        Scanner in = new Scanner(System.in);
        System.out.print("Set a positive integer value between 0 and 100 for the row location of the monster: ");
        int rowLocation = in.nextInt();
        System.out.print("Set a positive integer value between 0 and 100 for the column location of the monster: ");
        int colLocation = in.nextInt();
        radar.setMonsterLocation(rowLocation,colLocation);
        System.out.print("Set an integer value between -5 and 5 for the x velocity of the monster: ");
        int dx1 = in.nextInt();
        radar.setXVelocity(dx1);
        System.out.print("Set an integer value between -5 and 5 for the y velocity of the monster: ");
        int dy1 = in.nextInt();
        radar.setYVelocity(dy1);
        
        System.out.println("Expected x velocity: " +dx1);
        System.out.println("Expected y velocity: " +dy1);
        
        for(int i = 0; i < 5; i++)
        {
            radar.scan();
        }
        int dx2 = radar.getXVelocity();
        int dy2 = radar.getYVelocity();
        
        System.out.println("Found x velocity: "+dx2);
        System.out.println("Found y velocity: "+dy2);
        
        if( dx1 == dx2)
        {
            assertSame("x velocity is "+ dx1, dx1, dx2);
        }
        else
        {
            assertSame("x velocity should be " + dx1 + " but came up as " + dx2,dx1,dx2);
        }
        
        if( dy1 == dy2)
        {
            assertSame("y velocity is " + dy1,dy1, dy2);
        }
        else
        {
            assertSame("y velocity should be " + dy1 + " but came up as " + dy2,dy1,dy2);
        }
    }
    
}
