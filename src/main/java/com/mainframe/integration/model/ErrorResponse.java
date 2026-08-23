package com.mainframe.integration.model;
import java.util.List;

public class ErrorResponse {
    private int status;
    private String error;
    private String message;
    private List<String> errors;

    public ErrorResponse(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public ErrorResponse(int status, String error, String message, List<String> errors) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.errors = errors;
    }
    public int getStatus() {
        return status;
    }
    public String getError() {
        return error;
    }
    public String getMessage() {
        return message;
    }
    public List<String> getErrors(){ return errors;}
}
