# Cambios realizados y contexto para el equipo (y para cualquier IA)

Si vas a usar una IA para seguir trabajando, pásale este archivo primero para que
trabaje con el mismo criterio. Fecha de estos cambios: 19-09-2026.

## 1. Qué es el proyecto

Sistema de inventario en Java Swing (NetBeans, proyecto Ant) con PostgreSQL, para el
curso *Algoritmos y Estructuras de Datos* (UTP). El documento del curso ya no está en el repo
(se quitó `docs/`; sigue en el historial de git, commit `4a2dbcb`) (el .md pide MySQL, pero el docente aceptó PostgreSQL).

Conexión a la base: ver `CONECCION A LA BD.txt` (usuario `postgres`, contraseña `root`,
base `inventario`). Resumen de carpetas en `README.md`.

## 2. Reglas para trabajar (mismo flujo)

- **No usar `java.util` (ArrayList, LinkedList, Stack, etc.) para las estructuras del
  curso.** Usar las de `src/estructuras/`. Solo `List` aparece al leer de la BD en `ProductoDAO`.
- No editar a mano el bloque `initComponents()` ni las líneas `//GEN-...` de `Sistema.java` y
  `LoginForm.java`: los regenera el diseñador de NetBeans (los `.form`).
- Contraseña de PostgreSQL: `root` para todos, así `Conexion.java` no cambia entre equipos.
- Las rutas de librerías en `nbproject/project.properties` deben ser **relativas** (`lib/...`),
  nunca `C:\Users\...`.
- Antes de subir: compilar y correr las pruebas de `test/estructuras/` (ver sección 6).
- Idioma del proyecto y de los comentarios: español.

## 3. Cambios en el código

### Limpieza (código sin uso)
- Borradas: `modelo/ProductoBase`, `Gestionable`, `Reportable`, `Usuario`, `GestorInventario`.
- Quitada la referencia a iText en `nbproject/project.properties` (nadie la usaba).
- `Producto`: quitados constructor y getters sin uso; agregado `toString()`.
- `Proveedor`: ahora es clase de datos simple (sin interfaces).
- `LoginForm`: quitado un `dispose()` duplicado.
- `Sistema`: quitados métodos muertos (`configurarTablas`, `listarProductos`, etc.) y los `TODO`/`println` de depuración.

### Corrección de errores
- **Eliminar producto nunca funcionaba**: `Sistema` llamaba a `dao.eliminar(int)`, que solo lanzaba
  `UnsupportedOperationException`. Ahora `ProductoDAO.eliminar(int id)` hace el `DELETE`.
- **Errores de BD silenciosos**: `Conexion.getConnection()` devolvía `null` y el DAO solo imprimía
  en consola, así que la pantalla decía "agregado correctamente" aunque fallara. Ahora
  `Conexion` y `ProductoDAO` lanzan `SQLException` y `Sistema` muestra un mensaje con el detalle
  (método `errorBD`).
- **Validaciones** al agregar/editar producto: nombre no vacío, precio > 0, stock >= 0
  (método `datosProductoValidos`).
- **Editar producto** usaba el ID del cuadro de texto; ahora usa el ID de la fila seleccionada.
- `ProductoDAO.listar()` ordena por `id`. Quitado `buscarPorNombre`/`buscarPorId` (sin uso).

### Estructuras de datos (paquete `src/estructuras/`)
| Unidad | Clase | Uso en `Sistema.java` |
|---|---|---|
| 1 (estática) | `ArregloEstatico<T>` (capacidad fija) | `inventario` (100 productos) y `proveedores` (50) |
| 1 (estática) | `MatrizAlmacen` (`int[][]`, 5 estantes x 6 posiciones) | Columna UBICACIÓN en la pestaña Almacén (`E2-P3`) |
| 2 | `ListaEnlazada<T>` + `Nodo<T>` | Líneas de la boleta (`modelo/DetalleBoleta`) |
| 3 | `Pila<T>` | Ctrl+Z en la tabla de la boleta deshace la última línea eliminada |
| 3 | `Cola<T>` | Productos con stock <= 5 se encolan y se avisan al iniciar (orden FIFO) |
| 4 | `ArbolBinarioBusqueda<T>` | Índice por id; la búsqueda por código en Almacén usa `buscar` |

El árbol se llena con `insertarBalanceado` (por el medio) porque la lista llega ordenada por id y,
si no, degeneraría en una lista.

## 4. Reorganización de carpetas

```
Algoritmo_proyect/
  src/ test/ nbproject/      (sin mover, para no romper NetBeans)
  lib/                       driver JDBC postgresql-42.7.13.jar (ruta relativa)
  database/                  inventario.sql (script) e inventario.backup (antes "Inventario_pryect")
  boletas/                   salida: boletas y reportes de inventario que genera el sistema
  README.md
  CONECCION A LA BD.txt
  CAMBIOS_Y_CONTEXTO.md
```

Nota: el archivo `Inventario_pryect` **no era un inventario de productos**, era el volcado
(`pg_dump`) de la base de datos. Ahora se llama `database/inventario.backup`.

## 5. Pendientes conocidos

- La boleta no se guarda en BD ni descuenta stock; no valida el código contra el inventario.
- Los proveedores solo viven en memoria (no hay tabla en la BD).
- Credenciales de la BD y del login (`Usuario1`/`1590`) están escritas en el código
  (aceptable para el curso; no publicar como proyecto real).
- Cada operación abre una conexión nueva (no hay pool).
- No se ha probado la interfaz gráfica de punta a punta en un equipo con NetBeans tras estos cambios.

## 6. Cómo compilar y probar las estructuras (sin NetBeans)

Usar el mismo JDK para compilar y ejecutar (en algunos equipos hay dos Java y falla con
`UnsupportedClassVersionError`):

```
javac -encoding UTF-8 -d out src/estructuras/*.java src/modelo/*.java test/estructuras/*.java
java -cp out estructuras.PruebasUnidad1
java -cp out estructuras.PruebasUnidades2a4
```

Resultado esperado: 35/35 y 48/48 pruebas correctas.

## 7. Carpeta `boletas/` (cambio posterior)

Se eliminó `docs/` y se creó `boletas/`. El sistema debe guardar ahí, al pulsar el botón
correspondiente, las boletas y el reporte de inventario. La carpeta está en git solo con
`.gitkeep`; su contenido generado se ignora (`.gitignore`). **Aún no está programada la
generación**: falta implementar los botones que escriban en `boletas/`.
El PDF/MD del curso y el reporte de la primera sesión se pueden recuperar con
`git show 4a2dbcb:docs/...`.
