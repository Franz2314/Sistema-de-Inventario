package estructuras;

/**
 * Cola (FIFO) implementada con nodos enlazados: el primer elemento encolado
 * es el primero en salir.
 */
public class Cola<T> {

    private Nodo<T> frente;
    private Nodo<T> fin;
    private int tamano;

    public int tamano() {
        return tamano;
    }

    public boolean estaVacia() {
        return tamano == 0;
    }

    public boolean encolar(T item) {
        if (item == null) {
            return false;
        }
        Nodo<T> nuevo = new Nodo<>(item);
        if (fin == null) {
            frente = nuevo;
        } else {
            fin.siguiente = nuevo;
        }
        fin = nuevo;
        tamano++;
        return true;
    }

    /** Quita y devuelve el elemento del frente, o null si la cola está vacía. */
    public T desencolar() {
        if (frente == null) {
            return null;
        }
        T dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null;
        }
        tamano--;
        return dato;
    }

    /** Devuelve el elemento del frente sin quitarlo, o null si la cola está vacía. */
    public T frente() {
        return frente == null ? null : frente.dato;
    }

    public void limpiar() {
        frente = null;
        fin = null;
        tamano = 0;
    }
}
