package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Fondo {
	private double x;
	private double y;
	private Image imgFondo;
	double anguloFondo;
	
	public Fondo(double x, double y) {
		this.x = x;
		this.y = y;
		imgFondo = Herramientas.cargarImagen("magma.jpg");
		anguloFondo=0;
	}
	
	public void dibujarse(Entorno entorno)
	{
		entorno.dibujarImagen(imgFondo, this.x, this.y, this.anguloFondo, 0.820);
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	
	
}