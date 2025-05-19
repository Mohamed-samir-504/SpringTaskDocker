package org.example.springtaskdocker.Common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private LocalDateTime timestamp;
    private String message;
    private int status;
    private T data;  //Can be null

    public ApiResponse(String message, int status, T data) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public ApiResponse(String message, int status) {
        this(message, status, null);
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setData(T data) {
        this.data = data;
    }
}