package estructuras;

/** Nodo enlazado simple: guarda un dato y la referencia al siguiente nodo. */
class Nodo<T> {

    T dato;
    Nodo<T> siguiente;

    Nodo(T dato) {
        this.dato = dato;
    }
}
