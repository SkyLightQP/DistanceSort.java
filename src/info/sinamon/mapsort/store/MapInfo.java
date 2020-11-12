package info.sinamon.mapsort.store;

import info.sinamon.mapsort.data.PlaceData;

import java.util.HashMap;

public class MapInfo {
    /*
        장소들을 등록하고 관리하기 위한 클래스입니다.
        Key-Value 형태의 Map 자료구조를 사용합니다.
     */

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
