package ed.u2.sorting;

import java.util.Arrays;

public final class InsertionSort {
    // Firma estandar
    public static void sort(int[] arreglo) {
        // llama al metodo principal, habilitando o deshabilitando las trazas
        sort(arreglo, false);
    }
    // Metodo de ordenamiento sobrecargado, permite imprimir trazas
    public static SortContadores sort(int[] arreglo, boolean trace) {
        long comparaciones = 0;
        long swaps = 0;

        long tiempoInicio = System.nanoTime();
        // recorre el array desde el segundo elemento
        for (int i = 1; i < arreglo.length; i++) {
            int aux = arreglo[i]; // aux = se inserta la sublista ordenada
            int pos = i - 1; // apunta al ultimo elemento de la sublista ordenada (izquierda de << i >>)
            /*
            * se ejecuta mientras:
            * 1. no se salga del arreglo por la izquierda pos >= 0
            * 2. el elemento de la lista ordenada (sublista izquierda) sea mayor que el elemento a insertar (aux)
            */
            while (pos >= 0 && arreglo[pos] > aux) {
                comparaciones++;

                if (arreglo[pos] > aux) {
                    arreglo[pos + 1] = arreglo[pos];
                    pos--;
                    swaps++; // Contamos el desplazamiento
                } else {
                    // La comparación se hizo, pero dio falso. Salimos.
                    break;
                }
            }
            // cuando while termina, se asigna la posicion correcta, insertando el elemento aux
            arreglo[pos + 1] = aux;
        }
        long tiempoFinal = System.nanoTime();

        if (trace) {
            // muestra el arreglo ordenado
            System.out.print(SortingUtils.mostrarArregloFinal(arreglo));
        }
        return new SortContadores(tiempoFinal-tiempoInicio, comparaciones, swaps);
    }
}