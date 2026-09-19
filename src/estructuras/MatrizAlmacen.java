package estructuras;

/**
 * Matriz bidimensional que representa los estantes del almacén.
 * Cada fila es un estante y cada columna una posición dentro del estante.
 * Una celda guarda el id del producto ubicado ahí, o VACIO si está libre.
 */
public class MatrizAlmacen {

    public static final int VACIO = 0;
    public static final String SIN_UBICACION = "Sin ubicación";

    private final int[][] celdas;

    public MatrizAlmacen(int estantes, int posiciones) {
        if (estantes <= 0 || posiciones <= 0) {
            throw new IllegalArgumentException("Las dimensiones deben ser mayores que 0");
        }
        celdas = new int[estantes][posiciones];
    }

    public int estantes() {
        return celdas.length;
    }

    public int posiciones() {
        return celdas[0].length;
    }

    public int obtener(int fila, int columna) {
        if (!enRango(fila, columna)) {
            return VACIO;
        }
        return celdas[fila][columna];
    }

    /** Ubica un producto en una celda libre. Un producto ocupa una sola celda. */
    public boolean ubicar(int idProducto, int fila, int columna) {
        if (idProducto <= VACIO || !enRango(fila, columna)
                || celdas[fila][columna] != VACIO || buscar(idProducto) != null) {
            return false;
        }
        celdas[fila][columna] = idProducto;
        return true;
    }

    /**
     * Ubica el producto en la primera celda libre (recorriendo estante por estante).
     * Si ya estaba ubicado devuelve su posición actual; si no hay espacio devuelve null.
     */
    public int[] ubicarEnPrimeraLibre(int idProducto) {
        if (idProducto <= VACIO) {
            return null;
        }
        int[] actual = buscar(idProducto);
        if (actual != null) {
            return actual;
        }
        for (int f = 0; f < celdas.length; f++) {
            for (int c = 0; c < celdas[f].length; c++) {
                if (celdas[f][c] == VACIO) {
                    celdas[f][c] = idProducto;
                    return new int[]{f, c};
                }
            }
        }
        return null;
    }

    /** Devuelve {fila, columna} del producto, o null si no está ubicado. */
    public int[] buscar(int idProducto) {
        if (idProducto <= VACIO) {
            return null;
        }
        for (int f = 0; f < celdas.length; f++) {
            for (int c = 0; c < celdas[f].length; c++) {
                if (celdas[f][c] == idProducto) {
                    return new int[]{f, c};
                }
            }
        }
        return null;
    }

    public boolean retirar(int idProducto) {
        int[] pos = buscar(idProducto);
        if (pos == null) {
            return false;
        }
        celdas[pos[0]][pos[1]] = VACIO;
        return true;
    }

    /**
     * Deja en la matriz solo los productos vigentes: retira los que ya no existen
     * y ubica en la primera celda libre los que todavía no tenían lugar.
     */
    public void sincronizar(int[] idsVigentes) {
        for (int f = 0; f < celdas.length; f++) {
            for (int c = 0; c < celdas[f].length; c++) {
                if (celdas[f][c] != VACIO && !contiene(idsVigentes, celdas[f][c])) {
                    celdas[f][c] = VACIO;
                }
            }
        }
        for (int id : idsVigentes) {
            ubicarEnPrimeraLibre(id);
        }
    }

    public int ocupadas() {
        int total = 0;
        for (int[] fila : celdas) {
            for (int celda : fila) {
                if (celda != VACIO) {
                    total++;
                }
            }
        }
        return total;
    }

    public int libres() {
        return estantes() * posiciones() - ocupadas();
    }

    /** Etiqueta legible de la ubicación, por ejemplo "E2-P3" (estante 2, posición 3). */
    public String etiqueta(int idProducto) {
        int[] pos = buscar(idProducto);
        if (pos == null) {
            return SIN_UBICACION;
        }
        return "E" + (pos[0] + 1) + "-P" + (pos[1] + 1);
    }

    public void limpiar() {
        for (int[] fila : celdas) {
            java.util.Arrays.fill(fila, VACIO);
        }
    }

    private boolean enRango(int fila, int columna) {
        return fila >= 0 && fila < celdas.length && columna >= 0 && columna < celdas[0].length;
    }

    private static boolean contiene(int[] ids, int id) {
        for (int x : ids) {
            if (x == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int f = 0; f < celdas.length; f++) {
            sb.append("E").append(f + 1).append(":");
            for (int c = 0; c < celdas[f].length; c++) {
                sb.append(String.format(" %3d", celdas[f][c]));
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
