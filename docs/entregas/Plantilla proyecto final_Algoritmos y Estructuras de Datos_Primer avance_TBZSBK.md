# SISTEMA DE INVENTARIO

**Integrantes**

- Mijael Angel Aratoma Huanhuayo
- Angely Lucero Cotrina Miranda
- Diego Humberto Vasques Ancasi
- Jhasmin Elizabeth Torres Otiniano
- Frankin Mateos Chamorro

**Docente:** David Orrego Granados

**Año:** 2026

---

## Índice General

- [1. FASE 1: Análisis del Contexto y Fundamentos (3 puntos)](#1-fase-1-análisis-del-contexto-y-fundamentos-3-puntos)
  - [1.1. Gestión y Análisis de la Solución (1.5 puntos)](#11-gestión-y-análisis-de-la-solución-15-puntos)
  - [1.2. Gestión y Fundamentos de Datos (1.5 puntos)](#12-gestión-y-fundamentos-de-datos-15-puntos)
- [2. FASE 2: Arquitectura y Construcción del Software (15 puntos)](#2-fase-2-arquitectura-y-construcción-del-software-15-puntos)
  - [2.1. Lógica del Software (2 puntos)](#21-lógica-del-software-2-puntos)
  - [2.2. Unidad 1: Estructuras de Datos Lineales Estáticas (3 puntos)](#22-unidad-1-estructuras-de-datos-lineales-estáticas-3-puntos)
  - [2.3. Unidad 2: Estructuras de Datos Dinámicas, Listas (4 puntos)](#23-unidad-2-estructuras-de-datos-dinámicas-listas-4-puntos)
  - [2.4. Unidad 3: Pilas y Colas (3 puntos)](#24-unidad-3-pilas-y-colas-3-puntos)
  - [2.5. Unidad 4: Estructuras No Lineales, Árboles (3 puntos)](#25-unidad-4-estructuras-no-lineales-árboles-3-puntos)
- [3. FASE 3: Validación, Conclusiones y Código Fuente (2 puntos)](#3-fase-3-validación-conclusiones-y-código-fuente-2-puntos)
  - [3.1. Pruebas de Funcionamiento (1 punto)](#31-pruebas-de-funcionamiento-casos-de-prueba-1-punto)
  - [3.2. Conclusiones y Recomendaciones (1 punto)](#32-conclusiones-y-recomendaciones-1-punto)
  - [3.3. Referencias Bibliográficas](#33-referencias-bibliográficas)
  - [3.4. Anexos / Repositorio](#34-anexos--repositorio)

---

## 1. FASE 1: Análisis del Contexto y Fundamentos (3 puntos)

### 1.1. Gestión y Análisis de la Solución (1.5 puntos)

#### 1.1.1. Introducción

El presente proyecto consiste en el desarrollo de un sistema de gestión de inventario orientado a una tienda dedicada a la comercialización de accesorios de computadora. La aplicación ha sido desarrollada en el lenguaje Java con el entorno Apache NetBeans, y su interfaz gráfica usa componentes Swing.

El sistema facilita la administración de los productos, los proveedores y las boletas de venta, y permite registrar, consultar, actualizar y eliminar información de manera organizada.

El proyecto incorpora conceptos de Programación Orientada a Objetos y estructuras de datos implementadas por el equipo (arreglos, matriz, lista enlazada, pila, cola y árbol binario de búsqueda), que aplican los conocimientos del curso. Los productos se guardan de forma persistente en una base de datos PostgreSQL, a la que el sistema accede mediante clases especializadas.

#### 1.1.2. Descripción de la problemática

La gestión manual de un inventario genera problemas en una tienda de accesorios de computadora, sobre todo cuando hay muchos productos. Registrar la información en archivos físicos o de texto dificulta las búsquedas y las actualizaciones, y aumenta el riesgo de pérdida o duplicación de datos.

Los principales problemas identificados son:

- Dificultad para consultar rápidamente los productos disponibles.
- Problemas para controlar las cantidades en stock.
- Demora en la actualización de la información de los productos.
- Riesgo de errores durante el registro manual.
- Dificultad para administrar la información de los proveedores.
- Poca eficiencia en las búsquedas de productos.
- Falta de centralización de la información.
- Dificultad para generar y administrar los documentos de venta.

Para resolverlos se propone un sistema informático que centralice la información del inventario y facilite las operaciones de registro, consulta, modificación y eliminación.

#### 1.1.3. Objetivos del Sistema

**Objetivo general**

Desarrollar un sistema de gestión de inventario con Java, NetBeans y PostgreSQL que permita administrar de manera organizada los productos, los proveedores y las boletas de una tienda de accesorios de computadora.

**Objetivos específicos**

1. Implementar un módulo de autenticación que controle el acceso al sistema.
2. Registrar productos con código, nombre, precio y stock.
3. Implementar operaciones CRUD para la administración de productos.
4. Permitir la búsqueda de productos por código o por nombre.
5. Facilitar la administración de los proveedores.
6. Mantener actualizada la información del inventario.
7. Implementar la conexión entre la aplicación Java y PostgreSQL.
8. Aplicar conceptos de Programación Orientada a Objetos.
9. Aplicar estructuras de datos propias (estáticas y dinámicas) al almacenamiento y procesamiento de la información.
10. Facilitar la generación y gestión de boletas de venta.

#### 1.1.4. Alcance y Limitaciones

**Alcance**

El sistema contempla los siguientes procesos:

- Inicio de sesión de usuarios.
- Registro, consulta, actualización y eliminación de productos.
- Búsqueda de productos por nombre o por código.
- Administración de proveedores (agregar, editar, eliminar y ver datos de contacto).
- Visualización del inventario y de la ubicación de cada producto en el almacén.
- Armado de la boleta de venta con cálculo del total y opción de deshacer la última línea eliminada.
- Aviso de productos con stock bajo, en el orden en que deben reponerse.
- Conexión con una base de datos PostgreSQL.

**Limitaciones**

- El sistema está orientado a una tienda de accesorios de computadora.
- Es una aplicación de escritorio en Java; no incluye plataforma web ni aplicación móvil.
- Requiere PostgreSQL instalado y configurado, con conexión local a la base de datos `inventario`.
- Solo los productos se guardan en la base de datos. Los proveedores y las líneas de la boleta viven en memoria y se pierden al cerrar la aplicación.
- El acceso usa una credencial fija definida en el código (`LoginForm`); no hay tabla de usuarios ni roles.
- Las estructuras estáticas tienen capacidad fija: 100 productos y 50 proveedores en memoria. La matriz del almacén tiene 5 estantes de 6 posiciones (30 celdas).
- La ubicación de cada producto en el almacén se asigna automáticamente al cargar el inventario y no se guarda en la base de datos.
- La boleta se calcula y se muestra en pantalla, pero todavía no se guarda ni se exporta a PDF.

#### 1.1.5. Diagrama de Gantt

El proyecto se planificó para 18 semanas, en cinco fases: Análisis, Diseño, Desarrollo, Pruebas y Despliegue. Las semanas se cuentan desde la fecha de inicio del proyecto (*por completar por el equipo*).

| Fase | Actividad | S1 | S2 | S3 | S4 | S5 | S6 | S7 | S8 | S9 | S10 | S11 | S12 | S13 | S14 | S15 | S16 | S17 | S18 |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| Análisis | Levantamiento de requisitos y entidades | ■ | ■ | ■ | | | | | | | | | | | | | | | |
| Diseño | Diseño de la base de datos y de la interfaz | | | | ■ | ■ | ■ | | | | | | | | | | | | |
| Desarrollo | Interfaz y conexión a PostgreSQL | | | | | | | ■ | ■ | ■ | ■ | | | | | | | | |
| Desarrollo | Estructuras de datos (Unidades 1 a 4) | | | | | | | | | ■ | ■ | ■ | ■ | ■ | ■ | | | | |
| Pruebas | Casos de prueba y correcciones | | | | | | | | | | | | | | | ■ | ■ | ■ | |
| Despliegue | Entrega final y documentación | | | | | | | | | | | | | | | | | | ■ |

### 1.2. Gestión y Fundamentos de Datos (1.5 puntos)

#### 1.2.1. Identificación de Entidades

Las entidades representan los objetos del mundo real que intervienen en las operaciones de la tienda.

| Entidad | Descripción | Dónde vive hoy |
|---|---|---|
| Producto | Accesorios de computadora que vende la tienda (mouse, mousepads, grips y otros). Guarda el precio y la cantidad disponible. | Base de datos PostgreSQL |
| Proveedor | Personas o empresas que abastecen los productos. Guarda los datos de contacto. | Arreglo en memoria |
| Detalle de Boleta | Línea de una boleta: producto vendido, cantidad y precio al momento de la venta. | Lista enlazada en memoria |
| Boleta | Comprobante que se genera por una venta. | Planificada (todavía no se guarda) |
| Usuario | Persona autorizada para ingresar al sistema. | Credencial fija en `LoginForm` |
| Inventario | Conjunto de productos disponibles y su ubicación en el almacén. | Arreglo y matriz en memoria, cargados desde la base de datos |

#### 1.2.2. Diccionario de Datos

**Entidad: Producto** (tabla `productos` en PostgreSQL)

| Atributo | Tipo de dato | Descripción |
|---|---|---|
| id | INTEGER (serial, clave primaria) | Identificador único del producto. |
| nombre | VARCHAR(100), obligatorio | Nombre o descripción del producto. |
| precio | NUMERIC(10,2), obligatorio | Precio de venta. |
| stock | INTEGER, obligatorio | Cantidad disponible en el inventario. |

**Entidad: Proveedor** (clase `modelo.Proveedor`)

| Atributo | Tipo de dato | Descripción |
|---|---|---|
| id | String | Identificador único del proveedor. |
| nombre | String | Nombre o razón social. |
| telefono | String | Número telefónico de contacto. |
| correo | String | Correo electrónico. |

La entidad Proveedor almacena y administra la información de las personas o empresas que abastecen a la tienda.

**Entidad: Detalle de Boleta** (clase `modelo.DetalleBoleta`)

| Atributo | Tipo de dato | Descripción |
|---|---|---|
| codigo | String | Código del producto vendido. |
| descripcion | String | Descripción del producto. |
| cantidad | int | Unidades vendidas. |
| precio | double | Precio unitario al momento de la venta. |
| subtotal | double (calculado) | Cantidad multiplicada por el precio. |

**Entidades planificadas** (todavía sin tabla ni clase propia)

| Entidad | Atributos previstos |
|---|---|
| Usuario | idUsuario (INT), usuario (VARCHAR), contraseña (VARCHAR), rol (VARCHAR) |
| Boleta | idBoleta (INT), fecha (DATE), total (DECIMAL) |

---

## 2. FASE 2: Arquitectura y Construcción del Software (15 puntos)

### 2.1. Lógica del Software (2 puntos)

**Organización del código**

| Paquete | Contenido | Responsabilidad |
|---|---|---|
| (raíz) | `LoginForm`, `Sistema` | Interfaz gráfica: inicio de sesión y ventana principal con las pestañas Productos, Proveedores, Boleta y Almacén. |
| `Conexion` | `Conexion`, `ProductoDAO` | Acceso a PostgreSQL: conexión y operaciones sobre la tabla `productos`. |
| `modelo` | `Producto`, `Proveedor`, `DetalleBoleta` | Clases de datos del negocio. |
| `estructuras` | `ArregloEstatico`, `MatrizAlmacen`, `ListaEnlazada`, `Pila`, `Cola`, `ArbolBinarioBusqueda` | Estructuras de datos implementadas por el equipo, sin usar las colecciones de Java. |

**Flujo principal**

1. El usuario inicia sesión en `LoginForm`.
2. `Sistema` carga los productos desde PostgreSQL mediante `ProductoDAO`.
3. Los productos se copian a un arreglo en memoria, se ubican en la matriz del almacén y se indexan en un árbol de búsqueda.
4. Las tablas de la interfaz se llenan recorriendo esas estructuras.
5. Las altas, ediciones y bajas de productos se guardan en la base de datos y el inventario se vuelve a cargar.

**Base de datos.** Al inicio el sistema pensaba guardar la información en archivos de texto, lo que traía limitaciones: dificultad para buscar datos, falta de integridad, riesgo de pérdida de información y poca escalabilidad. Al usar PostgreSQL se mejoró la organización, la seguridad, la integridad y la rapidez de acceso, y se facilitan las relaciones entre entidades, difíciles de manejar con archivos planos.

```sql
CREATE TABLE productos (
    id     SERIAL PRIMARY KEY,
    nombre VARCHAR(100)  NOT NULL,
    precio NUMERIC(10,2) NOT NULL,
    stock  INTEGER       NOT NULL
);
```

La aplicación se conecta a `jdbc:postgresql://localhost:5432/inventario`. El respaldo de la base de datos está en el archivo `Inventario_pryect`.

**Roles.** El sistema tiene un único perfil de acceso, el del usuario de la tienda, validado en `LoginForm`. La separación por roles (por ejemplo administrador y cajero) queda como mejora futura junto con la tabla `usuarios`.

### 2.2. Unidad 1: Estructuras de Datos Lineales Estáticas (3 puntos)

#### Estructuras unidimensionales de objetos (1.5 puntos)

La clase `estructuras.ArregloEstatico<T>` es un arreglo unidimensional de objetos con capacidad fija que se define al crearlo. Los elementos se mantienen contiguos desde la posición 0. Se usa con dos tipos de objeto:

- `ArregloEstatico<Producto>` con capacidad 100: copia en memoria del inventario.
- `ArregloEstatico<Proveedor>` con capacidad 50: lista de proveedores de la pestaña Proveedores.

| Operación | Método | Comportamiento |
|---|---|---|
| Inserción | `insertar(item)` | Agrega al final. Devuelve `false` si el arreglo está lleno o el dato es nulo. |
| Inserción | `insertarEn(pos, item)` | Inserta en una posición desplazando los elementos a la derecha. |
| Lectura | `obtener(pos)` | Devuelve el elemento, o `null` si la posición no es válida. |
| Lectura | `buscar(criterio)` | Búsqueda lineal: posición del primer elemento que cumple el criterio, o -1. |
| Recorrido | `recorrer(accion)` | Visita todos los elementos en orden. |
| Actualización | `actualizar(pos, item)` | Reemplaza el elemento de la posición. |
| Eliminación | `eliminar(pos)` | Quita el elemento, desplaza a la izquierda y lo devuelve. |
| Copia | `copiar()` | Devuelve un arreglo nuevo, independiente del original. |
| Utilidad | `tamano()`, `capacidad()`, `estaLleno()`, `estaVacio()`, `limpiar()` | Consulta y vaciado. |

**Uso en el sistema:**

- Al cargar o refrescar, `Sistema.cargarProductos()` copia el resultado de la base de datos al arreglo de productos, y las tablas Productos y Almacén se llenan recorriéndolo.
- La búsqueda por nombre en Almacén recorre el arreglo y no distingue mayúsculas de minúsculas. Guarda sus resultados en un arreglo auxiliar.
- Agregar, editar y eliminar proveedores opera directamente sobre el arreglo de proveedores, con validación de ID repetido y aviso cuando se llena.

#### Estructuras bidimensionales, matrices (1.5 puntos)

La clase `estructuras.MatrizAlmacen` es una matriz `int[][]` que representa los estantes del almacén: cada fila es un estante, cada columna una posición dentro del estante, y cada celda guarda el id del producto ubicado ahí (0 si está libre). Tiene 5 estantes de 6 posiciones.

**Justificación en el negocio.** La pestaña Almacén debe indicar dónde se encuentra físicamente cada producto. Una matriz modela directamente la disposición de estantes y posiciones.

| Método | Función |
|---|---|
| `ubicar(id, fila, columna)` | Coloca un producto en una celda libre (un producto ocupa una sola celda). |
| `ubicarEnPrimeraLibre(id)` | Lo coloca en la primera celda libre, recorriendo estante por estante. |
| `buscar(id)` | Devuelve `{fila, columna}` o `null`. |
| `retirar(id)` | Libera la celda del producto. |
| `sincronizar(ids)` | Retira los productos que ya no existen y ubica los nuevos, conservando el lugar de los que siguen. |
| `etiqueta(id)` | Texto para la interfaz, por ejemplo `E2-P3` (estante 2, posición 3), o `Sin ubicación`. |
| `ocupadas()`, `libres()` | Conteo de celdas. |

**Uso en el sistema:** la tabla de la pestaña Almacén tiene la columna **UBICACIÓN**, que muestra la etiqueta de cada producto. Si el almacén se llena (más de 30 productos), los restantes aparecen como `Sin ubicación`.

### 2.3. Unidad 2: Estructuras de Datos Dinámicas, Listas (4 puntos)

La clase `estructuras.ListaEnlazada<T>` es una lista enlazada simple. Cada elemento vive en un `Nodo` (dato y referencia al siguiente) y la lista crece y se reduce en ejecución, sin capacidad máxima. Mantiene referencias a la cabeza y a la cola, por lo que insertar al final no recorre la lista.

| Operación | Método |
|---|---|
| Inserción | `insertarInicio`, `insertarFinal`, `insertarEn(pos, item)` |
| Lectura | `obtener(pos)`, `buscar(criterio)`, `recorrer(accion)` |
| Actualización | `actualizar(pos, item)` |
| Eliminación | `eliminar(pos)` (devuelve el elemento), `limpiar()` |
| Otras | `invertir()`, `tamano()`, `estaVacia()` |

**Uso en el sistema.** Las líneas de la boleta (`DetalleBoleta`) se guardan en una `ListaEnlazada`. Una lista es adecuada porque una boleta tiene una cantidad de líneas que no se conoce de antemano y se agregan y quitan constantemente. Agregar una línea la inserta al final; eliminar una fila la quita de la lista; y el botón Total suma los subtotales recorriendo la lista. La tabla de la pantalla se vuelve a dibujar desde la lista después de cada cambio.

### 2.4. Unidad 3: Pilas y Colas (3 puntos)

Ambas estructuras usan nodos enlazados, como la lista.

**Pila (`estructuras.Pila<T>`, LIFO).** Métodos: `apilar`, `desapilar`, `tope`, `tamano`, `estaVacia`, `limpiar`.

*Uso en el sistema: deshacer.* Cuando se elimina una línea de la boleta, se apila. Con **Ctrl+Z** sobre la tabla de la boleta se desapila la última línea eliminada y se vuelve a agregar (al final de la boleta). Se puede deshacer varias veces, en orden inverso al de eliminación.

**Cola (`estructuras.Cola<T>`, FIFO).** Métodos: `encolar`, `desencolar`, `frente`, `tamano`, `estaVacia`, `limpiar`.

*Uso en el sistema: reposición.* Al cargar el inventario, los productos con stock de 5 unidades o menos se encolan en el orden en que se detectan. Al iniciar el sistema se vacía la cola y se muestra un aviso con esos productos, en orden de llegada, para que se repongan primero los que se detectaron antes.

### 2.5. Unidad 4: Estructuras No Lineales, Árboles (3 puntos)

La clase `estructuras.ArbolBinarioBusqueda<T>` es un árbol binario de búsqueda: en cada nodo, los elementos menores están en el subárbol izquierdo y los mayores en el derecho. El orden lo define un `Comparator`, por lo que sirve para cualquier tipo de objeto.

| Operación | Método |
|---|---|
| Inserción | `insertar(item)` (rechaza duplicados) |
| Búsqueda | `buscar(clave)`, `minimo()`, `maximo()` |
| Eliminación | `eliminar(clave)` (hoja, un hijo y dos hijos, reemplazando por el sucesor) |
| Recorridos | `enOrden`, `preOrden`, `postOrden` |
| Otras | `altura()`, `tamano()`, `estaVacio()`, `limpiar()` |

**Uso en el sistema.** El árbol indexa los productos por `id`. La búsqueda por código de la pestaña Almacén localiza el producto en el árbol (`buscar`) sin recorrer todo el inventario.

**Limitación y solución.** El árbol no se autobalancea: si se insertan ids ya ordenados, se vuelve una lista y pierde su ventaja. Por eso `ProductoDAO.listar()` devuelve los productos ordenados por id (`ORDER BY id`) y `Sistema` construye el índice insertando por el elemento del medio (`insertarBalanceado`), lo que deja el árbol con altura mínima.

---

## 3. FASE 3: Validación, Conclusiones y Código Fuente (2 puntos)

### 3.1. Pruebas de Funcionamiento: Casos de Prueba (1 punto)

**Pruebas automáticas de las estructuras.** Están en `Algoritmo_proyect/test/estructuras/`: `PruebasUnidad1.java` (35 casos) y `PruebasUnidades2a4.java` (48 casos). Al ejecutarlas, todos los casos pasan (83 de 83).

| Estructura | Casos verificados |
|---|---|
| Arreglo estático | Inserción al final y en el medio; rechazo con arreglo lleno, dato nulo o posición inválida; búsqueda; recorrido; actualización; eliminación con desplazamiento; copia independiente del original; limpieza; capacidad inválida. |
| Matriz del almacén | Ubicar en celda libre; rechazo de celda ocupada, fuera de rango o producto repetido; primera celda libre; búsqueda; etiqueta legible; sincronización con el inventario; retiro; almacén lleno. |
| Lista enlazada | Inserción al inicio, en el medio y al final; búsqueda; actualización; eliminación de la cabeza, del medio y de la cola; invertir; vaciado; suma de subtotales de una boleta. |
| Pila | Orden LIFO; tope; desapilar en pila vacía; limpieza. |
| Cola | Orden FIFO; frente; desencolar en cola vacía; reutilización tras vaciarse. |
| Árbol binario de búsqueda | Inserción y duplicados; recorridos en orden, preorden y postorden; altura; mínimo y máximo; búsqueda; eliminación de hoja, de nodo con un hijo y con dos hijos; búsqueda de productos por id; degeneración con datos ordenados. |

**Cómo ejecutarlas** (desde `Algoritmo_proyect`, con JDK 17 o superior):

```bash
javac -encoding UTF-8 -d salida src/modelo/*.java src/estructuras/*.java test/estructuras/*.java
java -cp salida estructuras.PruebasUnidad1
java -cp salida estructuras.PruebasUnidades2a4
```

**Pruebas de la aplicación (manuales).** Requieren PostgreSQL activo con la base `inventario`.

| N.º | Caso | Pasos | Resultado esperado |
|---|---|---|---|
| 1 | Login correcto | Ingresar la credencial válida | Se abre la ventana principal |
| 2 | Login incorrecto | Ingresar una credencial errónea | Mensaje de error y se limpia la contraseña |
| 3 | Agregar producto | Pestaña Productos, llenar nombre, precio y stock, Agregar | El producto aparece en la tabla |
| 4 | Editar producto | Seleccionar un producto, cambiar datos, Editar | Se actualiza la fila y la base de datos |
| 5 | Eliminar producto | Seleccionar un producto, Eliminar | Desaparece de la tabla y de la base de datos |
| 6 | Buscar por código | Pestaña Almacén, ingresar un ID, Buscar | Aparece solo ese producto con su ubicación |
| 7 | Buscar por nombre | Almacén, ingresar parte del nombre en minúsculas | Aparecen los productos que lo contienen |
| 8 | Restaurar | Almacén, Restaurar | Vuelven a mostrarse todos los productos |
| 9 | Agregar proveedor | Pestaña Proveedores, llenar datos, Agregar | Aparece en la tabla; un ID repetido se rechaza |
| 10 | Editar y eliminar proveedor | Seleccionar, Editar o Eliminar | La tabla se actualiza |
| 11 | Boleta y total | Pestaña Boleta, agregar dos líneas, Total | Total igual a la suma de los subtotales |
| 12 | Deshacer línea | Eliminar una línea de la boleta y presionar Ctrl+Z sobre la tabla | La línea vuelve a aparecer |
| 13 | Aviso de reposición | Iniciar con productos de stock 5 o menos | Aparece el aviso con esos productos |

*Resultado de las pruebas manuales: pendiente de ejecución y registro por el equipo.*

### 3.2. Conclusiones y Recomendaciones (1 punto)

**Conclusiones**

- El sistema centraliza el inventario de la tienda en PostgreSQL y permite registrar, consultar, actualizar, eliminar y buscar productos desde una interfaz de escritorio.
- Se implementaron estructuras de datos propias de las cuatro unidades y cada una resuelve una necesidad concreta: el arreglo mantiene el inventario y los proveedores, la matriz ubica los productos en el almacén, la lista enlazada guarda las líneas de la boleta, la pila permite deshacer, la cola ordena la reposición y el árbol acelera la búsqueda por código.
- Las estructuras son genéricas y se verifican con 83 casos de prueba automáticos que se pueden repetir en cualquier momento.
- La revisión del código permitió corregir un error real: el botón de eliminar productos llamaba a un método sin implementar y nunca funcionaba. También se eliminaron las clases sin uso y el código duplicado.

**Recomendaciones**

- Guardar proveedores y boletas en la base de datos (tablas `proveedores`, `boletas` y `detalle_boleta`), de modo que no se pierdan al cerrar la aplicación.
- Reemplazar la credencial fija por una tabla `usuarios` con contraseñas cifradas y roles.
- Guardar la ubicación de los productos en la base de datos para que no dependa del orden de carga.
- Generar la boleta en PDF e incorporar la biblioteca correspondiente solo cuando se implemente esa función.
- Mover los datos de conexión (usuario y contraseña de la base de datos) a un archivo de configuración fuera del código.
- Si la cantidad de productos supera la capacidad de los arreglos, ampliar las capacidades o cambiar el inventario a una estructura dinámica.

### 3.3. Referencias Bibliográficas

- Oracle. (s. f.). *Java Platform, Standard Edition API Specification*. https://docs.oracle.com/en/java/javase/
- Oracle. (s. f.). *Creating a GUI with Swing* (The Java Tutorials). https://docs.oracle.com/javase/tutorial/uiswing/
- PostgreSQL Global Development Group. (s. f.). *PostgreSQL Documentation*. https://www.postgresql.org/docs/
- PostgreSQL Global Development Group. (s. f.). *PostgreSQL JDBC Driver Documentation*. https://jdbc.postgresql.org/documentation/

### 3.4. Anexos / Repositorio

**Estructura del proyecto**

```text
Algoritmos/
├── Inventario_pryect                    Respaldo de la base de datos PostgreSQL
├── Algoritmo_proyect/
│   ├── src/
│   │   ├── LoginForm.java, Sistema.java
│   │   ├── Conexion/                    Conexion, ProductoDAO
│   │   ├── modelo/                      Producto, Proveedor, DetalleBoleta
│   │   ├── estructuras/                 ArregloEstatico, MatrizAlmacen, ListaEnlazada,
│   │   │                                Pila, Cola, ArbolBinarioBusqueda, Nodo
│   │   └── imagenes/
│   ├── test/estructuras/                PruebasUnidad1, PruebasUnidades2a4
│   └── nbproject/                       Configuración de NetBeans
└── Plantilla proyecto final_...md/.pdf  Este documento
```

**Requisitos para ejecutar:** JDK, Apache NetBeans, PostgreSQL con la base `inventario` restaurada desde `Inventario_pryect`, el controlador JDBC de PostgreSQL agregado a las bibliotecas del proyecto y la biblioteca *Absolute Layout* de NetBeans.

**Repositorio:** *(enlace por completar por el equipo)*
