package info.sinamon.mapsort.data;

import info.sinamon.mapsort.enums.Category;

public class PlaceData {
    private String name;
    private double latitude;
    private double longitude;
    private Category category;

    public PlaceData(String name, double latitude, double longitude, Category category) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public String toString() {
        return "PlaceData{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", category=" + category +
                '}';
    }
}
