package juego;

//import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Bloques {
    
    private Image img;
    private Image img2;
    private double x;
    private double y;
    private double angulo;
    private double escala;
    private boolean destructible;
    
    public Bloques(double x, double y) {
        img = Herramientas.cargarImagen("bloqueDestructible.jpg");
        img2 = Herramientas.cargarImagen("bloqueIndestructible.jpg");
        this.x = x;
        this.y = y;
        this.angulo = 0;
        this.escala = 0.15;
        this.destructible = false;
    }
    
    public void dibujarBloqueDestructible(Entorno entorno)
    {
        //entorno.dibujarRectangulo(this.x, this.y, 38, 38, 0, Color.CYAN);
        entorno.dibujarImagen(img, this.x, this.y, this.angulo, this.escala);
        this.destructible = true;
    }
    
    public void dibujarBloqueIndestructible(Entorno entorno) {
    	//entorno.dibujarRectangulo(this.x, this.y, 38, 38, 0, Color.CYAN);
    	entorno.dibujarImagen(img2, this.x, this.y, angulo, 0.15);
    	this.destructible = false;
    }
    
    public static Bloques[] crearFilaDeBloques(int cant, double x, double y, double espacio) {
        Bloques[] filaDeBloques = new Bloques[cant];	// Se agrega un array de bloques del tamaño dado en cant
        for (int i = 0; i < cant; i++) {
            filaDeBloques[i] = new Bloques(x + i * espacio, y);		// Se crea un nuevo bloque con sus respectivas medidas	
        }
        return filaDeBloques;
    }
    
    public static Bloques[] crearMultiplesFilasDeBloques(int filas, int cantPorFila, double x, double y, double espacioX, double espacioY) {
        Bloques[] multiplesFilas = new Bloques[filas * cantPorFila];	// Se agrega un array de filas (cada una del tamaño dado en cantPorFila)
        int pos = 0;	// Posicion en el array
        for (int i = 0; i < filas; i++) {
            Bloques[] fila = crearFilaDeBloques(cantPorFila, x, y - i * espacioY, espacioX);	// Se crea una fila
            for (int j = 0; j < fila.length; j++) {
                multiplesFilas[pos++] = fila[j];	// Se asigna el bloque actual al array multiplesFilas
            }
        }
        return multiplesFilas;
    }

	public double getAlto() {
		return this.img.getHeight(null)*this.escala;
	}
	public double getAncho() {
		return this.img.getWidth(null)*this.escala;
	}

	public double getTecho() {
		return this.y-this.getAlto()/2;

	}
	public double getPiso() {
		return this.y+this.getAlto()/2;
	}

	public double getIzquierda() {
		return this.x-this.getAncho()/2;

	}
	public double getDerecha() {
		return this.x+this.getAncho()/2;
	}

}


