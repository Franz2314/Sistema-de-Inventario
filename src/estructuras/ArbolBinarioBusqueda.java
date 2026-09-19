package estructuras;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Árbol binario de búsqueda: para cada nodo, los elementos menores están en el
 * subárbol izquierdo y los mayores en el derecho. El criterio de orden lo define
 * el comparador; dos elementos que comparan igual se consideran duplicados.
 * No se autobalancea: insertar elementos ya ordenados lo degenera en una lista.
 */
public class ArbolBinarioBusqueda<T> {

    private static class NodoArbol<T> {
        T dato;
        NodoArbol<T> izquierdo;
        NodoArbol<T> derecho;

        NodoArbol(T dato) {
            this.dato = dato;
        }
    }

    private final Comparator<T> comparador;
    private NodoArbol<T> raiz;
    private int tamano;

    public ArbolBinarioBusqueda(Comparator<T> comparador) {
        this.comparador = comparador;
    }

    public int tamano() {
        return tamano;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    // ---------- Inserción ----------

    /** Inserta el elemento; devuelve false si es null o ya existe uno equivalente. */
    public boolean insertar(T item) {
        if (item == null) {
            return false;
        }
        if (raiz == null) {
            raiz = new NodoArbol<>(item);
            tamano++;
            return true;
        }
        NodoArbol<T> actual = raiz;
        while (true) {
            int c = comparador.compare(item, actual.dato);
            if (c == 0) {
                return false;
            }
            if (c < 0) {
                if (actual.izquierdo == null) {
                    actual.izquierdo = new NodoArbol<>(item);
                    break;
                }
                actual = actual.izquierdo;
            } else {
                if (actual.derecho == null) {
                    actual.derecho = new NodoArbol<>(item);
                    break;
                }
                actual = actual.derecho;
            }
        }
        tamano++;
        return true;
    }

    // ---------- Búsqueda ----------

    /** Devuelve el elemento equivalente a la clave, o null si no está. */
    public T buscar(T clave) {
        NodoArbol<T> actual = raiz;
        while (actual != null) {
            int c = comparador.compare(clave, actual.dato);
            if (c == 0) {
                return actual.dato;
            }
            actual = c < 0 ? actual.izquierdo : actual.derecho;
        }
        return null;
    }

    public T minimo() {
        return raiz == null ? null : menor(raiz).dato;
    }

    public T maximo() {
        if (raiz == null) {
            return null;
        }
        NodoArbol<T> actual = raiz;
        while (actual.derecho != null) {
            actual = actual.derecho;
        }
        return actual.dato;
    }

    // ---------- Eliminación ----------

    /** Elimina el elemento equivalente a la clave; devuelve false si no existe. */
    public boolean eliminar(T clave) {
        if (buscar(clave) == null) {
            return false;
        }
        raiz = eliminar(raiz, clave);
        tamano--;
        return true;
    }

    private NodoArbol<T> eliminar(NodoArbol<T> nodo, T clave) {
        int c = comparador.compare(clave, nodo.dato);
        if (c < 0) {
            nodo.izquierdo = eliminar(nodo.izquierdo, clave);
        } else if (c > 0) {
            nodo.derecho = eliminar(nodo.derecho, clave);
        } else {
            if (nodo.izquierdo == null) {
                return nodo.derecho;
            }
            if (nodo.derecho == null) {
                return nodo.izquierdo;
            }
            // Dos hijos: se reemplaza por el sucesor (menor del subárbol derecho).
            NodoArbol<T> sucesor = menor(nodo.derecho);
            nodo.dato = sucesor.dato;
            nodo.derecho = eliminar(nodo.derecho, sucesor.dato);
        }
        return nodo;
    }

    private NodoArbol<T> menor(NodoArbol<T> nodo) {
        while (nodo.izquierdo != null) {
            nodo = nodo.izquierdo;
        }
        return nodo;
    }

    // ---------- Recorridos ----------

    /** Izquierdo, raíz, derecho: entrega los elementos ordenados. */
    public void enOrden(Consumer<T> accion) {
        enOrden(raiz, accion);
    }

    /** Raíz, izquierdo, derecho. */
    public void preOrden(Consumer<T> accion) {
        preOrden(raiz, accion);
    }

    /** Izquierdo, derecho, raíz. */
    public void postOrden(Consumer<T> accion) {
        postOrden(raiz, accion);
    }

    private void enOrden(NodoArbol<T> nodo, Consumer<T> accion) {
        if (nodo != null) {
            enOrden(nodo.izquierdo, accion);
            accion.accept(nodo.dato);
            enOrden(nodo.derecho, accion);
        }
    }

    private void preOrden(NodoArbol<T> nodo, Consumer<T> accion) {
        if (nodo != null) {
            accion.accept(nodo.dato);
            preOrden(nodo.izquierdo, accion);
            preOrden(nodo.derecho, accion);
        }
    }

    private void postOrden(NodoArbol<T> nodo, Consumer<T> accion) {
        if (nodo != null) {
            postOrden(nodo.izquierdo, accion);
            postOrden(nodo.derecho, accion);
            accion.accept(nodo.dato);
        }
    }

    // ---------- Otros ----------

    /** Número de niveles del árbol; 0 si está vacío. */
    public int altura() {
        return altura(raiz);
    }

    private int altura(NodoArbol<T> nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + Math.max(altura(nodo.izquierdo), altura(nodo.derecho));
    }

    public void limpiar() {
        raiz = null;
        tamano = 0;
    }
}
