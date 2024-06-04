package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Fondo {
	private double x;
	private double y;
	private Image imgFondo;
	double anguloFondo;
	private Image gameover;
	
	public Fondo(double x, double y) {
		this.x = x;
		this.y = y;
		imgFondo = Herramientas.cargarImagen("magma.jpg");
		gameover = Herramientas.cargarImagen("gameover.jpg");
		anguloFondo=0;
	}
	
	public void dibujarse(Entorno entorno)
	{
		entorno.dibujarImagen(imgFondo, this.x, this.y, this.anguloFondo, 0.820);
	}
	
	public void juegoTermiando(Entorno entorno) {
		entorno.dibujarImagen(gameover, this.x, this.y, this.anguloFondo, 0.20);
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	
	
}