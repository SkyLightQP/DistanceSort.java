package info.sinamon.mapsort.data;

import info.sinamon.mapsort.enums.Category;

public class PlaceData implements Comparable<PlaceData> {
    private double latitude;
    private double longitude;
    private Category category;

    public PlaceData(double latitude, double longitude, Category category) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.category = category;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getDistance(final PlaceData other) {
        final double dx = getLatitude() - other.getLatitude();
        final double dy = getLongitude() - other.getLongitude();

        return Math.sqrt(dx*dx + dy*dy);
    }

    @Override
    public int compareTo(PlaceData o) {
        final double distanceDelta = getDistance(this) - getDistance(o);
        return distanceDelta < 0 ? -1 : 1;
    }

    @Override
    public String toString() {
        return "PlaceData{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", category=" + category +
                '}';
    }
}
