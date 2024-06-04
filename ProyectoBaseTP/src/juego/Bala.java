package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Bala {
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private double escala;
	private boolean direccion;
	private Image balader;
	private Image balaizq;

	public Bala(double x, double y) {
		this.x = x;
		this.y = y;
		this.ancho = 25;
		this.alto = 20;
		this.escala = 0.5;
		this.direccion = true;
		this.balader = Herramientas.cargarImagen("paraguader.png");
		this.balaizq = Herramientas.cargarImagen("paraguaizq.png");

	}
	public void dibujar(Entorno e) {
		if (this.direccion) {
			e.dibujarImagen(balader, x, y, 3.15, escala);
		}
		if (this.direccion == false) {
			e.dibujarImagen(balaizq, x, y, 0, escala);
		}
	}

	public void moverDisparo(boolean dire) {
		if (dire == true) {
			this.x += 10;
			direccion = true;
		} else {
			this.x -= 10;
			direccion = false;
		}
	}

	public boolean estaDentroDelMapa(Entorno e) {
		if (this.x < -50 || this.x > e.ancho() + 50 || this.y < -50 || this.y > e.alto() + 50)
			return false;
		return true;
	}

	public boolean colisionaCon(double otroX, double otroY, double ancho, double alto) {
		return this.x < otroX + ancho && this.x + this.ancho > otroX && this.y < otroY + alto
				&& this.y + this.alto > otroY;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getAncho() {
		return ancho;
	}

	public double getAlto() {
		return alto;
	}
}