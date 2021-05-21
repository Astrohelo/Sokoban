package soko;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
/**
 * A zenét kezeli.
 * @author Axel
 *
 */
public class Music extends Thread{
	public void run(){
		try {
			FileInputStream fileInputStream = new FileInputStream("pushit.mp3");
			Player player = new Player(fileInputStream);
			player.play();
	      
	    }catch(FileNotFoundException e) {
	    	e.printStackTrace();
	    }catch(JavaLayerException e) {
	    	e.printStackTrace();
	    }
	    }
}
