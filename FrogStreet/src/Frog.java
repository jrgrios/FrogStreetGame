import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Frog extends Rectangle {
	Image imagen;

	public Frog(Image imagen) {
		super(500, 25, 75, 75);
		this.imagen = imagen;
	}

	public void dibujar(Graphics g, Applet a) {
		g.drawImage(imagen, x, y, width, height, a);
	}
}
