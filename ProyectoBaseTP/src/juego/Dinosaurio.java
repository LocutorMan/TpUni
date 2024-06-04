package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Dinosaurio {

	private Image img;
	private Image img2;
	private double x;
	private double y;
	private boolean direccion;
	private double angulo;
	private double escala;
	private double VelociodadDesplazamiento;
	private double desplazamientoGravedad;
	private boolean estaApoyado;
	private boolean movDino;
	private Bomba bomba;
	private int contadorBomba;
	

	// Constructor de dinosaurio

	public Dinosaurio(double x, double y) {
		this.img = Herramientas.cargarImagen("dinoder.png");
		this.img2 = Herramientas.cargarImagen("dinoizq.png");
		this.x = x;
		this.y = y;
		this.direccion = false;
		this.angulo = 0;
		this.escala = 0.07;
		this.VelociodadDesplazamiento = 1;
		this.desplazamientoGravedad = 0.5;
		this.estaApoyado = false;
		this.movDino = true;
		this.contadorBomba = 0;
	}
	
	// Disparo de la bomba
	public void disparo(Entorno entorno) {
		if (contadorBomba > 2000 && bomba == null) {
			bomba = new Bomba(this.x, this.y, this.movDino);
			contadorBomba = 0; // El contador de bomba se resetea despues de disparar
		}
		if (bomba != null) {
			bomba.dibujarBomba(entorno);
			bomba.moverBomba();
			if (!bomba.estaDentroDelMapa(entorno)) {
				bomba = null;
			}
		}

	}

	// dibuja al dinosaurio y la direccion donde esta mirando la imagen
	public void dibujar(Entorno entorno) {
		if (direccion) {
			entorno.dibujarImagen(this.img, this.x, this.y, this.angulo, this.escala);
		} else {
			entorno.dibujarImagen(this.img2, this.x, this.y, this.angulo, this.escala);
		}
	}

	// Hace que el dinosaurio se mueva, y si choca contra un borde de la pantalla
	// cambia de direccion

	public void direccion(Entorno e) {
		
		if (this.getDerecha() > e.ancho() || this.getIzquierda() < 0) {
			movDino = !movDino;
		}
		if (movDino) {
			direccion = movDino;
			this.moverDerecha();
		}
		if (!movDino) {
			direccion = movDino;
			this.moverIzquierda();
		}
	}

	// crea una columna de dinosaurios con los espacios y la cantidad de dinosaurios

	public static Dinosaurio[] dinos(int cant, double x, double y, double espacioHorizontal, double espacioVertical) {
		Dinosaurio[] dinos = new Dinosaurio[cant];
		for (int i = 0; i < cant; i++) {
			dinos[i] = new Dinosaurio(x + i * espacioHorizontal, y + i * espacioVertical);
		}
		return dinos;
	}
	
	public void moverIzquierda() {
		x -= VelociodadDesplazamiento;
	}

	public void moverDerecha() {
		x += VelociodadDesplazamiento;
	}

	public void gravedad() {
		if (!estaApoyado) {
			y += desplazamientoGravedad;
		}
	}
	
	public void cambiarDireccion() {
		this.movDino = !this.movDino;
	}
	
	public int getContadorBomba() {
		return contadorBomba;
	}

	public void setContadorBomba(int contadorBomba) {
		this.contadorBomba = contadorBomba;
	}
	
	public Bomba getBomba() {
		return bomba;
	}

	public void setBomba(Bomba bomba) {
		this.bomba = bomba;
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

	public boolean isEstaApoyado() {
		return estaApoyado;
	}

	public void setEstaApoyado(boolean estaApoyado) {
		this.estaApoyado = estaApoyado;
	}

	public void setY(double y) {
		this.y = y;
	}
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

}
