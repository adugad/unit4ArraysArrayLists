import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        int dx1 = radar.getXVelocity();
        int dy1 = radar.getYVelocity();
        for(int i = 0; i < 5; i++)
        {
            radar.scan();
        }
        int dx2 = radar.getXVelocity();
        int dy2 = radar.getYVelocity();
        
        if( dx1 != dx2 || dy1 != dy2)
        {
            System.out.println("X velocity should be " +dx1+ " and Y velocity should be " +dy1);
            System.out.println("X velocity should not be " +dx2+ " and Y velocity should not be " +dy2);
        }
    }
    
}
