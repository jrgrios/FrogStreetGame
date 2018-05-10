
import java.applet.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class FrogStreet extends Applet implements Runnable {
	Thread animacion;
	Image imagen;
	/// Imagenes del juego
	Image fondo, imagenrana;
	Frog rana;
	// Sonidos
	AudioClip sonidorana;
	Graphics noseve;

	public void init() {
		imagen = createImage(1500, 1500);
		noseve = imagen.getGraphics();
		fondo = getImage(getCodeBase(), "imagenesFrogStreet/fondo.jpg");
		imagenrana = getImage(getCodeBase(), "imagenesFrogStreet/rana.png");
		rana = new Frog(imagenrana);
		try {
			sonidorana = getAudioClip(new URL(getDocumentBase(), "sonidos/croak.wav"));
		} catch (MalformedURLException e) {
		}
	}

	public void start() {
		animacion = new Thread(this);
		animacion.start(); // llama al método run()
	}

	public void paint(Graphics g) {
		noseve.setColor(Color.green);
		noseve.fillRect(0, 0, 2000, 2000);
		/// Dibujamos las imagenes del juego
		noseve.drawImage(fondo, 25, 25, 620, 620, this);

		rana.dibujar(noseve, this);

		g.drawImage(imagen, 0, 0, this);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void run() {
		while (true) {
			repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
			;
		}
	}

	public boolean keyDown(Event e, int tecla) {
		// Izquierda
		if (tecla == 1006) {
			sonidorana.play();
			rana.x -= 10;
		}
		// Derecha
		if (tecla == 1007) {
			sonidorana.play();
			rana.x += 10;
		}
		/// Baja
		if (tecla == 1005) {
			sonidorana.play();
			rana.y += 10;
		}
		// Sube
		if (tecla == 1004) {
			sonidorana.play();
			rana.y -= 10;
		}

		repaint();
		return true;
	}

}
