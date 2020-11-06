package info.sinamon.mapsort.enums;

public enum Category {
    직업("직업"),
    의료("의료"),
    생산품판매("생산품판매");

    private String job;

    Category(String job) {
        this.job = job;
    }

    public String getJob() {
        return this.job;
    }
}
