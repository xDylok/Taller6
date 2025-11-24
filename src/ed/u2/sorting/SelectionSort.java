package ed.u2.sorting;

import java.util.Arrays;

/*
 * Algoritmo de ordenacion por seleccion
 */
public final class SelectionSort {
    // Firma estandar
    public static void sort(int[] arreglo) {
        sort(arreglo, false);
    }
    // metodo de ordenamiento sobrecargado, permite imprimir trazas
    public static SortContadores sort(int[] arreglo, boolean trace) {
        long comparaciones = 0;
        long swaps = 0;
        long tiempoInicio = System.nanoTime();

        // bucle externo: recorre el array para colocar el elemento en la posicion << i >>
        for (int i = 0; i < arreglo.length - 1; i++) {
            int min = i; // almacena el indice del elemento mas pequeño encontrado, asumiendo q << i >> es el minimo para empezar
            // bucle interno: busca en el resto del array desde << i+1 >> hasat el final
            for (int j = i + 1; j < arreglo.length; j++) {
                comparaciones++;
                // if -> si se encuentra un elemento mas pequeño que  min, actualiza min para que apunte al nuevo indice
                if (arreglo[min] > arreglo[j]) {
                    min = j;
                }
            }
            // solo se realiza si el intercambio del indice del minmo es diferente a la posicion actual << i >>,
            // si son iguales, no se cambia pq esta en su lugar
            if (min != i) {
                int temp = arreglo[i];
                arreglo[i] = arreglo[min];
                arreglo[min] = temp;
                swaps++;
            }
            // imprime el estado del array despues de cada pasada del bucle externo
            if (trace) {
                System.out.println(SortingUtils.C_AZUL +
                        "\tIteracion: |" + SortingUtils.C_ROJO + i + SortingUtils.C_AZUL +
                        "|: Min encontrado: |" + SortingUtils.C_ROJO + arreglo[i] + SortingUtils.C_AZUL +
                        "| Arreglo actualizado: " + SortingUtils.C_ROJO + Arrays.toString(arreglo) +
                        SortingUtils.C_RESET);
            }
        }
        long tiempoFinal = System.nanoTime();
        // imprime el numero total de swaps realizados
        if (trace)
            System.out.println(SortingUtils.C_VERDE + "Total de swaps: " + swaps + SortingUtils.C_RESET);
        // imprime el arreglo final
        System.out.print(SortingUtils.mostrarArregloFinal(arreglo));;

        return new SortContadores(tiempoFinal-tiempoInicio, comparaciones, swaps);
    }

    // SIRVE PARA CALCULAR CONSTANTE DE KAPREKAR: 19/11/25
    public static SortContadores sortInvert(int[] arreglo) {
        long comparaciones = 0;
        long swaps = 0;

        long tiempoInicio = System.nanoTime();
        // bucle externo: recorre el array para colocar el elemento en la posicion << i >>
        for (int i = 0; i < arreglo.length - 1; i++) {
            int min = i; // almacena el indice del elemento mas pequeño encontrado, asumiendo q << i >> es el minimo para empezar
            // bucle interno: busca en el resto del array desde << i+1 >> hasat el final
            for (int j = i + 1; j < arreglo.length; j++) {
                comparaciones++;
                // if -> ordena de mayor a menor
                if (arreglo[min] < arreglo[j]) {
                    min = j;
                }
            }
            // solo se realiza si el intercambio del indice del minmo es diferente a la posicion actual << i >>,
            // si son iguales, no se cambia pq esta en su lugar
            if (min != i) {
                int temp = arreglo[i];

                arreglo[i] = arreglo[min];
                arreglo[min] = temp;
                swaps++;
            }
        }
        long tiempoFinal = System.nanoTime();
        return new SortContadores(tiempoFinal-tiempoInicio, comparaciones, swaps);
    }
}

