/***********************************************************************
*Name: JGImageManager
*Description: singleton class that controls the processo of load a image 
*             or returns your reference
*Author: Silvano Malfatti
*Date: 01/05/20
************************************************************************/

//Package declaration
package JGames2D;

//Used packages
import java.net.URL;
import java.util.ArrayList;

public class JGImageManager
{
	//Class Attributes
	private static ArrayList<JGImage> vetImages = null;
	
	/***********************************************************
	*Name: JGImageManager
	*Description: private constructor
	*Parameters: None
	*Return: None
	************************************************************/
	private JGImageManager()
	{}
	
	/***********************************************************
	*Name: init
	*Description:prepare the imageManager to the use
	*Parameters: None
	*Return: None
	************************************************************/
	public static void init()
	{
		vetImages = new ArrayList<JGImage>();
	}
	
	/***********************************************************
	*Name: loadImage
	*Description: load a image or reclycle your reference
	*Parameters: URL
	*Return: JGImage
	************************************************************/
	public static JGImage loadImage(URL pName)
	{
		//try to recycle a image
		for (JGImage image : vetImages)
		{
			if (pName.getPath().equals(image.getImageName()))
			{
				return image;
			}
		}
		
		//Creates a new image
		JGImage image = new JGImage();
		if (image.load(pName))
		{
			vetImages.add(image);
			return image;
		}
		
		return null;
	}
	
	/*******************************************
   	* Name: freeImage
   	* Description: free a image from manager
   	* Parameters: JGImage
   	* Returns: none
   	******************************************/
    public static void free(JGImage image) 
    {
    	vetImages.remove(image);
    }
	
	/*******************************************
   	* Name: free
   	* Description: free resources
   	* Parameters: none
   	* Returns: none
   	******************************************/
    public static void free() 
    {
		for (JGImage image : vetImages)
		{
			image.free();
		}
		vetImages.clear();
		vetImages = null;
	}
}
