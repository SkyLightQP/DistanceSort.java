package info.sinamon.mapsort.services;

import info.sinamon.mapsort.Pair;
import info.sinamon.mapsort.data.PlaceData;
import info.sinamon.mapsort.store.MapInfo;

import java.util.ArrayList;

public class Distance {
    public ArrayList<Pair<String, Double>> getDistanceByMySelf(PlaceData myPlace) {
        ArrayList<Pair<String, Double>> result = new ArrayList();

        var it = MapInfo.getMapInfo().entrySet();

        it.forEach(entry -> {
            var key = entry.getKey();
            var value = entry.getValue();
            double distanceAroundByMe = value.getDistance(myPlace);

            result.add(new Pair<String, Double>(key, distanceAroundByMe));
        });

        return result;
    }
}
