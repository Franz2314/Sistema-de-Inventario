package estructuras;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Arreglo unidimensional de objetos con capacidad fija (estructura estática).
 * Los elementos se mantienen contiguos desde la posición 0 hasta tamano() - 1.
 * Los métodos que modifican devuelven false o null cuando no se puede
 * realizar la operación (arreglo lleno, posición inválida o elemento nulo).
 */
public class ArregloEstatico<T> {

    private final Object[] datos;
    private int cantidad;

    public ArregloEstatico(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que 0");
        }
        datos = new Object[capacidad];
    }

    public int capacidad() {
        return datos.length;
    }

    public int tamano() {
        return cantidad;
    }

    public boolean estaVacio() {
        return cantidad == 0;
    }

    public boolean estaLleno() {
        return cantidad == datos.length;
    }

    // ---------- Inserción ----------

    /** Inserta al final del arreglo. */
    public boolean insertar(T item) {
        if (item == null || estaLleno()) {
            return false;
        }
        datos[cantidad++] = item;
        return true;
    }

    /** Inserta en una posición desplazando los elementos siguientes a la derecha. */
    public boolean insertarEn(int pos, T item) {
        if (item == null || estaLleno() || pos < 0 || pos > cantidad) {
            return false;
        }
        for (int i = cantidad; i > pos; i--) {
            datos[i] = datos[i - 1];
        }
        datos[pos] = item;
        cantidad++;
        return true;
    }

    // ---------- Lectura ----------

    @SuppressWarnings("unchecked")
    public T obtener(int pos) {
        if (pos < 0 || pos >= cantidad) {
            return null;
        }
        return (T) datos[pos];
    }

    /** Búsqueda lineal: devuelve la posición del primer elemento que cumple el criterio, o -1. */
    public int buscar(Predicate<T> criterio) {
        for (int i = 0; i < cantidad; i++) {
            if (criterio.test(obtener(i))) {
                return i;
            }
        }
        return -1;
    }

    /** Recorre los elementos en orden, de la posición 0 a tamano() - 1. */
    public void recorrer(Consumer<T> accion) {
        for (int i = 0; i < cantidad; i++) {
            accion.accept(obtener(i));
        }
    }

    // ---------- Actualización ----------

    public boolean actualizar(int pos, T item) {
        if (item == null || pos < 0 || pos >= cantidad) {
            return false;
        }
        datos[pos] = item;
        return true;
    }

    // ---------- Eliminación ----------

    /** Elimina la posición desplazando los siguientes a la izquierda; devuelve el elemento eliminado. */
    public T eliminar(int pos) {
        if (pos < 0 || pos >= cantidad) {
            return null;
        }
        T eliminado = obtener(pos);
        for (int i = pos; i < cantidad - 1; i++) {
            datos[i] = datos[i + 1];
        }
        datos[--cantidad] = null;
        return eliminado;
    }

    public void limpiar() {
        for (int i = 0; i < cantidad; i++) {
            datos[i] = null;
        }
        cantidad = 0;
    }

    // ---------- Copia ----------

    /** Devuelve un arreglo nuevo, con la misma capacidad y las mismas referencias. */
    public ArregloEstatico<T> copiar() {
        ArregloEstatico<T> copia = new ArregloEstatico<>(datos.length);
        for (int i = 0; i < cantidad; i++) {
            copia.datos[i] = datos[i];
        }
        copia.cantidad = cantidad;
        return copia;
    }
}
