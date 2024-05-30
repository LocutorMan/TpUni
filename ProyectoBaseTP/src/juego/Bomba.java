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
    private double escala;
    

    public Bomba(double x, double y, double velocidad,double escala) {
        this.img = Herramientas.cargarImagen("bomba.png"); 
        this.x = x;
        this.y = y;
        this.velocidad = velocidad;
        this.activa = true;
        this.escala = 0.05;
    }

    public void dibujar(Entorno entorno) {
        if (activa) {
            entorno.dibujarImagen(img, this.x, this.y, 0, this.escala); 
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


