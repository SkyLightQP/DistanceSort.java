package info.sinamon.mapsort.data;

import info.sinamon.mapsort.enums.Category;

public class UserInput {
    public double lat;
    public double longg;
    public Category category;

    public UserInput(double lat, double longg, Category category) {
        this.lat = lat;
        this.longg = longg;
        this.category = category;
    }
}
