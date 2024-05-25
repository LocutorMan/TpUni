package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Princesa {
	
	
	// -------------------- AGREGAR SPRITE NUEVO PARA estaSaltando --------------------  
	
    
    private Image img;
    private Image img2;
    private double x;
    private double y;
    private boolean direccion;
    private double angulo;
    private double escala;
    private double VelociodadDesplazamiento;
    private double desplazamientoSaltar;
    private double desplazamientoGravedad;
    private boolean estaApoyado;
    private boolean estaSaltando;
    
    public Princesa(double x, double y){
        this.img = Herramientas.cargarImagen("princesaDerecha.png");
        this.img2 = Herramientas.cargarImagen("princesaIzquierda.png");
        this.x = x;
        this.y = y;
        this.direccion = false;
        this.angulo = 0;
        this.escala = 0.5;
        this.VelociodadDesplazamiento = 3;
        this.desplazamientoSaltar = 160;
        this.desplazamientoGravedad = 0.5;
        this.estaApoyado = false;
        this.estaSaltando = false;
    }
        
    public void dibujarse(Entorno entorno) {
        if(direccion) {
            entorno.dibujarImagen(this.img, this.x, this.y, this.angulo, this.escala);
        } else {
            entorno.dibujarImagen(this.img2, this.x, this.y, this.angulo, this.escala);
        }
    }
    
    public void moverIzquierda() {
        x -= VelociodadDesplazamiento;
    }
    
    public void moverDerecha() {
        x += VelociodadDesplazamiento;
    }
    
    public void saltar() {
        if(!estaSaltando) {
            y -= desplazamientoSaltar;
            
            // -------------------- AGREGAR ACA EL SPRITE --------------------
            
            estaSaltando = true;
            estaApoyado = false;
        }
    }

    public void gravedad() {
        if (!estaApoyado) {
            y += desplazamientoGravedad;
        }
    }

    public double getAlto() {
        return this.img.getHeight(null) * this.escala;
    }

    public double getAncho() {
        return this.img.getWidth(null) * this.escala;
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

    public boolean isDireccion() {
        return direccion;
    }

    public void setDireccion(boolean direccion) {
        this.direccion = direccion;
    }

    public boolean isEstaSaltando() {
        return estaSaltando;
    }

    public void setEstaSaltando(boolean estaSaltando) {
        this.estaSaltando = estaSaltando;
    }

    public boolean isEstaApoyado() {
        return estaApoyado;
    }

    public void setEstaApoyado(boolean estaApoyado) {
        this.estaApoyado = estaApoyado;
    }

    public void setY(double y) {
        this.y = y;
    }
}

