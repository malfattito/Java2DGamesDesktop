/***********************************************************************
*Name: JGAnimation
*Description: represents the animation sequence of image frames
*Author: Silvano Malfatti
*Date: 01/05/20
************************************************************************/


//Package declarationn
package JGames2D;

//Used Packages
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.net.URL;

public class JGLayer 
{
	//Class attributes
	public JGVector2D offset = null;
	public JGVector2D speed = null;
	private JGEngine gameManager = null;
	private JGFrameIndex[] vetBlocks = null;
	private JGVector2D blockSize = null;
	private JGVector2D layerSize = null;
	private JGImage tileImage = null;
	private JGColorIndex[] vetColorIndex = null;
	private boolean visible = false;
	private boolean autoRender = false;
	
	/***********************************************************
	*Name: JGLayer
	*Description: constructor
	*Parameters: JGGameManager, JGVector2D
	*Return: none
	************************************************************/
	public JGLayer(JGEngine manager, JGVector2D blockSize)
	{
		vetBlocks = null;
		tileImage = null;
		vetColorIndex = null;
		this.blockSize = blockSize;
		gameManager = manager;
		offset = new JGVector2D();
		speed = new JGVector2D();
		layerSize = new JGVector2D();
		visible = true;
		autoRender = false;
	}
	
	/***********************************************************
	*Name: JGLayer
	*Description: constructor
	*Parameters: JGGameManager, JGVector2D, JGVector2D
	*Return: none
	************************************************************/
	public JGLayer(JGEngine manager, JGVector2D pTamanhoCamada, JGVector2D pTamanhoBloco)
	{
		gameManager = manager;
		layerSize = pTamanhoCamada;
		vetColorIndex = null;
		tileImage = null;
		vetBlocks = new JGFrameIndex[(int)(pTamanhoCamada.getX() * pTamanhoCamada.getY())];
		for (int iIndex=0; iIndex<vetBlocks.length; iIndex++)
		{
			vetBlocks[iIndex] = new JGFrameIndex();
		}
		blockSize = pTamanhoBloco;
		speed = new JGVector2D();
		offset = new JGVector2D();
		visible = true;
		autoRender = false;
	}
	
	/***********************************************************
	*Name: setVetColorIndex
	*Description: defines the color index to create the layer
	*Parameters: JGColorIndex[]
	*Return: none
	************************************************************/
	public void setIndiceCores(JGColorIndex[] pIndicesCores)
	{
		vetColorIndex = pIndicesCores;
	}
	
	/***********************************************************
	*Name: getLayerSize
	*Description: returns the layer size
	*Parameters: none
	*Return: JGVector2D
	************************************************************/
	public JGVector2D getLayerSize()
	{
		return layerSize;
	}
	
	/***********************************************************
	*Name: getBlockSize
	*Description: returns the block size
	*Parameters: none
	*Return: JGVector2D
	************************************************************/
	public JGVector2D getBlockSize()
	{
		return blockSize;
	}
	
	/***********************************************************
	*Name: getOffset
	*Description: returns the offset of the layer
	*Parameters: none
	*Return: JGVector2D
	************************************************************/
	public JGVector2D getOffset()
	{
		return offset;
	}
	
	/***********************************************************
	*Name: getBlockIndexByColor
	*Description: returns the block index using your RGB color
	*Parameters: none
	*Return: JGVector2D
	************************************************************/
	private int getBlockIndexByColor(int R, int G, int B)
	{
		for (int iIndex=0; iIndex < vetColorIndex.length; iIndex++)
		{
			if (vetColorIndex[iIndex].getColor().getRed() == R   && 
				vetColorIndex[iIndex].getColor().getGreen() == G && 
				vetColorIndex[iIndex].getColor().getBlue() == B)
			{
				return vetColorIndex[iIndex].getFrameIndex();
			}
		}
		return -1;
	}
	
	/***********************************************************
	*Name: setFrameIndex
	*Description:sets the frame index by a specific position
	*Parameters: int, int
	*Return: none
	************************************************************/
	public void setFrameIndex(int index, int frameIndex)
	{
		vetBlocks[index].setFrameIndex(frameIndex);
	}
	
	/***********************************************************
	*Name: getFrameIndexByPosition
	*Description:returns the frame to the position index
	*Parameters: int
	*Return: int
	************************************************************/
	public int getFrameIndexByPosition(int position)
	{
		return vetBlocks[position].getFrameIndex();
	}
	
	/***********************************************************
	*Name: getAutoRender
	*Description:returns the frame to the position index
	*Parameters: none
	*Return: boolean
	************************************************************/
	public boolean getAutoRender()
	{
		return autoRender;
	}
	
	/***********************************************************
	*Name: setAutoRender
	*Description:set the frame to the position index
	*Parameters: boolean
	*Return: none
	************************************************************/
	public void setAutoRender(boolean autoRender)
	{
		this.autoRender = autoRender;
	}
	
	/***********************************************************
	*Name: setVisible
	*Description: configures the visibility of the layer
	*Parameters: boolean
	*Return: void
	************************************************************/
	public void setVisible(boolean pVisivel)
	{
		visible = pVisivel;
	}
	
	/***********************************************************
	*Name: getVisible
	*Description: getter of visibility of the layer
	*Parameters: none
	*Return: boolean
	************************************************************/
	boolean getVisivel()
	{
		return visible;
	}
	
	/***********************************************************
	*Name: setTileImage
	*Description: set the image used to the tiles
	*Parameters: URL
	*Return: void
	************************************************************/
	public void setTileImage(URL file)
	{
		tileImage = JGImageManager.loadImage(file);
	}
	
