/***********************************************************************
*Name: JGVector2D
*Description: This class represents a 2D vector
*Author: Silvano Malfatti
*Date: 01/05/20
************************************************************************/

//Package declaration
package JGames2D;

public class JGVector2D 
{
	//Class attributes
	private double dX;
	private double dY;
	
	/***********************************************************
	*Name: JGVetor2D()
	*Description: No parameters constructor
	*Parameters: None
	*Return: None
	************************************************************/
	public JGVector2D()
	{
		dX = 0.0;
		dY = 0.0;
	}
	
	/***********************************************************
	*Name: JGVetor2D()
	*Description: parameterized constructor
	*Parameters: double, double
	*Return: None
	************************************************************/
	public JGVector2D(double pX, double pY)
	{
		dX = pX;
		dY = pY;
	}
	
	/***********************************************************
	*Name: getX()
	*Description: dX getter
	*Parameters: none
	*Return: double
	************************************************************/
	public double getX()
	{
		return dX;
	}
	
	/***********************************************************
	*Name: getY()
	*Description: dY getter
	**Parameters: none
	*Return: double
	************************************************************/
	public double getY()
	{
		return dY;
	}
	
	/***********************************************************
	*Name: setX()
	*Description: dX setter
	*Parameters: double
	*Return: none
	************************************************************/
	public void setX(double pX)
	{
		dX = pX;
	}
	
	/***********************************************************
	*Name: setY()
	*Description: dY setter
	*Parameters: double
	*Return: none
	************************************************************/
	public void setY(double pY)
	{
		dY = pY;
	}
	
	/***********************************************************
	*Name: setXY()
	*Description: dX and dY setter
	**Parameters: double, double
	*Return: none
	************************************************************/
	public void setXY(double pX, double pY)
	{
		dX = pX;
		dY = pY;
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
