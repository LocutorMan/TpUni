package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Bomba {
	private Image img;
    private double x;
    private double y;
    private double velocidad;
    private boolean activa;

    public Bomba(double x, double y, double velocidad) {
        this.img = Herramientas.cargarImagen("bombaa.png"); 
        this.x = x;
        this.y = y;
        this.velocidad = velocidad;
        this.activa = true;
    }

    public void dibujar(Entorno entorno) {
        if (activa) {
            entorno.dibujarImagen(img, this.x, this.y, 0, 0.1); 
        }
    }

    public void mover() {
        if (activa) {
            this.x += velocidad;
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
    
    public void desactivar() {
        this.activa = false;
    }

}


