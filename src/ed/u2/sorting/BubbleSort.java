package ed.u2.sorting;

import java.util.Arrays;

// Ordenamiento burbuja: compara entre dos elementos de un array
public final class BubbleSort {
    public static void sort(int[] arreglo) {
        sort(arreglo, false);
    }

    public static SortContadores sort(int[] arreglo, boolean trace) {
        long comparaciones = 0;
        long swaps = 0;

        long tiempoInicio = System.nanoTime();
        int nro = arreglo.length;
        // si el array esta vacio o tiene un elemento, no ordena nada
        if (nro < 2) {
            // muestra el arreglo vacio o de un elemento
            return new SortContadores(System.nanoTime() - tiempoInicio, 0, 0);
        }
        //bucle externo, contrala el nro de pasadas, se ejecuta n-1 veces
        for (int i = 0; i < arreglo.length; i++) {
            // reastrear si se hizo algun cambio: en esta pasada, se reinicia al inicio de cada pasada
            boolean swapped = false;

            // bucle interno: hace comparaciones e intercambios, mueve el elemento mas grande hasta el final del array
            for (int j = 0; j < nro - i - 1; j++) {
                comparaciones++;
                // compara elementos juntos
                if (arreglo[j] > arreglo[j + 1]) {
                    // si estan mal arreglados, se intercambian
                    comparaciones++;
                    int temp = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = temp;
                    swaps++;
                    swapped = true; // marca si hubo un intercambio
                    if (trace) {
                        System.out.println(SortingUtils.C_ROJO + "\t> SWAP: " + SortingUtils.C_RESET + arreglo[j + 1] + " <-> " + arreglo[j] +
                                " | " + SortingUtils.C_CELESTE + Arrays.toString(arreglo) + SortingUtils.C_RESET +
                                " | " + SortingUtils.C_VERDE + " Contador de comparaciones: " + SortingUtils.C_CELESTE + comparaciones +
                                SortingUtils.C_RESET);
                    }
                }
            }
            //si swapped es falso, no hubo intercambios, el array ya esta ordenado
            if (!swapped) {
                if (trace) {
                    System.out.println(SortingUtils.C_VERDE +
                            "Corte Temprano: no hubo intercambios. Array ordenado." +
                            SortingUtils.C_RESET);
                }
                break; // rompe bucle externo
            }
        }
        long tiempoFinal = System.nanoTime();
        if (trace) {
            // muestra el arreglo ordenado
            System.out.print(SortingUtils.mostrarArregloFinal(arreglo));
        }
        return new SortContadores(tiempoFinal-tiempoInicio, comparaciones, swaps);
    }
}
