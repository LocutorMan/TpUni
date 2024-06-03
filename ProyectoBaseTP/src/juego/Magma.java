package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Magma {
	private Image imagen;
	private double x;
	private double y;
	private double angulo;
	private double escala;
	private double velocidad;
	
	public Magma(double x,double y) {
		this.x = x;
		this.y = y;
		this.escala = 1;
		this.angulo = 0;
		this.imagen = Herramientas.cargarImagen("MagmaSubiendo.jpg");
		this.velocidad = 0.05;
	}
	
	public void dibujar(Entorno entorno) {
		entorno.dibujarImagen(this.imagen, x, y, angulo);
	}
	
	public void subir() {
		y -= velocidad;
	}

}
