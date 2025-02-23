
//PACOTES UTILIZADOS
import java.awt.Color;
import java.net.URL;

import JGames2D.JGLevel;
import JGames2D.JGSoundEffect;
import JGames2D.JGSoundManager;
import JGames2D.JGSprite;
import JGames2D.JGTimer;

//CLASSE QUE PROGRAMA A TELA DE SPLASH SCREEN DO GAME
public class CenaAbertura extends JGLevel
{
	//ATRIBUTOS DA CLASSE
	JGSprite sprite = null;
	int state = 0;
	JGTimer timer = null;
	
	@Override
	public void init() 
	{	
		//DECLARA UM TIMER PARA MEDIR O TEMPO DE 2 SEGUNDOS
		timer = new JGTimer(1500);
		
		//CONFIGURA O FUNDO DA TELA PARA AMARELO
		gameManager.windowManager.setBackgroundColor(Color.yellow);
		
		//CRIA UM SPRITE E CONFIGURA SUA POSICAO (CENTRO DA TELA) E ZOOM DE 4X
		sprite = createSprite(getURL("/Images/spr_produtors.png") ,1, 1);
		sprite.position.setX(gameManager.windowManager.width / 2);
		sprite.position.setY(gameManager.windowManager.height / 2);
		sprite.zoom.setXY(6, 6);
	}
	
	@Override
	public void execute() 
	{
		//PRIMEIRO ESTADO, DIMINUI O ZOOM ATÉ CHEGAR  0.5
		//REINICA O OBJETO DE TEMPO PARA MEDIR 2 SEGUNDOS
		//MUDA O VALOR DA VARIAVEL STATE
		if (state == 0)
		{
			timer.update();
			
			if (!timer.isTimeEnded())
				return;
			
			
			sprite.zoom.setX(sprite.zoom.getX() - 0.25);
			sprite.zoom.setY(sprite.zoom.getY() - 0.25);
			
			if (sprite.zoom.getX() <= 0.5)
			{
				sprite.zoom.setXY(0.5, 0.5);
				timer.restart(2000);
				state = 1;
			}
		}
		//ATUALIZA O OBJETO DE TEMPO E QUANDO ACABAR TROCA A CENA
		else if (state == 1)
		{
			timer.update();
			
			if (timer.isTimeEnded())
			{
				gameManager.setCurrentLevel(1);
				return;
			}
		}
	}
	
	private URL getURL(String image)
	{
		return getClass().getResource(image);
	}
}
