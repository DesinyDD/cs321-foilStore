package org.turkey.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseMessage {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("error")
    @Expose
    private Error error;

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
    public ResponseMessage(String status, Error error) {
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

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return getStatus().equals("success");
    }
    @Override
    public String toString() {
        return "ResponseMessage{" +
                "status='" + status + '\'' +
                ", error=" + error +
                '}';
    }
}