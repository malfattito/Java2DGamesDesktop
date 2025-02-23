
//PACOTES UTILIZADOS
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.net.URL;

import JGames2D.JGLevel;
import JGames2D.JGSprite;
import JGames2D.JGVector2D;

//CLASSE QUE PROGRAMA A TELA DE SPLASH SCREEN DO GAME
public class CenaCreditos extends JGLevel
{
	//ATRIBUTOS DA CLASSE
	JGSprite sprite = null;
	
	@Override
	public void init() 
	{	
		//CONFIGURA O FUNDO DA TELA PARA AMARELO
		gameManager.windowManager.setBackgroundColor(Color.BLUE);
		
		//CRIA UM SPRITE E CONFIGURA SUA POSICAO (CENTRO DA TELA) E ZOOM DE 4X
		sprite = createSprite(getURL("/Images/spr_credits.png") ,1, 1);
		sprite.zoom.setXY(0.5, 0.5);
		sprite.position.setX(gameManager.windowManager.width / 2);
		sprite.position.setY(gameManager.windowManager.height + sprite.frameHeight/2 * sprite.zoom.getY());
		sprite.moveTo(8000, new JGVector2D(sprite.position.getX(), - sprite.frameHeight/2 * sprite.zoom.getY()));
	}
	
	@Override
	public void execute() 
	{
		//SE MOVIMENTO ACABOU VOLTA PARA MENU
		if (sprite.isMoveEnded())
		{
			gameManager.setCurrentLevel(1);
			return;
		}
		
		//SE TECLA ESC PRESSIONADA VOLTA PARA MENU
		if (gameManager.inputManager.keyTyped(KeyEvent.VK_ESCAPE))
		{
			gameManager.setCurrentLevel(1);
			return;
		}
		
	}
	
	private URL getURL(String image)
	{
		return getClass().getResource(image);
	}
}
