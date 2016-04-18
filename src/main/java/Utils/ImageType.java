package Utils;


public enum ImageType {

    POINT(""),
    BATHROOM(""),
    STAIRS(""),
    ELEVATOR(""),
    WAITINGROOM(""),
    FLOOR("");

    private String resourceFileName;

    ImageType(String resourceFileName) {

        this.resourceFileName = resourceFileName;

    }

    public String getResourceFileName() {

        return resourceFileName;
    }
}
