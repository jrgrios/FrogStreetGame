package Juego;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FrogStreet extends Applet implements Runnable {
	Thread animacion;
	Image imagen;
	/// Imagenes del juego
	Image fondo, imagenrana;
	Frog rana;
	// Sonidos
	AudioClip sonidorana;
	Graphics noseve;
	List<Log> logLeft1;
	List<Log> logRight1;
	int contador = 100;

	List<Car> carMiddle;
	List<Car> carUp;
	List<Car> carDown;

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
		// lista izquierda 1
		logLeft1 = new ArrayList<Log>();
		logLeft1.add(new Log(getImage(getCodeBase(), "imagenesFrogStreet/log1.png"), 500, 120));
		// lista derecha 1
		logRight1 = new ArrayList<Log>();
		logRight1.add(new Log(getImage(getCodeBase(), "imagenesFrogStreet/log1.png"), 80, 150));
		// car Middle 1
		carMiddle = new ArrayList<Car>();
		carMiddle.add(new Car(getImage(getCodeBase(), "imagenesFrogStreet/coche1.png"), 500, 320));
		// car UP
		carUp = new ArrayList<Car>();
		carUp.add(new Car(getImage(getCodeBase(), "imagenesFrogStreet/coche2.png"), 200, 150));
	}

	public void start() {
		animacion = new Thread(this);
		animacion.start(); // llama al método run()
	}

	public void paint(Graphics g) {
		resize(800, 800);
		noseve.setColor(Color.green);
		noseve.fillRect(0, 0, 2000, 2000);
		/// Dibujamos las imagenes del juego
		noseve.drawImage(fondo, 25, 25, 620, 620, this);

		for (int i = 0; i < logLeft1.size(); i++) {
			logLeft1.get(i).dibujar(noseve, this);
		}

		for (int i = 0; i < logRight1.size(); i++) {
			logRight1.get(i).dibujar(noseve, this);
		}

		for (int i = 0; i < carMiddle.size(); i++) {
			carMiddle.get(i).dibujar(noseve, this);
		}

		for (int i = 0; i < carUp.size(); i++) {
			carUp.get(i).dibujar(noseve, this);
		}

		rana.dibujar(noseve, this);

		g.drawImage(imagen, 0, 0, this);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void run() {

		while (true) {

			for (int i = 0; i < logLeft1.size(); i++) {
				logLeft1.get(i).actualizar();
				if (logLeft1.get(i).x <= 50)
					logLeft1.remove(i);
			}
			for (int j = 0; j < logRight1.size(); j++) {
				logRight1.get(j).actualizarDerecha();
				if (logRight1.get(j).x >= 500)
					logRight1.remove(j);
			}
			for (int i = 0; i < logLeft1.size(); i++) {
				if (rana.intersects(logLeft1.get(i))) {
					rana.x = logLeft1.get(i).x;
				} else
					rana.x = rana.x;
			}
			for (int i = 0; i < logRight1.size(); i++) {
				if (rana.intersects(logRight1.get(i))) {
					rana.x = logRight1.get(i).x;
				} else
					rana.x = rana.x;
					
			}

			contador += 50;
			if (contador == 2000) {
				logLeft1.add(new Log(getImage(getCodeBase(), "imagenesFrogStreet/log1.png"), 500, 120));
				logRight1.add(new Log(getImage(getCodeBase(), "imagenesFrogStreet/log1.png"), 80, 150));
				contador = 50;

			}
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
			;
		}
	}

	public boolean keyDown(Event e, int tecla) {
		// Izquierda
		if (tecla == 1006) {
			sonidorana.play();
			rana.x -= 40;
		}
		// Derecha
		if (tecla == 1007) {
			sonidorana.play();
			rana.x += 40;
		}
		/// Baja
		if (tecla == 1005) {
			sonidorana.play();
			rana.y += 40;
		}
		// Sube
		if (tecla == 1004) {
			sonidorana.play();
			rana.y -= 40;
		}

		repaint();
		return true;
	}

}
