# Taller 7 - Implementacion Algoritmos de Busqueda

---

Implementacion y validacion comparativa de variantes de busqueda (Secuencial, Binaria, Centinela) sobre Arreglos y
Listas Enlazadas (SLL), analizando el manejo de precondiciones, casos borde y optimizaciones.

**Autores:**
* **Jostin Vasquez**
* **Miguel Veintimilla**
* **Darwin Correa**
* **Wilson Palma**

---
## Ejecucion del proyecto:

1. Clonar el repositorio.
2. Abrir el proyecto en un IDE (IntelliJ IDEA / VS Code).
3. **Verificar dependencias:** El proyecto utiliza librerias nativas (`java.util.function`), no requiere imports externos.
4. **Ejecutar:**
    * **Ubicar la clase principal:** `src/ed/u2/sorting/SearchDemo.java`.
    * Ejecutar el metodo `main`.
    * La consola mostrara tablas de evidencia con validacion de colores (Verde: Exito, Rojo: Fallo/Advertencia).

---

## Funcionalidades implementadas

### Busqueda.java
- `indexOfFirst(int[] a, int key)` — retorna índice de la primera ocurrencia o `-1`.
- `indexOfLast(int[] a, int key)` — retorna índice de la última ocurrencia (optimizado hacia atrás).
- `findAll(int[] a, IntPredicate p)` — retorna lista de índices que cumplen la condición.
- `searchSequentialSentinel(int[] a, int key)` — búsqueda secuencial optimizada con centinela.
- `binaria(int[] a, int key)` — búsqueda binaria iterativa con validación de precondición.
- `sort(int[] a)` — utilidad para ordenar automáticamente arreglos desordenados.
- `isSorted(int[] a)` — validación de ordenamiento O(n).
- `findFirst(Nodo head, int key)` y `findLast(Nodo head, int key)` — variantes para SLL.

### Lista.java y Nodo.java
- `Nodo`: estructura básica con campo `valor` y referencia `sig`.
- `Lista`: implementación minimalista de SLL.
    - `pushBack(int valor)`: inserción al final para armar los casos de prueba.
    - `getHead()`: expone el nodo inicial para permitir la búsqueda externa.

### SearchDemo.java
- Orquestador de pruebas que ejecuta los algoritmos sobre los Datasets (A-K) definidos en `SortingUtils`.
- Implementa lógica de recuperación: si la búsqueda binaria falla por desorden, ordena y reintenta.
- Genera la tabla de evidencias en consola.

---

## Interpretación y conclusiones
1. **Eficiencia según la estructura de datos:** la búsqueda binaria presenta un número de comparaciones significativamente menor que la búsqueda secuencial cuando se aplica sobre arreglos ordenados (complejidad O(log n)). Sin embargo, su aplicabilidad depende de la precondición de ordenación del arreglo.
2. **Secuencial clásico vs centinela:** la variante con centinela simplifica el bucle de búsqueda y reduce, en muchos casos, el número de comparaciones al eliminar la comprobación de límites en cada iteración. Los resultados empíricos en la tabla de evidencias permiten observar dicha diferencia de forma cuantitativa.
3. **Listas enlazadas:** la búsqueda binaria no es adecuada para listas simplemente enlazadas porque el acceso a una posición intermedia no se realiza en tiempo constante; por tanto, la opción práctica es la búsqueda secuencial.
4. **Duplicados:** cuando existen valores duplicados, las funciones `lowerBound` y `upperBound` permiten localizar con precisión los extremos del rango de ocurrencias usando variantes basadas en búsqueda binaria.

---
## Resultados

### 1. Pruebas Estandar (Secuencial vs Centinela)
| Dataset (Tipo) | Contenido Resumido | Key | Metodo | Resultado |
| :--- | :--- | :--- | :--- | :--- |
| **Set A** (Random) | [8, 3, 6, 3, 9] | 3 | Secuencial | **1** (Primer indice) |
| **Set A** (Random) | [8, 3, 6, 3, 9] | 3 | Centinela | **1** (Idem) |
| **Set B** (Inverso) | [5, 4, 3, 2, 1] | 4 | Secuencial | **1** |
| **Set E** (Random) | [9, 1, 8, 2] | 9 | Secuencial | **0** (Inicio) |

### 2. Pruebas Binaria (Validacion de Orden)
| Dataset (Tipo) | Contenido | Key | Metodo | Resultado |
| :--- | :--- | :--- | :--- | :--- |
| **Set C** (Ordenado)| [1, 2, 3, 4, 5] | 4 | Binaria | **3** |
| **Set J** (Casi Ord)| [1, 3, 4, 10, 6] | 10 | Binaria | **NO ORDENADO** |
| **Set J** [Sort] | [1, 3, 4, 6, 10] | 10 | Binaria+Sort | **4** (Recuperado) |

### 3. Casos Borde y Listas
| Dataset / Estructura | Caso | Key | Metodo | Resultado |
| :--- | :--- | :--- | :--- | :--- |
| **Set F** (Vacio) | `[]` | 5 | Secuencial | **-1** (No encontrado) |
| **Set G** (Unitario) | `[2]` | 2 | Secuencial | **0** |
| **Set D** (Duplicados)| `[2, 2, 2, 2]` | 2 | First Occur | **0** |
| **Set D** (Duplicados)| `[2, 2, 2, 2]` | 2 | Last Occur | **3** |
| **SLL** | `3->1->3->2` | 3 | findFirst | **@Nodo(val=3)** |
| **SLL** | `3->1->3->2` | 3 | findLast | **@Nodo(val=3)** (Posicion 2) |

### Tabla de Evidencias (Búsquedas en Arrays)

| Dataset | Array | Key | Método | Índice (Res) | Comparaciones |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **A** | `[8, 3, 6, 3, 9]` | 7 | Clásico | -1 | 10 |
| | | | Centinela | -1 | 5 |
| **A** | `[8, 3, 6, 3, 9]` | 5 | Clásico | -1 | 10 |
| | | | Centinela | -1 | 5 |
| **B** | `[5, 4, 3, 2, 1]` | 5 | Clásico | 0 | 2 |
| | | | Centinela | 0 | 1 |
| **C** | `[1, 2, 3, 4, 5]` | 4 | Binaria | 3 | 5 |
| **D** | `[2, 2, 2, 2]` | 2 | Clásico | 0 | 2 |
| | | | Centinela | 0 | 1 |