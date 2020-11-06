package info.sinamon.mapsort;

import info.sinamon.mapsort.data.PlaceData;
import info.sinamon.mapsort.enums.Category;
import info.sinamon.mapsort.services.Distance;
import info.sinamon.mapsort.services.SortPlace;
import info.sinamon.mapsort.store.MapInfo;

import java.util.ArrayList;

public class MapSort {
    public static void main(String[] args) {
        MapInfo.putMap("테스트1", new PlaceData(10.10, 10.10, Category.직업));
        MapInfo.putMap("테스트2", new PlaceData(11.10, 11.10, Category.직업));
        MapInfo.putMap("테스트3", new PlaceData(12.10, 12.10, Category.직업));
        MapInfo.putMap("테스트4", new PlaceData(13.10, 13.10, Category.직업));
        MapInfo.putMap("테스트5", new PlaceData(14.10, 14.10, Category.직업));
        MapInfo.putMap("테스트6", new PlaceData(15.10, 15.10, Category.직업));
        MapInfo.putMap("테스트7", new PlaceData(16.10, 16.10, Category.직업));
        MapInfo.putMap("테스트8", new PlaceData(17.10, 17.10, Category.직업));

        Distance sp = new Distance();
        ArrayList<Pair<String, Double>> list = sp.getDistanceByMySelf(new PlaceData(212.3, 123.1, Category.직업));
        ArrayList sortedList = new SortPlace<Double>().sort(list);

        sortedList.forEach(o -> );
    }
}
