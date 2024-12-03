package net.gilmor.plate.model;

public class JsonResponse<T> {
    private boolean success;
    private String message;
    private T content;

    public JsonResponse(boolean success, String message, T content) {
        this.success = success;
        this.content = content;
        this.message = message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }
}