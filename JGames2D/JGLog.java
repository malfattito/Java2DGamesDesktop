/***********************************************************************
*Name: JGLog
*Description: singleton that creates a file log to receive the engines messages
*Author: Silvano Malfatti
*Date: 01/05/20
************************************************************************/

//Package declaration
package JGames2D;

//Used packages
import java.io.FileWriter;
import javax.swing.JOptionPane;

public class JGLog 
{
	/***********************************************************
	*Name: CLog
	*Description: private constructor
	*Parameters: none
	*Return: None
	************************************************************/
	private JGLog()
	{}
	
	/*******************************************
	* Name:init
	* Description: inits the log system
	* Parameters: none
	* Returns: none
	******************************************/
	public static void init()
	{
		try
		{
			FileWriter file = new FileWriter("LOG.txt");
			file.write("*************************************************\n");
			file.write("                   LOG                        \n");
			file.write("*************************************************\n\n");
			file.flush();
			file.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"OPEN LOG ERROR");
		}
	}
	
	/*******************************************
	* Name:writeLog
	* Description: write a message log
	* Parameters: String
	* Returns: none
	******************************************/
	public static void writeLog(String logMessage)
	{
		try
		{
			FileWriter arquivo = new FileWriter("LOG.txt",true);
			arquivo.write(logMessage);
			arquivo.flush();
			arquivo.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"LOG WRITE ERROR");
		}
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
