package soko;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.*;

/**
 * Ez egy JFrame oszt�ly.
 * @author Axel
 *
 */
public class GameFrame extends JFrame{


	
	GameFrame() throws IOException{

		this.add(new Kepernyo());
		this.setTitle("Sokoban made by V�rai Axel");
		this.setSize(new Dimension(600,600));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);   
		this.setLocationRelativeTo(null);
	}
	
}
