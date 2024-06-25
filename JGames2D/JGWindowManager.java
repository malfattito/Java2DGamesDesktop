/***********************************************************************
*Name: JGWindowManager
*Description: represents the window application and your resources
*Author: Silvano Malfatti
*Date: 01/05/20
************************************************************************/

//Package declaration
package JGames2D;

import java.awt.Color;
//Used packages
import java.awt.Cursor;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class JGWindowManager extends JFrame
{
	//Class Attributes
	private int xPos, yPos;
	private int colorsByPixell;
	private String windowTitle = null;
	private boolean fullScreen;
	private GraphicsDevice graphDevice = null;
	private BufferedImage backBuffer = null;
	private JGEngine gameManager = null;
	private Cursor cursor = null;
	public Color backgroundColor = Color.black;
	public int width, height;
	
	/***********************************************************
	*Name: JGWindowManager
	*Description: construtor default
	*Parameters: JGGameManager
	*Return: None
	************************************************************/
	JGWindowManager(JGEngine gameManager)
	{
		windowTitle = "JGames2D";
		this.gameManager = gameManager;
		graphDevice = null;
		width = 800;
		height = 600;
		colorsByPixell = 32;
		fullScreen = false;
		xPos = 0;
		yPos = 0;
		initWindow();
	}
	
	/***********************************************************
	*Name: getGraphicsBackBuffer()
	*Description: returns the Graphics BackBuffer
	*Parameters: void
	*Return: Graphics2D
	************************************************************/
	Graphics2D getGraphicsBackBuffer()
	{
		return backBuffer.createGraphics();
	}
	
	/***********************************************************
	*Name: setBacrkgoundCcolor
	*Description: sets the backround color of window
	*Parameters:None
	*Return: none
	************************************************************/
	public void setBackgroundColor(Color color)
	{
		gameManager.graphics.setColor(color);
	}
	
	/***********************************************************
	*Name: setBackgroundColor
	*Description: returns the fullscreen mode
	*Parameters:None
	*Return: boolean
	************************************************************/
	public boolean getFullScreen()
	{
		return fullScreen;
	}
	
	/***********************************************************
	*Name: getGraphicsDevice
	*Description: returns the GraphicsDevice object
	*Parameters:None
	*Return: boolean
	************************************************************/
	public GraphicsDevice getGraphicsDevice()
	{
		return graphDevice;
	}
	
	/***********************************************************
	*Name: geBackBuffer
	*Description: returns the backbuffer
	*Parameters:None
	*Return: BufferedImage
	************************************************************/
	public BufferedImage getBackBufferImage()
	{
		return backBuffer;
	}
	
	/***********************************************************
	*Name: setWindowsPosition
	*Description: set the posicion of the windows in desktop
	*Parameters:int, int
	*Return: none
	************************************************************/
	public void setWindowPosition(int pPosX, int pPosY)
	{
		xPos = pPosX;
		yPos = pPosY;
	}
	
	/***********************************************************
	*Name: setWindowTitle
	*Description: set the title of the window
	*Parameters: String
	*Return: none
	************************************************************/
	public void setWindowTitle(String pTitle)
	{
		windowTitle = pTitle;
	}
	
	/***********************************************************
	*Name: setFullScreen
	*Description: set the fullscreen mode
	*Parameters: boolean
	*Return: none
	************************************************************/
	public void setfullScreen(boolean fullScreenMode)
	{
		fullScreen = fullScreenMode;
	}
	
	/***********************************************************
	*Name: setResolution
	*Description: set the window resolution
	*Parameters: boolean
	*Return: none
	************************************************************/
	public void setResolution(int width, int height, int depth)
	{
		this.width = width;
		this.height = height;
		colorsByPixell = depth;
		backBuffer = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		gameManager.graphics = backBuffer.createGraphics();
	}
	
	/***********************************************************
	*Name: initWindow
	*Description: show the window
	*Parameters: none
	*Return: none
	************************************************************/
	private void initWindow()
	{
		
		if (backBuffer == null)
		{
			backBuffer = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
			gameManager.graphics = backBuffer.createGraphics();
		}
		
		//Esconde o ponteiro do mouse
		cursor = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(""), new Point(0,0),"invisible");
		this.setCursor(cursor);
		
		//Solicita o foco para a janela
		requestFocus();
		
		/*Trata o botão fechar no caso de modo janela*/
		addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e)
			{		
				gameManager.finish();
			} 						
		}
		);
	}
	
	/***********************************************************
	*Name: mostraJanela()
	*Description: mostra a janela
	*Parametros: Nenhum
	*Retorno: Nenhum
	************************************************************/
	public void showWindow()
	{
		//Verifica o modo de vídeo
		if (fullScreen)
		{
			//Modo tela cheia
			//Configura o estilo da janela
			setUndecorated(true);
			setResizable(false);
			setLocation(0,0);
			
			//Configura o modo FullScreen
			DisplayMode displayMode = new DisplayMode(width,height,colorsByPixell,DisplayMode.REFRESH_RATE_UNKNOWN);
			GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
			graphDevice = environment.getDefaultScreenDevice();
			setLocation(graphDevice.getDisplayMode().getWidth() / 2, graphDevice.getDisplayMode().getHeight() / 2);
			graphDevice.setFullScreenWindow(this);
			graphDevice.setDisplayMode(displayMode);
		}
		else
		{
			//Modo Janela
			setTitle(windowTitle);
			setLocation(xPos,yPos);
			setSize(width,height);
			setVisible(true);
			setResizable(false);
		}
	}
	
	/***********************************************************
	*Name: paint()
	*Description: método da classe JFrame que repinta a janela
	*Parametros: Graphics2D
	*
	*
	*Retorno: Nenhum
	************************************************************/
	public void paint(Graphics graphics)
	{
		Graphics2D g2d = (Graphics2D)graphics;
		g2d.drawImage(backBuffer,0,0,null);
	}
	
	/*******************************************
   	* Name: free
   	* Description: free resources
   	* Parameters: none
   	* Returns: none
   	******************************************/
	public void free()
	{
		if (fullScreen)
		{
			graphDevice.setFullScreenWindow(null);
			graphDevice = null;
		}
		windowTitle = null;
		backBuffer.flush();
		backBuffer = null;
		cursor = null;
		gameManager = null;
		backgroundColor = null;
	}
}