	/***********************************************************
	*Name: setSpeed
	*Description: set the layer speed
	*Parameters: JGVector2D
	*Return: void
	************************************************************/
	public void setSpeed(JGVector2D speed)
	{
		this.speed = speed;
	}
	
	/***********************************************************
	*Name: scrollLayer
	*Description: scroll the layer with the current speed
	*Parameters: none
	*Return: void
	************************************************************/
	public void scrollLayer()
	{
		offset.setX(offset.getX() + speed.getX());
		offset.setY(offset.getY() + speed.getY());
	}
	
	/***********************************************************
	*Name: drawBlock
	*Description: int, int, int, Graphics2D
	*Parameters: none
	*Return: void
	************************************************************/
	private void drawBlock(int frameIndex, int x, int y, Graphics2D g2d)
	{
		int rowsCount = (int)(tileImage.getImage().getWidth() / blockSize.getX());
		
		int width = (int)blockSize.getX();
		int height = (int)blockSize.getY();
		
		int fx = (int)((frameIndex % rowsCount) * width);
		int fy = (int)((frameIndex / rowsCount) * height);
		
		g2d.drawImage(tileImage.getImage() ,x, y, x + width, y + height ,fx, fy, fx + width, fy + height, null);
	}
	
	/***********************************************************
	*Name: createLayer()
	*Description: create a layer based in a color image
	*Parameters: URL
	*Return: void
	************************************************************/
	public void createLayer(URL fileName)
	{
		//Loads the pixel image
		BufferedImage pixelImage = JGImageManager.loadImage(fileName).getImage();
		
		//Testa se a imagem foi carregada
		if (pixelImage == null)
		{
			return;
		}
		
		//set the layer size
		layerSize.setX(pixelImage.getWidth());
		layerSize.setY(pixelImage.getHeight());
		
		//Cria o vetor de bricks
		vetBlocks = new JGFrameIndex[(int)(layerSize.getX() * layerSize.getY())];
		
		//Obtem o modelo de cores da imagem
		ColorModel colorModel = pixelImage.getColorModel();
		
		//Configura os bricks da layer conforme a cor da imagem
		for (int iIndex = 0;  iIndex < pixelImage.getWidth(); iIndex++)
		{
			for (int jIndex = 0; jIndex < pixelImage.getHeight(); jIndex++)
			{
				int index = getBlockIndexByColor(colorModel.getRed(pixelImage.getRGB(iIndex, jIndex)),
						                         colorModel.getGreen(pixelImage.getRGB(iIndex, jIndex)), 
						                         colorModel.getBlue(pixelImage.getRGB(iIndex, jIndex)));
				if (index >= 0)
				{
					vetBlocks[iIndex + (jIndex*pixelImage.getWidth())] = new JGFrameIndex();
					vetBlocks[iIndex + (jIndex*pixelImage.getWidth())].setFrameIndex(index);
				}
				else
				{
					vetBlocks[iIndex + (jIndex*pixelImage.getWidth())] = null;
				}
			}
		}
	}
	
	/***********************************************************
	*Name: render()
	*Description: create a layer based in a color image
	*Parameters: Graphics2D
	*Return: void
	************************************************************/
	public void  render()
	{
		int xBlock = 0;
		int yBlock = 0;
		double xPosition = 0.0f;
		double offsetX = offset.getX();
		double offsetY = offset.getY();
		double layerSizeX = layerSize.getX();
		double layerSizeY = layerSize.getY();
		
		//Retorna se a layer não estiver visível
		if (!visible)
		{
			return;
		}
		
		if (blockSize.getX() ==0 || blockSize.getY() == 0)
		{
			System.out.println("Zerou");
		}
		
		//Calcula o início da layer em x caso offset seja menor que zero
		if (offsetX > 0)
		{	
			int mult = (int)Math.ceil(Math.abs(offsetX) / blockSize.getX());
			xBlock = ((int)layerSizeX - ((mult % (int)layerSizeX))) == (int)layerSizeX ? 0 : ((int)layerSizeX - ((mult % (int)layerSizeX))); 
			offsetX -= mult * blockSize.getX();
		}
		//Guarda o início do offset e o brick inicial em X
		xPosition = offsetX;
		
		//Calcula o início da layer em y caso offset seja menor que zero
		if (offsetY > 0)
		{
			int mult = (int)Math.ceil(Math.abs(offsetY) / blockSize.getY());
			yBlock = ((int)layerSizeY - ((mult % (int)layerSizeY))) == (int)layerSizeY ? 0 : ((int)layerSizeY - ((mult % (int)layerSizeY))); 
			offsetY -= mult * blockSize.getY();
		}
		
		//Desenha todos os bricks da layer
		for(int iStartX = xBlock; offsetY < gameManager.windowManager.getHeight(); 
				                  yBlock = (yBlock+1)%(int)layerSizeY, 
				                  offsetY += blockSize.getY(),
				                  xBlock = iStartX,offsetX = (int)xPosition)
		{
			for( ; offsetX < gameManager.windowManager.getWidth(); xBlock = (xBlock+1)%(int)layerSizeX, offsetX += blockSize.getX())
			{	
				if (vetBlocks == null)
				{
					return;
				}
				
				int blockIndex = xBlock + (yBlock * (int)layerSizeX);
				if (vetBlocks[blockIndex] != null)
				{
					drawBlock(vetBlocks[blockIndex].getFrameIndex(), (int) offsetX, (int) offsetY, gameManager.graphics);	
				}
			}
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
		//JGImageManager.free(tileImage);
		gameManager = null;
		vetBlocks = null;
		blockSize = null;
		offset = null;
		layerSize = null;
		speed = null;
		tileImage = null;
		for (int index = 0; index < vetColorIndex.length; index++)
		{
			vetColorIndex[index].free();
		}
		vetColorIndex = null;
	}
}
