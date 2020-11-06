package info.sinamon.mapsort.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortPlace<T extends Number & Comparable<? super T>> {
    public ArrayList<T> sort(ArrayList<T> arr) {
        int N = arr.size();

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N - 1 - i; j++) {
                if(arr.get(j).compareTo(arr.get(j + 1)) > 0) {
                    swap(arr, j, j + 1);
                }
            }
        }

        return arr;
    }

    private void swap(List<T> list, int i, int j) {
        final List l = list;
        l.set(i, l.set(j, l.get(i)));
    }
}
