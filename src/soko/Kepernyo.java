package soko;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.io.IOException;
/**
 * A k�perny�n val� kirajzol�st kezeli.
 * @author Axel
 *
 */
public class Kepernyo extends JPanel {
	
	private boolean running=false;	//fut e a j�t�k valamelyik szinten van e a j�t�kos
	private JButton jatek,sajatgame,dicsoseg,vissza,mentes; // a f�bb gombok
	private JButton elsoSzint,masodikSzint,harmadikSzint,negyedikSzint,otodikSzint,hatodikSzint;  //A szintek gombjai
	private JTextField text;
	private String nev;
	Clicklistener click;
	private Character c;
	private Box b;
	private Szint sz;
	private Dicsoseglista dicso;
	private int szint=0;
	private KeyListener bill;
	private MouseListener eger;
	private boolean palyakeszit=false;
	private boolean listaz=false;
	
	/**
	 * Inicializ�lom a k�perny�t.
	 */
	public Kepernyo()  { //Inicializ�lom a K�perny�t
		dicso = new Dicsoseglista();
		click= new Clicklistener();
		Gombok gombok=new Gombok(click);
		text = new JTextField("Adja meg a nev�t.");  
	    text.setBounds(250,60, 100,30);  
		vissza = gombok.getVissza();
		jatek = gombok.getJatek();
		sajatgame = gombok.getSajat();
		dicsoseg = gombok.getDicsoseg();
		elsoSzint = gombok.getElso();
		masodikSzint = gombok.getMasodik();
		harmadikSzint = gombok.getHarmadik();
		negyedikSzint = gombok.getNegyedik();
		otodikSzint = gombok.getOtodik();
		hatodikSzint = gombok.getHatodik();
		mentes = gombok.getMentes();
		szint=1;
		this.setBackground(Color.CYAN);
		this.add(text);
		this.add(jatek);
		this.add(sajatgame);
		this.add(dicsoseg);
		this.setFocusable(true);
		this.setLayout(null);
		}
		/**
		 * A szintek k�perny�je.
		 */
		public void szintek() { /// itt lehet v�lasztani a szintek k�z�tt, ezt kirajzolom
			nev=text.getText();
			this.remove(text);
			this.remove(jatek);
			this.remove(sajatgame);
			this.remove(dicsoseg);
			this.add(elsoSzint);
			this.add(masodikSzint);
			this.add(harmadikSzint);
			this.add(negyedikSzint);
			this.add(otodikSzint);
			this.add(hatodikSzint);
			this.add(vissza);
			revalidate();
			repaint();
		}
		
		/**
		 * A p�lya szerkeszt� ablaka
		 * @throws IOException Ha nem l�tezne a f�jl.
		 */
		public void palyamaker() throws IOException { //p�lyak�szit� ablak
			this.remove(text);
			this.remove(jatek);
			this.remove(sajatgame);
			this.remove(dicsoseg);
			this.add(mentes);
			this.add(vissza);
			this.running=true;
			palyakeszit=true;
			this.removeMouseListener(eger);
			this.removeKeyListener(bill);
			this.sz=new Szint("palyamaker.txt");
			revalidate();
			repaint();
			this.addMouseListener(eger = new MouseListener() { //palya keszites eg�r kattint�ssal
			    @Override
			    public void mouseClicked(MouseEvent e) {
			    	sz.katt(e.getX(),e.getY());
			        repaint();
			    }
				@Override
				public void mouseEntered(MouseEvent e) {
				}
				@Override
				public void mouseExited(MouseEvent e) {
				}
				@Override
				public void mousePressed(MouseEvent e) {
				}
				@Override
				public void mouseReleased(MouseEvent e) {
				}
			});
			}
		

		/**
		 * Ha v�ge a j�t�knak a men�t rajzolja ki �jra
		 */
		public void endGame() { // v�ge a j�t�knak, ujra kirajzolja a men�t
			palyakeszit=false;
			listaz = false;
			szint=1;
			this.removeAll();
			running=false;
			this.remove(vissza);
			this.remove(mentes);
			this.add(text);
			this.add(jatek);
			this.add(dicsoseg);
			this.add(sajatgame);
			revalidate();
			repaint();
		}
		
