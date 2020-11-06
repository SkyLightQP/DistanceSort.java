package info.sinamon.mapsort.store;

import info.sinamon.mapsort.data.PlaceData;

import java.util.HashMap;

public class MapInfo {
    private static final HashMap<String, PlaceData> mapInfo = new HashMap();

    public static PlaceData getMap(String key) {
        return mapInfo.get(key);
    }

    public static void putMap(String key, PlaceData value) {
        mapInfo.put(key, value);
    }

    public static HashMap<String, PlaceData> getMapInfo() {
        return mapInfo;
    }
}
