package info.sinamon.mapsort.services;

import info.sinamon.mapsort.data.PlaceData;
import info.sinamon.mapsort.data.PlaceDistance;
import info.sinamon.mapsort.store.MapInfo;

import java.util.ArrayList;
import java.util.List;

public class Distance {
    public List<PlaceDistance> getDistanceByMySelf(PlaceData myPlace) {
        // 거리 데이터를 저장할 List를 만듭니다.
        ArrayList result = new ArrayList();

        var it = MapInfo.getMapInfo().entrySet();

        it.forEach(entry -> {
            var key = entry.getKey();
            var value = entry.getValue();
            double distanceAroundByMe = value.getDistance(myPlace);

            result.add(new PlaceDistance(value, distanceAroundByMe));
        });

        return result;
    }
}
