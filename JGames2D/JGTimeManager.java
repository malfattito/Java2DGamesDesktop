/***********************************************************************
*Name: JGTimeManager
*Description: Singleton class that controls and updates all time objects
*Author: Silvano Malfatti
*Date: 01/05/20
************************************************************************/

//Package declaration
package JGames2D;

public class JGTimeManager 
{
	//Class attributes
	private static long 	lLastFrameTime = 0;
	private static long 	lCurrentFrameTime = 0;
	private static long 	lCurrentTime = 0;
	private static final int MIN_INTERVAL = 20;
	
	/***********************************************************
	*Name: JGTimeManager
	*Description: Private and no parameters constructor
	*Parameters: None
	*Return: None
	************************************************************/
	private JGTimeManager()
	{ 
		lLastFrameTime = System.currentTimeMillis();
		lCurrentFrameTime = 0;
	}
	
	/*******************************************
	* Name: getCurrentFrameTime()
	* Description: returns the current frame time
	* Parameters: none
	* Returns: long
	******************************************/
	public static long getCurrentFrameTime()
	{
		return lCurrentFrameTime;
	}
	
	/*******************************************
	* Name: restart()
	* Description: restart the last frame time
	* Parameters: none
	* Returns: none
	******************************************/
	public static void restart()
	{
		lLastFrameTime = System.currentTimeMillis();
	}
	
	/*******************************************
	* Name: update()
	* Description: handle time events
	* Parameters: none
	* Returns: none
	******************************************/
	public static void update()
	{	
		//Verify the minimum frame time
		do
		{
			lCurrentTime = System.currentTimeMillis();
			lCurrentFrameTime = (lCurrentTime > lLastFrameTime) ? (lCurrentTime - lLastFrameTime) : 0;
			lLastFrameTime = (lCurrentTime >= lLastFrameTime) ? lLastFrameTime : lCurrentTime;
		}
		while(!(lCurrentFrameTime >= MIN_INTERVAL));
		
		lLastFrameTime = lCurrentTime;
	}
	
	/*******************************************
   	* Name: free
   	* Description: free resources
   	* Parameters: none
   	* Returns: none
   	******************************************/
    public void free() 
    {
    }
}
