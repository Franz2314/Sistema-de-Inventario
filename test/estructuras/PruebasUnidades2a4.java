package estructuras;

import java.util.Comparator;
import modelo.DetalleBoleta;
import modelo.Producto;

/**
 * Casos de prueba de las Unidades 2 a 4 (lista enlazada, pila, cola y árbol binario de búsqueda).
 * Se ejecuta con: java -cp <clases> estructuras.PruebasUnidades2a4
 */
public class PruebasUnidades2a4 {

    private static int total = 0;
    private static int fallos = 0;

    private static void verificar(String caso, boolean condicion) {
        total++;
        if (!condicion) {
            fallos++;
        }
        System.out.println((condicion ? "OK    " : "FALLO ") + caso);
    }

    private static String texto(ListaEnlazada<Integer> lista) {
        StringBuilder sb = new StringBuilder();
        lista.recorrer(x -> sb.append(x).append(' '));
        return sb.toString().trim();
    }

    private static String texto(ArbolBinarioBusqueda<Integer> arbol, int recorrido) {
        StringBuilder sb = new StringBuilder();
        if (recorrido == 0) {
            arbol.enOrden(x -> sb.append(x).append(' '));
        } else if (recorrido == 1) {
            arbol.preOrden(x -> sb.append(x).append(' '));
        } else {
            arbol.postOrden(x -> sb.append(x).append(' '));
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        pruebasLista();
        pruebasPila();
        pruebasCola();
        pruebasArbol();
        System.out.println();
        System.out.println(total - fallos + "/" + total + " pruebas correctas");
        if (fallos > 0) {
            System.exit(1);
        }
    }

    private static void pruebasLista() {
        ListaEnlazada<Integer> l = new ListaEnlazada<>();
        verificar("Lista nueva está vacía", l.estaVacia() && l.tamano() == 0 && l.obtener(0) == null);
        verificar("Insertar null se rechaza", !l.insertarFinal(null) && !l.insertarInicio(null) && !l.insertarEn(0, null));

        l.insertarFinal(2);
        l.insertarFinal(3);
        l.insertarInicio(1);
        verificar("Insertar al inicio y al final", texto(l).equals("1 2 3") && l.tamano() == 3);
        verificar("Insertar en el medio", l.insertarEn(2, 99) && texto(l).equals("1 2 99 3"));
        verificar("Insertar en posiciones 0 y tamaño", l.insertarEn(0, 0) && l.insertarEn(l.tamano(), 4) && texto(l).equals("0 1 2 99 3 4"));
        verificar("Insertar en posición inválida se rechaza", !l.insertarEn(-1, 5) && !l.insertarEn(20, 5));

        verificar("Obtener por posición", l.obtener(3) == 99 && l.obtener(6) == null && l.obtener(-1) == null);
        verificar("Buscar por criterio", l.buscar(x -> x == 99) == 3 && l.buscar(x -> x == 1000) == -1);
        verificar("Actualizar", l.actualizar(3, 3) && l.obtener(3) == 3 && !l.actualizar(9, 1) && !l.actualizar(0, null));

        verificar("Eliminar del medio devuelve el elemento", l.eliminar(3) == 3 && texto(l).equals("0 1 2 3 4"));
        verificar("Eliminar el primero", l.eliminar(0) == 0 && texto(l).equals("1 2 3 4"));
        verificar("Eliminar el último y seguir insertando al final",
                l.eliminar(3) == 4 && l.insertarFinal(7) && texto(l).equals("1 2 3 7"));
        verificar("Eliminar posición inválida devuelve null", l.eliminar(10) == null && l.eliminar(-1) == null);

        l.invertir();
        verificar("Invertir la lista", texto(l).equals("7 3 2 1"));
        l.insertarFinal(0);
        verificar("Tras invertir el final sigue siendo válido", texto(l).equals("7 3 2 1 0"));

        while (!l.estaVacia()) {
            l.eliminar(0);
        }
        verificar("Vaciar la lista elemento por elemento", l.tamano() == 0 && l.insertarFinal(1) && texto(l).equals("1"));
        l.limpiar();
        verificar("Limpiar deja la lista vacía", l.estaVacia());

        ListaEnlazada<DetalleBoleta> boleta = new ListaEnlazada<>();
        boleta.insertarFinal(new DetalleBoleta("1", "Mouse", 2, 25.0));
        boleta.insertarFinal(new DetalleBoleta("2", "Teclado", 1, 60.0));
        double[] suma = {0};
        boleta.recorrer(d -> suma[0] += d.getSubtotal());
        verificar("Detalle de boleta: suma de subtotales", suma[0] == 110.0);
    }

    private static void pruebasPila() {
        Pila<String> p = new Pila<>();
        verificar("Pila nueva está vacía", p.estaVacia() && p.desapilar() == null && p.tope() == null);
        verificar("Apilar null se rechaza", !p.apilar(null));
        p.apilar("a");
        p.apilar("b");
        p.apilar("c");
        verificar("Tope es el último apilado", p.tope().equals("c") && p.tamano() == 3);
        verificar("Desapilar respeta LIFO", p.desapilar().equals("c") && p.desapilar().equals("b") && p.tamano() == 1);
        p.apilar("d");
        verificar("Apilar después de desapilar", p.desapilar().equals("d") && p.desapilar().equals("a") && p.estaVacia());
        p.apilar("x");
        p.limpiar();
        verificar("Limpiar vacía la pila", p.estaVacia() && p.tope() == null);
    }

    private static void pruebasCola() {
        Cola<String> c = new Cola<>();
        verificar("Cola nueva está vacía", c.estaVacia() && c.desencolar() == null && c.frente() == null);
        verificar("Encolar null se rechaza", !c.encolar(null));
        c.encolar("a");
        c.encolar("b");
        c.encolar("c");
        verificar("Frente es el primero encolado", c.frente().equals("a") && c.tamano() == 3);
        verificar("Desencolar respeta FIFO", c.desencolar().equals("a") && c.desencolar().equals("b") && c.tamano() == 1);
        c.encolar("d");
        verificar("Encolar después de desencolar", c.desencolar().equals("c") && c.desencolar().equals("d") && c.estaVacia());
        verificar("Cola vaciada admite nuevos elementos", c.encolar("z") && c.frente().equals("z"));
        c.limpiar();
        verificar("Limpiar vacía la cola", c.estaVacia() && c.frente() == null);
    }

    private static void pruebasArbol() {
        ArbolBinarioBusqueda<Integer> a = new ArbolBinarioBusqueda<Integer>(Comparator.<Integer>naturalOrder());
        verificar("Árbol nuevo está vacío", a.estaVacio() && a.altura() == 0 && a.minimo() == null && a.maximo() == null);

        int[] valores = {50, 30, 70, 20, 40, 60, 80};
        for (int v : valores) {
            a.insertar(v);
        }
        verificar("Insertar y contar", a.tamano() == 7 && !a.estaVacio());
        verificar("Insertar duplicado o null se rechaza", !a.insertar(50) && !a.insertar(null) && a.tamano() == 7);
        verificar("Recorrido en orden entrega los datos ordenados", texto(a, 0).equals("20 30 40 50 60 70 80"));
        verificar("Recorrido preorden", texto(a, 1).equals("50 30 20 40 70 60 80"));
        verificar("Recorrido postorden", texto(a, 2).equals("20 40 30 60 80 70 50"));
        verificar("Altura del árbol", a.altura() == 3);
        verificar("Mínimo y máximo", a.minimo() == 20 && a.maximo() == 80);
        verificar("Buscar existente e inexistente", a.buscar(60) == 60 && a.buscar(65) == null);

        verificar("Eliminar hoja", a.eliminar(20) && texto(a, 0).equals("30 40 50 60 70 80"));
        verificar("Eliminar nodo con un hijo", a.eliminar(30) && texto(a, 0).equals("40 50 60 70 80"));
        verificar("Eliminar nodo con dos hijos (raíz)", a.eliminar(50) && texto(a, 0).equals("40 60 70 80") && a.buscar(50) == null);
        verificar("Eliminar inexistente devuelve false", !a.eliminar(999) && a.tamano() == 4);

        a.limpiar();
        verificar("Limpiar vacía el árbol", a.estaVacio() && a.tamano() == 0);

        ArbolBinarioBusqueda<Producto> productos = new ArbolBinarioBusqueda<>(Comparator.comparingInt(Producto::getId));
        productos.insertar(new Producto(5, "Mouse", 25.0, 10));
        productos.insertar(new Producto(2, "Teclado", 60.0, 5));
        productos.insertar(new Producto(8, "Mousepad", 15.0, 20));
        Producto encontrado = productos.buscar(new Producto(2, "", 0, 0));
        verificar("Buscar producto por id con comparador", encontrado != null && encontrado.getNombre().equals("Teclado"));
        verificar("Producto con id repetido se rechaza", !productos.insertar(new Producto(5, "Otro", 1.0, 1)));

        ArbolBinarioBusqueda<Integer> lineal = new ArbolBinarioBusqueda<Integer>(Comparator.<Integer>naturalOrder());
        for (int i = 1; i <= 5; i++) {
            lineal.insertar(i);
        }
        verificar("Insertar datos ordenados degenera el árbol", lineal.altura() == 5);
    }
}
