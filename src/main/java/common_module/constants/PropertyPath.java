package common_module.constants;

public enum PropertyPath {
    BASE_URL("base.url"),
    ALL_BORDERS_PATH("json.path.getting.all.borders"),
    BORDERS_COLLECTION_PATH("json.path.getting.collections.of.borders"),
    CODES_PARAMTER_NAME("codes.parameter.name");

    private final String path;

    PropertyPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
