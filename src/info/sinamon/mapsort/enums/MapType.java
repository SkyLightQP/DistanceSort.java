package info.sinamon.mapsort.enums;

public enum MapType {
    MY(-2),
    NEARBY(-1);

    private int value;

    MapType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
