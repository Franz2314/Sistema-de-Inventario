package estructuras;

import modelo.Producto;

/**
 * Casos de prueba de la Unidad 1 (arreglo unidimensional y matriz).
 * Se ejecuta con: java -cp <clases> estructuras.PruebasUnidad1
 */
public class PruebasUnidad1 {

    private static int total = 0;
    private static int fallos = 0;

    private static void verificar(String caso, boolean condicion) {
        total++;
        if (!condicion) {
            fallos++;
        }
        System.out.println((condicion ? "OK    " : "FALLO ") + caso);
    }

    public static void main(String[] args) {
        pruebasArreglo();
        pruebasMatriz();
        System.out.println();
        System.out.println(total - fallos + "/" + total + " pruebas correctas");
        if (fallos > 0) {
            System.exit(1);
        }
    }

    private static void pruebasArreglo() {
        ArregloEstatico<Producto> a = new ArregloEstatico<>(3);
        Producto mouse = new Producto(1, "Mouse", 25.0, 10);
        Producto teclado = new Producto(2, "Teclado", 60.0, 5);
        Producto pad = new Producto(3, "Mousepad", 15.0, 20);

        verificar("Arreglo nuevo está vacío", a.estaVacio() && a.tamano() == 0);
        verificar("Insertar al final", a.insertar(mouse) && a.insertar(teclado) && a.tamano() == 2);
        verificar("Insertar null se rechaza", !a.insertar(null));
        verificar("Insertar en posición 0 desplaza a la derecha",
                a.insertarEn(0, pad) && a.obtener(0) == pad && a.obtener(1) == mouse && a.obtener(2) == teclado);
        verificar("Arreglo lleno rechaza inserciones", a.estaLleno() && !a.insertar(mouse) && !a.insertarEn(0, mouse));
        verificar("Insertar en posición inválida se rechaza", !a.insertarEn(9, mouse) && !a.insertarEn(-1, mouse));

        verificar("Buscar por id encuentra la posición", a.buscar(p -> p.getId() == 2) == 2);
        verificar("Buscar inexistente devuelve -1", a.buscar(p -> p.getId() == 99) == -1);
        verificar("Obtener fuera de rango devuelve null", a.obtener(3) == null && a.obtener(-1) == null);

        int[] suma = {0};
        a.recorrer(p -> suma[0] += p.getStock());
        verificar("Recorrer visita todos los elementos", suma[0] == 35);

        Producto teclado2 = new Producto(2, "Teclado gamer", 90.0, 4);
        verificar("Actualizar reemplaza el elemento", a.actualizar(2, teclado2) && a.obtener(2).getNombre().equals("Teclado gamer"));
        verificar("Actualizar posición inválida se rechaza", !a.actualizar(5, teclado2) && !a.actualizar(0, null));

        ArregloEstatico<Producto> copia = a.copiar();
        verificar("Copia tiene el mismo contenido", copia.tamano() == 3 && copia.obtener(0) == pad);
        copia.eliminar(0);
        verificar("Modificar la copia no altera el original", a.tamano() == 3 && copia.tamano() == 2);

        Producto eliminado = a.eliminar(0);
        verificar("Eliminar devuelve el elemento y desplaza a la izquierda",
                eliminado == pad && a.tamano() == 2 && a.obtener(0) == mouse && a.obtener(2) == null);
        verificar("Eliminar posición inválida devuelve null", a.eliminar(7) == null);

        a.limpiar();
        verificar("Limpiar deja el arreglo vacío", a.estaVacio() && a.obtener(0) == null);

        boolean lanzo = false;
        try {
            new ArregloEstatico<Producto>(0);
        } catch (IllegalArgumentException e) {
            lanzo = true;
        }
        verificar("Capacidad 0 es inválida", lanzo);
    }

    private static void pruebasMatriz() {
        MatrizAlmacen m = new MatrizAlmacen(2, 3);

        verificar("Dimensiones de la matriz", m.estantes() == 2 && m.posiciones() == 3 && m.libres() == 6);
        verificar("Ubicar en celda libre", m.ubicar(10, 0, 1) && m.obtener(0, 1) == 10);
        verificar("No se ubica en celda ocupada", !m.ubicar(11, 0, 1));
        verificar("No se ubica un producto dos veces", !m.ubicar(10, 1, 0));
        verificar("Celda fuera de rango se rechaza", !m.ubicar(12, 5, 5) && m.obtener(5, 5) == MatrizAlmacen.VACIO);
        verificar("Id no válido se rechaza", !m.ubicar(0, 1, 1) && !m.ubicar(-3, 1, 1));

        int[] pos = m.ubicarEnPrimeraLibre(20);
        verificar("Primera celda libre recorre estante por estante", pos != null && pos[0] == 0 && pos[1] == 0);
        verificar("Ubicar un producto ya ubicado devuelve su posición",
                m.ubicarEnPrimeraLibre(10)[1] == 1 && m.ocupadas() == 2);
        verificar("Buscar devuelve {fila, columna}", m.buscar(10)[0] == 0 && m.buscar(10)[1] == 1);
        verificar("Buscar inexistente devuelve null", m.buscar(99) == null);
        verificar("Etiqueta legible", m.etiqueta(10).equals("E1-P2") && m.etiqueta(99).equals(MatrizAlmacen.SIN_UBICACION));

        m.sincronizar(new int[]{20, 30, 40});
        verificar("Sincronizar retira productos que ya no existen", m.buscar(10) == null);
        verificar("Sincronizar conserva la ubicación de los que siguen", m.buscar(20)[0] == 0 && m.buscar(20)[1] == 0);
        verificar("Sincronizar ubica los nuevos", m.buscar(30) != null && m.buscar(40) != null && m.ocupadas() == 3);

        verificar("Retirar libera la celda", m.retirar(30) && m.buscar(30) == null && !m.retirar(30));

        MatrizAlmacen pequena = new MatrizAlmacen(1, 1);
        pequena.ubicarEnPrimeraLibre(1);
        verificar("Sin espacio no se ubica", pequena.ubicarEnPrimeraLibre(2) == null && pequena.libres() == 0);

        m.limpiar();
        verificar("Limpiar vacía toda la matriz", m.ocupadas() == 0);
    }
}