		/**
		 * Elindul az egyik p�lya level.
		 * @param fileszint A p�lya f�jl neve.
		 * @param level	A szint sz�mlagosan ez kell a k�vetkez� szintre val� l�p�shez.
		 * @throws IOException Ha nem tal�lja a f�jlt.
		 */
		public void startGame(String fileszint,int level) throws IOException { //Elindul a j�t�k beolvassa a p�ly�t, ad egy keylistenert ha m�g eddig nem volt 
			this.remove(elsoSzint);										//ami mozgatja a karaktert illetve rajzol ha megnyomt�k.
			this.remove(masodikSzint);
			this.remove(harmadikSzint);
			this.remove(negyedikSzint);
			this.remove(otodikSzint);
			this.remove(hatodikSzint);
			szint=level+1;
			this.running=true;
			this.sz=new Szint(fileszint);
			this.c = sz.getChar();
			this.c.setName(nev);
			this.b = sz.getBox();
			this.removeMouseListener(eger);
			this.removeKeyListener(bill);
			this.addKeyListener(bill=new KeyListener(){ //awsd mozg�s,   r reset
				@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					switch(e.getKeyCode()) {  
					case KeyEvent.VK_A:
						c.move(-1,-50, 0,sz.getSzint(),b);
						kovetkezoszint();
						repaint(); 
						break;
					case KeyEvent.VK_D:
						c.move(1,50, 0,sz.getSzint(),b);
						kovetkezoszint();
						repaint();
						break;
					case KeyEvent.VK_W:
						c.move(c.getIrany(),0, -50,sz.getSzint(),b);
						kovetkezoszint();
						repaint();
						break;
					case KeyEvent.VK_S:
						c.move(c.getIrany(),0, 50,sz.getSzint(),b);
						kovetkezoszint();
						repaint();
						break;
					case KeyEvent.VK_R: //RESET
						c.reset();
						b.reset();
						repaint();
						break;
					}
				}
				catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			});
			this.revalidate();
			this.repaint();
		}
		
		/**
		 * Dics�s�g list�nak az ablaka.
		 */
		public void dicsosegAblak() {  //dics�s�glista ablaka
			this.remove(text);
			this.remove(jatek);
			this.remove(sajatgame);
			this.remove(dicsoseg);
			this.add(vissza);
			listaz=true;
			this.revalidate();
			this.repaint();
		}
		
		public void paintComponent(Graphics g) { //rajzol�s
			super.paintComponent(g);
			draw(g);
		}
		
		public void draw(Graphics g) { //rajzol�s
			if(running) {
			sz.draw(g,palyakeszit);
			 if (!palyakeszit)
				 g.drawString("L�p�s:"+c.getLepes(), 210, 520);
			}
			if(listaz) {
				dicso.draw(g);
			}
			
		}
		/**
		 * A k�vetkez� szintre l�p�st kezeli.
		 * @throws IOException Ha nem l�tezne a f�jl
		 */
		public void kovetkezoszint() throws IOException {  //k�vetkez� szintre l�p�st kezeli
			if(b.getX()==sz.getWinX() && b.getY()==sz.getWinY()) {
				if (szint==2) {
					dicso.mentes(szint-2,c);
					startGame("masodikszint.txt",2);
				}
				else if (szint==3) {
					dicso.mentes(szint-2,c);
					startGame("harmadikszint.txt",3);
				}
				else if (szint==4) {
					dicso.mentes(szint-2,c);
					startGame("negyedikszint.txt",4);
				}
				else if (szint==5) {
					dicso.mentes(szint-2,c);
					startGame("otodikszint.txt",5);
				}
				else if (szint==6) {
					dicso.mentes(szint-2,c);
					startGame("palyamaker.txt",6);
				}
					else if(szint==7) {
					endGame();
				}
			}	
		}
			
		/**
		 * A gombok megnyom�s�t figyeli.
		 * @author Axel
		 *
		 */
			private class Clicklistener implements ActionListener{ //gombok megnyom�s�t kezeli

				public void actionPerformed(ActionEvent e){
					try {
					if (e.getSource() == jatek){
						szintek();
					}
					else if (e.getSource() == sajatgame){
						palyamaker();
					}
					else if (e.getSource() == dicsoseg){
						dicsosegAblak();
					}
					else if (e.getSource() == elsoSzint){
						startGame("elsoszint.txt",1);
					}
					else if (e.getSource() == masodikSzint){
						startGame("masodikszint.txt",2);
					}
					else if (e.getSource() == harmadikSzint){
						startGame("harmadikszint.txt",3);
					}
					else if (e.getSource() == negyedikSzint){
						startGame("negyedikszint.txt",4);
					}
					else if (e.getSource() == otodikSzint){
						startGame("otodikszint.txt",5);
					}
					else if (e.getSource() == hatodikSzint){
						startGame("palyamaker.txt",6);
					}
					else if (e.getSource() == vissza){
						endGame();
					}
					else if (e.getSource() == mentes){
						sz.mentes();
						endGame();
					}
					}
					catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
		public int getSzint() {return szint;} //tesztel�shez van r� sz�ks�g
		public boolean getRun(){return running;}
		public boolean getListaz(){return listaz;}
		public boolean getPalyaKeszit(){return palyakeszit;}
}
