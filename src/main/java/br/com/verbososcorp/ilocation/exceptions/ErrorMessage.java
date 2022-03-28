package br.com.verbososcorp.ilocation.exceptions;

import java.util.Date;

public class ErrorMessage {
    private Date currentDate;
    private String message;
    private int status;
    private String description;

    public ErrorMessage( int status, Date currentDate, String message, String description) {
        this.status = status;
        this.currentDate = currentDate;
        this.message = message;
        this.description = description;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
