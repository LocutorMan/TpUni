package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Bomba {
	private double x, y;
	private Image bombader;
	private Image bombaizq;
	private boolean direccion;
	private double velocidad;
	private double escala;

	// Constructor

	public Bomba(double x, double y, boolean direccion) {
		this.x = x;
		this.y = y;
		this.direccion = direccion;
		this.bombader = Herramientas.cargarImagen("bombader.png");
		this.bombaizq = Herramientas.cargarImagen("bombaizq.png");
		this.velocidad = 5.0;
		this.escala = 0.05;
	}

	// Dibujar la bomba
	public void dibujarBomba(Entorno entorno) {
		if (this.direccion) {
			entorno.dibujarImagen(bombader, this.x, this.y, 0, escala);
		} else
			entorno.dibujarImagen(bombaizq, this.x, this.y, 0, escala);
	}

	// Mueve la bomba
	public void moverBomba() {
		if (this.direccion) {
			this.x += this.velocidad;

		} else {
			this.x -= this.velocidad;
		}
	}

	// Si la bomba choca contra los brodes del mapa
	public boolean estaDentroDelMapa(Entorno entorno) {
		return this.x >= 0 && this.x <= entorno.ancho() && this.y >= 0 && this.y <= entorno.alto();
	}

	// Si choca la bomba con otro objeto
	public boolean colisionaCon(double otroX, double otroY, double ancho, double alto) {
		return this.x < otroX + ancho && this.x + this.bombader.getWidth(null) * 0.1 > otroX && this.y < otroY + alto
				&& this.y + this.bombader.getHeight(null) * 0.1 > otroY;
	}

	// Getters y Setters

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public boolean esDireccion() {
		return direccion;
	}

	public void setDireccion(boolean direccion) {
		this.direccion = direccion;
	}

	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	public double getAlto() {
		return this.bombader.getHeight(null) * this.escala;
	}

	public double getAncho() {
		return this.bombader.getWidth(null) * this.escala;
	}
	
	public double getTecho() {
		return this.y - getAlto() / 2;
	}

	public double getPiso() {
		return this.y + getAlto() / 2;
	}

	public double getIzquierda() {
		return this.x - getAncho() / 2;
	}

	public double getDerecha() {
		return this.x + getAncho() / 2;
	}
}
