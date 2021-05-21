package soko;
import java.io.IOException;

/**
 * Ez a main.
 * @author Axel
 *
 */
public class program {
	

	public static void main(String[] args) throws IOException {
		  Music zene = new Music();
		  zene.start();
		
		new GameFrame();
	}

}
