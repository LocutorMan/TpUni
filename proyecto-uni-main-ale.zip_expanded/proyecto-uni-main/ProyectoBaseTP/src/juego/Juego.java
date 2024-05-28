package juego;

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

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Attack on Titan, Final Season - Grupo ... - v1", 800, 600);

		// Inicializar lo que haga falta para el juego
		// ...

		// Fondo
		fondo = new Fondo(400, 300);

		// Filas de bloques
		this.primerFila = Bloques.crearFilaDeBloques(21, 18, 580, 39.5);
		this.bloques = Bloques.crearMultiplesFilasDeBloques(4, 24, 18, 434, 39.5, 146);

		// Princesa
		this.princesa = new Princesa(400, 525);

		// Dinosaurio
		this.dinosaurio = new Dinosaurio(120, 525);

		// Dinosaurios

		this.dinos1 = Dinosaurio.dinos(4, 50, 85, 20, 146);// columna de la izquierda
		this.dinos2 = Dinosaurio.dinos(4, 750, 85, -20, 146);// colimna de la derecha

		// Colisionador
		this.colisionador = new Colisionador();

		// Inicia el juego!
		this.entorno.iniciar();
	}

	public void tick() {
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

		// Dinosaurio
		// movimiento de dinosaurio, y cambio de direccion si choca una pared

		for (int i = 0; i < dinos1.length; i++) {
			// primer columna de dinos
			dinos1[i].dibujar(entorno);// dibuja al dinosaurio en la posicion i
			dinos1[i].direccion(entorno);// direcciona al dinosaurio
			dinos1[i].gravedad();// si el dinosaurio no esta apoyado cae
			colisionador.manejarColisiones(dinos1[i], primerFila);// comprueba si esta apoyado
			colisionador.manejarColisiones(dinos1[i], bloques);// comprueba si esta apoyado
			// segunda columna de dinos
			dinos2[i].dibujar(entorno);// dibuja al dinosaurio en la posicion i
			dinos2[i].direccion(entorno);// direcciona al dinosaurio
			dinos2[i].gravedad();// si el dinosaurio no esta apoyado cae
			colisionador.manejarColisiones(dinos2[i], primerFila);// compruba si esta apoyado
			colisionador.manejarColisiones(dinos2[i], bloques);// comprueba si esta apoyado

		}

	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
