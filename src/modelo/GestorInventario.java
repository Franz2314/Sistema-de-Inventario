package modelo;

import java.util.ArrayList;
import java.util.List;

public class GestorInventario<T> {
    private List<T> lista = new ArrayList<>();

    public void agregar(T item) {
        lista.add(item);
        System.out.println("Elemento agregado: " + item.toString());
    }

    public void eliminar(T item) {
        lista.remove(item);
        System.out.println("Elemento eliminado: " + item.toString());
    }

    public List<T> obtenerTodo() {
        return lista;
    }

    public void mostrarInventario() {
        for (T item : lista) {
            System.out.println(item.toString());
        }
    }
}
