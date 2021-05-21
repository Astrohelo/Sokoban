package soko;


import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class GameTeszt {

	@Test
	public void Character() { //karakter inicializ�l�sa
		 Character c=new Character(200,250,50);
	     Assert.assertEquals(c.getIrany(),1);
	     Assert.assertEquals(c.getX(),200);
	     Assert.assertEquals(c.getY(),250);
	     Assert.assertEquals(c.getPrevX(),200);
	     Assert.assertEquals(c.getPrevY(),250);
	}
	
	@Test
	public void move() throws IOException {
		Szint sz= new Szint("elsoszint.txt"); //itt a karakter 100,250 pontban van illetve a doboz 250,250 pontban
		Character c=sz.getChar();
		Box b=sz.getBox();
	    c.move(1, 50, 0, sz.getSzint(), b); //az�rt kell 3x mert a dobozt ha el�ri akkor annyival tolja meg amennyit � l�pett , lehetne 2 move is de igy �tl�that�bb
	    c.move(1, 50, 0, sz.getSzint(), b);
	    c.move(1, 50, 0, sz.getSzint(), b);
	    Assert.assertEquals(c.getX(),c.getKezdoX()+150);
	    Assert.assertEquals(c.getPrevX(),200);
	    Assert.assertEquals(b.getX(),b.getPrevX()+50);
	}
	
	@Test
	public void reset() throws IOException{
		Szint sz= new Szint("elsoszint.txt"); //itt a karakter 100,250 pontban van illetve a doboz 250,250 pontban
		Character c=sz.getChar();
		Box b=sz.getBox();
	    c.move(1, 50, 0, sz.getSzint(), b);
	    c.move(1, 50, 0, sz.getSzint(), b);
	    c.move(1, 50, 0, sz.getSzint(), b);
	    c.reset();
	    b.reset();
	    Assert.assertEquals(c.getX(),100);
	    Assert.assertEquals(c.getPrevX(),100);
	    Assert.assertEquals(b.getX(),250);
	    Assert.assertEquals(b.getX(),250);
	}
	
	@Test (expected = IOException.class) //Jatek inditasa
	public void startGame() throws IOException{
		Kepernyo k=new Kepernyo();
		k.startGame("asdasd.txt", 3);
	}
	
	@Test 
	public void Szint() throws IOException{ //Szint beolvas�sa
		Szint sz=new Szint("elsoszint.txt");
		Assert.assertEquals(sz.getChar().getX(),100);
		Assert.assertEquals(sz.getBox().getX(),250);
		Assert.assertEquals(sz.getWinX(),350);
		Assert.assertEquals(sz.getWinY(),250);
	}
	
	@Test 
	public void kovetkezoszint() throws IOException{ // k�vetkez� szintre l�p�s
		Kepernyo k=new Kepernyo();
		k.startGame("elsoszint.txt", 1);
		k.kovetkezoszint();
		Assert.assertEquals(k.getRun(),true);
		Assert.assertEquals(k.getSzint(),2);
	}
	
	@Test 
	public void endGame() throws IOException{ // vissza l�p�s a men�be
		Kepernyo k=new Kepernyo();
		k.startGame("elsoszint.txt", 1);
		k.kovetkezoszint();
		k.endGame();
		Assert.assertEquals(k.getSzint(),1);
		Assert.assertEquals(k.getRun(),false);
	}
	@Test 
	public void dicsosegAblak() throws IOException{ // a dics�s�glista megtekint�se
		Kepernyo k=new Kepernyo();
		k.dicsosegAblak();
		Assert.assertEquals(k.getRun(),false);
		Assert.assertEquals(k.getListaz(),true);
	}
	
	@Test 
	public void palyamaker() throws IOException{ // a p�lyak�szit�s
		Kepernyo k=new Kepernyo();
		k.palyamaker();
		Assert.assertEquals(k.getPalyaKeszit(),true);
		Assert.assertEquals(k.getRun(),true);
	}
	
	@Test 
	public void katt() throws IOException{ // a p�lyak�szit�s kattint�s falr�l h�tt�r
		Szint sz=new Szint("elsoszint.txt");
		sz.katt(510, 60);
		sz.katt(10, 10);
		Assert.assertEquals(sz.getSzint()[0][0].getFalE(),false);
	}
	


}
