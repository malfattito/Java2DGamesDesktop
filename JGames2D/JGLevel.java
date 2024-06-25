/***********************************************************************
*Name: JGLevel
*Description: represents a scene level
*Author: Silvano Malfatti
*Date: 01/05/20
************************************************************************/

//Package declaration
package JGames2D;

import java.net.URL;
//Used packages
import java.util.ArrayList;

public abstract class JGLevel 
{
	//Class attributes
	public ArrayList<JGLayer> vetLayers = null;
	public ArrayList<JGSprite> vetSprites = null;
	protected JGEngine gameManager = null;
	
	/***********************************************************
	*Name: JGLevel
	*Description: constructor
	*Parameters: JGGameManager
	*Return: none
	************************************************************/
	public JGLevel()
	{
		vetLayers = new ArrayList<JGLayer>();
		vetSprites = new ArrayList<JGSprite>();
	}
	
	/***********************************************************
	*Name: setGameGamager
	*Description: sets the gameManager object of the level
	*Parameters: JGGameManager
	*Return: none
	************************************************************/
	public void setGameManager(JGEngine manager)
	{
		gameManager = manager;
	}
	
	/***********************************************************
	*Name: execute
	*Description: abstract method used to define the logic
	*Parameters: None
	*Return: None
	************************************************************/
	public abstract void execute();
	
	/***********************************************************
	*Name: execute
	*Description: abstract method used to init the scene
	*Parameters: None
	*Return: None
	************************************************************/
	public abstract void init();
	
	/***********************************************************
	*Name: render()
	*Description: render this level
	*Parameters: None
	*Return: None
	************************************************************/
	public void render()
	{
		//Renderiza as camadas
		for (JGLayer layer : vetLayers)
		{
			if (layer.getAutoRender())
			{
				layer.render();
			}
		}
		
		//Renderiza os sprites
		for (JGSprite sprite : vetSprites)
		{
			if (sprite.autoRender)
			{
				sprite.render();
			}
		}
	}
	
	/***********************************************************
	*Name: update()
	*Description: updates the elements of visual objects
	*Parameters: None
	*Return: None
	************************************************************/
	public void update()
	{
		//Atualiza as camadas
		for (JGLayer layer : vetLayers)
		{
			layer.scrollLayer();
		}
		
		//Atualiza os sprites
		for (JGSprite sprite : vetSprites)
		{
			sprite.updateSprite();
		}
	}
	
	/***********************************************************
	*Name: createLayer()
	*Description: create a layer
	*Parameters: JGVector2D, boolean
	*Return: JGLayer
	************************************************************/
	public JGLayer createLayer(URL tileImage, URL layerImage, JGColorIndex[] colors, JGVector2D blockSize, boolean autoRender)
	{
		JGLayer layer = new JGLayer(gameManager, blockSize);
		
		layer.setTileImage(tileImage);
		layer.setIndiceCores(colors);
		layer.createLayer(layerImage);
		
		if (layer != null)
		{
			layer.setAutoRender(autoRender);
			vetLayers.add(layer);
			return layer;
		}
		
		return null;
	}
	
	/***********************************************************
	*Name: createLayer()
	*Description: create a layer
	*Parameters: JGVector2D, JGVector2D, boolean
	*Return: None
	************************************************************/
	public JGLayer createLayer(URL tileImage, JGVector2D layerSize, JGVector2D blockSize, boolean autoRender)
	{
		JGLayer layer = new JGLayer(gameManager, layerSize, blockSize);
		layer.setTileImage(tileImage);
		
		if (layer != null)
		{
			layer.setAutoRender(autoRender);
			vetLayers.add(layer);
			return layer;
		}
		
		return null;
	}
	
	/***********************************************************
	*Name: createSprite
	*Description: create a visual object
	*Parameters: JGVector2D, boolean
	*Return: JGSprite
	************************************************************/
	public JGSprite createSprite(URL image, int lines, int colunms)
	{
		JGSprite sprite = new JGSprite(gameManager, lines, colunms);
		sprite.setSpriteImage(image);
		
		if (sprite != null)
		{
			sprite.autoRender = true;
			vetSprites.add(sprite);
			return sprite;
		}
		
		return null;
	}
	
	/***********************************************************
	*Name: free
	*Description: free resources
	*Parameters: none
	*Return: none
	************************************************************/
	public void free()
	{
		//Libera os sprites
		for (JGSprite sprite : vetSprites)
		{
			sprite.free();
		}
		vetSprites.clear();
		
		//Libera as camadas
		for (JGLayer layer : vetLayers)
		{
			layer.free();
		}
		vetLayers.clear();
	}
	
	public void finalize()
	{
		vetLayers = null;
		vetSprites = null;
		gameManager = null;
	}	
}
