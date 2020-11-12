package info.sinamon.mapsort.data;

import info.sinamon.mapsort.enums.Category;

public class PlaceDistance extends PlaceData implements Comparable<PlaceDistance> {
    double distance;

    public PlaceDistance(PlaceData data, double distance) {
        super(data.getName(), data.getLatitude(), data.getLongitude(), data.getCategory());
        this.distance = distance;
    }

    public PlaceDistance(String name, double latitude, double longitude, Category category, double distance) {
        super(name, latitude, longitude, category);
        this.distance = distance;
    }

    public double getDistanceToOther() {
        return distance;
    }

    public void setDistanceToOther(double distance) {
        this.distance = distance;
    }

    @Override
    public int compareTo(PlaceDistance o) {
        final double distanceDelta = getDistanceToOther() - o.getDistanceToOther();
        return distanceDelta < 0 ? -1 : 1;
    }

    @Override
    public String toString() {
        return "PlaceDistance{" +
                "distance=" + distance +
                '}';
    }
}
