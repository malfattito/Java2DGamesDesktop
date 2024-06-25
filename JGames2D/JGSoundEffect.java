/***********************************************************************
*Name: JGSoundEffect
*Description: represents the animation sequence of image frames
*Author: Silvano Malfatti
*Date: 01/05/20
************************************************************************/

//Package declaration
package JGames2D;

//Used packages
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class JGSoundEffect 
{
	//Class Attributes
	private Clip clip = null;
	private String fileName = null;
	
	/***********************************************************
	*Name: JGSoundEffect
	*Description: constructor of a sound object
	*Parameters: URL
	*Return: None
	************************************************************/
	JGSoundEffect(URL file)
	{
		try
		{
			AudioInputStream stream = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(stream);
			fileName = file.getPath();
		}
		catch(Exception e)
		{
		}
	}
	
	/***********************************************************
	*Name: getSoundName
	*Description: returns the name of the sound
	*Parameters: none
	*Return: String
	************************************************************/
	public String getSoundName()
	{
		return fileName;
	}
	
	/***********************************************************
	*Name: setVolume
	*Description: configures the volume of sound reproduction
	*Parameters: float(0 - 100)
	*Return: None
	************************************************************/
	public void setVolume(float volume)
	{
		volume = volume / 100.0f;
		
		FloatControl control = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
		
		if (control != null)
		{
			control.setValue((20f * (float) Math.log10(volume)));
		}
	}
	
	/***********************************************************
	*Name: play
	*Description: start sound reproduction
	*Parameters: None
	*Return: None
	************************************************************/
	public void play()
	{
		clip.setFramePosition(0);
		clip.start();
	}
	
	/***********************************************************
	*Name: play
	*Description: start sound reproduction in loop
	*Parameters: None
	*Return: None
	************************************************************/
	public void loop()
	{
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		clip.setFramePosition(0);
		clip.start();
	}
	
	/***********************************************************
	*Name: stop
	*Description: stop sound reproduction
	*Parameters: None
	*Return: None
	************************************************************/
	public void stop()
	{
		clip.stop();
		clip.close();
	}
	
	/***********************************************************
	*Name: free
	*Description: free resources
	*Parameters: None
	*Return: None
	************************************************************/
	public void free()
	{
		clip = null;
		fileName = null;
	}
}
