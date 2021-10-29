package org.turkey.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseMessage {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("error")
    @Expose
    private String error;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseMessage() {
    }

    /**
     *
     * @param error
     * @param status
     */
    public ResponseMessage(String status, String error) {
        super();
        this.status = status;
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "status='" + status + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}