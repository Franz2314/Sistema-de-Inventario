package estructuras;

/**
 * Pila (LIFO) implementada con nodos enlazados: el último elemento apilado
 * es el primero en salir.
 */
public class Pila<T> {

    private Nodo<T> tope;
    private int tamano;

    public int tamano() {
        return tamano;
    }

    public boolean estaVacia() {
        return tamano == 0;
    }

    public boolean apilar(T item) {
        if (item == null) {
            return false;
        }
        Nodo<T> nuevo = new Nodo<>(item);
        nuevo.siguiente = tope;
        tope = nuevo;
        tamano++;
        return true;
    }

    /** Quita y devuelve el elemento del tope, o null si la pila está vacía. */
    public T desapilar() {
        if (tope == null) {
            return null;
        }
        T dato = tope.dato;
        tope = tope.siguiente;
        tamano--;
        return dato;
    }

    /** Devuelve el elemento del tope sin quitarlo, o null si la pila está vacía. */
    public T tope() {
        return tope == null ? null : tope.dato;
    }

    public void limpiar() {
        tope = null;
        tamano = 0;
    }
}
