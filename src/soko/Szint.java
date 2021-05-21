package soko;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 * Beolvassa a szintet txtb�l �s �tadja a k�perny�nek, � kezeli a p�lyaszerkeszt�st is.
 * @author Axel
 *
 */
public class Szint {
	private Character c;
	private Box b;
	private Field[][] palya;
	Image palyaelem,fal,karakterjobbra,karakterbalra,doboz,win;
	private int meretek=50;
	private int eger=0;
	
	/**
	 * Beolvassa a szintet �s a k�peket
	 * @param file File neve.
	 * @throws IOException Ha nem l�tezik ilyen nev� f�jl.
	 */
	Szint(String file) throws IOException{
		palya = new Field[10][10];
		szintBeolvas(file);
		fal = ImageIO.read(new File(System.getProperty("user.dir")+File.separator+"kepek"+File.separator+ "fal.png"));
		palyaelem = ImageIO.read(new File(System.getProperty("user.dir")+File.separator+"kepek"+File.separator+ "palyaelem.png"));
		karakterjobbra= ImageIO.read(new File(System.getProperty("user.dir")+File.separator+"kepek"+File.separator+ "duckie.png"));
		karakterbalra= ImageIO.read(new File(System.getProperty("user.dir")+File.separator+"kepek"+File.separator+ "duckiebalra.png"));
		doboz = ImageIO.read(new File(System.getProperty("user.dir")+File.separator+"kepek"+File.separator+ "alien.png"));
		win = ImageIO.read(new File(System.getProperty("user.dir")+File.separator+"kepek"+File.separator+ "win.png"));
	

	}
	/**
	 * Beolvassa a szintet egy f�jlb�l.
	 * @param file File neve
	 * @throws FileNotFoundException Ha nem l�tezne a f�jl.
	 */
	public void szintBeolvas(String file) throws FileNotFoundException { //beolvasom a p�ly�t �s inicializ�lom az elemeket
		File f = new File(file);
		Scanner sc = new Scanner(f);

			for (int i = 0; i < palya.length; i++) {
						
				String sor = sc.nextLine();
				if (sor == null) {break;}
				String array[] = sor.split(" ");
						
					for (int j = 0; j < palya[0].length; j++) {
							
						this.palya[i][j] = new Field(j*meretek, i*meretek, meretek,false,false);		
						if (array[j].equals("f")) {
							this.palya[i][j].setFal(true);
						}
						else if (array[j].equals("c")) {
							this.c = new Character(j*meretek, i*meretek, meretek);
						}
						else if(array[j].equals("b")) {
							this.b = new Box(j*meretek, i*meretek, meretek);
						}
						else if(array[j].equals("w")) {
							this.palya[i][j].setWin(true);
						}
					}
				}
			
		sc.close();
		}
	/**
	 * Kirajzolja a p�ly�t
	 * @param g Kirajzol�shoz.
	 * @param palyakeszit P�lyak�szit� m�dot figyeli.
	 */
	public void draw(Graphics g,boolean palyakeszit) { //p�lya rajzol�s
		
			for (int i=0; i<palya.length; i++) {
				for (int j=0; j<palya[0].length; j++) { 
					if(palya[i][j].getFalE()==true) {
						g.drawImage(fal, palya[i][j].getX(), palya[i][j].getY(), palya[i][j].getOldal(), palya[i][j].getOldal(),null);
					}
					else if(palya[i][j].getFalE()==false && palya[i][j].getWinE()== false){
						g.drawImage(palyaelem, palya[i][j].getX(), palya[i][j].getY(), palya[i][j].getOldal(), palya[i][j].getOldal(),null);
					}
					else {
						g.drawImage(win, palya[i][j].getX(), palya[i][j].getY(), palya[i][j].getOldal(), palya[i][j].getOldal(),null);
					}
					
				}
			}
			if (c.getIrany()==1) {
				g.drawImage(karakterjobbra, c.getX(), c.getY(), c.getOldal(), c.getOldal(),null);
			}
			else {
				g.drawImage(karakterbalra, c.getX(), c.getY(), c.getOldal(), c.getOldal(),null);
			}
			g.drawImage(doboz, b.getX(), b.getY(), b.getOldal(), b.getOldal(), null);
		
		if (palyakeszit==true) { //ha p�lyak�szit� m�dban van
			g.drawImage(palyaelem, 500,50,meretek,meretek,null);
			g.drawImage(fal, 500,100,meretek,meretek,null);
			g.drawImage(karakterjobbra, 500,150,meretek,meretek,null);
			g.drawImage(doboz, 500,200,meretek,meretek,null);
			g.drawImage(win, 500,250,meretek,meretek,null);
		}
	}
	public Character getChar(){return c;}
	public Box getBox(){return b;}
	/**
	 * Visszaadja a c�l mez� X v�ltoz�j�t.
	 * @return X
	 */
	public int getWinX(){
		for (int i=0; i<palya.length; i++) {
			for (int j=0; j<palya[0].length; j++) { 
				if(palya[i][j].getWinE()==true) {
					return palya[i][j].getX();
				}
			}
		}
		return 0;
	}
	/**
	 * Visszaadja a c�l mez� Y v�ltoz�j�t.
	 * @return Y
	 */
	public int getWinY(){
		for (int i=0; i<palya.length; i++) {
			for (int j=0; j<palya[0].length; j++) { 
				if(palya[i][j].getWinE()==true) {
					return palya[i][j].getY();
				}
			}
		}
		return 0;
	}
	/**
	 * A kattint�st kezeli a p�lyak�szit� m�dban.
	 * @param x A kattint�s x helye.
	 * @param y A kattint�s y helye.
	 */
	public void katt(int x, int y) { //kattint�st kezeli a p�lyak�szit� m�dban
		int realX=x/meretek;
		int realY=y/meretek;
		if(realX<10 && realY<10) {/////belekattintt
			if (eger==1){ //h�tt�r
				palya[realY][realX].setFal(false);
				palya[realY][realX].setWin(false);
			}
			else if (eger==2){ //fal
				if(b.getX()/meretek==realX && b.getY()/meretek==realY || c.getX()/meretek==realX && c.getY()/meretek==realY ) {
					
				}
				else {
					palya[realY][realX].setFal(true);
					palya[realY][realX].setWin(false);
				}
			}
			else if (eger==3){ //karakter
				if(b.getX()/meretek==realX && b.getY()/meretek==realY ) {
					
				}
				else {
					palya[realY][realX].setFal(false);
					palya[realY][realX].setWin(false);
					c.setAll(x-x%meretek,y-y%meretek,0);
				}
			}
			else if (eger==4){ //doboz
				if(c.getX()/meretek==realX && c.getY()/meretek==realY ) {
					
				}
				else {
					palya[realY][realX].setFal(false);
					palya[realY][realX].setWin(false);
					b.setAll(x-x%meretek,y-y%meretek);
				}
			}
			else if (eger==5){ //win
				if(b.getX()/meretek==realX && b.getY()/meretek==realY || c.getX()/meretek==realX && c.getY()/meretek==realY ) {
					
				}
				else {
					for (int i=0; i<palya.length; i++) {
						for (int j=0; j<palya[0].length; j++) { 
							palya[j][i].setWin(false);
						}
					}
					palya[realY][realX].setFal(false);
					palya[realY][realX].setWin(true);
					
				}
			}
		}
		else {
			if(realX==10) {
				if (realY==1) {
					eger=1;
				}
				else if (realY==2) {
					eger=2;
				}
				else if (realY==3) {
					eger=3;
				}
				else if (realY==4) {
					eger=4;
				}
				else if (realY==5) {
					eger=5;
				}
			}
			
		}
	}
	
	/**
	 * Elmenti a k�szitett p�ly�t.
	 */
	public void mentes() { //elmenti a k�szitett p�ly�t
		try {
			PrintWriter myWriter = new PrintWriter(new FileWriter("palyamaker.txt"));
		      
		      for (int i=0; i<palya.length; i++) {
		    	  for (int j=0; j<palya[0].length; j++) { 
		    		  if(palya[i][j].getFalE()==true) {
		    			  myWriter.write("f ");
		    		  }
		    		  else if(palya[i][j].getFalE()==false && palya[i][j].getWinE()== false){
		    			  if (c.getX()/meretek==j && c.getY()/meretek==i) {
		    				  myWriter.write("c ");
		    			  }
		    			  else if (b.getX()/meretek==j && b.getY()/meretek==i) {
		    				  myWriter.write("b ");
		    			  }
		    			  else{
		    				  myWriter.write("h ");
		    			  }
		    		  }
		    		  else {
		    			  myWriter.write("w ");
		    		  }
		    		  
		    	  }
		    	  myWriter.write("\n");
		      }
		      myWriter.flush();
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }

	}
	
	public Field[][] getSzint() {return palya;}
	
}
