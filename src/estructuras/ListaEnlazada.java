package estructuras;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Lista enlazada simple (estructura dinámica): crece y se reduce en ejecución,
 * sin capacidad máxima. Las posiciones empiezan en 0.
 * Los métodos que modifican devuelven false o null cuando la operación no es válida.
 */
public class ListaEnlazada<T> {

    private Nodo<T> cabeza;
    private Nodo<T> cola;
    private int tamano;

    public int tamano() {
        return tamano;
    }

    public boolean estaVacia() {
        return tamano == 0;
    }

    // ---------- Inserción ----------

    public boolean insertarInicio(T item) {
        if (item == null) {
            return false;
        }
        Nodo<T> nuevo = new Nodo<>(item);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
        if (cola == null) {
            cola = nuevo;
        }
        tamano++;
        return true;
    }

    public boolean insertarFinal(T item) {
        if (item == null) {
            return false;
        }
        Nodo<T> nuevo = new Nodo<>(item);
        if (cola == null) {
            cabeza = nuevo;
        } else {
            cola.siguiente = nuevo;
        }
        cola = nuevo;
        tamano++;
        return true;
    }

    public boolean insertarEn(int pos, T item) {
        if (item == null || pos < 0 || pos > tamano) {
            return false;
        }
        if (pos == 0) {
            return insertarInicio(item);
        }
        if (pos == tamano) {
            return insertarFinal(item);
        }
        Nodo<T> anterior = nodoEn(pos - 1);
        Nodo<T> nuevo = new Nodo<>(item);
        nuevo.siguiente = anterior.siguiente;
        anterior.siguiente = nuevo;
        tamano++;
        return true;
    }

    // ---------- Lectura ----------

    public T obtener(int pos) {
        if (pos < 0 || pos >= tamano) {
            return null;
        }
        return nodoEn(pos).dato;
    }

    /** Búsqueda lineal: posición del primer elemento que cumple el criterio, o -1. */
    public int buscar(Predicate<T> criterio) {
        int pos = 0;
        for (Nodo<T> actual = cabeza; actual != null; actual = actual.siguiente) {
            if (criterio.test(actual.dato)) {
                return pos;
            }
            pos++;
        }
        return -1;
    }

    public void recorrer(Consumer<T> accion) {
        for (Nodo<T> actual = cabeza; actual != null; actual = actual.siguiente) {
            accion.accept(actual.dato);
        }
    }

    // ---------- Actualización ----------

    public boolean actualizar(int pos, T item) {
        if (item == null || pos < 0 || pos >= tamano) {
            return false;
        }
        nodoEn(pos).dato = item;
        return true;
    }

    // ---------- Eliminación ----------

    /** Elimina la posición indicada y devuelve el elemento eliminado. */
    public T eliminar(int pos) {
        if (pos < 0 || pos >= tamano) {
            return null;
        }
        Nodo<T> eliminado;
        if (pos == 0) {
            eliminado = cabeza;
            cabeza = cabeza.siguiente;
            if (cabeza == null) {
                cola = null;
            }
        } else {
            Nodo<T> anterior = nodoEn(pos - 1);
            eliminado = anterior.siguiente;
            anterior.siguiente = eliminado.siguiente;
            if (eliminado == cola) {
                cola = anterior;
            }
        }
        tamano--;
        return eliminado.dato;
    }

    public void limpiar() {
        cabeza = null;
        cola = null;
        tamano = 0;
    }

    /** Invierte el orden de la lista cambiando las referencias de los nodos. */
    public void invertir() {
        Nodo<T> anterior = null;
        Nodo<T> actual = cabeza;
        cola = cabeza;
        while (actual != null) {
            Nodo<T> siguiente = actual.siguiente;
            actual.siguiente = anterior;
            anterior = actual;
            actual = siguiente;
        }
        cabeza = anterior;
    }

    private Nodo<T> nodoEn(int pos) {
        Nodo<T> actual = cabeza;
        for (int i = 0; i < pos; i++) {
            actual = actual.siguiente;
        }
        return actual;
    }
}
