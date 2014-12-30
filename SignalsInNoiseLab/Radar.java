
/**
 * The model for radar scan and accumulator
 * 
 * @author @Annika Dugad
 * @version 15 December 2014
 */
public class Radar
{
    
    // stores whether each cell triggered detection for the current scan of the radar
    private boolean[][] currentScan;
    private boolean[][] preScan;
    // value of each cell is incremented for each scan in which that cell triggers detection 
    private int[][] accumulatorV;
    // location of the monster
    private int monsterLocationRow;
    private int monsterLocationCol;

    // variables for the velocity of the monster
    private int dx;
    private int dy;
    
    // probability that a cell will trigger a false detection (must be >= 0 and < 1)
    private double noiseFraction;
    
    // number of scans of the radar since construction
    private int numScans;

    /**
     * Constructor for objects of class Radar
     * 
     * @param   rows    the number of rows in the radar grid
     * @param   cols    the number of columns in the radar grid
     */
    public Radar(int rows, int cols)
    {
        // initialize instance variables
        currentScan = new boolean[rows][cols]; // elements will be set to false
        preScan = new boolean[rows][cols];
        accumulatorV = new int[11][11];
        // randomly set the location of the monster (can be explicity set through the
        //  setMonsterLocation method
        monsterLocationRow = (int)((Math.random() * rows) / 10.0);
        monsterLocationCol = (int)((Math.random() * cols) / 10.0);
        
        dx = (int) (Math.random() * 5);
        dy = (int) (Math.random() * 5);
        
        noiseFraction = 0.10;
        numScans= 0;
    }
    
    /**
     * Performs a scan of the radar. Noise is injected into the grid and the accumulator is updated.
     * 
     * @pre  dx and dy are integer values between 5 and -5
     * 
     */
    public void scan()
    {
        // zero the current scan grid
        for(int row = 0; row < currentScan.length; row++)
        {
            for(int col = 0; col < currentScan[0].length; col++)
            {
                currentScan[row][col] = false;
            }
        }
        
        // detect the monster
        
        currentScan[monsterLocationRow][monsterLocationCol] = true;
        
        // inject noise into the grid
        injectNoise();
        
        int xVelocity = 0;
        int yVelocity = 0;
        
        if(numScans > 0)
        {
            for(int row = 0; row < currentScan.length; row++)
            {
                for(int col = 0; col < currentScan[0].length; col++)
                {
                    if(preScan[row][col] == true)
                    {
                        for(int row2 = 0; row2 < currentScan.length; row2++)
                        {
                            for(int col2 = 0; col2 < currentScan[0].length; col2++)
                            {
                                if(currentScan[row2][col2] == true)
                                {
                                    xVelocity = col2 - col;
                                    yVelocity = row2 - row;
                                    if((xVelocity >= -5) && (xVelocity <=5) && (yVelocity >= -5) && (yVelocity <=5))
                                    {
                                        xVelocity += 5;
                                        yVelocity += 5;
                                        accumulatorV[yVelocity][xVelocity]++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            for(int row = 0; row < accumulatorV.length; row++)
                {
                    for(int col = 0; col < accumulatorV[0].length; col++)
                    {
                        if ( accumulatorV[row][col] == numScans)
                        {
                            dx = col - 5;
                            dy = row - 5;
                        }
                    }
                }
        }
        
        for(int row = 0; row < currentScan.length; row++)
        {
            for(int col = 0; col < currentScan[0].length; col++)
            {
                preScan[row][col] = currentScan[row][col];
            }
        }
        
        if(monsterLocationCol < 100 || monsterLocationRow < 100)
        {
            monsterLocationCol += dx;
            monsterLocationRow += dy;
        }
        if(monsterLocationCol >= 100)
        {
            monsterLocationCol -= 100;
        }
        if(monsterLocationRow >= 100)
        {
            monsterLocationRow -= 100;
        }
        // keep track of the total number of scans
        numScans++;
    }

    /**
     * Sets the location of the monster
     * 
     * @param   row     the row in which the monster is located
     * @param   col     the column in which the monster is located
     * @pre row and col must be within the bounds of the radar grid
     */
    public void setMonsterLocation(int row, int col)
    {
        // remember the row and col of the monster's location
        monsterLocationRow = row;
        monsterLocationCol = col;
        
        // update the radar grid to show that something was detected at the specified location
        currentScan[row][col] = true;
    }
    
     /**
     * Sets the probability that a given cell will generate a false detection
     * 
     * @param   fraction    the probability that a given cell will generate a flase detection expressed
     *                      as a fraction (must be >= 0 and < 1)
     */
    public void setNoiseFraction(double fraction)
    {
        noiseFraction = fraction;
    }
    
    /**
     * Gets the x component of the monster's velocity
     * 
     * @return dx the horizontal component of velocity
     */
    public int getXVelocity()
    {
        return dx;
    }
    
    /**
     * Gets the y component of the monster's velocity
     * 
     * @return dy the vertical component of velocity
     */
    public int getYVelocity()
    {
        return dy;
    }
    
     /**
     * Sets the x component of the monster's velocity
     * 
     * @param x the horizontal component of velocity
     */
    public void setXVelocity(int x)
    {
        dx = x;
    }
    
     /**
     * Sets the y component of the monster's velocity
     * 
     * @param y the vertical component of velocity
     */
    public void setYVelocity(int y)
    {
        dy = y;
    }
    
    /**
     * Sets cells as falsely triggering detection based on the specified probability
     * 
     */
    private void injectNoise()
    {
        for(int row = 0; row < currentScan.length; row++)
        {
            for(int col = 0; col < currentScan[0].length; col++)
            {
                // each cell has the specified probablily of being a false positive
                if(Math.random() < noiseFraction)
                {
                    currentScan[row][col] = true;
                }
            }
        }
    }
    
}
