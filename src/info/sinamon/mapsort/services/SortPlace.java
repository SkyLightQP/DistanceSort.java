package info.sinamon.mapsort.services;

import info.sinamon.mapsort.data.PlaceDistance;

import java.util.List;

public class SortPlace {
    public List<PlaceDistance> sort(List<PlaceDistance> arr) {
        int N = arr.size();

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N - 1 - i; j++) {
                if (arr.get(j).compareTo(arr.get(j + 1)) > 0) {
                    swap(arr, j, j + 1);
                }
            }
        }

        return arr;
    }

    private void swap(List<PlaceDistance> list, int i, int j) {
        final List<PlaceDistance> l = list;
        l.set(i, l.set(j, l.get(i)));
    }
}
