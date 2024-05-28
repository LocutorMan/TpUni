package juego;

public class Colisionador {
	
	/**
	 * Verifica si hay colision entre el primer y segundo objeto dado 
	 * @param Princesa El primer objeto a vereficar
	 * @param Bloques El segundo objeto a verificar
	 */
    public static boolean verificarColision(Princesa princesa, Bloques bloque) {
        return princesa.getDerecha() > bloque.getIzquierda() &&
               princesa.getIzquierda() < bloque.getDerecha() &&
               princesa.getPiso() > bloque.getTecho() &&
               princesa.getTecho() < bloque.getPiso();
    }
    
    public void manejarColisiones(Princesa princesa, Bloques[] bloques) {
        boolean estaApoyada = false;

        for (int i = 0; i < bloques.length; i++) {		// Se recorre los bloques uno por uno
            if (verificarColision(princesa, bloques[i])) {
                if (princesa.getPiso() <= bloques[i].getPiso() /*&& princesa.getPiso() > bloques[i].getTecho()*/) {		// Colision entre el piso de la princesa y el piso del bloque
                    princesa.setY(bloques[i].getTecho() - princesa.getAlto()/2);		// Se ubica a la princesa justo encima del bloque
                	princesa.velocidadSalto(0);
                    princesa.setEstaSaltando(false);
                    princesa.setEstaApoyado(true);
                    if (bloques[i].isdestrucible()) {
                        bloques[i].destruir(); // Destruye el bloque si es destructible
                    }
                    estaApoyada = true;                    
                    break;
                }
                
//                if (princesa.getTecho() <= bloques[i].getPiso()) {		// Colision entre el techo de la princesa y el piso del bloque
//                	if(!bloques[i].isdestrucible()) {		// Si No es destructibles...
//                    	princesa.setY(bloques[i].getPiso() + princesa.getAlto() / 2);
//                        princesa.velocidadSalto(0);
//                        princesa.setEstaSaltando(false);
//                        princesa.setEstaApoyado(true);
//                	} else if(!bloques[i].isdestrucible()) {		// Si es destructible...
//                		princesa.setY(bloques[i].getPiso() + princesa.getAlto() / 2);
//                		princesa.velocidadSalto(0);
//                        princesa.setEstaSaltando(false);
//                        princesa.setEstaApoyado(true);
//                        bloques[i].isdestrucible();
//                	}
//                }
            }
        }

        princesa.setEstaApoyado(estaApoyada);	// Se informa si la princesa esta sobre un bloque o no
    }
    
 // Colisiones para el Dinosaurio

 	public static boolean verificarColision(Dinosaurio dinosaurio, Bloques bloque) {
 		return dinosaurio.getDerecha() > bloque.getIzquierda() && dinosaurio.getIzquierda() < bloque.getDerecha()
 				&& dinosaurio.getPiso() > bloque.getTecho() && dinosaurio.getTecho() < bloque.getPiso();
 	}

 	public void manejarColisiones(Dinosaurio dinosaurio, Bloques[] bloques) {
 		boolean estaApoyada = false;

 		for (int i = 0; i < bloques.length; i++) { // Se recorre los bloques uno por uno
 			if (verificarColision(dinosaurio, bloques[i])) {
 				if (dinosaurio.getPiso() > bloques[i].getTecho() && dinosaurio.getTecho() < bloques[i].getPiso()) {
 					dinosaurio.setY(bloques[i].getTecho() - dinosaurio.getAlto() / 2); // Se ubica al Dinosaurio justo
 																						// encima del bloque
 					estaApoyada = true;
 					break;
 				}
 			}
 		}

 		dinosaurio.setEstaApoyado(estaApoyada); // Se informa si el Dinosaurio esta sobre un bloque o no
 	}

 	// detecta cuando el dinosaurio choca contra una pared

// 	public static boolean colisionParedes(Dinosaurio dino, Entorno e) {
 //
// 		return dino.getDerecha() > e.ancho() || dino.getIzquierda() < 0;
// 	}

 	// detecta cuando el dinosaurio choca con la princesa

 	public static boolean dinoPrincesa(Dinosaurio d, Princesa p) {
 		return p.getDerecha() > d.getIzquierda() && p.getIzquierda() < d.getDerecha();
 	}
    
    
}
