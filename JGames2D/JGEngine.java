/***********************************************************************
*Name: JGGameManager
*Description: class that controlls all resources of the engine
*Author: Silvano Malfatti
*Date: 01/05/20
************************************************************************/

//Package Declaration
package JGames2D;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class JGEngine implements Runnable
{
	//Constants of the class
	private final int SLEEP_TIPME = 33;
	
	//Class attributes
	public JGWindowManager windowManager = null;
	public JGInputManager inputManager = null;
	public Graphics2D graphics = null;
	public JGLevel currentLevel = null;
	private ArrayList<JGLevel> vetLevels = null;
	private boolean executing = true;
	
	/***********************************************************
	*Name: JGGameManager
	*Description: constructor
	*Parameters: none
	*Return: none
	************************************************************/
	public JGEngine()
	{
		loadResources();
	}
	
	/***********************************************************
	*Name: loadResources
	*Description: load the engine resources
	*Parameters: none
	*Return: none
	************************************************************/
	private void loadResources()
	{
		vetLevels = new ArrayList<JGLevel>();
		//JGLog.init();
		JGImageManager.init();
		JGSoundManager.init();
		windowManager = new JGWindowManager(this);
		inputManager = new JGInputManager(windowManager);
	}
	
	/***********************************************************
	*Name: start
	*Description: starts the engine execution
	*Parameters: none
	*Return: none
	************************************************************/
	public void start()
	{
		if (vetLevels.size( ) > 0)
		{
			setCurrentLevel(0);
		}
		windowManager.showWindow();
		Thread thread = new Thread(this);
		thread.start();
	}
	
	/***********************************************************
	*Name: run
	*Description: method defined by the runnable interface
	*Parameters: none
	*Return: none
	************************************************************/
	public void run()
	{
		while (executing)
		{
			update();
			swapBuffers();
			pause();
		}
		free();
		System.exit(0);
	}
	
	/***********************************************************
	*Name: update()
	*Description: method defined by the runnable interface
	*Parameters: none
	*Return: none
	************************************************************/
	private void update()
	{
		JGTimeManager.update();
		
		if (currentLevel != null)
		{
			if (currentLevel.vetLayers.size() == 0)
			{
				graphics.fillRect(0, 0, windowManager.getWidth(), windowManager.getHeight());
			}
			
			currentLevel.render();
			currentLevel.update();
			currentLevel.execute();
		}
	}
	
	/***********************************************************
	*Name: pause
	*Description: pause the game loop
	*Parameters: none
	*Return: none
	************************************************************/
	private void pause()
	{
		try
		{
			Thread.sleep(SLEEP_TIPME);
		}
		catch(Exception e)
		{
			System.out.println(e);
			System.exit(1);
		}
	}
	
	/***********************************************************
	*Name: finish
	*Description: ends the engine execution
	*Parameters: none
	*Return: none
	************************************************************/
	public void finish()
	{
		executing = false;
	}
	
	/***********************************************************
	*Name: swapBuffers
	*Description: changes the front buffer by back buffer
	*Parameters: none
	*Return: none
	************************************************************/
	private void swapBuffers()
	{
		windowManager.repaint();
	}
	
	/***********************************************************
	*Name: setCurrentLevel
	*Description: define the current level to be executed
	*Parameters: none
	*Return: none
	************************************************************/
	public void setCurrentLevel(int levelIndex)
	{
		if (levelIndex >= 0 && levelIndex < vetLevels.size())
		{
			if (currentLevel != null)
			{
				currentLevel.free();
			}
			
			currentLevel = vetLevels.get(levelIndex);
			currentLevel.init();
			
			JGTimeManager.restart();
			inputManager.reset();
		}
	}
	
	/***********************************************************
	*Name: addLevel
	*Description: add a new level to the engine levels list
	*Parameters: none
	*Return: none
	************************************************************/
	public void addLevel(JGLevel newLevel)
	{
		if (newLevel != null)
		{
			newLevel.setGameManager(this);
			vetLevels.add(newLevel);
		}
	}
	
	/***********************************************************
	*Name: free
	*Description: free the engine resources
	*Parameters: none
	*Return: none
	************************************************************/
	private void free()
	{
		windowManager.removeKeyListener(inputManager);
		windowManager.removeMouseListener(inputManager);
		windowManager.removeMouseMotionListener(inputManager);
		windowManager.free();
		
		currentLevel = null;
		
		for (JGLevel level : vetLevels)
		{
			level.free();
		}
		vetLevels.clear();
		vetLevels = null;
		
		JGImageManager.free();
		JGSoundManager.free();
		
		inputManager.free();
		inputManager = null;
		
		graphics = null;
		
		System.gc();
	}
}