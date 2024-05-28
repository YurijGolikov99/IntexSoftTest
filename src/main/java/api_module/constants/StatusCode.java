package api_module.constants;

public enum StatusCode {
    OK(200, "OK"),
    BAD_REQUEST(400, "Bad request "),
    NOT_FOUND(404,"Not found");

    private final int statusCode;
    private final String message;

    StatusCode(int code, String message) {
        this.statusCode = code;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage(){
        return message;
    }
}
