package Juego;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Car extends Rectangle {
	Image car;

	public Car(Image car, int posX, int posY) {
		super(posX, posY, 80, 60);
		this.car = car;
	}

	public void dibujar(Graphics g, Applet a) {
		g.drawImage(car, x, y, width, height, a);
	}

}
