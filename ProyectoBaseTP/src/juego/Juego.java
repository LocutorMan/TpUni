package juego;

import java.awt.Color;

import entorno.*;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;

	// Variables y métodos propios de cada grupo
	// ...
	private Fondo fondo;
	private Bloques[] primerFila;
	private Bloques[] bloques;
	private Princesa princesa;
	private Colisionador colisionador;
	private Dinosaurio dinosaurio;
	private Dinosaurio[] dinos1;
	private Dinosaurio[] dinos2;
	private Bomba bomba;
	private Bala bala;
	private Magma magma;
	private boolean inmortal;
	private boolean dire;

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Attack on Titan, Final Season - Grupo ... - v1", 800, 600);

		// Inicializar lo que haga falta para el juego
		// ...

		// Fondo
		fondo = new Fondo(400, 300);

		// Filas de bloques
		this.primerFila = Bloques.crearFilaDeBloques(21, 18, 580, 39.5);
		this.bloques = Bloques.crearMultiplesFilasDeBloques(4, 21, 18, 434, 39.5, 146);

		// Princesa
		this.princesa = new Princesa(400, 525);

		// Bala de la princesa
		this.bala = null;

		// Dinosaurio
		this.dinosaurio = new Dinosaurio(120, 525);

		// Bomba del dinosaurio
		this.bomba = null;

		// Dinosaurios

		this.dinos1 = Dinosaurio.dinos(4, 50, 85, 20, 146);// columna de la izquierda
		this.dinos2 = Dinosaurio.dinos(4, 750, 85, -20, 146);// colimna de la derecha

		// Colisionador
		this.colisionador = new Colisionador();

		// Magma que va subiendo
		this.magma = new Magma(400, 900);

		// Inicia el juego!
		this.entorno.iniciar();
	}

	public void tick() {

		for (int i = 0; i < dinos1.length; i++) {
			if (dinos1[i] != null && dinos2[i] != null) {
				if (colisionador.dinoPrincesa(princesa, dinos1[i]) || colisionador.dinoPrincesa(princesa, dinos2[i])) {
					princesa = null; // La princesa es null si toca un dinosaurio
					break; // Sal del bucle una vez que se haya detectado una colisión
				}
			}

		}

		// Procesamiento de un instante de tiempo
		// ...
		// Fondo
		fondo.dibujarse(entorno);

		// Filas de bloques
		for (int i = 0; i < primerFila.length; i++) { // Primer fila de bloques (piso).
			primerFila[i].dibujarBloqueDestructible(entorno);
		}

		int contBloques = 0;
		for (int i = 0; i < bloques.length; i++) {
			if (contBloques < 5) {
				bloques[i].dibujarBloqueDestructible(entorno);
				colisionador.manejarColisiones(princesa, bloques);
				contBloques++;
			} else {
				for (int j = 0; j < 3; j++) { // Dibuja tres bloques indestructibles
					bloques[i + j].dibujarBloqueIndestructible(entorno);
				}
				i += 2;// Se incrementa i en 2 para saltar los bloques ya dibujados
				contBloques = 0; // Se reinicia el contador al dibujar 3 bloques indestructibles
			}
		}

		// Princesa
		if (princesa != null) {
			princesa.dibujarse(entorno);
			colisionador.manejarColisiones(princesa, primerFila);
			colisionador.manejarColisiones(princesa, bloques);
//        colisionador.manejarColisiones(princesa, bloques);

			if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
				princesa.setDireccion(false);
				princesa.moverIzquierda();
			}

			if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
				princesa.setDireccion(true);
				princesa.moverDerecha();
			}

			if (entorno.sePresiono(entorno.TECLA_ESPACIO)) {
				princesa.saltar();
			}

			if (entorno.sePresiono('c')) {
				if (bala == null) {
					bala = new Bala(princesa.getX(), princesa.getY());
					dire = princesa.isDireccion();
				}
			}

			// Dibujar y mover disparo
			if (bala != null) {
				bala.dibujar(entorno);
				bala.moverDisparo(dire);
				if (!bala.estaDentroDelMapa(entorno)) {
					bala = null;
				}
			}
		}

		// Dinosaurio
		// movimiento de dinosaurio, y cambio de direccion si choca una pared
		if (princesa != null) {
			// Colision entre princesa y tiranosaurio
			for (int i = 0; i < dinos1.length; i++) {
				if (dinos1[i] != null) {
					dinos1[i].setContadorBomba(dinos1[i].getContadorBomba() + 10);
					dinos1[i].disparo(entorno);
					if (dinos1[i].getBomba() != null && dinos1[i].getBomba().colisionaCon(princesa.getX(),
							princesa.getY(), princesa.getAncho(), princesa.getAlto())) {
						princesa = null;
						dinos1[i].setBomba(null); // Eliminar bomba después de colisionar
					}
//					if (dinos2[i].getBomba() != null && dinos2[i].getBomba().colisionaCon(princesa.getX(),
//							princesa.getY(), princesa.getAncho(), princesa.getAlto())) {
//						princesa = null;
//						dinos2[i].setBomba(null); // Eliminar bomba después de colisionar
//					}

					// primer columna de dinos
					dinos1[i].dibujar(entorno);// dibuja al dinosaurio en la posicion i
					dinos1[i].direccion(entorno);// direcciona al dinosaurio
					dinos1[i].gravedad();// si el dinosaurio no esta apoyado cae
					colisionador.manejarColisiones(dinos1[i], primerFila);// comprueba si esta apoyado
					colisionador.manejarColisiones(dinos1[i], bloques);// comprueba si esta apoyado
					if (bala != null && colisionador.detectarColision(bala, dinos1[i])) {
						dinos1[i] = null; // Eliminar tiranosaurio
						bala = null; // Eliminar disparo
					}
					if (bala != null && dinos1[i].getBomba() != null
							&& bala.colisionaCon(dinos1[i].getBomba().getX(),
									dinos1[i].getBomba().getY(), dinos1[i].getBomba().getAncho(),
									dinos1[i].getBomba().getAlto())) {
						bala = null; // Eliminar disparo
						dinos1[i].setBomba(null); // Eliminar bomba
					}
				}
			}
			// segunda columna de dinos
//				if (dinos2[i] != null) {
//					dinos2[i].setContadorBomba(dinos1[i].getContadorBomba() + 10);
//					dinos2[i].disparo(entorno);
			for (int i = 0; i < dinos1.length; i++) {
				if (dinos2[i] != null) {
					dinos2[i].setContadorBomba(dinos2[i].getContadorBomba() + 10);
					dinos2[i].disparo(entorno);
					if (dinos2[i].getBomba() != null && dinos2[i].getBomba().colisionaCon(princesa.getX(),
							princesa.getY(), princesa.getAncho(), princesa.getAlto())) {
						princesa = null;
						dinos2[i].setBomba(null); // Eliminar bomba después de colisionar
					}
					dinos2[i].dibujar(entorno);// dibuja al dinosaurio en la posicion i
					dinos2[i].direccion(entorno);// direcciona al dinosaurio
					dinos2[i].gravedad();// si el dinosaurio no esta apoyado cae
					colisionador.manejarColisiones(dinos2[i], primerFila);// compruba si esta apoyado
					colisionador.manejarColisiones(dinos2[i], bloques);// comprueba si esta apoyado
					if (bala != null && colisionador.detectarColision(bala, dinos2[i])) {
						dinos2[i] = null; // Eliminar tiranosaurio
						bala = null; // Eliminar disparo
					}
					if (bala != null && dinos2[i].getBomba() != null
							&& bala.colisionaCon(dinos2[i].getBomba().getX(),
									dinos2[i].getBomba().getY(), dinos2[i].getBomba().getAncho(),
									dinos2[i].getBomba().getAlto())) {
						bala = null; // Eliminar disparo
						dinos2[i].setBomba(null); // Eliminar bomba
					}
				}
				// cambio de direccion si chocan
				for (int j = 0; j < dinos1.length; j++) {
					// si colisionan cambia la direccion de los dinosaurios
					if (dinos1[i] != null && dinos2[j] != null && colisionador.colisionDeDinos(dinos1[i], dinos2[j])) {
						dinos1[i].cambiarDireccion();
						dinos2[j].cambiarDireccion();
					}
				}
				for (int j = 0; j < dinos1.length; j++) {
					// si colisionan cambia la direccion de los dinosaurios
					if (dinos2[i] != null && dinos2[j] != null && colisionador.colisionDeDinos(dinos2[i], dinos2[j])) {
						dinos2[i].cambiarDireccion();
						dinos2[j].cambiarDireccion();
					}
				}
				for (int k = 0; k < dinos1.length; k++) {
					// si colisionan cambia la direccion de los dinosaurios
					if (dinos1[i] != null && dinos1[k] != null && colisionador.colisionDeDinos(dinos1[i], dinos1[k])) {
						dinos1[i].cambiarDireccion();
						dinos1[k].cambiarDireccion();
					}
				}

//				if (bala != null && colisionador.detectarColision(bala, dinos1[i])) {
//					dinos1[i] = null; // Eliminar tiranosaurio
//					bala = null; // Eliminar disparo
//				}
//				if (bala != null && colisionador.detectarColision(bala, dinos2[i])) {
//					dinos2[i] = null; // Eliminar tiranosaurio
//					bala = null; // Eliminar disparo
//				}

			}

			// dibuja el magma y avanza para arriba si la princesa NO es null
			magma.dibujar(entorno);
			if (princesa != null) {
				magma.subir();
			}

			// si la princesa es null aparece la pantalla de juego terminado
			if (princesa == null) {
				fondo.juegoTermiando(entorno);
			}
		}

	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
