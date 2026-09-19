# Sistema de Inventario (Algoritmos y Estructuras de Datos, UTP)

Aplicación Java Swing (NetBeans) con PostgreSQL.

## Estructura de carpetas

| Carpeta | Contenido |
|---|---|
| `src/` | Código fuente. `Conexion/` (acceso a BD), `modelo/` (Producto, Proveedor, DetalleBoleta), `estructuras/` (arreglo, matriz, lista, pila, cola, árbol), `imagenes/`, `PDF/` y las pantallas `LoginForm` y `Sistema`. |
| `test/` | Pruebas de las estructuras de datos. |
| `lib/` | Librerías externas (driver JDBC de PostgreSQL). Va dentro de git para que todo el grupo lo tenga. |
| `database/` | Base de datos: `inventario.sql` (script legible) e `inventario.backup` (respaldo para pgAdmin). |
| `docs/entregas/` | Documento del curso (plantilla, avances). |
| `docs/referencia/` | PDFs de referencia (Inventario, boleta). |
| `nbproject/` | Configuración de NetBeans. No editar a mano. |

## Puesta en marcha

1. Instalar PostgreSQL y pgAdmin 4. Usuario `postgres`, contraseña `root` (o la que use `src/Conexion/Conexion.java`).
2. En pgAdmin crear una base de datos llamada `inventario`.
3. Clic derecho en `inventario` → Query Tool → abrir `database/inventario.sql` → ejecutar (F5).
   (Alternativa: clic derecho → Restore… → `database/inventario.backup`.)
4. Abrir la carpeta del proyecto en NetBeans y ejecutar. Login: `Usuario1` / `1590`.

El driver ya está en `lib/` con ruta relativa, no hay que configurarlo.
