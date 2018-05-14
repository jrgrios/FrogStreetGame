package Juego;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Log extends Rectangle {

	Image log;

	public Log(Image log, int posX, int posY) {
		super(posX, posY, 80, 60);
		this.log = log;
	}

	public void dibujar(Graphics g, Applet a) {
		g.drawImage(log, x, y, width, height, a);
	}

	public void actualizar() {
		x -= (int) (Math.random() * 16);
	}

	public void actualizarDerecha() {
		x += (int) (Math.random() * 16);
	}

}
